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
		// ���config����
		String config = runData.getParameters().getString("config");
		if (config == null || config.equals("")) {
			context.put("errMsg", "configΪ��");
			nav.forwardTo("/backdoor/error.vm");
			return;
		}
		// ���pid����
		String pid = runData.getParameters().getString("pid");
		int intPid = Integer.parseInt(pid.trim());

		// �����������
		List<Integer> nids = new ArrayList<Integer>();
		String[] split = config.split(",");
		for (String one : split) {
			String process = one;
			if (one.contains("(")) {
				process = one.substring(0, one.indexOf("("));
			}
			nids.add(Integer.parseInt(process.trim()));
		}

		// �������������Ƿ��㹻
		int checkMixSize = 0;
		if (intPid == 201 || intPid == 301) {// ��ҳ�Ƽ���ȫ���Ƽ�����6
			checkMixSize = 6;
		} else {// �����Ƽ�����9
			checkMixSize = 9;
		}
		if (nids.size() < checkMixSize) {
			context.put("errMsg", "�Ƽ����鱾̫�٣�������Ҫ" + checkMixSize + "��");
			nav.forwardTo("/backdoor/error.vm");
			return;
		}

		// ɾ��ԭ�еĲ������µ�
		recommondService.deleteByTypeDirect(intPid);

		recommondService.save(intPid, nids);

		nav.forwardTo("/backdoor/recommend.vm");
	}
}
