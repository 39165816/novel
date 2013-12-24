/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.lp.orm.sequence;

/**
 * 序列接口
 * 
 * @author nianbing
 */
public interface Sequence {

	/**
	 * 取得序列下一个值
	 * 
	 * @return 返回序列下一个值
	 * @throws SequenceException
	 */
	long nextValue() throws SequenceException;
}
