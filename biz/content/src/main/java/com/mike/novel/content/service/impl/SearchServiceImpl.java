package com.mike.novel.content.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;

import com.mike.novel.common.util.SiteUtils;
import com.mike.novel.content.service.NovelBasicService;
import com.mike.novel.content.service.SearchService;
import com.mike.novel.dto.NovelBasicDo;

public class SearchServiceImpl implements SearchService, InitializingBean {

	@Resource
	private NovelBasicService novelBasicService;

	// key�� ���� + ���� + ����ƴ�� + ����ƴ���� value Ϊ��Ӧ��С˵do��
	private Map<String, NovelBasicDo> novelMap = new TreeMap<String, NovelBasicDo>();
	// �����
	private static final int LOAD_INTERVAL = 5 * 60 * 1000;

	@Override
	public List<NovelBasicDo> search(String keyword) {
		List<NovelBasicDo> result = new ArrayList<NovelBasicDo>();
		if (keyword == null || keyword.equals("")) {
			return result;
		}
		// ���key������������ݣ�������
		Set<String> keySet = novelMap.keySet();
		for (String key : keySet) {
			if (key.indexOf(keyword) != -1) {
				result.add(novelMap.get(key));
			}
		}

		return result;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		loadFromDb();
		// ������������
		new Thread(new TimeLoadTask()).start();
	}

	private void loadFromDb() {
		Map<String, NovelBasicDo> newMap = new TreeMap<String, NovelBasicDo>();
		List<NovelBasicDo> allNovels = novelBasicService.findAll();
		for (NovelBasicDo oneNovel : allNovels) {
			StringBuffer oneKey = new StringBuffer();
			// ����
			String author = oneNovel.getAuthor();
			oneKey.append(author).append(" ");
			// ����ƴ��
			if (author != null) {
				oneKey.append(SiteUtils.getEname(author)).append(" ");
			}
			// ����
			String title = oneNovel.getTitle();
			oneKey.append(title).append(" ");
			// ����ƴ��
			if (title != null) {
				oneKey.append(SiteUtils.getEname(title));
			}

			newMap.put(oneKey.toString(), oneNovel);
		}

		novelMap = newMap;
	}

	class TimeLoadTask implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(LOAD_INTERVAL);
					loadFromDb();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
