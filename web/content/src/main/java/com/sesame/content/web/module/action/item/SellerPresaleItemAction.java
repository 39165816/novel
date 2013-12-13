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
package com.sesame.content.web.module.action.item;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.citrus.service.form.FormService;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;

/**
 * ����Ԥ����Ʒaction
 *
 * @author jiashu.wujs
 */
public class SellerPresaleItemAction {
	private final static Logger logger = LoggerFactory.getLogger(SellerPresaleItemAction.class);

	@Resource
	private FormService formService;

	/**
	 * �ύ��ˣ�detailҳ�棩
	 *
	 * @param runData
	 * @param context
	 * @param nav
	 */
	public void doCommitById(TurbineRunData runData, Context context, Navigator nav) {
//		// ��ֹcsrf����
//		if (!CsrfTokenForWeb3.check(runData.getRequest())) {
//			context.put("errMsg", "�Ƿ��ύ");
//			nav.forwardTo("/item/error.vm");
//			return;
//		}
//
//		long presaleId = runData.getParameters().getLong("presaleId");
//        long itemId = runData.getParameters().getLong("itemId");
//		if (presaleId == 0) {
//			context.put("errMsg", "ϵͳ��������Ԥ����ƷID������");
//			nav.forwardTo("/item/error.vm");
//			return;
//		}
//
//		Result<Boolean> result = sellerPresaleItemManager.applyPresaleItem(presaleId);
//		if (!result.isSuccess()) {
//			logger.error("Commit SellerPresaleItem Error, msg=" + result.getErrMsg());
//            UlcLogUtil.operLog(null, UlcLogUtil.OPER_DO_APPLY, String.valueOf(itemId), "�ύ���ʧ��!");
//			context.put("errMsg", "�ύ���ʧ��");
//			nav.forwardTo("/item/error.vm");
//			return;
//		}else{
//            UlcLogUtil.operLog(null, UlcLogUtil.OPER_DO_APPLY, String.valueOf(itemId), "�ύ��˳ɹ�!");
//		}
//
//		nav.forwardTo("/item/success.vm");
	}



}
