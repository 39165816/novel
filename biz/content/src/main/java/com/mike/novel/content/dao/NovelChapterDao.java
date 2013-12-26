package com.mike.novel.content.dao;

import java.io.Serializable;
import java.util.List;

import com.mike.novel.dto.NovelChapterDo;

public interface NovelChapterDao {

	public Integer save(NovelChapterDo novelChapterDo);

	public void update(NovelChapterDo novelChapterDo);

	public void deleteById(Serializable id);

	public NovelChapterDo getbyCid(long cid);

	public List<NovelChapterDo> getbyVid(int vid);

}
