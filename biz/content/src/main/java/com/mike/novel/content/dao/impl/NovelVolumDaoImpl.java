package com.mike.novel.content.dao.impl;

import java.io.Serializable;
import java.util.List;

import com.alibaba.lp.orm.ibatis.page.PageSqlMapClientDaoSupport;
import com.mike.novel.content.dao.NovelVolumDao;
import com.mike.novel.dto.NovelVolumDo;

public class NovelVolumDaoImpl extends PageSqlMapClientDaoSupport<NovelVolumDo>
		implements NovelVolumDao {

	@Override
	public Integer save(NovelVolumDo novelVolumDo) {
		if (novelVolumDo == null) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().insert(
				getSqlMapNamesapce() + "insert", novelVolumDo);
	}

	@Override
	public void update(NovelVolumDo novelVolumDo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub

	}

	@Override
	public NovelVolumDo getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getSqlMapNamesapce() {
		return "novelVolum.";
	}

	@Override
	public List<NovelVolumDo> queryByNid(long nid) {
		@SuppressWarnings("unchecked")
		List<NovelVolumDo> flowList = getSqlMapClientTemplate().queryForList(
				getSqlMapNamesapce() + "queryByNid", nid);
		return flowList;
	}

}
