package com.tmall.neo.home.web.module.screen;

import java.util.List;
import java.util.TreeMap;

import javax.annotation.Resource;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.mike.novel.common.util.SiteConstants;
import com.mike.novel.content.service.NovelBasicService;
import com.mike.novel.content.service.RecommondService;
import com.mike.novel.dto.NovelBasicDo;
import com.mike.novel.util.ConfigConstants;
import com.mike.novel.util.NovelType;

/**
 * 站点首页
 */
public class Index {

	@Resource
	private RecommondService recommondService;

	@Resource
	private NovelBasicService novelBasicService;

	@Resource
	private ConfigConstants configConstants;

	public void execute(Context context, Navigator nav) {
		// 获得首页推荐的小说列表 6篇
		Integer pid = configConstants.getPid(SiteConstants.INDEX_RECOMMEND_ID);
		List<Integer> recommondIds = recommondService.queryByTypeDirect(pid);
		List<NovelBasicDo> recommend = null;
		if (recommondIds != null && !recommondIds.isEmpty()) {
			recommend = novelBasicService.findByNids(recommondIds);
			conventPicPath(recommend);
			conventIntroduce(recommend, 80);
			limitSize(recommend, 6);
		}
		context.put("strongRecommend", recommend);

		TreeMap<String, List<NovelBasicDo>> pidRecommend = new TreeMap<String, List<NovelBasicDo>>();

		// 获取每个类型的推荐列表（所有分类）
		for (NovelType one : NovelType.values()) {
			recommondIds = recommondService.queryByType(one.type);
			List<NovelBasicDo> pidrecommend = null;
			if (recommondIds != null && !recommondIds.isEmpty()) {
				pidrecommend = novelBasicService.findByNids(recommondIds);
				conventPicPath(pidrecommend);
				conventIntroduce(pidrecommend, 40);
				limitSize(pidrecommend, 9);
			}
			pidRecommend.put(one.name, pidrecommend);
		}
		context.put("pidRecommend", pidRecommend);

		// 获得最新入库的50本小说列表
		List<NovelBasicDo> newest = novelBasicService.findNewest();
		context.put("newestDos", newest);

	}

	private void conventPicPath(List<NovelBasicDo> recommend) {
		if (recommend != null) {
			for (NovelBasicDo one : recommend) {
				// 转化pic路径
				one.setPicturePath(configConstants.getPictureAccessPath() + one.getPicturePath());
			}
		}
	}

	private void conventIntroduce(List<NovelBasicDo> recommend, int size) {
		if (recommend != null) {
			for (NovelBasicDo one : recommend) {
				String intro = one.getIntroduce();

				if (intro.length() > size)
					// 转化pic路径
					one.setIntroduce(intro.substring(0, size - 3) + "...");
			}
		}
	}

	private void limitSize(List<NovelBasicDo> recommend, int size) {
		if (recommend != null) {
			if (recommend.size() > size)
				for (int i = recommend.size() - 1; i >= size; i--)
					recommend.remove(i);
		}
	}
}
