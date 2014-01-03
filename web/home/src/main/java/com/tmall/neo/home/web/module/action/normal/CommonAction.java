package com.tmall.neo.home.web.module.action.normal;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.mike.novel.content.service.SearchService;
import com.mike.novel.dto.NovelBasicDo;

public class CommonAction {
	@Resource
	private SearchService searchService;

	/**
	 * �ύ��ˣ�detailҳ�棩
	 * 
	 * @param runData
	 * @param context
	 * @param nav
	 */
	public void doCommit(TurbineRunData runData, Context context, Navigator nav) {
		// ������
		String keyword = runData.getParameters().getString("keyword");
		if (keyword == null || keyword.trim().equals("")) {
			context.put("errMsg", "urlΪ��");
			nav.forwardTo("/backdoor/error.vm");
			return;
		}

		List<NovelBasicDo> novelDos = searchService.search(keyword);
		context.put("novelDos", novelDos);
		context.put("keyword", keyword);
		nav.forwardTo("/search.vm");
	}
}
