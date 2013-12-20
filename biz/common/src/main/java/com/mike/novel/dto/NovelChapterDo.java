package com.mike.novel.dto;

/**
 * 类 NovelChapterDo 的实现描述：章Do
 * 
 * @author 听雷 2013年12月18日下午7:42:30
 */
public class NovelChapterDo {

	private int id;

	private int vid;

	private long cid;

	private long nextid;

	private long upid;

	private String cname;

	private int cnum;

	private boolean isContent;

	private TasksDo task;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public long getNextid() {
		return nextid;
	}

	public void setNextid(long nextid) {
		this.nextid = nextid;
	}

	public long getUpid() {
		return upid;
	}

	public void setUpid(long upid) {
		this.upid = upid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public boolean isContent() {
		return isContent;
	}

	public void setContent(boolean isContent) {
		this.isContent = isContent;
	}

	public TasksDo getTask() {
		return task;
	}

	public void setTask(TasksDo task) {
		this.task = task;
	}

}
