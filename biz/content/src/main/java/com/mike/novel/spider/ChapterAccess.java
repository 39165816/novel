package com.mike.novel.spider;


/**
 * 类 ChapterAccess 的实现描述：章的爬虫接口
 * 
 * @author 听雷 2013年12月19日下午3:31:34
 */
public interface ChapterAccess {

	/**
	 * 输入章节的URl，返回章节的内容
	 */
	public String accessChapter(String url);

}
