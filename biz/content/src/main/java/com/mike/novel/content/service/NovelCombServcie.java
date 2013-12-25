package com.mike.novel.content.service;

import java.util.List;

import com.mike.novel.dto.NovelVolumDo;
import com.mike.novel.dto.vo.NovelStatusVo;

/**
 * 小说的复合操作
 * 
 */
public interface NovelCombServcie {
	/**
	 * 查询小说的信息（包括任务执行状态）
	 */
	NovelStatusVo queryNovelStatus(long nid);

	/**
	 * 保存卷、章、任务信息
	 */
	void saveVolumAndTask(List<NovelVolumDo> volums);

	/**
	 * 执行一个小说的爬行
	 */
	void processTask(long nid);

	/**
	 * 查询小说的信息（不包括任务）
	 */
	NovelStatusVo queryNovelInfo(long nid);
}
