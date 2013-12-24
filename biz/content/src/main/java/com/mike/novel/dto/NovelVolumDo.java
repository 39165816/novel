package com.mike.novel.dto;

import java.sql.Date;
import java.util.List;

/**
 * 类 NovelVolumDo 的实现描述：卷do
 * 
 * @author 听雷 2013年12月18日下午7:40:43
 */
public class NovelVolumDo {

	private int id;
	// 小说编号
	private int nid;
	// 卷编号
	private int vid;
	// 卷名字
	private String vname;
	// 第几卷
	private int vnum;
	// 卷介绍
	private String vintro;
	// 其它字段1
	private String info1;
	// 其它字段2
	private String info2;

	// 章list
	private List<NovelChapterDo> chapters;

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

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public int getVnum() {
		return vnum;
	}

	public void setVnum(int vnum) {
		this.vnum = vnum;
	}

	public String getVintro() {
		return vintro;
	}

	public void setVintro(String vintro) {
		this.vintro = vintro;
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

	public List<NovelChapterDo> getChapters() {
		return chapters;
	}

	public void setChapters(List<NovelChapterDo> chapters) {
		this.chapters = chapters;
	}

}
