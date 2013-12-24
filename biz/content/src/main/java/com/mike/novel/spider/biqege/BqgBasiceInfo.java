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
 * biquge基本信息爬虫
 * 
 * @author 听雷 2013年12月18日下午6:56:46
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
			throw new RuntimeException("首页harvest配置文件路径没找到", e);
		}
		Scraper indexScraper = new Scraper(indexConfig,
				configConstants.getProjectBaseHome()
						+ BqgConstants.HARVEST_WORKING_DIR);

		String pictureSavePath = getAPictureUrl();
		NovelStatusVo result = new NovelStatusVo();

		// 设置spider的变量
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
		// TODO: 把volum和task存到db中

		return result;
	}

	private NovelBasicDo curlBasicInfo(Scraper indexScraper,
			String pictureSavePath) {
		NovelBasicDo novelBasicDo = new NovelBasicDo();
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
		novelBasicDo.setIntroduce(introduce.toString());
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
		// TODO: 待定nid，通过全局id来做
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
