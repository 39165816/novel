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
 * ȫ����ҳ
 * 
 */
public class Q {
	private static final String NAME = "ȫ��С˵";

	@Resource
	private RecommondService recommondService;

	@Resource
	private NovelBasicService novelBasicService;

	@Resource
	private ConfigConstants configConstants;

	public void execute(Context context, Navigator nav) {
		context.put("name", NAME);
		// ���Ƶ���Ƽ���С˵�б�
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

		// �������С˵���б�
		List<NovelBasicDo> alldos = novelBasicService.findByFinish();
		context.put("alldos", alldos);
	}

	private void conventPicPath(List<NovelBasicDo> recommend) {
		if (recommend != null) {
			for (NovelBasicDo one : recommend) {
				// ת��pic·��
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
					// ת��pic·��
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
