/**
 * Project: novel-biz-content
 * 
 * File Created at 2014年5月6日下午4:39:54
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
package com.mike.novel.content.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webharvest.runtime.Scraper;

import com.mike.novel.content.dao.NovelBasicDao;
import com.mike.novel.content.dao.NovelChapterDao;
import com.mike.novel.content.dao.NovelVolumDao;
import com.mike.novel.content.service.BqgAutoUpdateService;
import com.mike.novel.content.service.NovelCombServcie;
import com.mike.novel.dto.NovelBasicDo;
import com.mike.novel.dto.NovelChapterDo;
import com.mike.novel.dto.NovelVolumDo;
import com.mike.novel.spider.biqege.BqgIndexCommonServiceImpl;

/**
 * bqg自动更新章节
 * 
 * @author 听雷 2014年5月6日下午4:39:54
 */
public class BqgAutoUpdateServiceImpl implements BqgAutoUpdateService {
    @Resource
    private BqgIndexCommonServiceImpl bqgIndexCommonService;
    @Resource
    private NovelVolumDao             novelVolumDao;
    @Resource
    private NovelChapterDao           novelChapterDao;
    @Resource
    private NovelCombServcie          novelCombServcie;
    @Resource
    private NovelBasicDao             novelBasicDao;
    private static final Logger       logger = LoggerFactory
                                                     .getLogger(BqgAutoUpdateServiceImpl.class);

    @Override
    public void autoUpdate(int nid, String url) {
        Scraper indexScraper = bqgIndexCommonService.getIndexScraper();
        bqgIndexCommonService.executeIndexCurl(url, indexScraper);
        // 分析基本信息
        NovelBasicDo novelBasicDo = bqgIndexCommonService.parseBasicInfo(indexScraper, url);
        List<NovelVolumDo> webVolums = bqgIndexCommonService.parseVolums(indexScraper,
                novelBasicDo.getTitle(), nid);

        // 查出db中章节
        List<NovelVolumDo> dbvolums = novelVolumDao.queryByNid(nid);
        if (dbvolums != null) { // 对于每个卷，补充章信息
            for (NovelVolumDo oneVolum : dbvolums) {
                oneVolum.setChapters(novelChapterDao.getbyVid(oneVolum.getVid()));
            }
        }

        if (!checkData(nid, url, webVolums, dbvolums)) {
            return;
        }

        compareAndSave(nid, url, webVolums, dbvolums);

        // 更新小说完结状态
        if (novelBasicDo.isFinished()) {
            NovelBasicDo updateDO = new NovelBasicDo();
            updateDO.setNid(nid);
            updateDO.setFinished(true);
            novelBasicDao.update(novelBasicDo);
        }
    }

    private boolean checkData(int nid, String url, List<NovelVolumDo> volums,
                              List<NovelVolumDo> dbvolums) {
        // 校验是否为空
        if (volums == null || volums.size() == 0) {
            logger.warn("nid=" + nid + ",url=" + url + " bqg is empty!");
            return false;
        }
        if (dbvolums == null || dbvolums.size() == 0) {
            logger.warn("nid=" + nid + ",url=" + url + " db is empty!");
            return false;
        }
        // 检验第一卷的名字
        if (!volums.get(0).getVname().equals(dbvolums.get(0).getVname())) {
            logger.warn("something is wrong: " + volums.get(0).getVname() + "!="
                    + dbvolums.get(0).getVname());
            return false;
        }
        return true;
    }

    private void compareAndSave(int nid, String url, List<NovelVolumDo> webVolums,
                                List<NovelVolumDo> dbvolums) {

        int webVolumSize = webVolums.size();
        int dbVolumSize = dbvolums.size();
        NovelVolumDo lastDbVolum = dbvolums.get(dbVolumSize - 1);
        if (webVolumSize == dbVolumSize) {//卷数量相同
            List<NovelChapterDo> webChapters = webVolums.get(webVolumSize - 1).getChapters();
            List<NovelChapterDo> dbChapters = lastDbVolum.getChapters();
            // web中为空，不需要处理
            if (webChapters == null || webChapters.size() == 0) {
                return;
            }
            // db中为空，这中情况应该不存在。有卷号，但没有章节。报错！
            // 注意:这个逻辑可能存在问题，当前按这个简单处理
            if (dbChapters == null || dbChapters.size() == 0) {
                logger.warn("nid=" + nid + ",url=" + url + ", volumn num " + dbVolumSize
                        + " in db has no chapter!");
                return;
            }

            if (webChapters.size() <= dbChapters.size()) { //章节相同，无更新
                return;
            } else {//处理多出来的章节
                List<NovelVolumDo> updateVolums = new ArrayList<NovelVolumDo>();
                buildUpdateVolums(lastDbVolum, dbChapters, webChapters, updateVolums);
                saveVolumAndUpdateLastChapter(dbChapters, updateVolums);
            }
        } else if (webVolumSize < dbVolumSize) {//web小于db
            logger.warn("nid=" + nid + ",url=" + url + ", webVolums " + webVolumSize + " < "
                    + " dbvolums " + dbVolumSize);
            return;
        } else {
            //比较db最后卷和web中相应卷
            List<NovelChapterDo> dbChapters = lastDbVolum.getChapters();
            List<NovelChapterDo> webChapters = webVolums.get(dbVolumSize - 1).getChapters();
            List<NovelVolumDo> updateVolums = new ArrayList<NovelVolumDo>();
            if (webChapters != null && webChapters.size() > 0) {
                if (dbChapters == null || dbChapters.size() == 0) {
                    logger.error("nid=" + nid + ",url=" + url + ", volumn num " + dbVolumSize
                            + " in db has no chapter!");
                    return;
                } else {
                    if (webChapters.size() <= dbChapters.size()) { //章节相同，无更新
                        return;
                    } else {
                        buildUpdateVolums(lastDbVolum, dbChapters, webChapters, updateVolums);
                    }
                }
            }
            // 处理多出来的卷
            for (int i = dbVolumSize - 1; i < webVolumSize; i++) {
                updateVolums.add(webVolums.get(i));
            }

            saveVolumAndUpdateLastChapter(dbChapters, updateVolums);
        }
    }

    private void saveVolumAndUpdateLastChapter(List<NovelChapterDo> dbChapters,
                                               List<NovelVolumDo> updateVolums) {
        NovelChapterDo lastDbChapter = dbChapters.get(dbChapters.size() - 1);
        long upid = lastDbChapter.getCid();
        long nextId = novelCombServcie.saveVolumAndTask(updateVolums, upid, false);
        lastDbChapter.setNextid(nextId);
        novelChapterDao.update(lastDbChapter);
    }

    private void buildUpdateVolums(NovelVolumDo lastDbVolum, List<NovelChapterDo> dbChapters,
                                   List<NovelChapterDo> webChapters, List<NovelVolumDo> updateVolums) {
        NovelVolumDo udpateVolumDo = new NovelVolumDo();
        updateVolums.add(udpateVolumDo);
        //设置更新信息
        udpateVolumDo.setNid(lastDbVolum.getNid());
        udpateVolumDo.setVid(lastDbVolum.getVid());
        List<NovelChapterDo> updateChapters = new ArrayList<NovelChapterDo>();
        udpateVolumDo.setChapters(updateChapters);
        for (int i = dbChapters.size() - 1; i < webChapters.size(); i++) {
            updateChapters.add(webChapters.get(i));
        }
    }
}
