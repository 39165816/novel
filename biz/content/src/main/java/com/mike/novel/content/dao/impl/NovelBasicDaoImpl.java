package com.mike.novel.content.dao.impl;

import java.io.Serializable;
import java.util.List;

import com.alibaba.lp.orm.ibatis.page.Page;
import com.alibaba.lp.orm.ibatis.page.PageSqlMapClientDaoSupport;
import com.mike.novel.content.dao.NovelBasicDao;
import com.mike.novel.dto.NovelBasicDo;

public class NovelBasicDaoImpl extends PageSqlMapClientDaoSupport<NovelBasicDo>
		implements NovelBasicDao {

	@Override
	public Integer save(NovelBasicDo novelBasicDo) {
		if (novelBasicDo == null) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().insert(
				getSqlMapNamesapce() + "insert", novelBasicDo);
	}

	@Override
	public void update(NovelBasicDo novelBasicDo) {
		if (novelBasicDo == null) {
			return;
		}
		getSqlMapClientTemplate().update(getSqlMapNamesapce() + "update",
				novelBasicDo);
	}

	@Override
	public void deleteById(Serializable id) {
		getSqlMapClientTemplate().delete(getSqlMapNamesapce() + "deleteById",
				id);
	}

	@Override
	public NovelBasicDo getById(Serializable id) {
		if (id == null) {
			return null;
		}
		return (NovelBasicDo) getSqlMapClientTemplate().queryForObject(
				getSqlMapNamesapce() + "getById", id);
	}

	@Override
	public List<NovelBasicDo> findByNids(List<Integer> ids) {
		@SuppressWarnings("unchecked")
		List<NovelBasicDo> flowList = getSqlMapClientTemplate().queryForList(
				getSqlMapNamesapce() + "findByNids", ids);
		return flowList;
	}

	@Override
	public List<NovelBasicDo> find(NovelBasicDo novelBasicDo) {
		@SuppressWarnings("unchecked")
		List<NovelBasicDo> flowList = getSqlMapClientTemplate().queryForList(
				getSqlMapNamesapce() + "find", novelBasicDo);
		return flowList;
	}

	@Override
	public Page<NovelBasicDo> queryPage(Integer pageIndex, Integer sizePerPage,
			NovelBasicDo novelBasicDo) {
		return this.page(pageIndex, sizePerPage, novelBasicDo,
				getSqlMapNamesapce() + "getCount", getSqlMapNamesapce()
						+ "queryPage");
	}

	private String getSqlMapNamesapce() {
		return "novelBasic.";
	}

	@Override
	public NovelBasicDo queryByTargetUrl(String targetUrl) {
		return (NovelBasicDo) getSqlMapClientTemplate().queryForObject(
				getSqlMapNamesapce() + "queryByTargetUrl", targetUrl);
	}

	@Override
	public NovelBasicDo getByNid(long nid) {
		return (NovelBasicDo) getSqlMapClientTemplate().queryForObject(
				getSqlMapNamesapce() + "getByNid", nid);
	}

	@Override
	public List<NovelBasicDo> findByType(int type) {
		@SuppressWarnings("unchecked")
		List<NovelBasicDo> flowList = getSqlMapClientTemplate().queryForList(
				getSqlMapNamesapce() + "findByType", type);
		return flowList;
	}

	@Override
	public List<NovelBasicDo> findAll() {
		@SuppressWarnings("unchecked")
		List<NovelBasicDo> flowList = getSqlMapClientTemplate().queryForList(
				getSqlMapNamesapce() + "findAll");
		return flowList;
	}

}
