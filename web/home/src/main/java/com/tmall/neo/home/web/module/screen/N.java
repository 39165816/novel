package com.tmall.neo.home.web.module.screen;

import javax.annotation.Resource;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.mike.novel.content.service.NovelCombServcie;
import com.mike.novel.dto.vo.NovelStatusVo;

/**
 * 一部小说的首页
 */
public class N {

	@Resource
	private NovelCombServcie novelCombService;

	public void execute(@Param("nid") String nid, Context context, Navigator nav) {
		// 检测入参
		if (null == nid || nid.equals("")) {
			context.put("errMsg", "nid为空");
			nav.forwardTo("/error.vm");
			return;
		}
		int intNid = Integer.parseInt(nid);

		// 检测是否存在
		NovelStatusVo novelInfo = novelCombService.queryNovelInfo(intNid);
		if (!novelInfo.isExists()) {
			context.put("errMsg", "小说不存在 nid" + nid);
			nav.forwardTo("/error.vm");
			return;
		}
		context.put("novelStatus", novelInfo);
	}
}
