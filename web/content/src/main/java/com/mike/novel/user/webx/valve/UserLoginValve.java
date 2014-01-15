package com.mike.novel.user.webx.valve;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.alibaba.citrus.service.uribroker.URIBrokerService;
import com.alibaba.citrus.service.uribroker.uri.URIBroker;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.util.TurbineUtil;
import com.alibaba.citrus.util.ServletUtil;
import com.alibaba.citrus.util.StringUtil;

/**
 * check if user has logged in if logged in, continue pipeline if not logger in,
 * redirect to login page
 * 
 */
public class UserLoginValve extends AbstractValve {

	@Resource
	private HttpServletRequest request;
	@Resource
	private URIBrokerService uriBrokerService;

	private static final String NEED_LOGIN_URL = "backdoor";

	@Override
	public void invoke(PipelineContext pipelineContext) throws Exception {

		HttpSession session = request.getSession();
		TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);
		SessionUserDo sessionUser = SessionHelper.getSessionUserDo(session);

		String requestUrl = request.getRequestURL().toString();
		String path = ServletUtil.getResourcePath(request);
		if (sessionUser == null) {
			URIBroker loginUrl = uriBrokerService
					.getURIBroker(HttpSessionConstant.LOGIN_URL);
			if (!loginUrl.render().equalsIgnoreCase(requestUrl)
					&& needLogin(path)) {
				rundata.setRedirectLocation(loginUrl.render());
				return;
			}

		}
		pipelineContext.invokeNext();
	}

	private boolean needLogin(String path) {
		int lastSlashIndex = path.lastIndexOf("/");
		if (lastSlashIndex >= 0) {
			path = path.substring(0, lastSlashIndex)
					+ "/"
					+ StringUtil
							.toCamelCase(path.substring(lastSlashIndex + 1));
		} else {
			path = StringUtil.toCamelCase(path);
		}
		return path.contains(NEED_LOGIN_URL) ? true : false;
	}

}
