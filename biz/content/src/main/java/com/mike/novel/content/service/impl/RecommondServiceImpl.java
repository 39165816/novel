package com.mike.novel.content.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.mike.novel.content.dao.RecommondDao;
import com.mike.novel.content.service.RecommondService;
import com.mike.novel.dto.RecommondDo;
import com.mike.novel.util.ConfigConstants;

public class RecommondServiceImpl implements RecommondService {

	@Resource
	private RecommondDao recommondDao;

	@Resource
	private ConfigConstants configConstants;

	@Override
	public List<Integer> queryByType(int type) {
		Integer pid = configConstants.getPid("p" + type);
		if (pid == null) {
			return null;
		}

		List<Integer> result = new ArrayList<Integer>();
		List<RecommondDo> recommonds = recommondDao.getByPid(pid);
		if (recommonds == null) {
			return result;
		}
		for (RecommondDo oneRec : recommonds) {
			result.add(oneRec.getNid());
		}

		return result;
	}
}
