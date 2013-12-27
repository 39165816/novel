package com.mike.novel.content.service;

import java.util.List;

public interface RecommondService {

	/**
	 * 根据小说的类型，返回该推荐类目的nid List
	 */
	List<Integer> queryByType(int type);
}
