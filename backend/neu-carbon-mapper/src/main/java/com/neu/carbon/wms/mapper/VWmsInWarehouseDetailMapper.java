package com.neu.carbon.wms.mapper;

import java.util.List;

import com.neu.carbon.wms.domain.VWmsInWarehouseDetail;

/**
 * 入库明细报表Mapper接口
 * 
 * @author neuedu
 * @date 2022-07-27
 */
public interface VWmsInWarehouseDetailMapper 
{
    /**
     * 查询入库明细报表
     * 
     * @param materialId 入库明细报表ID
     * @return 入库明细报表
     */
    public VWmsInWarehouseDetail selectVWmsInWarehouseDetailById(Long materialId);

    /**
     * 查询入库明细报表列表
     * 
     * @param vWmsInWarehouseDetail 入库明细报表
     * @return 入库明细报表集合
     */
    public List<VWmsInWarehouseDetail> selectVWmsInWarehouseDetailList(VWmsInWarehouseDetail vWmsInWarehouseDetail);
}
