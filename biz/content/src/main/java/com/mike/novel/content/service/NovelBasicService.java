package com.mike.novel.content.service;

import java.util.List;

import com.mike.novel.dto.NovelBasicDo;

/**
 * 小说基本信息接口
 */
public interface NovelBasicService {

	/**
	 * 保存基本信息do
	 */
	public Integer insert(NovelBasicDo novelBasicDo);

	/**
	 * 检测需要爬去的url是否存在
	 */
	public NovelBasicDo queryByTargetUrl(String targetUrl);

	/**
	 * 根据nid查找
	 */
	public NovelBasicDo queryByNid(long nid);

	/**
	 * 根据小说nid列表，批量返回小说信息
	 */
	public List<NovelBasicDo> findByNids(List<Integer> nids);

	/**
	 * 根据小说的类型，返回所有的小说（后续小说多了可以考虑分页或者静态化）
	 * 
	 */
	public List<NovelBasicDo> findByType(int type);

	/**
	 * 返回所有小说列表
	 */
	public List<NovelBasicDo> findAll();

	/**
	 * 返回最新入库的小说
	 */
	public List<NovelBasicDo> findNewest();

	/**
	 * 返回所有完结小说
	 */
	public List<NovelBasicDo> findByFinish();

}
