package com.mike.novel.content.service;

import com.mike.novel.dto.vo.NovelStatusVo;

public interface NovelStatusServcie {
    /**
     * 根据小说nid，返回小说的状态：包括基本信息，爬取的状态等
     */
	NovelStatusVo queryNovelStatus(long nid);
    
}
