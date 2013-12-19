package com.mike.novel.dto;

import java.sql.Date;
import java.util.List;

/**
 * 类 NovelBasicDo 的实现描述：小说基本信息Do
 * 
 * @author 听雷 2013年12月18日下午7:37:42
 */
public class NovelBasicDo {

    private int                id;

    private int                nid;

    private String             picturePath;

    private String             author;

    private boolean            isFinished;

    private String             introduce;

    private Date               lastUpdateTime;

    private int                isForDownload;

    private int                type;

    private String             info1;

    private String             info2;
    
    private boolean  isReadyPublic;
    
    private boolean isGeneratehtml;

    //卷list
    private List<NovelVolumDo> volums;

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

	public boolean isGeneratehtml() {
		return isGeneratehtml;
	}

	public void setGeneratehtml(boolean isGeneratehtml) {
		this.isGeneratehtml = isGeneratehtml;
	}
    
    

}
