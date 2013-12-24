package com.mike.novel.content.dao.impl;

import java.io.Serializable;

import com.alibaba.lp.orm.ibatis.page.PageSqlMapClientDaoSupport;
import com.mike.novel.content.dao.NovelPageDao;
import com.mike.novel.dto.NovelPageDo;

public class NovelPageDaoImpl extends PageSqlMapClientDaoSupport<NovelPageDo>
		implements NovelPageDao {

	@Override
	public Integer save(NovelPageDo novelPageDo) {
		if (novelPageDo == null) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().insert(
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

	@Override
	public NovelPageDo getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getSqlMapNamesapce() {
		return "novelPage.";
	}
}
