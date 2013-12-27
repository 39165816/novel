package com.mike.novel.content.dao.impl;

import java.util.List;

import com.alibaba.lp.orm.ibatis.page.PageSqlMapClientDaoSupport;
import com.mike.novel.content.dao.RecommondDao;
import com.mike.novel.dto.RecommondDo;

public class RecommondDaoImpl extends PageSqlMapClientDaoSupport<RecommondDo>
		implements RecommondDao {

	@Override
	public Integer save(RecommondDo recommondDo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecommondDo> getByPid(int pid) {
		@SuppressWarnings("unchecked")
		List<RecommondDo> TasksDo = getSqlMapClientTemplate().queryForList(
				getSqlMapNamesapce() + "getByPid", pid);
		return TasksDo;
	}

	private String getSqlMapNamesapce() {
		return "recommond.";
	}

}
