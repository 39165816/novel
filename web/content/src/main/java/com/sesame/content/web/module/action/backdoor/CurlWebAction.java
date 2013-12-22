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

import com.alibaba.citrus.service.form.FormService;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.mike.novel.content.service.NovelStatusServcie;
import com.mike.novel.dto.vo.NovelStatusVo;
import com.mike.novel.spider.BasicInfoAccess;
import com.mike.novel.spider.biqege.BqgConstants;

/**
 * ����Ԥ����Ʒaction
 * 
 * @author jiashu.wujs
 */
public class CurlWebAction {
	@Resource
	private FormService formService;

	@Resource
	private BasicInfoAccess basicInfoAccess;
	@Resource
	private NovelStatusServcie novelStatusServcie;

	/**
	 * �ύ��ˣ�detailҳ�棩
	 * 
	 * @param runData
	 * @param context
	 * @param nav
	 */
	public void doCommit(TurbineRunData runData, Context context, Navigator nav) {
		// TODO: ��ֹcsrf����

		// ������
		String targetUrl = runData.getParameters().getString("inputUrl");
		if (targetUrl == null || targetUrl.equals("")) {
			context.put("errMsg", "urlΪ��");
			nav.forwardTo("/backdoor/error.vm");
			return;
		}

		if (!targetUrl.startsWith(BqgConstants.BQG_WEBSITE)) {
			context.put("errMsg", "�Ƿ���url: " + targetUrl);
			nav.forwardTo("/backdoor/error.vm");
			return;

		}

		NovelStatusVo novelStatus = basicInfoAccess.executeIndexPage(targetUrl);
		// TODO: add more task info!
		novelStatusServcie.queryNovelStatus(novelStatus.getNovelBasicDo().getId());
		context.put("novelStatus", novelStatus);

		nav.forwardTo("/backdoor/status.vm");
	}

}
