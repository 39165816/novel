package com.mike.novel.content.service;

import java.sql.Date;
import java.util.List;

import com.mike.novel.dto.HottestDo;

public interface HottestService {
	/**
	 * ±£´ædo
	 */
	public Integer insert(HottestDo hottestDo);

	public boolean check(Date statisticTime);

	public List<HottestDo> findByTime(Date statisticTime);
}
