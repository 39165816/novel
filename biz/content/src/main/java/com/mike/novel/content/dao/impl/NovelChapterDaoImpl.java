package com.mike.novel.content.dao.impl;

import java.io.Serializable;

import com.alibaba.lp.orm.ibatis.page.PageSqlMapClientDaoSupport;
import com.mike.novel.content.dao.NovelChapterDao;
import com.mike.novel.dto.NovelChapterDo;

public class NovelChapterDaoImpl extends
		PageSqlMapClientDaoSupport<NovelChapterDo> implements NovelChapterDao {

	@Override
	public Integer save(NovelChapterDo novelChapterDo) {
		if (novelChapterDo == null) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().insert(
				getSqlMapNamesapce() + "insert", novelChapterDo);
	}

	@Override
	public void update(NovelChapterDo novelChapterDo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub

	}

	@Override
	public NovelChapterDo getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getSqlMapNamesapce() {
		return "novelChapter.";
	}
}
