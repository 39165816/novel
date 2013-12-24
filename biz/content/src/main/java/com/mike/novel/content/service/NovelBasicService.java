package com.mike.novel.content.service;

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
}
