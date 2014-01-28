package com.mike.novel.tasks;

import java.util.Calendar;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.mike.novel.content.service.RecommondService;

/**
 * ÿ���賿3�㣬����С˵���Ȱ��Զ�����Ƽ�
 * 
 */
public class RecommendRandomTask implements InitializingBean {
	@Resource
	private RecommondService recommondService;

	// ���ڼ��ļ��ʱ�� 1Сʱ
	private static final int CHECK_INTEVAL = 1 * 3600 * 1000;
	private Calendar lastUpdateTime = null;
	private static Logger loggor = LoggerFactory
			.getLogger(RecommendRandomTask.class);

	class RecommendRandomRunnable implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					// ��ȡ��ǰ��ʱ��,�Ƿ񳬹�24Сʱ
					Calendar now = Calendar.getInstance();
					now.add(Calendar.DAY_OF_YEAR, -1);
					if (now.after(lastUpdateTime)) {
						// ִ�������������ʱ��
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
		// ���û��3�㣬last updateΪ����
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
