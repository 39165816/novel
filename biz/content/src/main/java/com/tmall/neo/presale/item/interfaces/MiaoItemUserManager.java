package com.tmall.neo.presale.item.interfaces;

import java.util.List;

import com.tmall.neo.presale.item.interfaces.dto.MiaoFreshItemDTO;

public interface MiaoItemUserManager {
    /**
     * ��ѯ��������Ʒ��Ϣ
     */
    List<MiaoFreshItemDTO> queryMiaoItems(List<Long> itemIdList);
    
}
