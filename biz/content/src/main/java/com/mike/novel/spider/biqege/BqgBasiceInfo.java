package com.mike.novel.spider.biqege;

import java.util.List;

import javax.annotation.Resource;

import org.webharvest.runtime.Scraper;
import org.webharvest.runtime.variables.Variable;

import com.alibaba.lp.orm.sequence.BizCommonSequence;
import com.mike.novel.content.service.NovelBasicService;
import com.mike.novel.content.service.NovelCombServcie;
import com.mike.novel.dto.NovelBasicDo;
import com.mike.novel.dto.NovelVolumDo;
import com.mike.novel.dto.vo.NovelStatusVo;
import com.mike.novel.spider.BasicInfoAccess;
import com.mike.novel.util.ConfigConstants;

/**
 * biquge基本信息爬虫
 * 
 * @author 听雷 2013年12月18日下午6:56:46
 */
public class BqgBasiceInfo implements BasicInfoAccess {

    @Resource
    private ConfigConstants           configConstants;
    @Resource
    private NovelBasicService         novelBasicService;
    @Resource
    private NovelCombServcie          novelCombServcie;
    @Resource
    private BqgIndexCommonServiceImpl bqgIndexCommonService;
    @Resource
    private BizCommonSequence         bizCommonSequence;

    public NovelStatusVo executeIndexPage(String indexPage) {
        Scraper indexScraper = bqgIndexCommonService.getIndexScraper();
        bqgIndexCommonService.executeIndexCurl(indexPage, indexScraper);

        // 分析基本信息
        NovelBasicDo novelBasicDo = bqgIndexCommonService.parseBasicInfo(indexScraper, indexPage);
        // 处理图片
        String picName = getPictureName();
        downloadPicture(indexScraper, picName);
        novelBasicDo.setPicturePath(picName);
        // 通过全局id来做
        novelBasicDo.setNid(bizCommonSequence.getNidSequenceCode());
        novelBasicService.insert(novelBasicDo);

        // 分析卷和章节信息，并把volum和task存到db
        int nid = novelBasicDo.getNid();
        List<NovelVolumDo> volums = bqgIndexCommonService.parseVolums(indexScraper,
                novelBasicDo.getTitle(), nid);
        novelCombServcie.saveVolumAndTask(volums, 0l, true);

        //保存结果
        NovelStatusVo result = new NovelStatusVo();
        result.setNovelBasicDo(novelBasicDo);
        result.setVolums(volums);
        return result;
    }

    private void downloadPicture(Scraper indexScraper, String picName) {
        Variable pictureUrl = (Variable) indexScraper.getContext().get("pictureUrl");
        Scraper pictureScraper = bqgIndexCommonService.getPictureScraper();
        pictureScraper.getContext().setVar("pictureUrl", pictureUrl.toString());
        pictureScraper.getContext().setVar("pictureSavePath",
                configConstants.getPictureSavePath() + picName);
    }

    private String getPictureName() {
        StringBuffer sb = new StringBuffer();
        sb.append(System.nanoTime()).append(".jpg");
        return sb.toString();
    }

}
