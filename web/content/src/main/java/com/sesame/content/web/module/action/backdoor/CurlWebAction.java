/**
 * Project: neoportal-web-presale
 *
 * File Created at 2013-8-2
 * $Id$
 *
 * Copyright 2008 Alibaba.com Croporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.sesame.content.web.module.action.backdoor;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.citrus.service.form.FormService;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.mike.novel.content.service.NovelBasicService;
import com.mike.novel.content.service.NovelCombServcie;
import com.mike.novel.dto.NovelBasicDo;
import com.mike.novel.dto.vo.NovelStatusVo;
import com.mike.novel.spider.BasicInfoAccess;
import com.mike.novel.util.BqgConstants;

/**
 */
public class CurlWebAction {
	@Resource
	private FormService formService;

	@Resource
	private BasicInfoAccess basicInfoAccess;
	@Resource
	private NovelCombServcie novelCombServcie;

	@Resource
	private NovelBasicService novelBasicService;

	private final static Logger log = LoggerFactory
			.getLogger(CurlWebAction.class);

	/**
	 * 提交审核（detail页面）
	 * 
	 * @param runData
	 * @param context
	 * @param nav
	 */
	public void doCommit(TurbineRunData runData, Context context, Navigator nav) {
		// TODO: 防止csrf攻击

		// 检查参数
		String targetUrl = runData.getParameters().getString("inputUrl");
		if (targetUrl == null || targetUrl.equals("")) {
			context.put("errMsg", "url为空");
			nav.forwardTo("/backdoor/error.vm");
			return;
		}

		// 特殊的处理
		if (targetUrl.equals("spring12345")) {
			process();
			context.put("errMsg", "特殊命令已受到！");
			nav.forwardTo("/backdoor/error.vm");
			return;
		}

		if (!targetUrl.startsWith(BqgConstants.BQG_WEBSITE)) {
			context.put("errMsg", "非法的url: " + targetUrl);
			nav.forwardTo("/backdoor/error.vm");
			return;

		}

		NovelStatusVo queryNovelStatus = processTargetUrl(targetUrl);

		context.put("novelStatus", queryNovelStatus);
		nav.forwardTo("/backdoor/status.vm");
	}

	private NovelStatusVo processTargetUrl(String targetUrl) {
		NovelStatusVo novelStatus;
		NovelBasicDo novelBasicDo = novelBasicService
				.queryByTargetUrl(targetUrl);
		if (novelBasicDo == null) {
			novelStatus = basicInfoAccess.executeIndexPage(targetUrl);
		} else {
			// 查询状态
			novelStatus = new NovelStatusVo();
			novelStatus.setNovelBasicDo(novelBasicDo);
		}
		int nid = novelStatus.getNovelBasicDo().getNid();
		NovelStatusVo queryNovelStatus = novelCombServcie.queryNovelStatus(nid);
		return queryNovelStatus;
	}

	private void process() {
		for (int i = 0; i < abc.length; i++) {
			processTargetUrl(abc[i]);
			log.info("processed " + abc[i] + "\t " + i + "/" + abc.length
					+ " !");
		}

	}

	private static final String[] abc = {  "http://www.biquge.com/4_4748/",
			"http://www.biquge.com/4_4767/", "http://www.biquge.com/4_4741/",
			"http://www.biquge.com/4_4938/", "http://www.biquge.com/4_4910/",
			"http://www.biquge.com/5_5227/", "http://www.biquge.com/4_4889/",
			"http://www.biquge.com/5_5028/", "http://www.biquge.com/5_5034/",
			"http://www.biquge.com/4_4888/", "http://www.biquge.com/4_4806/",
			"http://www.biquge.com/4_4913/", "http://www.biquge.com/5_5168/",
			"http://www.biquge.com/4_4768/", "http://www.biquge.com/5_5026/",
			"http://www.biquge.com/4_4894/", "http://www.biquge.com/5_5228/",
			"http://www.biquge.com/5_5195/", "http://www.biquge.com/0_8/",
			"http://www.biquge.com/0_377/", "http://www.biquge.com/0_106/",
			"http://www.biquge.com/0_298/", "http://www.biquge.com/0_300/",
			"http://www.biquge.com/0_350/", "http://www.biquge.com/0_89/",
			"http://www.biquge.com/0_135/", "http://www.biquge.com/0_51/",
			"http://www.biquge.com/0_186/", "http://www.biquge.com/0_250/",
			"http://www.biquge.com/0_367/", "http://www.biquge.com/0_253/",
			"http://www.biquge.com/0_237/", "http://www.biquge.com/0_369/",
			"http://www.biquge.com/0_493/", "http://www.biquge.com/0_329/",
			"http://www.biquge.com/0_65/", "http://www.biquge.com/4_4752/",
			"http://www.biquge.com/4_4561/", "http://www.biquge.com/4_4795/",
			"http://www.biquge.com/5_5233/", "http://www.biquge.com/4_4559/",
			"http://www.biquge.com/4_4678/", "http://www.biquge.com/4_4917/",
			"http://www.biquge.com/4_4558/", "http://www.biquge.com/5_5092/",
			"http://www.biquge.com/4_4686/", "http://www.biquge.com/4_4912/",
			"http://www.biquge.com/4_4560/", "http://www.biquge.com/4_4977/",
			"http://www.biquge.com/4_4937/", "http://www.biquge.com/4_4538/",
			"http://www.biquge.com/5_5122/", "http://www.biquge.com/4_4658/",
			"http://www.biquge.com/5_5093/", "http://www.biquge.com/5_5050/",
			"http://www.biquge.com/5_5210/", "http://www.biquge.com/5_5090/",
			"http://www.biquge.com/5_5175/", "http://www.biquge.com/4_4822/",
			"http://www.biquge.com/4_4733/", "http://www.biquge.com/4_4921/",
			"http://www.biquge.com/5_5669/", "http://www.biquge.com/4_4487/",
			"http://www.biquge.com/5_5005/", "http://www.biquge.com/4_4500/",
			"http://www.biquge.com/5_5171/", "http://www.biquge.com/4_4953/",
			"http://www.biquge.com/4_4694/", "http://www.biquge.com/4_4448/",
			"http://www.biquge.com/5_5039/", "http://www.biquge.com/4_4693/",
			"http://www.biquge.com/5_5132/", "http://www.biquge.com/4_4959/",
			"http://www.biquge.com/5_5043/", "http://www.biquge.com/4_4805/",
			"http://www.biquge.com/5_5164/", "http://www.biquge.com/4_4726/",
			"http://www.biquge.com/5_5011/", "http://www.biquge.com/5_5172/",
			"http://www.biquge.com/4_4823/", "http://www.biquge.com/4_4955/",
			"http://www.biquge.com/4_4931/", "http://www.biquge.com/4_4782/",
			"http://www.biquge.com/4_4897/", "http://www.biquge.com/5_5116/",
			"http://www.biquge.com/4_4927/", "http://www.biquge.com/4_4911/",
			"http://www.biquge.com/5_5035/" };

}
