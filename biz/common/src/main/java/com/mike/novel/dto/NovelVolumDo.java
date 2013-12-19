package com.mike.novel.dto;

import java.util.List;

/**
 * 类 NovelVolumDo 的实现描述：卷do
 * 
 * @author 听雷 2013年12月18日下午7:40:43
 */
public class NovelVolumDo {

    private int                  id;

    private int                  nid;

    private int                  vid;

    private String               vname;

    private int                  vnum;

    private String               vintro;

    private String               info1;

    private String               info2;

    //章list
    private List<NovelChapterDo> chapters;

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
