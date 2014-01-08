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
			try {
				processTargetUrl(abc[i]);
				log.info("processed " + abc[i] + "\t " + i + "/" + abc.length
						+ " !");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
			} catch (Exception e) {
				log.error("Some Error happens:" + e.getMessage());
				log.error("Sesamegu url:  " + abc[i]);
			}
		}

	}

	private static final String[] abc = { "http://www.biquge.com/0_482/", };

}
