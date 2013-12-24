package com.mike.novel.content.dao;

import java.io.Serializable;

import com.mike.novel.dto.NovelChapterDo;

public interface NovelChapterDao {

	public Integer save(NovelChapterDo novelChapterDo);

	public void update(NovelChapterDo novelChapterDo);

	public void deleteById(Serializable id);

	public NovelChapterDo getById(Serializable id);

}
