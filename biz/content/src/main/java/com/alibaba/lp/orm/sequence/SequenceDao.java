/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.lp.orm.sequence;

/**
 * ����DAO�ӿ�
 * 
 * @author nianbing
 */
public interface SequenceDao {

	/**
	 * ȡ����һ�����õ���������
	 * 
	 * @param name
	 *            ��������
	 * @return ������һ�����õ���������
	 * @throws SequenceException
	 */
	SequenceRange nextRange(String name) throws SequenceException;
}
