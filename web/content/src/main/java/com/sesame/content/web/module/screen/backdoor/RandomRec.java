package com.sesame.content.web.module.screen.backdoor;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.mike.novel.content.service.RecommondService;

public class RandomRec {
	@Resource
	private RecommondService recommondService;

	public void execute(HttpSession httpSession, Context context, Navigator nav) {
		recommondService.random();
		nav.redirectToLocation("/content/backdoor/recommend.htm");
	}
}
