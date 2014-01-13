package com.mike.novel.dto;

import java.sql.Date;
import java.util.List;

import com.mike.novel.util.NovelType;

/**
 * 类 NovelBasicDo 的实现描述：小说基本信息Do
 * 
 * @author 听雷 2013年12月18日下午7:37:42
 */
public class NovelBasicDo {
	// 自增id
	private int id;
	// 小说id
	private int nid;
	// 图片的路径
	private String picturePath;
	// 作者
	private String author;
	// 是否完结
	private boolean isFinished;
	// 简介
	private String introduce;
	// 最后更新时间
	private Date lastUpdateTime;
	// 是否提供下载
	private int isForDownload;
	// 小说分类
	private int type;

	private String info1;

	private String info2;
	// 是否公开
	private boolean isReadyPublic;
	// 是否已生成html
	private boolean isGenerateHtml;
	// 标题
	private String title;
	// 原始的URL，用来去重
	private String originalUrl;
	// 是否生存txt
	private boolean isGenerateTxt;
	// 生成的txt包含的章节数
	private int generateTxtNum;

	// 卷list
	private List<NovelVolumDo> volums;

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

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getIsForDownload() {
		return isForDownload;
	}

	public void setIsForDownload(int isForDownload) {
		this.isForDownload = isForDownload;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public List<NovelVolumDo> getVolums() {
		return volums;
	}

	public void setVolums(List<NovelVolumDo> volums) {
		this.volums = volums;
	}

	public boolean isReadyPublic() {
		return isReadyPublic;
	}

	public void setReadyPublic(boolean isReadyPublic) {
		this.isReadyPublic = isReadyPublic;
	}

	public boolean isGenerateHtml() {
		return isGenerateHtml;
	}

	public void setGenerateHtml(boolean isGenerateHtml) {
		this.isGenerateHtml = isGenerateHtml;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getTypeName() {
		return NovelType.getName(type);
	}

	public boolean isGenerateTxt() {
		return isGenerateTxt;
	}

	public void setGenerateTxt(boolean isGenerateTxt) {
		this.isGenerateTxt = isGenerateTxt;
	}

	public int getGenerateTxtNum() {
		return generateTxtNum;
	}

	public void setGenerateTxtNum(int generateTxtNum) {
		this.generateTxtNum = generateTxtNum;
	}

	public String getTargetLink() {
		return "/n" + nid + ".html";
	}
}
