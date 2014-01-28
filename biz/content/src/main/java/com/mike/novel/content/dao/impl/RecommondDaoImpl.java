package com.mike.novel.content.dao.impl;

import java.util.List;

import com.alibaba.lp.orm.ibatis.page.PageSqlMapClientDaoSupport;
import com.mike.novel.content.dao.RecommondDao;
import com.mike.novel.dto.RecommondDo;

public class RecommondDaoImpl extends PageSqlMapClientDaoSupport<RecommondDo>
		implements RecommondDao {

	@Override
	public Integer save(RecommondDo recommondDo) {
		if (recommondDo == null) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().insert(
				getSqlMapNamesapce() + "insert", recommondDo);
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

	@Override
	public void deleteByTypeDirect(int pid) {
		getSqlMapClientTemplate().delete(getSqlMapNamesapce() + "deleteByPid",
				pid);
	}

	@Override
	public void deleteByIds(List<Integer> ids) {
		getSqlMapClientTemplate().delete(getSqlMapNamesapce() + "deleteByIds",
				ids);
	}

}
