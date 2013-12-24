/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.lp.orm.sequence;

/**
 * ���нӿ�
 * 
 * @author nianbing
 */
public interface Sequence {

	/**
	 * ȡ��������һ��ֵ
	 * 
	 * @return ����������һ��ֵ
	 * @throws SequenceException
	 */
	long nextValue() throws SequenceException;
}
