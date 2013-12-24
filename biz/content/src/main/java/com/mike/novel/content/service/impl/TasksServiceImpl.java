package com.mike.novel.content.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.mike.novel.content.dao.TasksDao;
import com.mike.novel.content.service.TasksService;
import com.mike.novel.dto.TasksDo;

public class TasksServiceImpl implements TasksService {
	@Resource
	private TasksDao tasksDao;

	@Override
	public List<TasksDo> queryByNid(long nid) {
		return tasksDao.getByNid(nid);
	}

	public Integer save(TasksDo tasksDo) {
		return tasksDao.save(tasksDo);
	}

	@Override
	public void finishTask(TasksDo tasksDo) {
		tasksDao.update(tasksDo);
	}

}
