package com.tmall.neo.home.web.module.screen;

import javax.annotation.Resource;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.mike.novel.content.service.NovelBasicService;
import com.mike.novel.content.service.NovelChapterService;
import com.mike.novel.content.service.NovelPageService;
import com.mike.novel.dto.NovelBasicDo;
import com.mike.novel.dto.NovelChapterDo;
import com.mike.novel.dto.NovelPageDo;

/**
 * 显示一个章节的内容
 */
public class P {

	@Resource
	private NovelBasicService novelBasicService;
	@Resource
	private NovelChapterService novelChapterService;
	@Resource
	private NovelPageService novelPageService;

	public void execute(@Param("nid") String nid, @Param("cid") String cid,
			Context context, Navigator nav) {
		// 检测入参
		if (null == nid || nid.equals("")) {
			context.put("errMsg", "nid为空");
			nav.forwardTo("/error.vm");
			return;
		}
		int intNid = Integer.parseInt(nid);

		if (null == cid || cid.equals("")) {
			context.put("errMsg", "cid为空");
			nav.forwardTo("/error.vm");
			return;
		}
		long longCid = Long.parseLong(cid);

		// 基本信息
		NovelBasicDo novelBasicDo = novelBasicService.queryByNid(intNid);
		if (novelBasicDo == null) {
			context.put("errMsg", "小说不存在 nid" + nid);
			nav.forwardTo("/error.vm");
			return;
		}
		context.put("novelBasicDo", novelBasicDo);

		// 章信息
		NovelChapterDo chapter = novelChapterService.queryByCid(longCid);
		if (chapter == null) {
			context.put("errMsg", "章不存在 longCid" + longCid);
			nav.forwardTo("/error.vm");
			return;
		}
		context.put("chapter", chapter);

		// 页信息
		NovelPageDo page = novelPageService.queryByCid(longCid);
		if (page == null) {
			context.put("errMsg", "页不存在 longCid" + longCid);
			nav.forwardTo("/error.vm");
			return;
		}
		context.put("page", page);
	}

}
