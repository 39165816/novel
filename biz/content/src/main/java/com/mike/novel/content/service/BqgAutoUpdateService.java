/**
 * Project: novel-biz-content
 * 
 * File Created at 2014��5��6������4:35:44
 * $Id$
 * 
 * Copyright 1999-2012 Alibaba.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.mike.novel.content.service;

/**
 * biquge�Զ�����service��<br/>
 * 1,���������С˵��ҳurl����������Ӧ���½ں�����<br/>
 * 2���Ա�db�м�¼�����������½ڲ���������� <br/>
 * 
 * @author ���� 2014��5��6������4:35:44
 */
public interface BqgAutoUpdateService {

    void autoUpdate(int nid, String url);
}
