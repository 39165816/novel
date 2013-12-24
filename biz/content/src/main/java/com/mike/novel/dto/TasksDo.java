package com.mike.novel.dto;

import java.sql.Date;

/**
 * 类 TasksDo 的实现描述：任务do
 * 
 * @author 听雷 2013年12月20日下午3:00:40
 */
public class TasksDo {

	private int id;
	// 小说id
	private int nid;
	// 章编号
	private long cid;
	// 待爬url
	private String url;
	// 是否完成
	private boolean isFinished;
	// 是否删除
	private boolean isDeleted;

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

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

}
