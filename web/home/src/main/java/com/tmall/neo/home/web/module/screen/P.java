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
 * ��ʾһ���½ڵ�����
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
		// ������
		if (null == nid || nid.equals("")) {
			context.put("errMsg", "nidΪ��");
			nav.forwardTo("/error.vm");
			return;
		}
		int intNid = Integer.parseInt(nid);

		if (null == cid || cid.equals("")) {
			context.put("errMsg", "cidΪ��");
			nav.forwardTo("/error.vm");
			return;
		}
		long longCid = Long.parseLong(cid);

		// ������Ϣ
		NovelBasicDo novelBasicDo = novelBasicService.queryByNid(intNid);
		if (novelBasicDo == null) {
			context.put("errMsg", "С˵������ nid" + nid);
			nav.forwardTo("/error.vm");
			return;
		}
		context.put("novelBasicDo", novelBasicDo);

		// ����Ϣ
		NovelChapterDo chapter = novelChapterService.queryByCid(longCid);
		if (chapter == null) {
			context.put("errMsg", "�²����� longCid" + longCid);
			nav.forwardTo("/error.vm");
			return;
		}
		context.put("chapter", chapter);

		// ҳ��Ϣ
		NovelPageDo page = novelPageService.queryByCid(longCid);
		if (page == null) {
			context.put("errMsg", "ҳ������ longCid" + longCid);
			nav.forwardTo("/error.vm");
			return;
		}
		context.put("page", page);
	}

}
