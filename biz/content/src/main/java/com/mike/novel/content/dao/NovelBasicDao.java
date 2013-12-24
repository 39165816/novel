package com.mike.novel.content.dao;

import java.io.Serializable;
import java.util.List;

import com.alibaba.lp.orm.ibatis.page.Page;
import com.mike.novel.dto.NovelBasicDo;

public interface NovelBasicDao {

	public Integer save(NovelBasicDo novelBasicDo);

	public void update(NovelBasicDo novelBasicDo);

	public void deleteById(Serializable id);

	public NovelBasicDo getById(Serializable id);

	public List<NovelBasicDo> findByIds(List<Serializable> ids);

	public List<NovelBasicDo> find(NovelBasicDo novelBasicDo);

	public Page<NovelBasicDo> queryPage(Integer pageIndex, Integer sizePerPage,
			NovelBasicDo novelBasicDo);

}
