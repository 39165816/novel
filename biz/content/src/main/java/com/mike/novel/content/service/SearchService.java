package com.mike.novel.content.service;

import java.util.List;

import com.mike.novel.dto.NovelBasicDo;

/**
 * 提供搜索服务
 * 
 */
public interface SearchService {

	/**
	 * 根据搜索关键字，返回结果
	 */
	List<NovelBasicDo> search(String keyword);

}
