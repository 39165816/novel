package com.mike.novel.dto;

import java.sql.Date;

/**
 * �� TasksDo ��ʵ������������do
 * 
 * @author ���� 2013��12��20������3:00:40
 */
public class TasksDo {

	private int id;
	// С˵id
	private int nid;
	// �±��
	private long cid;
	// ����url
	private String url;
	// �Ƿ����
	private boolean isFinished;
	// �Ƿ�ɾ��
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
