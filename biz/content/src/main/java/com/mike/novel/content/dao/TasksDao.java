package com.mike.novel.content.dao;

import java.io.Serializable;
import java.util.List;

import com.mike.novel.dto.TasksDo;

public interface TasksDao {

	public Integer save(TasksDo tasksDo);

	public void update(TasksDo tasksDo);

	public void deleteById(Serializable id);

	public List<TasksDo> getByNid(int nid);
}
