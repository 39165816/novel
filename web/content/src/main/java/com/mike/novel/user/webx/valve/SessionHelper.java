package com.mike.novel.user.webx.valve;

import javax.servlet.http.HttpSession;

public class SessionHelper {
	public static SessionUserDo getSessionUserDo(HttpSession session) {
		if (session == null) {
			throw new IllegalArgumentException(
					"SessionHelper Exception : cann't get user without session");
		}
		SessionUserDo user = (SessionUserDo) session
				.getAttribute(HttpSessionConstant.COOKIE_LOGIN_USER_DATA);
		return user;
	}

	public static void setSessionUserDo(HttpSession session,
			SessionUserDo sessionUser) {
		if (session == null) {
			throw new IllegalArgumentException(
					"SessionHelper Exception : cann't set user without session");
		}
		session.setAttribute(HttpSessionConstant.COOKIE_LOGIN_USER_DATA,
				sessionUser);
	}

	public static void removeSessionUserDo(HttpSession session) {
		if (session == null) {
			throw new IllegalArgumentException(
					"SessionHelper Exception : cann't remove user without session");
		}
		session.removeAttribute(HttpSessionConstant.COOKIE_LOGIN_USER_DATA);
		session.invalidate();
	}

}
