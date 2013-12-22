package com.mike.novel.spider.biqege;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;
import org.webharvest.runtime.variables.Variable;

import com.mike.novel.dto.NovelBasicDo;
import com.mike.novel.dto.NovelVolumDo;
import com.mike.novel.dto.vo.NovelStatusVo;
import com.mike.novel.spider.BasicInfoAccess;
import com.mike.novel.util.NovelType;
import com.mike.novel.util.PictureUrlGene;

/**
 * biquge������Ϣ����
 * 
 * @author ���� 2013��12��18������6:56:46
 */
public class BqgBasiceInfo implements BasicInfoAccess {

	private ScraperConfiguration indexConfig;
	private Scraper indexScraper;

	public BqgBasiceInfo() throws FileNotFoundException {
		indexConfig = new ScraperConfiguration(BqgConstants.HARVEST_INDEX_CONFIG);
		indexScraper = new Scraper(indexConfig, BqgConstants.HARVEST_WORKING_DIR);
	}

	public NovelStatusVo executeIndexPage(String indexPage) {

		String pictureSavePath = PictureUrlGene.getAPictureUrl();
		NovelStatusVo result = new NovelStatusVo();

		// ����spider�ı���
		indexScraper.getContext().setVar("IndexPage", indexPage);
		indexScraper.getContext().setVar("pictureSavePath", pictureSavePath);
		indexScraper.execute();

		NovelBasicDo novelBasicDo = curlBasicInfo(pictureSavePath);
		result.setNovelBasicDo(novelBasicDo);
		// TODO: ��novelBasicDo�浽db��

		Variable allinfo = (Variable) indexScraper.getContext().get("allinfo");
		List<NovelVolumDo> volums = BggIndexParseHelper.parse(allinfo.toString(), novelBasicDo.getTitle().length());
		result.setVolums(volums);
		// TODO: ��volum��task�浽db��

		return result;
	}

	private NovelBasicDo curlBasicInfo(String pictureSavePath) {
		NovelBasicDo novelBasicDo = new NovelBasicDo();
		// ����
		Variable title = (Variable) indexScraper.getContext().get("title");
		novelBasicDo.setTitle(title.toString());
		// ����
		Variable author = (Variable) indexScraper.getContext().get("author");
		novelBasicDo.setAuthor(author.toString().split("��")[1]);
		// ������ʱ��
		Variable lastUpdateTime = (Variable) indexScraper.getContext().get("lastUpdateTime");
		novelBasicDo.setLastUpdateTime(Date.valueOf(lastUpdateTime.toString().split("��")[1]));
		// ���
		Variable introduce = (Variable) indexScraper.getContext().get("introduce");
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
		novelBasicDo.setGeneratehtml(false);// Ĭ����db,������html

		return novelBasicDo;
	}

	public static void main(String[] args) throws IOException {
		new BqgBasiceInfo().executeIndexPage("http://www.biquge.com/0_494/");
	}
}
