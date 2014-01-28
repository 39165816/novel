package com.mike.novel.content.service;

import java.util.List;

public interface RecommondService {

	/**
	 * ����С˵�����ͣ����ظ��Ƽ���Ŀ��nid List
	 */
	List<Integer> queryByType(int type);

	List<Integer> queryByTypeDirect(int type);

	/**
	 * �����Ƽ�Ƶ��id��ɾ�����Ƶ����������Ϣ
	 */
	void deleteByTypeDirect(int pid);

	/**
	 * ����Ƶ�����Ƽ�
	 */
	void save(int intPid, List<Integer> nids);

	/**
	 * �����е�Ƶ���Ƽ���һ��
	 */
	void random();
}
