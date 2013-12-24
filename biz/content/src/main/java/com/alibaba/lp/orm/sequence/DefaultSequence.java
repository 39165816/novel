/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.lp.orm.sequence;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 序列默认实现
 * 
 * @author shenxun
 */
public class DefaultSequence implements Sequence {

	private final Lock lock = new ReentrantLock();

	private SequenceDao sequenceDao;

	/**
	 * 序列名称
	 */
	private String name;

	private volatile SequenceRange currentRange;

	public long nextValue() throws SequenceException {
		if (currentRange == null) {
			lock.lock();
			try {
				if (currentRange == null) {
					currentRange = sequenceDao.nextRange(name);
				}
			} finally {
				lock.unlock();
			}
		}

		long value = currentRange.getAndIncrement();
		if (value == -1) {
			lock.lock();
			try {
				for (;;) {
					if (currentRange.isOver()) {
						currentRange = sequenceDao.nextRange(name);
					}

					value = currentRange.getAndIncrement();
					if (value == -1) {
						continue;
					}

					break;
				}
			} finally {
				lock.unlock();
			}
		}

		if (value < 0) {
			throw new SequenceException("Sequence value overflow, value = "
					+ value);
		}

		return value;
	}

	public SequenceDao getSequenceDao() {
		return sequenceDao;
	}

	public void setSequenceDao(SequenceDao sequenceDao) {
		this.sequenceDao = sequenceDao;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
