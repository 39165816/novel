/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.lp.orm.sequence;

import java.util.concurrent.atomic.AtomicLong;

/**
 * ĞòÁĞÇø¼ä
 * 
 * @author nianbing
 */
public class SequenceRange {

	private final long min;
	private final long max;

	private final AtomicLong value;

	private volatile boolean over = false;

	public SequenceRange(long min, long max) {
		this.min = min;
		this.max = max;
		this.value = new AtomicLong(min);
	}

	public long getAndIncrement() {
		long currentValue = value.getAndIncrement();
		if (currentValue > max) {
			over = true;
			return -1;
		}

		return currentValue;
	}

	public long getMin() {
		return min;
	}

	public long getMax() {
		return max;
	}

	public boolean isOver() {
		return over;
	}
}
