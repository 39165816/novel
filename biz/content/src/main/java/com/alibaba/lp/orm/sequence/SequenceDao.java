/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.lp.orm.sequence;

/**
 * 序列DAO接口
 * 
 * @author nianbing
 */
public interface SequenceDao {

	/**
	 * 取得下一个可用的序列区间
	 * 
	 * @param name
	 *            序列名称
	 * @return 返回下一个可用的序列区间
	 * @throws SequenceException
	 */
	SequenceRange nextRange(String name) throws SequenceException;
}
