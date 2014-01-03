package com.mike.novel.spider.biqege;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;
import org.webharvest.runtime.variables.Variable;

import com.alibaba.lp.orm.sequence.BizCommonSequence;
import com.mike.novel.content.service.NovelBasicService;
import com.mike.novel.content.service.NovelCombServcie;
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
	@Resource
	private NovelCombServcie novelCombServcie;

	@Resource
	private BizCommonSequence bizCommonSequence;

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

		String picName = getPictureName();
		NovelStatusVo result = new NovelStatusVo();

		// ����spider�ı���
		indexScraper.getContext().setVar("IndexPage", indexPage);
		indexScraper.getContext().setVar("pictureSavePath",
				configConstants.getPictureSavePath() + picName);
		indexScraper.execute();

		// ���������������Ϣ
		NovelBasicDo novelBasicDo = curlBasicInfo(indexScraper, picName,
				indexPage);
		result.setNovelBasicDo(novelBasicDo);
		novelBasicService.insert(novelBasicDo);

		int nid = novelBasicDo.getNid();

		Variable allinfo = (Variable) indexScraper.getContext().get("allinfo");
		List<NovelVolumDo> volums = BggIndexParseHelper.parse(
				allinfo.toString(), novelBasicDo.getTitle().length(), nid);
		result.setVolums(volums);
		// ��volum��task�浽db��
		novelCombServcie.saveVolumAndTask(volums);

		return result;
	}

	private NovelBasicDo curlBasicInfo(Scraper indexScraper,
			String pictureSavePath, String indexPage) {
		NovelBasicDo novelBasicDo = new NovelBasicDo();
		// ����ԭʼ����URL
		novelBasicDo.setOriginalUrl(indexPage.trim());
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
		String intro = introduce.toString();
		if (intro != null && intro.length() > 2000) {
			intro = intro.substring(0, 1999);
		}

		novelBasicDo.setIntroduce(intro);
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
		novelBasicDo.setNid(bizCommonSequence.getNidSequenceCode()); // ͨ��ȫ��id����

		return novelBasicDo;
	}

	public String getPictureName() {
		StringBuffer sb = new StringBuffer();
		sb.append(System.nanoTime()).append(".jpg");
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		new BqgBasiceInfo().executeIndexPage("http://www.biquge.com/0_494/");
	}

}
