package com.mike.novel.dto.vo;

import com.mike.novel.dto.HottestDo;

public class HottestVo implements Comparable<HottestVo> {

	private HottestDo hottestDo;

	private boolean isFinished;

	private boolean isIntroduced;
	// 本站的小说地址
	private String targetUrl;
	// 小说分类
	private int type;
	// 小说id
	private int nid;

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public boolean isIntroduced() {
		return isIntroduced;
	}

	public void setIntroduced(boolean isIntroduced) {
		this.isIntroduced = isIntroduced;
	}

	public HottestDo getHottestDo() {
		return hottestDo;
	}

	public void setHottestDo(HottestDo hottestDo) {
		this.hottestDo = hottestDo;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	@Override
	public int compareTo(HottestVo o) {
		return this.hottestDo.getOrder() - o.getHottestDo().getOrder();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

}
