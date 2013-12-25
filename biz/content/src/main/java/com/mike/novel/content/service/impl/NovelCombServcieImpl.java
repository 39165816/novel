package com.mike.novel.content.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.lp.orm.sequence.BizCommonSequence;
import com.mike.novel.content.dao.NovelChapterDao;
import com.mike.novel.content.dao.NovelVolumDao;
import com.mike.novel.content.service.NovelBasicService;
import com.mike.novel.content.service.NovelCombServcie;
import com.mike.novel.content.service.TasksService;
import com.mike.novel.dto.NovelBasicDo;
import com.mike.novel.dto.NovelChapterDo;
import com.mike.novel.dto.NovelVolumDo;
import com.mike.novel.dto.TasksDo;
import com.mike.novel.dto.vo.NovelStatusVo;
import com.mike.novel.spider.biqege.BqgChapterTask;
import com.mike.novel.util.ConfigConstants;

public class NovelCombServcieImpl implements NovelCombServcie {

	@Resource
	private BizCommonSequence bizCommonSequence;
	@Resource
	private NovelVolumDao novelVolumDao;
	@Resource
	private NovelChapterDao novelChapterDao;
	@Resource
	private TasksService tasksService;

	@Resource
	private BqgChapterTask bqgChapterTask;
	@Resource
	private NovelBasicService novelBasicService;

	@Resource
	private ConfigConstants configConstants;

	@Override
	public NovelStatusVo queryNovelStatus(long nid) {
		NovelStatusVo result = queryNovelInfo(nid);

		// ������Ϣ
		List<TasksDo> tasks = tasksService.queryByNid(nid);
		result.setTasks(tasks);

		return result;
	}

	@Override
	public NovelStatusVo queryNovelInfo(long nid) {
		NovelStatusVo result = new NovelStatusVo();
		// ������Ϣ
		NovelBasicDo novelBasicDo = novelBasicService.queryByNid(nid);
		if (novelBasicDo == null) {
			result.setExists(false);
			return result;
		} else {
			result.setExists(true);
		}

		// ת��pic·��
		novelBasicDo.setPicturePath(configConstants.getPictureAccessPath()
				+ novelBasicDo.getPicturePath());

		result.setNovelBasicDo(novelBasicDo);

		// ����Ϣ
		List<NovelVolumDo> volums = novelVolumDao.queryByNid(nid);
		result.setVolums(volums);

		// ����ÿ������������Ϣ
		if (volums != null) {
			for (NovelVolumDo oneVolum : volums) {
				oneVolum.setChapters(novelChapterDao.getbyVid(oneVolum.getVid()));
			}
		}

		return result;
	}

	@Override
	public void saveVolumAndTask(List<NovelVolumDo> volums) {
		if (volums == null) {
			return;
		}

		long upid = 0l;
		long currentCid = bizCommonSequence.getCidSequenceCode();
		long nextid = bizCommonSequence.getCidSequenceCode();
		for (int i = 0; i < volums.size(); i++) {
			NovelVolumDo oneVolum = volums.get(i);
			// �����
			oneVolum.setVid(bizCommonSequence.getVidSequenceCode());
			novelVolumDao.save(oneVolum);

			// ������
			List<NovelChapterDo> chapters = oneVolum.getChapters();
			if (chapters == null) {
				continue;
			}
			for (int j = 0; j < chapters.size(); j++) {
				NovelChapterDo oneChapter = chapters.get(j);
				oneChapter.setVid(oneVolum.getVid());
				oneChapter.setUpid(upid);
				oneChapter.setCid(currentCid);
				if (i == volums.size() - 1 && j == chapters.size() - 1) {
					// ���һ�µ�nextidΪ 0
					oneChapter.setNextid(0l);
				} else {
					oneChapter.setNextid(nextid);
				}
				oneChapter.setContent(true);

				novelChapterDao.save(oneChapter);
				// ����
				upid = currentCid;
				currentCid = nextid;
				nextid = bizCommonSequence.getCidSequenceCode();
			}

			// ��������
			for (int j = 0; j < chapters.size(); j++) {
				NovelChapterDo novelChapterDo = chapters.get(j);
				TasksDo task = novelChapterDo.getTask();
				task.setNid(oneVolum.getNid());// nid
				task.setCid(novelChapterDo.getCid());// cid

				tasksService.save(task);
			}

		}

	}

	@Override
	public void processTask(long nid) {
		List<TasksDo> tasksDo = tasksService.queryByNid(nid);
		if (tasksDo == null || tasksDo.size() == 0) {
			return;
		}
		bqgChapterTask.processTask(tasksDo);
	}
}
