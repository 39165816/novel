package com.mike.novel.content.service;

import com.mike.novel.dto.vo.NovelStatusVo;

public interface NovelStatusServcie {
    /**
     * ����С˵nid������С˵��״̬������������Ϣ����ȡ��״̬��
     */
	NovelStatusVo queryNovelStatus(long nid);
    
}
