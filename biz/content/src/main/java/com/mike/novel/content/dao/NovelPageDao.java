package com.mike.novel.content.dao;

import java.io.Serializable;

import com.mike.novel.dto.NovelPageDo;

public interface NovelPageDao {

	public Integer save(NovelPageDo novelPageDo);

	public void update(NovelPageDo novelPageDo);

	public void deleteById(Serializable id);

	public NovelPageDo getById(Serializable id);

}
