package com.mike.novel.tools.txt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
 * ����һ��С˵��txt
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

	public void generateOne(int nid) throws IOException {
		// ����nid��ȡ�������½�
		NovelStatusVo queryNovelInfo = novelCombService.queryNovelInfo(nid);
		List<NovelVolumDo> volums = queryNovelInfo.getVolums();
		Assert.notNull(volums, "��Ϊ��");
		List<NovelPageDo> allPages = new ArrayList<NovelPageDo>();
		// ��ʱmap���洢�½�����. key Ϊcid�� valueΪname
		Map<Long, String> names = new HashMap<Long, String>();
		for (NovelVolumDo one : volums) {
			List<NovelChapterDo> chapters = one.getChapters();
			if (chapters != null) {

				List<Long> cids = new ArrayList<Long>();
				for (NovelChapterDo oneChapter : chapters) {
					cids.add(oneChapter.getCid());
					names.put(oneChapter.getCid(), oneChapter.getCname());
				}
				// ��ѯcontent
				List<NovelPageDo> pages = novelPageService.findByCids(cids);
				allPages.addAll(pages);

			}
		}
		NovelBasicDo novelBasicDo = queryNovelInfo.getNovelBasicDo();
		saveToFile(allPages, novelBasicDo, names);

		// �޸�db��Ӧ��״̬
		novelBasicDo.setGenerateTxt(true);
		novelBasicDo.setGenerateTxtNum(queryNovelInfo.getTotalNum());
		novelBasicService.updateTxtStatus(novelBasicDo);
	}

	private void saveToFile(List<NovelPageDo> allPages,
			NovelBasicDo novelBasicDo, Map<Long, String> names)
			throws IOException {
		// ��װ�ı�
		BufferedWriter output = new BufferedWriter(new FileWriter(
				getNovelFile(novelBasicDo)));

		// д�����
		StringBuffer sb = new StringBuffer(novelBasicDo.getTitle());
		sb.append("\n");
		output.write(sb.toString());

		for (int i = 0; i < allPages.size(); i++) {
			NovelPageDo novelPageDo = allPages.get(i);
			output.write("\n" + names.get(novelPageDo.getCid()) + "\n");
			output.write(novelPageDo.getFormatTxtContent());
			if (i >= AddTxtFlavors.INTERVAL_NUM
					&& i % AddTxtFlavors.INTERVAL_NUM == 0) {
				output.write("\n" + AddTxtFlavors.ADDS_INFO + "\n");
			}
		}
		output.close();
	}

	private String getNovelFile(NovelBasicDo novelBasicDo) {
		String path = configConstants.getProjectBaseHome() + "txt/";
		// С˵�ļ���= С˵����+nid
		return path + novelBasicDo.getTitle() + novelBasicDo.getNid() + ".txt";
	}
}
