package com.mike.novel.content.dao;

import java.util.List;

import com.mike.novel.dto.RecommondDo;

public interface RecommondDao {

	public Integer save(RecommondDo recommondDo);

	public List<RecommondDo> getByPid(int pid);

	public void deleteByTypeDirect(int pid);

	public void deleteByIds(List<Integer> ids);

}
