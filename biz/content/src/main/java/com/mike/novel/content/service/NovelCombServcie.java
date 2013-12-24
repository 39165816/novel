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
	 * 根据小说nid，返回小说的状态：包括基本信息，爬取的状态等
	 */
	NovelStatusVo queryNovelStatus(long nid);

	/**
	 * 保存卷、章、任务信息
	 */
	void saveVolumAndTask(List<NovelVolumDo> volums);

}
