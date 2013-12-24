package com.mike.novel.spider;

import com.mike.novel.dto.vo.NovelStatusVo;

/**
 * 基本信息爬虫接口
 */
public interface BasicInfoAccess {

	/**
	 * 输入小说url，爬取基本信息和生成任务后，返回小说nid
	 */
	public NovelStatusVo executeIndexPage(String indexPage);

}
