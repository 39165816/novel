package com.mike.novel.content.service;

import java.util.List;

import com.mike.novel.dto.NovelVolumDo;
import com.mike.novel.dto.vo.NovelStatusVo;

/**
 * С˵�ĸ��ϲ���
 * 
 */
public interface NovelCombServcie {
	/**
	 * ����С˵nid������С˵��״̬������������Ϣ����ȡ��״̬��
	 */
	NovelStatusVo queryNovelStatus(long nid);

	/**
	 * ������¡�������Ϣ
	 */
	void saveVolumAndTask(List<NovelVolumDo> volums);

}
