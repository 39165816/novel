package com.mike.novel.content.service.impl;

import javax.annotation.Resource;

import com.mike.novel.content.dao.NovelChapterDao;
import com.mike.novel.content.service.NovelChapterService;
import com.mike.novel.dto.NovelChapterDo;

public class NovelChapterServiceImpl implements NovelChapterService {

	@Resource
	private NovelChapterDao novelChapterDao;

	@Override
	public NovelChapterDo queryByCid(long cid) {
		return novelChapterDao.getbyCid(cid);
	}

}
