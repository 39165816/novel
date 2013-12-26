package com.mike.novel.dto;

import java.sql.Date;

/**
 * �� NovelChapterDo ��ʵ����������Do
 * 
 * @author ���� 2013��12��18������7:42:30
 */
public class NovelChapterDo {

	private int id;
	// ��id
	private int vid;
	// ��id
	private long cid;
	// ��һ��id
	private long nextid;
	// ��һ��id
	private long upid;
	// ������
	private String cname;
	// �±��
	private int cnum;
	// �Ƿ�������
	private boolean isContent;

	private String info1;

	private String info2;

	private TasksDo task;

	private Date gmtCreate;

	private Date gmtModify;

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModify() {
		return gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

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

	public String getInfo1() {
		return info1;
	}

	public void setInfo1(String info1) {
		this.info1 = info1;
	}

	public String getInfo2() {
		return info2;
	}

	public void setInfo2(String info2) {
		this.info2 = info2;
	}

	public String getUpUrl(long nid) {
		if (upid == 0l) {
			return "/n.htm?nid=" + nid;
		}

		return "/p.htm?nid=" + nid + "&" + "cid=" + upid;
	}

	public String getNextUrl(long nid) {
		if (nextid == 0l) {
			return "/n.htm?nid=" + nid;
		}

		return "/p.htm?nid=" + nid + "&" + "cid=" + nextid;
	}
}
