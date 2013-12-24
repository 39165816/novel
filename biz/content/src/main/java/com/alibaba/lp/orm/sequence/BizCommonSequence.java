/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.lp.orm.sequence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 业务类的ID生成器
 * 
 * @author haiquan.guhq 2011-8-13 下午01:30:10
 */
public class BizCommonSequence {

	private static final Logger logger = LoggerFactory
			.getLogger(BizCommonSequence.class);

	private Sequence nidSequence;
	private Sequence vidSequence;
	private Sequence cidSequence;

	/**
	 * nid序号
	 */
	public Integer getNidSequenceCode() {
		Long id;
		try {
			id = nidSequence.nextValue();
		} catch (SequenceException e) {
			logger.error("getNidSequenceCode", e);
			throw new RuntimeException("getNidSequenceCode failed");
		}
		return id.intValue();
	}

	/**
	 * vid序号
	 */
	public Integer getVidSequenceCode() {
		Long id;
		try {
			id = vidSequence.nextValue();
		} catch (SequenceException e) {
			logger.error("getVidSequenceCode", e);
			throw new RuntimeException("getVidSequenceCode failed");
		}
		return id.intValue();
	}

	/**
	 * cid序号
	 */
	public Integer getCidSequenceCode() {
		Long id;
		try {
			id = cidSequence.nextValue();
		} catch (SequenceException e) {
			logger.error("getCidSequenceCode", e);
			throw new RuntimeException("getCidSequenceCode failed");
		}
		return id.intValue();
	}

	public void setNidSequence(Sequence nidSequence) {
		this.nidSequence = nidSequence;
	}

	public void setVidSequence(Sequence vidSequence) {
		this.vidSequence = vidSequence;
	}

	public void setCidSequence(Sequence cidSequence) {
		this.cidSequence = cidSequence;
	}

}
