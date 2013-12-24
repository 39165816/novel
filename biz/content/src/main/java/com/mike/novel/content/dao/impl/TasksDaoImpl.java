package com.mike.novel.content.dao.impl;

import java.io.Serializable;
import java.util.List;

import com.alibaba.lp.orm.ibatis.page.PageSqlMapClientDaoSupport;
import com.mike.novel.content.dao.TasksDao;
import com.mike.novel.dto.TasksDo;

public class TasksDaoImpl extends PageSqlMapClientDaoSupport<TasksDo> implements
		TasksDao {

	@Override
	public Integer save(TasksDo tasksDo) {
		if (tasksDo == null) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().insert(
				getSqlMapNamesapce() + "insert", tasksDo);
	}

	@Override
	public void update(TasksDo tasksDo) {
		if (tasksDo == null) {
			return;
		}
		getSqlMapClientTemplate().update(getSqlMapNamesapce() + "update",
				tasksDo);
	}

	@Override
	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TasksDo> getByNid(long nid) {
		@SuppressWarnings("unchecked")
		List<TasksDo> TasksDo = getSqlMapClientTemplate().queryForList(
				getSqlMapNamesapce() + "getByNid", nid);
		return TasksDo;
	}

	private String getSqlMapNamesapce() {
		return "tasks.";
	}
}
