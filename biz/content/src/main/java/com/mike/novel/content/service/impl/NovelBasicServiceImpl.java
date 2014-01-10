package com.mike.novel.content.service.impl;

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
		return novelBasicDao.findByNids(nids);
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

}
