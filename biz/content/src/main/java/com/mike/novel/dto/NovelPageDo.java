package com.mike.novel.dto;

import java.sql.Date;

/**
 * 类 NovelPageDo 的实现描述：页do
 * 
 * @author 听雷 2013年12月18日下午7:44:26
 */
public class NovelPageDo {

	private long id;

	// 章id
	private long cid;
	// 正文
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
		result = result + "<br/><br/>";// 最后补上两个换行
		// 如果出现4个连续<br/>，替换为2个<br/>(兼容一些错误数据)
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
