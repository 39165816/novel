package com.mike.novel.content.service;

import java.util.List;

import com.mike.novel.dto.NovelBasicDo;

/**
 * С˵������Ϣ�ӿ�
 */
public interface NovelBasicService {

	/**
	 * ���������Ϣdo
	 */
	public Integer insert(NovelBasicDo novelBasicDo);

	/**
	 * �����Ҫ��ȥ��url�Ƿ����
	 */
	public NovelBasicDo queryByTargetUrl(String targetUrl);

	/**
	 * ����nid����
	 */
	public NovelBasicDo queryByNid(long nid);

	/**
	 * ����С˵nid�б���������С˵��Ϣ
	 */
	public List<NovelBasicDo> findByNids(List<Integer> nids);

	/**
	 * ����С˵�����ͣ��������е�С˵������С˵���˿��Կ��Ƿ�ҳ���߾�̬����
	 * 
	 */
	public List<NovelBasicDo> findByType(int type);

	/**
	 * ��������С˵�б�
	 */
	public List<NovelBasicDo> findAll();

	/**
	 * ������������С˵
	 */
	public List<NovelBasicDo> findNewest();

	/**
	 * �����������С˵
	 */
	public List<NovelBasicDo> findByFinish();

}
