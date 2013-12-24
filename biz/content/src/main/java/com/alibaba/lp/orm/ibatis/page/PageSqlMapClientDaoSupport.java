/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.lp.orm.ibatis.page;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * ��PageSqlMapClientDaoSupport.java��ʵ��������TODO ��ʵ������
 * 
 * @author huangjun Aug 5, 2011 6:25:05 PM
 */
public class PageSqlMapClientDaoSupport<E> extends SqlMapClientDaoSupport {

    private PageDialect pageDialect = new PageDialect();

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Page<E> page(Integer index, Integer sizePerPage, Object parameters, String countSqlName,
                        String listSqlName) {
        if (countSqlName == null || countSqlName.length() == 0) {
            throw new DataAccessException("countSqlName is null or empty.");
        }
        if (listSqlName == null || listSqlName.length() == 0) {
            throw new DataAccessException("listSqlName is null or empty.");
        }

        ParamMap param = ParamMap.create(index, sizePerPage);

        // �����ռ�
        if (parameters != null) {
            if (parameters instanceof Map) {
                param.addAll((Map) parameters);
            } else {
                if (BeanUtils.isSimpleValueType(parameters.getClass())) {
                    param.put("value", parameters);
                } else {
                    // �������е������Ƶ�����map�ĵ�һ��
                    moveBeanProperties2TopLevel(param, parameters);
                }
            }
        }

        // ͳ��
        int count = 0;
        count = (Integer) this.getSqlMapClientTemplate().queryForObject(countSqlName, param);

        Page page = new Page(index, sizePerPage, count);

        // ��ҳ��ѯ
        List datas = null;
        Map paramMap = param.toMap();
        paramMap = Page.constructPageInfo(pageDialect, paramMap);
        datas = this.find(listSqlName, paramMap);
        page.setDatas(datas);
        return page;
    }

    @SuppressWarnings({ "rawtypes" })
    private List find(String sqlName, Object parameters) {
        List datas = this.getSqlMapClientTemplate().queryForList(sqlName, parameters);
        if (datas == null) {
            datas = Collections.EMPTY_LIST;
        }
        return datas;
    }

    @SuppressWarnings("rawtypes")
    private void moveBeanProperties2TopLevel(ParamMap param, Object parameters) {
        if (parameters != null) {
            if (parameters instanceof Map) {
                param.addAll((Map) parameters);
            } else {
                PropertyDescriptor[] propertyDescriptors = BeanUtils
                        .getPropertyDescriptors(parameters.getClass());
                Method read;
                try {

                    for (PropertyDescriptor p : propertyDescriptors) {
                        if ("class".equals(p.getName())) {
                            continue;
                        }
                        read = p.getReadMethod();
                        if (read != null) {
                            Object value = read.invoke(parameters);
                            if (value != null) {
                                param.put(p.getName(), value);
                            }
                        }
                    }
                } catch (Exception e) {
                    throw new DataAccessException(e);
                }
            }
        }
    }

}
