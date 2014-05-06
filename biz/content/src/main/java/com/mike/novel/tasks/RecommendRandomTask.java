package com.mike.novel.tasks;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mike.novel.content.service.RecommondService;

/**
 * ÿ���賿3�㣬����С˵���Ȱ��Զ�����Ƽ�
 */
public class RecommendRandomTask {
    @Resource
    private RecommondService recommondService;

    private static Logger    loggor = LoggerFactory.getLogger(RecommendRandomTask.class);

    public void runTask() {
        loggor.info("RecommendRandomTask is running!");
        try {
            recommondService.random();
        } catch (Exception e) {
            loggor.error("RecommendRandomRunnable", e);
        }

    }
}
