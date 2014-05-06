package com.mike.novel.tasks;

import java.sql.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mike.novel.content.service.HottestService;
import com.mike.novel.dto.HottestDo;
import com.mike.novel.spider.baidu.BaiduHotAccess;

/**
 * 每天定期爬取百度的数据的任务
 * 
 * @author mike
 */
public class BaiduHotTask {

    private static Logger  loggor = LoggerFactory.getLogger(BaiduHotTask.class);
    @Resource
    private BaiduHotAccess baiduHotAccess;
    @Resource
    private HottestService hottestService;

    public void runTask() {
        try {
            loggor.info("BaiduHotTask is running!");
            // 获取当前的时间，并检测db是否存在记录
            java.util.Date now = new java.util.Date();
            @SuppressWarnings("deprecation")
            Date statisticTime = new Date(now.getYear(), now.getMonth(), now.getDate());

            boolean existed = hottestService.check(statisticTime);
            if (!existed) { // 如果不存在，爬取数据
                String accessTop50 = baiduHotAccess.accessTop50();
                String[] split = accessTop50.split("\n");
                for (String one : split) {
                    HottestDo hottestDo = HottestDo.buildFromString(one);
                    hottestDo.setStatisticTime(statisticTime);
                    hottestDo.setType(1);
                    hottestService.insert(hottestDo);
                }
            } else {
                loggor.warn(statisticTime.toString() + " is already existed");
            }

        } catch (Exception e) {
            loggor.error("BaiduHotRunnable", e);
        }
    }

}
