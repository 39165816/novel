package com.mike.novel.spider.baidu;

import java.sql.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.mike.novel.content.service.HottestService;
import com.mike.novel.dto.HottestDo;

/**
 * 每天定期爬取百度的数据的任务
 * 
 * @author mike
 * 
 */
public class BaiduHotTask implements InitializingBean {

	private static Logger loggor = LoggerFactory.getLogger(BaiduHotTask.class);
	@Resource
	private BaiduHotAccess baiduHotAccess;
	@Resource
	private HottestService hottestService;

	// 定期检测的间隔时间 2小时
	private static final int CHECK_INTEVAL = 2 * 3600 * 1000;

	public void runTask() {
		new Thread(new BaiduHotRunnable(), "BaiduHotTask").start();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		runTask();
		loggor.info("BaiduHotTask start!");
	}

	class BaiduHotRunnable implements Runnable {

		@SuppressWarnings("deprecation")
		@Override
		public void run() {
			while (true) {
				try {
					// 获取当前的时间，并检测db是否存在记录
					java.util.Date now = new java.util.Date();
					Date statisticTime = new Date(now.getYear(),
							now.getMonth(), now.getDate());

					boolean existed = hottestService.check(statisticTime);
					if (!existed) { // 如果不存在，爬取数据
						String accessTop50 = baiduHotAccess.accessTop50();
						String[] split = accessTop50.split("\n");
						for (String one : split) {
							HottestDo hottestDo = HottestDo
									.buildFromString(one);
							hottestDo.setStatisticTime(statisticTime);
							hottestDo.setType(1);
							hottestService.insert(hottestDo);
						}
					}

				} catch (Exception e) {
					loggor.error("BaiduHotRunnable", e);
				}

				try {
					Thread.sleep(CHECK_INTEVAL);
				} catch (InterruptedException e) {
				}
			}
		}

	}

}
