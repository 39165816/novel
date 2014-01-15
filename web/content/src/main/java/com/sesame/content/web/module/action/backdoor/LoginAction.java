package com.sesame.content.web.module.action.backdoor;

/*
 * Copyright (c) 2002-2012 Alibaba Group Holding Limited.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import javax.servlet.http.HttpSession;

import com.alibaba.citrus.service.requestcontext.parser.ParameterParser;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.mike.novel.user.webx.valve.HttpSessionConstant;
import com.mike.novel.user.webx.valve.SessionUserDo;

public class LoginAction {

	public void doLogin(TurbineRunData runData, Context context, Navigator nav,
			HttpSession session) throws Exception {

		String userId = runData.getParameters().getString("userId");
		String password = runData.getParameters().getString("password");

		boolean isCorrect = isCorrectUser(userId, password);

		if (isCorrect) {
			// 在session中创建petstoreUser对象
			SessionUserDo petstoreUser = (SessionUserDo) session
					.getAttribute(HttpSessionConstant.COOKIE_LOGIN_USER_DATA);

			if (petstoreUser == null) {
				petstoreUser = new SessionUserDo(userId);
			}

			session.setAttribute(HttpSessionConstant.COOKIE_LOGIN_USER_DATA,
					petstoreUser);

			// 跳转到return页面
			redirectToReturnPage(nav, "/content/backdoor/manager.htm");
		} else {
			context.put("errors", "用户名或者密码错误");
		}
	}

	public void doLogout(HttpSession session, Navigator nav,
			ParameterParser params) throws Exception {
		// 清除session中的user
		session.removeAttribute(HttpSessionConstant.COOKIE_LOGIN_USER_DATA);

		// 跳转到return页面
		redirectToReturnPage(nav, "");
	}

	private void redirectToReturnPage(Navigator nav, String url) {
		nav.redirectToLocation(url);
	}

	private boolean isCorrectUser(String name, String password) {
		if ("haiquan81".equals(name) && "12#$qwER".equals(password)) {
			return true;
		} else {
			return false;
		}
	}

}
