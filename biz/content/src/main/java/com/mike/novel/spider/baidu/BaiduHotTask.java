package com.mike.novel.spider.baidu;

import java.sql.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.mike.novel.content.service.HottestService;
import com.mike.novel.dto.HottestDo;

/**
 * ÿ�춨����ȡ�ٶȵ����ݵ�����
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

	// ���ڼ��ļ��ʱ�� 2Сʱ
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
					// ��ȡ��ǰ��ʱ�䣬�����db�Ƿ���ڼ�¼
					java.util.Date now = new java.util.Date();
					Date statisticTime = new Date(now.getYear(),
							now.getMonth(), now.getDate());

					boolean existed = hottestService.check(statisticTime);
					if (!existed) { // ��������ڣ���ȡ����
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
