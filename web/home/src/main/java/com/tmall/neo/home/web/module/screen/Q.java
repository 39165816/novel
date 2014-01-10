package com.tmall.neo.home.web.module.screen;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.mike.novel.common.util.SiteConstants;
import com.mike.novel.content.service.NovelBasicService;
import com.mike.novel.content.service.RecommondService;
import com.mike.novel.dto.NovelBasicDo;
import com.mike.novel.util.ConfigConstants;

/**
 * 全本首页
 * 
 */
public class Q {
	private static final String NAME = "全本小说";

	@Resource
	private RecommondService recommondService;

	@Resource
	private NovelBasicService novelBasicService;

	@Resource
	private ConfigConstants configConstants;

	public void execute(Context context, Navigator nav) {
		context.put("name", NAME);
		// 获得频道推荐的小说列表
		Integer pid = configConstants.getPid(SiteConstants.QUAN_RECOMMEND_ID);
		List<Integer> nids = recommondService.queryByTypeDirect(pid);
		List<NovelBasicDo> recommend = null;
		if (nids != null && !nids.isEmpty()) {
			recommend = novelBasicService.findByNids(nids);
			conventPicPath(recommend);
			conventIntroduce(recommend);
			limitSize(recommend);
		}
		context.put("recommend", recommend);

		// 获得所有小说的列表
		List<NovelBasicDo> alldos = novelBasicService.findByFinish();
		context.put("alldos", alldos);
	}

	private void conventPicPath(List<NovelBasicDo> recommend) {
		if (recommend != null) {
			for (NovelBasicDo one : recommend) {
				// 转化pic路径
				one.setPicturePath(configConstants.getPictureAccessPath()
						+ one.getPicturePath());
			}
		}
	}

	private void conventIntroduce(List<NovelBasicDo> recommend) {
		if (recommend != null) {
			for (NovelBasicDo one : recommend) {
				String intro = one.getIntroduce();

				if (intro.length() > 80)
					// 转化pic路径
					one.setIntroduce(intro.substring(0, 77) + "...");
			}
		}
	}

	private void limitSize(List<NovelBasicDo> recommend) {
		if (recommend != null) {
			if (recommend.size() > 6)
				for (int i = recommend.size() - 1; i >= 6; i--)
					recommend.remove(i);
		}
	}
}
