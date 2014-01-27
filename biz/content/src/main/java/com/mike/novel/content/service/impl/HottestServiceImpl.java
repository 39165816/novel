package com.mike.novel.content.service.impl;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import com.mike.novel.content.dao.HottestDao;
import com.mike.novel.content.service.HottestService;
import com.mike.novel.dto.HottestDo;

public class HottestServiceImpl implements HottestService {

	@Resource
	private HottestDao hottestDao;

	@Override
	public Integer insert(HottestDo hottestDo) {
		return hottestDao.save(hottestDo);
	}

	@Override
	public boolean check(Date statisticTime) {
		int count = hottestDao.check(statisticTime);
		return count == 0 ? false : true;
	}

	@Override
	public List<HottestDo> findByTime(Date statisticTime) {
		return hottestDao.findByTime(statisticTime);
	}

}
