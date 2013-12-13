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
package com.sesame.content.web.module.screen.item;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.Param;

/**
 * 卖家编辑预售商品页面
 * 
 * @author jiashu.wujs
 */
public class EditSellerPresaleItem {
	private final static Logger logger = LoggerFactory.getLogger(EditSellerPresaleItem.class);

	public void execute(@Param("itemId") long itemId, HttpSession httpSession, Context context, Navigator nav) {

	}


}
