package com.mike.novel.spider;

import com.mike.novel.dto.vo.NovelStatusVo;

/**
 * ������Ϣ����ӿ�
 */
public interface BasicInfoAccess {

	/**
	 * ����С˵url����ȡ������Ϣ����������󣬷���С˵nid
	 */
	public NovelStatusVo executeIndexPage(String indexPage);

}
