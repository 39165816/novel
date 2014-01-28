package com.mike.novel.content.service;

import java.util.List;

public interface RecommondService {

	/**
	 * 根据小说的类型，返回该推荐类目的nid List
	 */
	List<Integer> queryByType(int type);

	List<Integer> queryByTypeDirect(int type);

	/**
	 * 根据推荐频道id，删除这个频道的所有信息
	 */
	void deleteByTypeDirect(int pid);

	/**
	 * 保存频道的推荐
	 */
	void save(int intPid, List<Integer> nids);

	/**
	 * 把所有的频道推荐随一下
	 */
	void random();
}
