package com.tmall.neo.home.web.module.screen;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.mike.novel.content.service.NovelBasicService;
import com.mike.novel.content.service.RecommondService;
import com.mike.novel.dto.NovelBasicDo;
import com.mike.novel.util.ConfigConstants;
import com.mike.novel.util.NovelType;

/**
 * �������ҳ
 */
public class L {

	@Resource
	private RecommondService recommondService;

	@Resource
	private NovelBasicService novelBasicService;

	@Resource
	private ConfigConstants configConstants;

	public void execute(@Param("type") String type, Context context,
			Navigator nav) {
		// ������
		if (null == type || type.equals("")) {
			context.put("errMsg", "typeΪ��");
			nav.forwardTo("/error.vm");
			return;
		}
		// ����Ƿ����
		int intNid = Integer.parseInt(type);
		String name = NovelType.getName(intNid);
		if (name == null) {
			context.put("errMsg", "type������ type" + type);
			nav.forwardTo("/error.vm");
			return;
		}
		context.put("name", name);

		// ���Ƶ���Ƽ���С˵�б�
		List<Integer> nids = recommondService.queryByType(intNid);
		List<NovelBasicDo> recommend = null;
		if (nids != null && !nids.isEmpty()) {
			recommend = novelBasicService.findByNids(nids);
			conventPicPath(recommend);
			conventIntroduce(recommend);
			limitSize(recommend);
		}
		context.put("recommend", recommend);

		// �������С˵���б�
		List<NovelBasicDo> alldos = novelBasicService.findByType(intNid);
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

				if (intro.length() > 88)
					// ת��pic·��
					one.setIntroduce(intro.substring(0, 88) + "...");
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
