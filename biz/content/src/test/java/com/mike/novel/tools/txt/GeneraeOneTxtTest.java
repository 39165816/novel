package com.mike.novel.tools.txt;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GeneraeOneTxtTest {
	@Test
	public void test() {
		List<Long> cids = new ArrayList<Long>();
		for (long i = 0; i < 311; i++) {
			cids.add(i);
		}
		List<List<Long>> result = GenerateOneTxt.splitCids(cids);
		assertEquals(2, result.size());
		assertEquals(200, result.get(0).size());
		assertEquals(111, result.get(1).size());
		assertEquals(310l, result.get(1).get(110).longValue());
	}

}
