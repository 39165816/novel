package com.mike.novel.dto;

import java.sql.Date;

/**
 * �� NovelPageDo ��ʵ��������ҳdo
 * 
 * @author ���� 2013��12��18������7:44:26
 */
public class NovelPageDo {

	private int id;

	// ��id
	private long cid;
	// ����
	private String content;
	private String info1;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getInfo1() {
		return info1;
	}

	public void setInfo1(String info1) {
		this.info1 = info1;
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

}
