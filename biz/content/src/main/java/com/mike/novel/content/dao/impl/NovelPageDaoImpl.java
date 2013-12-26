package com.mike.novel.content.dao.impl;

import java.io.Serializable;

import com.alibaba.lp.orm.ibatis.page.PageSqlMapClientDaoSupport;
import com.mike.novel.content.dao.NovelPageDao;
import com.mike.novel.dto.NovelPageDo;

public class NovelPageDaoImpl extends PageSqlMapClientDaoSupport<NovelPageDo>
		implements NovelPageDao {

	@Override
	public Long save(NovelPageDo novelPageDo) {
		if (novelPageDo == null) {
			return 0l;
		}
		return (Long) getSqlMapClientTemplate().insert(
				getSqlMapNamesapce() + "insert", novelPageDo);
	}

	@Override
	public void update(NovelPageDo novelPageDo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub

	}

	private String getSqlMapNamesapce() {
		return "novelPage.";
	}

	@Override
	public NovelPageDo getByCid(long cid) {
		return (NovelPageDo) getSqlMapClientTemplate().queryForObject(
				getSqlMapNamesapce() + "getByCid", cid);
	}
}
