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
	 * ��ѯС˵����Ϣ����������ִ��״̬��
	 */
	NovelStatusVo queryNovelStatus(long nid);

	/**
	 * ������¡�������Ϣ
	 */
	void saveVolumAndTask(List<NovelVolumDo> volums);

	/**
	 * ִ��һ��С˵������
	 */
	void processTask(long nid);

	/**
	 * ��ѯС˵����Ϣ������������
	 */
	NovelStatusVo queryNovelInfo(long nid);
}
