package com.mike.novel.content.service;

import java.util.List;

import com.mike.novel.dto.NovelBasicDo;

/**
 * �ṩ��������
 * 
 */
public interface SearchService {

	/**
	 * ���������ؼ��֣����ؽ��
	 */
	List<NovelBasicDo> search(String keyword);

}
