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
 * biquge基本信息爬虫
 * 
 * @author 听雷 2013年12月18日下午6:56:46
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
			throw new RuntimeException("首页harvest配置文件路径没找到", e);
		}
		Scraper indexScraper = new Scraper(indexConfig,
				configConstants.getProjectBaseHome()
						+ BqgConstants.HARVEST_WORKING_DIR);

		String picName = getPictureName();
		NovelStatusVo result = new NovelStatusVo();

		// 设置spider的变量
		indexScraper.getContext().setVar("IndexPage", indexPage);
		indexScraper.getContext().setVar("pictureSavePath",
				configConstants.getPictureSavePath() + picName);
		indexScraper.execute();

		// 分析并保存基本信息
		NovelBasicDo novelBasicDo = curlBasicInfo(indexScraper, picName,
				indexPage);
		result.setNovelBasicDo(novelBasicDo);
		novelBasicService.insert(novelBasicDo);

		int nid = novelBasicDo.getNid();

		Variable allinfo = (Variable) indexScraper.getContext().get("allinfo");
		List<NovelVolumDo> volums = BggIndexParseHelper.parse(
				allinfo.toString(), novelBasicDo.getTitle().length(), nid);
		result.setVolums(volums);
		// 把volum和task存到db中
		novelCombServcie.saveVolumAndTask(volums);

		return result;
	}

	private NovelBasicDo curlBasicInfo(Scraper indexScraper,
			String pictureSavePath, String indexPage) {
		NovelBasicDo novelBasicDo = new NovelBasicDo();
		// 设置原始输入URL
		novelBasicDo.setOriginalUrl(indexPage.trim());
		// 标题
		Variable title = (Variable) indexScraper.getContext().get("title");
		novelBasicDo.setTitle(title.toString());
		// 作者
		Variable author = (Variable) indexScraper.getContext().get("author");
		novelBasicDo.setAuthor(author.toString().split("：")[1]);
		// 最后更新时间
		Variable lastUpdateTime = (Variable) indexScraper.getContext().get(
				"lastUpdateTime");
		novelBasicDo.setLastUpdateTime(Date.valueOf(lastUpdateTime.toString()
				.split("：")[1]));
		// 简介
		Variable introduce = (Variable) indexScraper.getContext().get(
				"introduce");
		String intro = introduce.toString();
		if (intro != null && intro.length() > 2000) {
			intro = intro.substring(0, 1999);
		}

		novelBasicDo.setIntroduce(intro);
		// 图片地址
		novelBasicDo.setPicturePath(pictureSavePath);
		// 类型
		Variable type = (Variable) indexScraper.getContext().get("type");
		novelBasicDo.setType(NovelType.getType(type.toString()).type);
		// 设置其它基本信息的默认值
		novelBasicDo.setFinished(true);// 当前只爬取完结版本
		novelBasicDo.setIsForDownload(0);// 默认不提供下载
		novelBasicDo.setReadyPublic(false);// 默认不公开
		novelBasicDo.setGenerateHtml(false);// 默认走db,不生成html
		novelBasicDo.setNid(bizCommonSequence.getNidSequenceCode()); // 通过全局id来做

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
