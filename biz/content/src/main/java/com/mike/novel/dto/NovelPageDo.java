package com.mike.novel.dto;

import java.sql.Date;

/**
 * �� NovelPageDo ��ʵ��������ҳdo
 * 
 * @author ���� 2013��12��18������7:44:26
 */
public class NovelPageDo {

	private long id;

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getFormatContent() {
		if (content == null || content.equals("")) {
			return "";
		}
		String newContent = content.replace("    ", "&nbsp;&nbsp;&nbsp;&nbsp;");
		String result = newContent.replace("\n", "<br/><br/>");
		result = result + "<br/><br/>";// �������������
		// �������4������<br/>���滻Ϊ2��<br/>(����һЩ��������)
		result = result.replace("<br/><br/><br/><br/>", "<br/><br/>");
		return result;
	}

	public String getFormatTxtContent() {
		if (content == null || content.equals("")) {
			return "";
		}
		String newContent = content.replace("    ", "");
		return newContent;
	}

	public static void main(String[] args) {
		String test = "abc\n" + "dd";
		System.out.println(test);
		System.out.println(test.replace("\n", "<br/>"));
	}

}
