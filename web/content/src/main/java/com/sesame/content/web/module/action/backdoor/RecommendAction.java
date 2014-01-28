package com.sesame.content.web.module.action.backdoor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.mike.novel.content.service.RecommondService;

public class RecommendAction {

	@Resource
	private RecommondService recommondService;

	public void doCommit(TurbineRunData runData, Context context, Navigator nav) {
		// 检查config参数
		String config = runData.getParameters().getString("config");
		if (config == null || config.equals("")) {
			context.put("errMsg", "config为空");
			nav.forwardTo("/backdoor/error.vm");
			return;
		}
		// 检查pid参数
		String pid = runData.getParameters().getString("pid");
		int intPid = Integer.parseInt(pid.trim());

		// 处理输入参数
		List<Integer> nids = new ArrayList<Integer>();
		String[] split = config.split(",");
		for (String one : split) {
			String process = one;
			if (one.contains("(")) {
				process = one.substring(0, one.indexOf("("));
			}
			nids.add(Integer.parseInt(process.trim()));
		}

		// 检查输入的数量是否足够
		int checkMixSize = 0;
		if (intPid == 201 || intPid == 301) {// 首页推荐和全本推荐至少6
			checkMixSize = 6;
		} else {// 分类推荐至少9
			checkMixSize = 9;
		}
		if (nids.size() < checkMixSize) {
			context.put("errMsg", "推荐的书本太少，至少需要" + checkMixSize + "本");
			nav.forwardTo("/backdoor/error.vm");
			return;
		}

		// 删除原有的并存入新的
		recommondService.deleteByTypeDirect(intPid);

		recommondService.save(intPid, nids);

		nav.forwardTo("/backdoor/recommend.vm");
	}
}
