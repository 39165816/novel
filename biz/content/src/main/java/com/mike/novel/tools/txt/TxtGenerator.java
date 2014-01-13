package com.mike.novel.tools.txt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mike.novel.content.service.NovelBasicService;
import com.mike.novel.content.service.NovelCombServcie;
import com.mike.novel.dto.NovelBasicDo;
import com.mike.novel.dto.vo.NovelStatusVo;

public class TxtGenerator {

	@Resource
	private NovelBasicService novelBasicService;

	@Resource
	private NovelCombServcie novelCombService;
	private static final int CORE_NUM = 5;
	@Resource
	private GenerateOneTxt generateOneTxt;

	private final static Logger log = LoggerFactory.getLogger(TxtGenerator.class);

	public void generateAll() {
		// ȡ��Ҫ���ɵ�С˵�б�id
		List<NovelBasicDo> findToGenerateTxt = novelBasicService.findToGenerateTxt();

		List<NovelBasicDo> toGenerate = new ArrayList<NovelBasicDo>();
		for (NovelBasicDo oneNovel : findToGenerateTxt) {
			if (!oneNovel.isGenerateTxt()) {// δ����
				toGenerate.add(oneNovel);
			} else {// ������
				if (oneNovel.isFinished()) {// �����
					// ���ò���
				} else { // δ���
					NovelStatusVo queryNovelInfo = novelCombService.queryNovelInfo(oneNovel.getNid());
					if (oneNovel.getGenerateTxtNum() != queryNovelInfo.getTotalNum()) {// �Ƚ�֮ǰ���ɵ��½ں͵�ǰ���½ڣ������һ�£�����������
						toGenerate.add(oneNovel);
					}
				}
			}
		}

		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(CORE_NUM);
		for (NovelBasicDo oneTask : toGenerate) {
			newFixedThreadPool.execute(new OneTask(oneTask.getNid()));
		}

	}

	class OneTask implements Runnable {

		private int nid;

		public OneTask(int nid) {
			this.nid = nid;
		}

		@Override
		public void run() {
			try {
				generateOneTxt.generateOne(nid);
			} catch (Exception e) {
				log.error("nid = " + nid, e);
			}

		}

	}

}
