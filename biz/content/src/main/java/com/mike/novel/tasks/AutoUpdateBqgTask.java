package com.mike.novel.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 根据bqg的更新，每天自动更新相应的内容
 */
public class AutoUpdateBqgTask {
    private static final Logger logger = LoggerFactory.getLogger(AutoUpdateBqgTask.class);

    public void update() {
        logger.info("AutoUpdateBqgTask is running");
    }
}
