package com.mike.novel.content.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.mike.novel.content.dao.NovelBasicDao;
import com.mike.novel.content.service.NovelBasicService;
import com.mike.novel.dto.NovelBasicDo;

public class NovelBasicServiceImpl implements NovelBasicService {

	@Resource
	private NovelBasicDao novelBasicDao;

	public Integer insert(NovelBasicDo novelBasicDo) {
		return novelBasicDao.save(novelBasicDo);
	}

	@Override
	public NovelBasicDo queryByTargetUrl(String targetUrl) {
		return novelBasicDao.queryByTargetUrl(targetUrl);
	}

	@Override
	public NovelBasicDo queryByNid(long nid) {
		return novelBasicDao.getByNid(nid);
	}

	@Override
	public List<NovelBasicDo> findByNids(List<Integer> nids) {
		if (nids == null || nids.size() == 0) {
			return new ArrayList<NovelBasicDo>();
		}
		List<NovelBasicDo> findByNids = novelBasicDao.findByNids(nids);
		// 因为in返回不能保证顺序，这里再处理回来
		List<NovelBasicDo> result = new ArrayList<NovelBasicDo>();
		if (findByNids != null && findByNids.size() > 0) {
			for (int one : nids) {
				for (NovelBasicDo novelBasicDo : findByNids) {
					if (novelBasicDo.getNid() == one) {
						result.add(novelBasicDo);
						break;
					}
				}
			}
		}
		return result;

	}

	@Override
	public List<NovelBasicDo> findByType(int type) {
		return novelBasicDao.findByType(type);
	}

	@Override
	public List<NovelBasicDo> findAll() {
		return novelBasicDao.findAll();
	}

	@Override
	public List<NovelBasicDo> findNewest() {
		return novelBasicDao.findNewest();
	}

	@Override
	public List<NovelBasicDo> findByFinish() {
		return novelBasicDao.findByFinish();
	}

	@Override
	public List<NovelBasicDo> findToGenerateTxt() {
		return novelBasicDao.findToGenerateTxt();
	}

	@Override
	public void updateTxtStatus(NovelBasicDo novelBasicDo) {
		novelBasicDao.updateTxtStatus(novelBasicDo);
	}

	@Override
	public NovelBasicDo queryByTitle(String name) {
		return novelBasicDao.queryByTitle(name);
	}

}
