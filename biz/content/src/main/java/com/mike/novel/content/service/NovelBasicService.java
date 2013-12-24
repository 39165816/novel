package com.mike.novel.content.service;

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
}
