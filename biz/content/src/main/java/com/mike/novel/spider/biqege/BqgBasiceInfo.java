package com.mike.novel.spider.biqege;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;
import org.webharvest.runtime.variables.Variable;

import com.mike.novel.content.service.NovelBasicService;
import com.mike.novel.dto.NovelBasicDo;
import com.mike.novel.dto.NovelVolumDo;
import com.mike.novel.dto.vo.NovelStatusVo;
import com.mike.novel.spider.BasicInfoAccess;
import com.mike.novel.util.BqgConstants;
import com.mike.novel.util.ConfigConstants;
import com.mike.novel.util.NovelType;

/**
 * biquge������Ϣ����
 * 
 * @author ���� 2013��12��18������6:56:46
 */
public class BqgBasiceInfo implements BasicInfoAccess {

	@Resource
	private ConfigConstants configConstants;

	@Resource
	private NovelBasicService novelBasicService;

	public NovelStatusVo executeIndexPage(String indexPage) {
		ScraperConfiguration indexConfig;
		try {
			indexConfig = new ScraperConfiguration(
					configConstants.getProjectBaseHome()
							+ BqgConstants.HARVEST_INDEX_CONFIG);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("��ҳharvest�����ļ�·��û�ҵ�", e);
		}
		Scraper indexScraper = new Scraper(indexConfig,
				configConstants.getProjectBaseHome()
						+ BqgConstants.HARVEST_WORKING_DIR);

		String pictureSavePath = getAPictureUrl();
		NovelStatusVo result = new NovelStatusVo();

		// ����spider�ı���
		indexScraper.getContext().setVar("IndexPage", indexPage);
		indexScraper.getContext().setVar("pictureSavePath", pictureSavePath);
		indexScraper.execute();

		NovelBasicDo novelBasicDo = curlBasicInfo(indexScraper, pictureSavePath);
		result.setNovelBasicDo(novelBasicDo);
		Integer id = novelBasicService.insert(novelBasicDo);

		Variable allinfo = (Variable) indexScraper.getContext().get("allinfo");
		List<NovelVolumDo> volums = BggIndexParseHelper.parse(
				allinfo.toString(), novelBasicDo.getTitle().length());
		result.setVolums(volums);
		// TODO: ��volum��task�浽db��

		return result;
	}

	private NovelBasicDo curlBasicInfo(Scraper indexScraper,
			String pictureSavePath) {
		NovelBasicDo novelBasicDo = new NovelBasicDo();
		// ����
		Variable title = (Variable) indexScraper.getContext().get("title");
		novelBasicDo.setTitle(title.toString());
		// ����
		Variable author = (Variable) indexScraper.getContext().get("author");
		novelBasicDo.setAuthor(author.toString().split("��")[1]);
		// ������ʱ��
		Variable lastUpdateTime = (Variable) indexScraper.getContext().get(
				"lastUpdateTime");
		novelBasicDo.setLastUpdateTime(Date.valueOf(lastUpdateTime.toString()
				.split("��")[1]));
		// ���
		Variable introduce = (Variable) indexScraper.getContext().get(
				"introduce");
		novelBasicDo.setIntroduce(introduce.toString());
		// ͼƬ��ַ
		novelBasicDo.setPicturePath(pictureSavePath);
		// ����
		Variable type = (Variable) indexScraper.getContext().get("type");
		novelBasicDo.setType(NovelType.getType(type.toString()).type);
		// ��������������Ϣ��Ĭ��ֵ
		novelBasicDo.setFinished(true);// ��ǰֻ��ȡ���汾
		novelBasicDo.setIsForDownload(0);// Ĭ�ϲ��ṩ����
		novelBasicDo.setReadyPublic(false);// Ĭ�ϲ�����
		novelBasicDo.setGenerateHtml(false);// Ĭ����db,������html
		// TODO: ����nid��ͨ��ȫ��id����
		// novelBasicDo.setNid(nid);

		return novelBasicDo;
	}

	public String getAPictureUrl() {
		StringBuffer sb = new StringBuffer(configConstants.getPictureSavePath());
		sb.append(System.nanoTime());
		sb.append(".jpg");
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		new BqgBasiceInfo().executeIndexPage("http://www.biquge.com/0_494/");
	}

}
