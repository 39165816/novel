/**
 * Project: novel-biz-content
 * 
 * File Created at 2014��5��6������4:51:56
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
 * �� BqgIndexCommonServiceImpl ��ʵ��������bqgС˵��ҳ�Ĺ��߷�����
 * 
 * @author ���� 2014��5��6������4:51:56
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
            throw new RuntimeException("��ҳharvest�����ļ�·��û�ҵ�", e);
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
            throw new RuntimeException("��ҳharvestͼƬû�ҵ�", e);
        }
        return new Scraper(pictureConfig, configConstants.getProjectBaseHome()
                + BqgConstants.HARVEST_WORKING_DIR);
    }

    public void executeIndexCurl(String indexPage, Scraper indexScraper) {
        indexScraper.getContext().setVar("IndexPage", indexPage); // ����spider�ı���
        indexScraper.execute();
    }

    public NovelBasicDo parseBasicInfo(Scraper indexScraper, String indexPage) {
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
        Variable lastUpdateTime = (Variable) indexScraper.getContext().get("lastUpdateTime");
        novelBasicDo.setLastUpdateTime(Date.valueOf(lastUpdateTime.toString().split("��")[1]));
        // ���
        Variable introduce = (Variable) indexScraper.getContext().get("introduce");
        String intro = introduce.toString();
        if (intro != null && intro.length() > 2000) {
            intro = intro.substring(0, 1999);
        }

        novelBasicDo.setIntroduce(intro);
        // ����
        Variable type = (Variable) indexScraper.getContext().get("type");
        novelBasicDo.setType(NovelType.getType(type.toString()).type);
        // �Ƿ����
        Variable isFinishedFlag = (Variable) indexScraper.getContext().get("isFinishedFlag");
        if ("b".equals(isFinishedFlag.toString())) {
            novelBasicDo.setFinished(false);
        } else {
            novelBasicDo.setFinished(true);
        }
        // ��������������Ϣ��Ĭ��ֵ
        novelBasicDo.setIsForDownload(0);// Ĭ�ϲ��ṩ����
        novelBasicDo.setReadyPublic(false);// Ĭ�ϲ�����
        novelBasicDo.setGenerateHtml(false);// Ĭ����db,������html
        return novelBasicDo;
    }

    public List<NovelVolumDo> parseVolums(Scraper indexScraper, String title, int nid) {
        Variable allinfo = (Variable) indexScraper.getContext().get("allinfo");
        List<NovelVolumDo> volums = BggIndexParseHelper.parse(allinfo.toString(), title.length(),
                nid);
        return volums;
    }
}
