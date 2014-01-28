package com.mike.novel.tasks;

import java.util.Calendar;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.mike.novel.content.service.RecommondService;

/**
 * 每天凌晨3点，根据小说的热榜，自动随机推荐
 * 
 */
public class RecommendRandomTask implements InitializingBean {
	@Resource
	private RecommondService recommondService;

	// 定期检测的间隔时间 1小时
	private static final int CHECK_INTEVAL = 1 * 3600 * 1000;
	private Calendar lastUpdateTime = null;
	private static Logger loggor = LoggerFactory
			.getLogger(RecommendRandomTask.class);

	class RecommendRandomRunnable implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					// 获取当前的时间,是否超过24小时
					Calendar now = Calendar.getInstance();
					now.add(Calendar.DAY_OF_YEAR, -1);
					if (now.after(lastUpdateTime)) {
						// 执行随机，并更新时间
						recommondService.random();
						updateTime();
					}
				} catch (Exception e) {
					loggor.error("RecommendRandomRunnable", e);
				}

				try {
					Thread.sleep(CHECK_INTEVAL);
				} catch (InterruptedException e) {
				}
			}

		}
	}

	public void runTask() {
		updateTime();

		new Thread(new RecommendRandomRunnable(), "RecommendRandomTask")
				.start();
	}

	private void updateTime() {
		lastUpdateTime = Calendar.getInstance();
		// 如果没过3点，last update为昨天
		if (lastUpdateTime.get(Calendar.HOUR_OF_DAY) < 3) {
			lastUpdateTime.set(Calendar.DAY_OF_YEAR,
					lastUpdateTime.get(Calendar.DAY_OF_YEAR) - 1);
		}

		lastUpdateTime.set(Calendar.HOUR_OF_DAY, 3);
		lastUpdateTime.set(Calendar.MINUTE, 0);
		lastUpdateTime.set(Calendar.SECOND, 0);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		runTask();
		loggor.info("RecommendRandomTask start!");
	}

}
