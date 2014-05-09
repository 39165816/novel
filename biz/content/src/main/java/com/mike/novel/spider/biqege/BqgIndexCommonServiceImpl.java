/**
 * Project: novel-biz-content
 * 
 * File Created at 2014年5月6日下午4:51:56
 * $Id$
 * 
 * Copyright 1999-2012 Alibaba.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.mike.novel.spider.biqege;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;
import org.webharvest.runtime.variables.Variable;

import com.mike.novel.dto.NovelBasicDo;
import com.mike.novel.dto.NovelVolumDo;
import com.mike.novel.util.BqgConstants;
import com.mike.novel.util.ConfigConstants;
import com.mike.novel.util.NovelType;

/**
 * 类 BqgIndexCommonServiceImpl 的实现描述：bqg小说首页的工具访问类
 * 
 * @author 听雷 2014年5月6日下午4:51:56
 */
public class BqgIndexCommonServiceImpl {
    @Resource
    private ConfigConstants configConstants;

    public Scraper getIndexScraper() {
        ScraperConfiguration indexConfig;
        try {
            indexConfig = new ScraperConfiguration(configConstants.getConfigFilePath()
                    + BqgConstants.HARVEST_INDEX_FINE_NAME);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("首页harvest配置文件路径没找到", e);
        }
        return new Scraper(indexConfig, configConstants.getProjectBaseHome()
                + BqgConstants.HARVEST_WORKING_DIR);
    }

    public Scraper getPictureScraper() {
        ScraperConfiguration pictureConfig;
        try {
            pictureConfig = new ScraperConfiguration(configConstants.getConfigFilePath()
                    + BqgConstants.HARVEST_PICTURE_FINE_NAME);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("首页harvest图片没找到", e);
        }
        return new Scraper(pictureConfig, configConstants.getProjectBaseHome()
                + BqgConstants.HARVEST_WORKING_DIR);
    }

    public void executeIndexCurl(String indexPage, Scraper indexScraper) {
        indexScraper.getContext().setVar("IndexPage", indexPage); // 设置spider的变量
        indexScraper.execute();
    }

    public NovelBasicDo parseBasicInfo(Scraper indexScraper, String indexPage) {
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
        Variable lastUpdateTime = (Variable) indexScraper.getContext().get("lastUpdateTime");
        novelBasicDo.setLastUpdateTime(Date.valueOf(lastUpdateTime.toString().split("：")[1]));
        // 简介
        Variable introduce = (Variable) indexScraper.getContext().get("introduce");
        String intro = introduce.toString();
        if (intro != null && intro.length() > 2000) {
            intro = intro.substring(0, 1999);
        }

        novelBasicDo.setIntroduce(intro);
        // 类型
        Variable type = (Variable) indexScraper.getContext().get("type");
        novelBasicDo.setType(NovelType.getType(type.toString()).type);
        // 是否完结
        Variable isFinishedFlag = (Variable) indexScraper.getContext().get("isFinishedFlag");
        if ("b".equals(isFinishedFlag.toString())) {
            novelBasicDo.setFinished(false);
        } else {
            novelBasicDo.setFinished(true);
        }
        // 设置其它基本信息的默认值
        novelBasicDo.setIsForDownload(0);// 默认不提供下载
        novelBasicDo.setReadyPublic(false);// 默认不公开
        novelBasicDo.setGenerateHtml(false);// 默认走db,不生成html
        return novelBasicDo;
    }

    public List<NovelVolumDo> parseVolums(Scraper indexScraper, String title, int nid) {
        Variable allinfo = (Variable) indexScraper.getContext().get("allinfo");
        List<NovelVolumDo> volums = BggIndexParseHelper.parse(allinfo.toString(), title.length(),
                nid);
        return volums;
    }
}
