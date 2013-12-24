package com.mike.novel.dto.vo;

import java.util.ArrayList;
import java.util.List;

import com.mike.novel.dto.NovelBasicDo;
import com.mike.novel.dto.NovelChapterDo;
import com.mike.novel.dto.NovelVolumDo;
import com.mike.novel.dto.TasksDo;

public class NovelStatusVo {

	private NovelBasicDo novelBasicDo;

	private List<NovelVolumDo> volums = new ArrayList<NovelVolumDo>();

	private boolean isExists;

	private List<TasksDo> tasks = new ArrayList<TasksDo>();

	public NovelBasicDo getNovelBasicDo() {
		return novelBasicDo;
	}

	public void setNovelBasicDo(NovelBasicDo novelBasicDo) {
		this.novelBasicDo = novelBasicDo;
	}

	public List<NovelVolumDo> getVolums() {
		return volums;
	}

	public void setVolums(List<NovelVolumDo> volums) {
		this.volums = volums;
	}

	public int getTotalNum() {
		if (volums == null) {
			return 0;
		}
		int total = 0;
		for (NovelVolumDo one : volums) {
			if (one.getChapters() != null) {
				total = one.getChapters().size() + total;
			}
		}

		return total;
	}

	public String getAllChapterName() {
		if (volums == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();

		for (NovelVolumDo one : volums) {
			if (one.getChapters() != null) {
				for (NovelChapterDo two : one.getChapters()) {
					sb.append(two.getCname() + "\n");
				}
			}
		}

		return sb.toString();
	}

	public String getTaskStatus() {
		int finished = 0;
		for (TasksDo oneTask : tasks) {
			if (oneTask.isFinished()) {
				finished++;
			}
		}

		StringBuffer sb = new StringBuffer();
		sb.append("总共有").append(tasks.size());
		sb.append("\t").append("当前完成了").append(finished);
		sb.append("个").append("还剩").append(tasks.size() - finished);
		return sb.toString();
	}

	public boolean isExists() {
		return isExists;
	}

	public void setExists(boolean isExists) {
		this.isExists = isExists;
	}

	public void setTasks(List<TasksDo> tasks) {
		this.tasks = tasks;
	}

}
