package com.mike.novel.tasks;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mike.novel.content.service.BqgAutoUpdateService;

/**
 * 根据bqg的更新，每天自动更新相应的内容
 */
public class AutoUpdateBqgTask {
    private static final Logger  logger = LoggerFactory.getLogger(AutoUpdateBqgTask.class);
    @Resource
    private BqgAutoUpdateService bqgAutoUpdateService;

    public void update() {
        logger.info("AutoUpdateBqgTask is running");
        int nid = 1431;
        String url = "http://www.biquge.com/4_4362/";
        bqgAutoUpdateService.autoUpdate(nid, url);
    }
}
