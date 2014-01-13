package com.mike.novel.content.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.mike.novel.content.dao.NovelPageDao;
import com.mike.novel.content.service.NovelPageService;
import com.mike.novel.dto.NovelPageDo;

public class NovelPageServiceImpl implements NovelPageService {

	@Resource
	private NovelPageDao novelPageDao;

	@Override
	public NovelPageDo queryByCid(long cid) {
		return novelPageDao.getByCid(cid);
	}

	@Override
	public List<NovelPageDo> findByCids(List<Long> cid) {
		if (cid == null || cid.size() == 0) {
			return new ArrayList<NovelPageDo>();
		}

		return novelPageDao.findByCids(cid);
	}
}
