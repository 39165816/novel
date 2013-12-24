package com.mike.novel.spider.biqege;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

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
			String content = hapterAccess.accessChapter(tasksDo.getUrl());
			NovelPageDo novelPageDo = new NovelPageDo();
			novelPageDo.setContent(content);
			novelPageDo.setCid(tasksDo.getCid());
			// 存page table、修改任务状态
			novelPageDao.save(novelPageDo);
			tasksDo.setFinished(true);
			tasksService.finishTask(tasksDo);

			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
			}
		}
	}
}
