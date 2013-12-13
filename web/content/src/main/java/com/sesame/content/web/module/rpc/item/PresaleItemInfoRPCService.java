package com.sesame.content.web.module.rpc.item;

import com.alibaba.citrus.extension.rpc.annotation.WebResource;

@WebResource("itemInfo")
public class PresaleItemInfoRPCService {
//	private final static Logger log = LoggerFactory.getLogger(PresaleItemInfoRPCService.class);
	
    
    
//    @ResourceMapping("/getMiaoItemInfo")
//    public List<MiaoFreshItemDTO> getMiaoItemInfo(@RequestParam(name = "ids") String ids, HttpSession httpSession) {
//        List<MiaoFreshItemDTO> list = new ArrayList<MiaoFreshItemDTO>();
//        try{
//            List<Long> idList = NeoCollectionUtil.uniqList(SplitUtils.split2ListLongByComma(ids));
//            
//            if(CollectionUtils.isEmpty(idList)){
//                return list;
//            }
//            
//            return miaoItemUserManager.queryMiaoItems(idList);
//        }catch(Exception e){
//            log.error("getMiaoItemInfo: failed to convent param to list! ids:" + ids ,e);
//        }
//        
//        return list;
//    }
    
}
