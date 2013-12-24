package com.mike.novel.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NovelTypeTest {

    @Test
    public void test() {
        assertEquals(NovelType.XIUZHENG, NovelType.getType("–ﬁ’Ê–°Àµ"));
    }

}
