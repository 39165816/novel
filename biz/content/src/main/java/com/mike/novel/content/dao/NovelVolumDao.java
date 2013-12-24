package com.mike.novel.content.dao;

import java.io.Serializable;

import com.mike.novel.dto.NovelVolumDo;

public interface NovelVolumDao {

	public Integer save(NovelVolumDo novelVolumDo);

	public void update(NovelVolumDo novelVolumDo);

	public void deleteById(Serializable id);

	public NovelVolumDo getById(Serializable id);

}
