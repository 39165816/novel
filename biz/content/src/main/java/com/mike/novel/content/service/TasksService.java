package com.mike.novel.content.service;

import java.util.List;

import com.mike.novel.dto.TasksDo;

public interface TasksService {
	List<TasksDo> queryByNid(long nid);

	Integer save(TasksDo tasksDo);

	void finishTask(TasksDo tasksDo);
}
