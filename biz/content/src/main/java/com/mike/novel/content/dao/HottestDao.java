package com.mike.novel.content.dao;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.mike.novel.dto.HottestDo;

public interface HottestDao {
	public Integer save(HottestDo hottestDo);

	public void deleteById(Serializable id);

	public int check(Date statisticTime);

	public List<HottestDo> findByTime(Date statisticTime);

}
