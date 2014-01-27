package com.sesame.content.web.module.action.backdoor;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.mike.novel.content.service.HottestService;
import com.mike.novel.content.service.NovelCombServcie;
import com.mike.novel.dto.HottestDo;
import com.mike.novel.dto.vo.HottestVo;

public class HottestAction {

	@Resource
	private HottestService hottestService;
	@Resource
	private NovelCombServcie novelCombServcie;

	/**
	 * ������������ڣ�չʾ�ȵ�����
	 */
	@SuppressWarnings("deprecation")
	public void doCommit(TurbineRunData runData, Context context, Navigator nav) {
		// ������
		String date = runData.getParameters().getString("date");
		if (date == null || date.equals("")) {
			context.put("errMsg", "dateΪ��");
			nav.forwardTo("/backdoor/error.vm");
			return;
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
		java.util.Date parse;
		try {
			parse = simpleDateFormat.parse(date);

		} catch (ParseException e) {
			context.put("errMsg", "date��ʽ����");
			nav.forwardTo("/backdoor/error.vm");
			return;
		}

		Date statisticTime = new Date(parse.getYear(), parse.getMonth(),
				parse.getDate());
		List<HottestDo> dos = hottestService.findByTime(statisticTime);
		// ����Ƿ���ᡢ��վ���Ƿ����
		List<HottestVo> addtionalInfo = novelCombServcie.getAddtionalInfo(dos);
		Collections.sort(addtionalInfo);

		context.put("hottestVos", addtionalInfo);
		nav.forwardTo("/backdoor/hottest.vm");
	}
}
