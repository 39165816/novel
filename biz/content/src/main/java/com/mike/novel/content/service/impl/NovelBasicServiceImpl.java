package com.mike.novel.content.service.impl;

import javax.annotation.Resource;

import com.mike.novel.content.dao.NovelBasicDao;
import com.mike.novel.content.service.NovelBasicService;
import com.mike.novel.dto.NovelBasicDo;

public class NovelBasicServiceImpl implements NovelBasicService {

	@Resource
	private NovelBasicDao novelBasicDao;

	public Integer insert(NovelBasicDo novelBasicDo) {
		return novelBasicDao.save(novelBasicDo);
	}

	@Override
	public NovelBasicDo queryByTargetUrl(String targetUrl) {
		return novelBasicDao.queryByTargetUrl(targetUrl);
	}

	@Override
	public NovelBasicDo queryByNid(long nid) {
		return novelBasicDao.getByNid(nid);
	}

}
