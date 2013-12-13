/**
 * Project: neoportal-biz-presale
 * 
 * File Created at 2013年12月11日下午1:54:40
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
package com.tmall.neo.presale.item.interfaces.dto;

import java.io.Serializable;

/**
 * 类 MiaoFreshItemDTO 的实现描述：喵鲜生活动频道中商品DTO
 * 
 * @author 听雷 2013年12月11日下午1:54:40
 */
public class MiaoFreshItemDTO implements Serializable {

    private static final long serialVersionUID = 1639536793725444565L;

    /**
     * 前台商品id
     */
    private Long              itemId;

    /**
     * 原价
     */
    private String            originalPrice;

    /**
     * 月销量
     */
    private int               monthlySoldAmount;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getMonthlySoldAmount() {
        return monthlySoldAmount;
    }

    public void setMonthlySoldAmount(int monthlySoldAmount) {
        this.monthlySoldAmount = monthlySoldAmount;
    }

}
