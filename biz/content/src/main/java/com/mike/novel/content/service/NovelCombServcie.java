package com.mike.novel.content.service;

import java.util.List;

import com.mike.novel.dto.HottestDo;
import com.mike.novel.dto.NovelVolumDo;
import com.mike.novel.dto.vo.HottestVo;
import com.mike.novel.dto.vo.NovelStatusVo;

/**
 * 小说的复合操作
 * 
 */
public interface NovelCombServcie {
	/**
	 * 查询小说的信息（包括任务执行状态）
	 */
	NovelStatusVo queryNovelStatus(int nid);

	/**
	 * 保存卷、章、任务信息
	 */
	void saveVolumAndTask(List<NovelVolumDo> volums);

	/**
	 * 执行一个小说的爬行
	 */
	void processTask(int nid);

	/**
	 * 查询小说的信息（不包括任务）
	 */
	NovelStatusVo queryNovelInfo(int nid);

	/**
	 * 根据小说的名字，添加小说的其他信息
	 */
	List<HottestVo> getAddtionalInfo(List<HottestDo> dos);
}
