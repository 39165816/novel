package com.mike.novel.spider.biqege;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.Resource;

import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;
import org.webharvest.runtime.variables.Variable;

import com.mike.novel.spider.ChapterAccess;
import com.mike.novel.util.BqgConstants;
import com.mike.novel.util.ConfigConstants;

/**
 * �� BqgChapterAccess ��ʵ��������biqugeҳ������
 * 
 * @author ���� 2013��12��19������3:36:38
 */
public class BqgChapterAccess implements ChapterAccess {
	@Resource
	private ConfigConstants configConstants;

	@Override
	public String accessChapter(String detailUrl) {
		ScraperConfiguration pageConfig;
		try {
			pageConfig = new ScraperConfiguration(
					configConstants.getProjectBaseHome()
							+ BqgConstants.HARVEST_PAGE_CONFIG);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("�Ҳ��� Page harvest�����ļ�·��.", e);
		}
		Scraper pageScraper = new Scraper(pageConfig,
				configConstants.getProjectBaseHome()
						+ BqgConstants.HARVEST_WORKING_DIR);
		pageScraper.getContext().setVar("chapter", detailUrl);
		pageScraper.execute();
		Variable content = (Variable) pageScraper.getContext().get("content");
		// �㲻�����룬&nbsp;&nbsp;�ᱻ�滻�ɠ����������滻����
		StringBuffer curlResult = new StringBuffer(new String(
				content.toBinary("iso-8859-1")));
		StringBuffer finalResult = new StringBuffer();
		for (int i = 0; i < curlResult.length(); i++) {
			char charAt = curlResult.charAt(i);
			if (charAt == '��') {
				finalResult.append("  ");
			} else {
				finalResult.append(charAt);
			}
		}
		return finalResult.toString();
	}

	public static void main(String[] args) throws IOException {
		System.out.println(new BqgChapterAccess()
				.accessChapter("http://www.biquge.com/0_494/205922.html"));
	}

}
