package com.mike.novel.content.dao.impl;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.alibaba.lp.orm.ibatis.page.PageSqlMapClientDaoSupport;
import com.mike.novel.content.dao.HottestDao;
import com.mike.novel.dto.HottestDo;

public class HottestDaoImpl extends PageSqlMapClientDaoSupport<HottestDo>
		implements HottestDao {

	@Override
	public Integer save(HottestDo novelChapterDo) {
		if (novelChapterDo == null) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().insert(
				getSqlMapNamesapce() + "insert", novelChapterDo);
	}

	@Override
	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub

	}

	private String getSqlMapNamesapce() {
		return "hottest.";
	}

	@Override
	public int check(Date statisticTime) {
		HottestDo hottestDo = new HottestDo();
		hottestDo.setStatisticTime(statisticTime);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				getSqlMapNamesapce() + "getTodayCount", hottestDo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HottestDo> findByTime(Date statisticTime) {
		HottestDo hottestDo = new HottestDo();
		hottestDo.setStatisticTime(statisticTime);
		return getSqlMapClientTemplate().queryForList(
				getSqlMapNamesapce() + "findByTime", hottestDo);
	}

}
