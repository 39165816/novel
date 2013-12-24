/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.lp.orm.ibatis.page;

import java.util.HashMap;
import java.util.Map;

/**
 * ��ParamMap.java��ʵ��������TODO ��ʵ������
 * 
 * @author huangjun Aug 5, 2011 6:46:14 PM
 */
public class ParamMap extends HashMap {
    private static final long serialVersionUID = 2183452622984973515L;
    public ParamMap put(Object key, Object value) {
        super.put(key, value);
        return this;
    }

    public void addAll(Map parameters) {
        this.putAll(parameters);
    }

    public Map toMap() {
        return this;
    }

    /**
     * ���ݷ�ҳ��������ParamMap����ʹ��ȱʡ��ÿҳ20����¼
     * 
     * @param index ��ǰҳ��
     * @return
     */
    public static ParamMap create(Integer index) {
        return create(index, null);
    }

    /**
     * ���ݷ�ҳ��������ParamMap����
     * 
     * @param index ��ǰҳ��
     * @param size ÿҳ��ʾ�ļ�¼��
     * @return
     */
    public static ParamMap create(Integer index, Integer size) {
        ParamMap param = new ParamMap();
        if (index != null) {
            param.put(Page.INDEX, index);
        }

        if (size != null) {
            param.put(Page.SIZE, size);
        } else {
            param.put(Page.SIZE, Page.DEFAULT_SIZE_PER_PAGE);
        }
        return param;
    }

    public static ParamMap create() {
        return new ParamMap();
    }

    public static Map createEmptyMap() {
        return create();
    }
}
