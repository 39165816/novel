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

	public NovelBasicDo queryByTargetUrl(String targetUrl);

	public NovelBasicDo getByNid(long nid);

	public List<NovelBasicDo> findByNids(List<Integer> nids);

	public List<NovelBasicDo> find(NovelBasicDo novelBasicDo);

	public Page<NovelBasicDo> queryPage(Integer pageIndex, Integer sizePerPage,
			NovelBasicDo novelBasicDo);

	public List<NovelBasicDo> findByType(int type);

	public List<NovelBasicDo> findAll();

	public List<NovelBasicDo> findNewest();

	public List<NovelBasicDo> findByFinish();

	public List<NovelBasicDo> findToGenerateTxt();

	public void updateTxtStatus(NovelBasicDo novelBasicDo);

}
