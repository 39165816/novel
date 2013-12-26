package com.sesame.content.web.module.screen.backdoor;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.mike.novel.content.service.NovelCombServcie;
import com.mike.novel.dto.vo.NovelStatusVo;

/**
 * 后台的门
 * 
 */
public class Runtask {
	private final static Logger logger = LoggerFactory.getLogger(Runtask.class);
	@Resource
	private NovelCombServcie novelCombServcie;

	public void execute(@Param("nid") String nid, HttpSession httpSession,
			Context context, Navigator nav) {
		if (null == nid || nid.equals("")) {
			logger.error("nid为空");
			context.put("errMsg", "nid为空");
			nav.forwardTo("/backdoor/error.vm");
			return;
		}
		int intNid = Integer.parseInt(nid);
		novelCombServcie.processTask(intNid);
		NovelStatusVo novelStatus = novelCombServcie.queryNovelStatus(intNid);
		context.put("novelStatus", novelStatus);
		nav.forwardTo("/backdoor/status.vm");
	}
}
