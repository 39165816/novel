package com.mike.novel.content.dao;

import java.io.Serializable;

import com.mike.novel.dto.TasksDo;

public interface TasksDao {

	public Integer save(TasksDo tasksDo);

	public void update(TasksDo tasksDo);

	public void deleteById(Serializable id);

	public TasksDo getById(Serializable id);

}
