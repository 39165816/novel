package com.mike.novel.content.dao.impl;

import java.io.Serializable;
import java.util.List;

import com.alibaba.lp.orm.ibatis.page.PageSqlMapClientDaoSupport;
import com.mike.novel.content.dao.NovelChapterDao;
import com.mike.novel.dto.NovelChapterDo;

public class NovelChapterDaoImpl extends PageSqlMapClientDaoSupport<NovelChapterDo> implements
        NovelChapterDao {

    @Override
    public Integer save(NovelChapterDo novelChapterDo) {
        if (novelChapterDo == null) {
            return 0;
        }
        return (Integer) getSqlMapClientTemplate().insert(getSqlMapNamesapce() + "insert",
                novelChapterDo);
    }

    @Override
    public void update(NovelChapterDo novelChapterDo) {
        if (novelChapterDo == null) {
            return;
        }
        getSqlMapClientTemplate().update(getSqlMapNamesapce() + "update", novelChapterDo);
    }

    @Override
    public void deleteById(Serializable id) {
        // TODO Auto-generated method stub

    }

    @Override
    public NovelChapterDo getbyCid(long cid) {
        return (NovelChapterDo) getSqlMapClientTemplate().queryForObject(
                getSqlMapNamesapce() + "getbyCid", cid);
    }

    private String getSqlMapNamesapce() {
        return "novelChapter.";
    }

    @Override
    public List<NovelChapterDo> getbyVid(int vid) {
        @SuppressWarnings("unchecked")
        List<NovelChapterDo> flowList = getSqlMapClientTemplate().queryForList(
                getSqlMapNamesapce() + "getbyVid", vid);
        return flowList;
    }
}
