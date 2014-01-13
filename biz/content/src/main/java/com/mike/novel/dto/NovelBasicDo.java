package com.mike.novel.dto;

import java.sql.Date;
import java.util.List;

import com.mike.novel.util.NovelType;

/**
 * �� NovelBasicDo ��ʵ��������С˵������ϢDo
 * 
 * @author ���� 2013��12��18������7:37:42
 */
public class NovelBasicDo {
	// ����id
	private int id;
	// С˵id
	private int nid;
	// ͼƬ��·��
	private String picturePath;
	// ����
	private String author;
	// �Ƿ����
	private boolean isFinished;
	// ���
	private String introduce;
	// ������ʱ��
	private Date lastUpdateTime;
	// �Ƿ��ṩ����
	private int isForDownload;
	// С˵����
	private int type;

	private String info1;

	private String info2;
	// �Ƿ񹫿�
	private boolean isReadyPublic;
	// �Ƿ�������html
	private boolean isGenerateHtml;
	// ����
	private String title;
	// ԭʼ��URL������ȥ��
	private String originalUrl;
	// �Ƿ�����txt
	private boolean isGenerateTxt;
	// ���ɵ�txt�������½���
	private int generateTxtNum;

	// ��list
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
