package com.mike.novel.spider.biqege;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;

import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;
import org.webharvest.runtime.variables.Variable;

import com.mike.novel.dto.NovelBasicDo;
import com.mike.novel.util.NovelType;

/**
 * biquge基本信息爬虫
 * 
 * @author 听雷 2013年12月18日下午6:56:46
 */
public class BqgBasiceInfo {

    private ScraperConfiguration indexConfig;
    private Scraper              indexScraper;

    public BqgBasiceInfo() throws FileNotFoundException {
        indexConfig = new ScraperConfiguration(BqgConstants.HARVEST_INDEX_CONFIG);
        indexScraper = new Scraper(indexConfig, BqgConstants.HARVEST_WORKING_DIR);
    }

    public void executeIndexPage(String indexPage) {
        indexScraper.getContext().setVar("IndexPage", indexPage);
        indexScraper.execute();

        NovelBasicDo novelBasicDo = new NovelBasicDo();
        //标题
        Variable title = (Variable) indexScraper.getContext().get("title");
        novelBasicDo.setTitle(title.toString());
        //作者
        Variable author = (Variable) indexScraper.getContext().get("author");
        System.out.println(author.toString().split("：")[1]);
        novelBasicDo.setAuthor(author.toString().split("：")[1]);
        //最后更新时间
        Variable lastUpdateTime = (Variable) indexScraper.getContext().get("lastUpdateTime");
        System.out.println(lastUpdateTime.toString().split("：")[1]);
        novelBasicDo.setLastUpdateTime(Date.valueOf(lastUpdateTime.toString().split("：")[1]));
        //简介
        Variable introduce = (Variable) indexScraper.getContext().get("introduce");
        System.out.println(introduce.toString());
        novelBasicDo.setIntroduce(introduce.toString());
        //图片地址
        Variable pictureUrl = (Variable) indexScraper.getContext().get("pictureUrl");
        System.out.println(BqgConstants.BQG_WEBSITE + pictureUrl.toString());
        //TODO: 待爬图片地址和保存
        novelBasicDo.setPicturePath(BqgConstants.BQG_WEBSITE + pictureUrl.toString());
        //类型
        Variable type = (Variable) indexScraper.getContext().get("type");
        System.out.println(type.toString());
        novelBasicDo.setType(NovelType.getType(type.toString()).type);

        Variable allinfo = (Variable) indexScraper.getContext().get("allinfo");
        System.out.println(allinfo.toString());

    }

    public static void main(String[] args) throws IOException {
        new BqgBasiceInfo().executeIndexPage("http://www.biquge.com/0_494/");
    }
}
