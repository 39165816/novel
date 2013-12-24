package com.mike.novel.dto;

import java.sql.Date;
import java.util.List;

/**
 * �� NovelVolumDo ��ʵ����������do
 * 
 * @author ���� 2013��12��18������7:40:43
 */
public class NovelVolumDo {

	private int id;
	// С˵���
	private int nid;
	// ����
	private int vid;
	// ������
	private String vname;
	// �ڼ���
	private int vnum;
	// �����
	private String vintro;
	// �����ֶ�1
	private String info1;
	// �����ֶ�2
	private String info2;

	// ��list
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
