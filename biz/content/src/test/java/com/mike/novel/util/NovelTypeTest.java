package com.mike.novel.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NovelTypeTest {

	@Test
	public void test() {
		assertEquals(NovelType.XIUZHENG, NovelType.getType("修真小说"));
	}

	@Test
	public void testType() {
		assertEquals("修真小说", NovelType.getName(1));
	}

}
