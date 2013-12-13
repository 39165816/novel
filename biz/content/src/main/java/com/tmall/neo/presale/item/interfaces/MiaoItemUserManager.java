package com.tmall.neo.presale.item.interfaces;

import java.util.List;

import com.tmall.neo.presale.item.interfaces.dto.MiaoFreshItemDTO;

public interface MiaoItemUserManager {
    /**
     * 查询喵鲜生商品信息
     */
    List<MiaoFreshItemDTO> queryMiaoItems(List<Long> itemIdList);
    
}
