package com.sesame.content.web.module.screen.backdoor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.mike.novel.content.service.NovelBasicService;
import com.mike.novel.content.service.RecommondService;
import com.mike.novel.dto.NovelBasicDo;

public class Recommend {

	@Resource
	private RecommondService recommondService;
	@Resource
	private NovelBasicService novelBasicService;

	private static final int[] RECOMMEND_PID = new int[] { 101, 102, 103, 104,
			105, 106, 201, 301 };

	public void execute(@Param("nid") String nid, HttpSession httpSession,
			Context context, Navigator nav) {
		// 结果map, key 为pid, value为nid(标题)的字符
		Map<Integer, String> contentMap = new HashMap<Integer, String>();

		// 组装当前的数据
		for (int one : RECOMMEND_PID) {
			List<Integer> nids = recommondService.queryByTypeDirect(one);
			List<NovelBasicDo> dos = novelBasicService.findByNids(nids);
			StringBuffer sb = new StringBuffer();
			for (NovelBasicDo oneNovel : dos) {
				sb.append(oneNovel.getNid()).append("(")
						.append(oneNovel.getTitle()).append(")").append(",");
			}
			contentMap.put(one, sb.toString());
		}

		context.put("contentMap", contentMap);
	}
}
