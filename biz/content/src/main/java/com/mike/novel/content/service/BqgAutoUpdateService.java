/**
 * Project: novel-biz-content
 * 
 * File Created at 2014年5月6日下午4:35:44
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
 * biquge自动更新service：<br/>
 * 1,根据输入的小说首页url，分析出对应的章节和内容<br/>
 * 2，对比db中记录，把新增的章节插入任务表中 <br/>
 * 
 * @author 听雷 2014年5月6日下午4:35:44
 */
public interface BqgAutoUpdateService {

    void autoUpdate(int nid, String url);
}
