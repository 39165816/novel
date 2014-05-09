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
 * biquge������Ϣ����
 * 
 * @author ���� 2013��12��18������6:56:46
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

        // ����������Ϣ
        NovelBasicDo novelBasicDo = bqgIndexCommonService.parseBasicInfo(indexScraper, indexPage);
        // ����ͼƬ
        String picName = getPictureName();
        downloadPicture(indexScraper, picName);
        novelBasicDo.setPicturePath(picName);
        // ͨ��ȫ��id����
        novelBasicDo.setNid(bizCommonSequence.getNidSequenceCode());
        novelBasicService.insert(novelBasicDo);

        // ��������½���Ϣ������volum��task�浽db
        int nid = novelBasicDo.getNid();
        List<NovelVolumDo> volums = bqgIndexCommonService.parseVolums(indexScraper,
                novelBasicDo.getTitle(), nid);
        novelCombServcie.saveVolumAndTask(volums, 0l, true);

        //������
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
