package com.tmall.neo.home.web.module.screen;

import javax.annotation.Resource;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.mike.novel.content.service.NovelCombServcie;
import com.mike.novel.dto.vo.NovelStatusVo;

/**
 * һ��С˵����ҳ
 */
public class N {

	@Resource
	private NovelCombServcie novelCombService;

	public void execute(@Param("nid") String nid, Context context, Navigator nav) {
		// ������
		if (null == nid || nid.equals("")) {
			context.put("errMsg", "nidΪ��");
			nav.forwardTo("/error.vm");
			return;
		}
		int intNid = Integer.parseInt(nid);

		// ����Ƿ����
		NovelStatusVo novelInfo = novelCombService.queryNovelInfo(intNid);
		if (!novelInfo.isExists()) {
			context.put("errMsg", "С˵������ nid" + nid);
			nav.forwardTo("/error.vm");
			return;
		}
		context.put("novelStatus", novelInfo);
	}
}
