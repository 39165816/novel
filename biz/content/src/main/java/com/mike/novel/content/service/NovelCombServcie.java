package com.mike.novel.content.service;

import java.util.List;

import com.mike.novel.dto.HottestDo;
import com.mike.novel.dto.NovelVolumDo;
import com.mike.novel.dto.vo.HottestVo;
import com.mike.novel.dto.vo.NovelStatusVo;

/**
 * С˵�ĸ��ϲ���
 * 
 */
public interface NovelCombServcie {
	/**
	 * ��ѯС˵����Ϣ����������ִ��״̬��
	 */
	NovelStatusVo queryNovelStatus(int nid);

	/**
	 * ������¡�������Ϣ
	 */
	void saveVolumAndTask(List<NovelVolumDo> volums);

	/**
	 * ִ��һ��С˵������
	 */
	void processTask(int nid);

	/**
	 * ��ѯС˵����Ϣ������������
	 */
	NovelStatusVo queryNovelInfo(int nid);

	/**
	 * ����С˵�����֣����С˵��������Ϣ
	 */
	List<HottestVo> getAddtionalInfo(List<HottestDo> dos);
}
