package com.mike.novel.dto.vo;

import java.util.List;

import com.mike.novel.dto.NovelBasicDo;
import com.mike.novel.dto.NovelChapterDo;
import com.mike.novel.dto.NovelVolumDo;

public class NovelStatusVo {

	private NovelBasicDo novelBasicDo;

	private List<NovelVolumDo> volums;

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
			total = one.getChapters().size() + total;
		}

		return total;
	}

	public String getAllChapterName() {
		if (volums == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();

		for (NovelVolumDo one : volums) {
			for (NovelChapterDo two : one.getChapters()) {
				sb.append(two.getCname() + "\n");
			}
		}

		return sb.toString();
	}
}
