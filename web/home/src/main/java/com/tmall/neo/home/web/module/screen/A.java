package com.tmall.neo.home.web.module.screen;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.mike.novel.content.service.NovelBasicService;
import com.mike.novel.dto.NovelBasicDo;
import com.mike.novel.util.ConfigConstants;

/**
 * ����С˵
 */
public class A {

	@Resource
	private NovelBasicService novelBasicService;

	@Resource
	private ConfigConstants configConstants;

	public void execute(Context context, Navigator nav) {
		context.put("name", "ȫ��С˵");
		// �������С˵���б�
		List<NovelBasicDo> alldos = novelBasicService.findAll();
		context.put("alldos", alldos);
	}

}
