package com.mike.novel.user.webx.valve;

public interface HttpSessionConstant {

	/**
	 * session user info attribute key
	 */
	final String COOKIE_LOGIN_USER_DATA = "_login_user";

	/**
	 * login url, configured in URIBrokerService
	 */
	final String LOGIN_URL = "loginLink";

	/**
	 * default url which redirect to after logged in
	 */
	final String DEFAULT_INDEX_URL = "userIndexLink";

	/**
	 * default error page url
	 */
	final String ERROR_URL = "errorLink";
}
