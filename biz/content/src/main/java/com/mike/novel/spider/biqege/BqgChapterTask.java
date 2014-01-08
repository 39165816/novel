package com.mike.novel.spider.biqege;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mike.novel.content.dao.NovelPageDao;
import com.mike.novel.content.service.TasksService;
import com.mike.novel.dto.NovelPageDo;
import com.mike.novel.dto.TasksDo;
import com.mike.novel.spider.ChapterAccess;

/**
 * 章节处理任务
 * 
 */
public class BqgChapterTask {

	private static Logger loggor = LoggerFactory
			.getLogger(BqgChapterTask.class);

	private static final int CORE_NUM = 2;
	private static final int SLEEP_TIME = 200;

	@Resource
	private ChapterAccess hapterAccess;
	@Resource
	private NovelPageDao novelPageDao;
	@Resource
	private TasksService tasksService;

	public void processTask(List<TasksDo> tasksDo) {
		ExecutorService newFixedThreadPool = Executors
				.newFixedThreadPool(CORE_NUM);
		for (TasksDo oneTask : tasksDo) {
			newFixedThreadPool.execute(new OneChapter(oneTask));
		}
	}

	class OneChapter implements Runnable {

		private TasksDo tasksDo;

		public OneChapter(TasksDo tasksDo) {
			this.tasksDo = tasksDo;
		}

		public void run() {
			if (tasksDo.isFinished()) {
				loggor.info("task is already finished!" + tasksDo.getUrl());
				return;
			}

			try {
				// 重试5次
				String content = null;
				for (int i = 0; i < 5; i++) {
					try {
						content = hapterAccess.accessChapter(tasksDo.getUrl());
						if (content != null) {
							break;
						}
					} catch (Exception e1) {
						if (i == 4) {
							throw e1;
						}
						Thread.sleep(SLEEP_TIME);
					}
				}

				NovelPageDo novelPageDo = new NovelPageDo();
				novelPageDo.setContent(content);
				novelPageDo.setCid(tasksDo.getCid());
				// 存page table、修改任务状态
				novelPageDao.save(novelPageDo);
				tasksDo.setFinished(true);
				tasksService.finishTask(tasksDo);

				loggor.info("curl one chapter suceesss! url" + tasksDo.getUrl());
				try {
					Thread.sleep(SLEEP_TIME);
				} catch (InterruptedException e) {
				}
			} catch (Exception e) {
				loggor.error("Url failed123: " + tasksDo.getUrl(), e);
			}
		}
	}
}
