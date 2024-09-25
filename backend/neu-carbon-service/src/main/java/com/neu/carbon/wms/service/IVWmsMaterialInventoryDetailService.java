package com.neu.carbon.wms.service;

import java.util.List;
import com.neu.carbon.wms.domain.VWmsMaterialInventoryDetail;

/**
 * 库存明细报表Service接口
 * 
 * @author neuedu
 * @date 2022-07-26
 */
public interface IVWmsMaterialInventoryDetailService 
{
    /**
     * 查询库存明细报表
     * 
     * @param whId 库存明细报表ID
     * @return 库存明细报表
     */
    public VWmsMaterialInventoryDetail selectVWmsMaterialInventoryDetailById(Long whId);

    /**
     * 查询库存明细报表列表
     * 
     * @param vWmsMaterialInventoryDetail 库存明细报表
     * @return 库存明细报表集合
     */
    public List<VWmsMaterialInventoryDetail> selectVWmsMaterialInventoryDetailList(VWmsMaterialInventoryDetail vWmsMaterialInventoryDetail);
}
