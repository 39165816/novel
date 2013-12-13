/**
 * Project: neoportal-biz-presale
 * 
 * File Created at 2013��12��11������1:54:40
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
 * �� MiaoFreshItemDTO ��ʵ���������������Ƶ������ƷDTO
 * 
 * @author ���� 2013��12��11������1:54:40
 */
public class MiaoFreshItemDTO implements Serializable {

    private static final long serialVersionUID = 1639536793725444565L;

    /**
     * ǰ̨��Ʒid
     */
    private Long              itemId;

    /**
     * ԭ��
     */
    private String            originalPrice;

    /**
     * ������
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
