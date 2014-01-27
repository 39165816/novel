package com.mike.novel.dto;

import java.sql.Date;

/**
 * 热门小说对象
 * 
 */
public class HottestDo {

	private int id;
	private Date gmtCreate;
	private Date gmtModify;
	private Date statisticTime;
	private String name;
	private int type;
	private int order;
	private int hotIndex;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Date getStatisticTime() {
		return statisticTime;
	}

	public void setStatisticTime(Date statisticTime) {
		this.statisticTime = statisticTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getHotIndex() {
		return hotIndex;
	}

	public void setHotIndex(int hotIndex) {
		this.hotIndex = hotIndex;
	}

	// 2,1,大主宰,175673
	// 4,2,莽荒纪,143521
	// 6,3,完美世界,122760
	public static HottestDo buildFromString(String hotString) {
		HottestDo hottestDo = new HottestDo();
		String[] split = hotString.split(",");
		hottestDo.setOrder(Integer.parseInt(split[1].trim()));
		hottestDo.setName(split[2].trim());
		hottestDo.setHotIndex(Integer.parseInt(split[3].trim()));

		return hottestDo;
	}
}
