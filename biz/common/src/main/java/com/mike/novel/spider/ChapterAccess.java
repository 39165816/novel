package com.mike.novel.spider;

import java.io.IOException;

/**
 * �� ChapterAccess ��ʵ���������µ�����ӿ�
 * 
 * @author ���� 2013��12��19������3:31:34
 */
public interface ChapterAccess {

    /**
     * �����½ڵ�URl�������½ڵ�����
     */
    public String accessChapter(String url) throws IOException;

}
