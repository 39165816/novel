package com.sesame.content.web.module.screen.backdoor;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.mike.novel.tools.txt.TxtGenerator;

/**
 * 后台的门
 * 
 */
public class ShowDoor {
	private final static Logger logger = LoggerFactory
			.getLogger(ShowDoor.class);

	private static String hardcode_pin = "123";

	private static String hardcode_pin_2 = "124";
	@Resource
	private TxtGenerator txtGenerator;

	public void execute(@Param("pin") String pin, HttpSession httpSession,
			Context context, Navigator nav) {

		if (hardcode_pin_2.equals(pin)) {
			txtGenerator.generateAll();
			return;
		}

		if (!hardcode_pin.equals(pin)) {
			logger.error("wrong pin:" + pin);
			context.put("errMsg", "wrong pin!");
			nav.forwardTo("/backdoor/error.vm");
			return;
		}

	}
}
