package com.mike.novel.tools.txt;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.mike.novel.content.service.NovelBasicService;
import com.mike.novel.content.service.NovelCombServcie;
import com.mike.novel.content.service.NovelPageService;
import com.mike.novel.dto.NovelBasicDo;
import com.mike.novel.dto.NovelChapterDo;
import com.mike.novel.dto.NovelPageDo;
import com.mike.novel.dto.NovelVolumDo;
import com.mike.novel.dto.vo.NovelStatusVo;
import com.mike.novel.util.ConfigConstants;

/**
 * 生成一本小说的txt
 */
public class GenerateOneTxt {
	@Resource
	private NovelCombServcie novelCombService;
	@Resource
	private NovelPageService novelPageService;

	@Resource
	private ConfigConstants configConstants;
	@Resource
	private NovelBasicService novelBasicService;

	private static final int MAX_ID_NUM = 200;

	private static Logger log = LoggerFactory.getLogger(GenerateOneTxt.class);

	public void generateOne(int nid) throws IOException {
		// 根据nid，取所所有章节
		NovelStatusVo queryNovelInfo = novelCombService.queryNovelInfo(nid);
		List<NovelVolumDo> volums = queryNovelInfo.getVolums();
		Assert.notNull(volums, "卷为空");
		// List<NovelPageDo> allPages = new ArrayList<NovelPageDo>();
		// 临时map，存储章节名字. key 为cid， value为name
		Map<Long, String> names = new HashMap<Long, String>();
		NovelBasicDo novelBasicDo = queryNovelInfo.getNovelBasicDo();

		FileOutputStream fos = new FileOutputStream(getNovelFile(novelBasicDo));
		OutputStreamWriter output = new OutputStreamWriter(fos, "UTF-8");

		// 写入标题
		StringBuffer sb = new StringBuffer(novelBasicDo.getTitle());
		sb.append("\n");
		output.write(sb.toString());

		int totalCount = 0;
		for (NovelVolumDo one : volums) {
			List<NovelChapterDo> chapters = one.getChapters();
			if (chapters != null) {

				List<Long> cids = new ArrayList<Long>();
				for (NovelChapterDo oneChapter : chapters) {
					cids.add(oneChapter.getCid());
					names.put(oneChapter.getCid(), oneChapter.getCname());
				}
				// 为了节约内存的空间，分组查询
				List<List<Long>> splitIds = splitCids(cids);

				// 查询content
				for (List<Long> oneIds : splitIds) {
					List<NovelPageDo> pages = novelPageService.findByCids(oneIds);
					for (int i = 0; i < pages.size(); i++) {
						NovelPageDo novelPageDo = pages.get(i);
						output.write("\n" + names.get(novelPageDo.getCid()) + "\n");
						output.write(novelPageDo.getFormatTxtContent());
						totalCount++;
						if (totalCount >= AddTxtFlavors.INTERVAL_NUM && totalCount % AddTxtFlavors.INTERVAL_NUM == 0) {
							output.write("\n" + AddTxtFlavors.ADDS_INFO + "\n");
						}
					}
				}

			}
		}

		output.flush();
		output.close();

		// saveToUTF8(allPages, novelBasicDo, names);

		// 修改db对应的状态
		novelBasicDo.setGenerateTxt(true);
		novelBasicDo.setGenerateTxtNum(queryNovelInfo.getTotalNum());
		novelBasicService.updateTxtStatus(novelBasicDo);
		log.info("finished " + getNovelFile(novelBasicDo));
	}

	// private void saveToFile(List<NovelPageDo> allPages, NovelBasicDo
	// novelBasicDo, Map<Long, String> names)
	// throws IOException {
	// // 组装文本
	// BufferedWriter output = new BufferedWriter(new
	// FileWriter(getNovelFile(novelBasicDo)));
	//
	// // 写入标题
	// StringBuffer sb = new StringBuffer(novelBasicDo.getTitle());
	// sb.append("\n");
	// output.write(sb.toString());
	//
	// for (int i = 0; i < allPages.size(); i++) {
	// NovelPageDo novelPageDo = allPages.get(i);
	// output.write("\n" + names.get(novelPageDo.getCid()) + "\n");
	// output.write(novelPageDo.getFormatTxtContent());
	// if (i >= AddTxtFlavors.INTERVAL_NUM && i % AddTxtFlavors.INTERVAL_NUM ==
	// 0) {
	// output.write("\n" + AddTxtFlavors.ADDS_INFO + "\n");
	// }
	// }
	// output.close();
	// }

	public static List<List<Long>> splitCids(List<Long> cids) {
		List<List<Long>> result = new ArrayList<List<Long>>();
		if (cids.size() <= MAX_ID_NUM) {
			result.add(cids);
			return result;
		}

		for (int i = 0; i < cids.size(); i = i + MAX_ID_NUM) {
			List<Long> one = new ArrayList<Long>();
			int endPosition = cids.size() > (i + MAX_ID_NUM) ? (i + MAX_ID_NUM) : cids.size();
			for (int j = i; j < endPosition; j++) {
				one.add(cids.get(j));
			}
			result.add(one);
		}

		return result;
	}

	private String getNovelFile(NovelBasicDo novelBasicDo) {
		String path = configConstants.getProjectBaseHome() + "txt/";
		// 小说文件名= 小说名字+nid
		return path + novelBasicDo.getTitle() + novelBasicDo.getNid() + ".txt";
	}
}
