package com.neu.carbon.wms.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.carbon.wms.domain.VWmsOutWarehouseDetail;
import com.neu.carbon.wms.mapper.VWmsOutWarehouseDetailMapper;
import com.neu.carbon.wms.service.IVWmsOutWarehouseDetailService;

/**
 * 物料出库明细Service业务层处理
 * 
 * @author neuedu
 * @date 2022-07-30
 */
@Service
public class VWmsOutWarehouseDetailServiceImpl implements IVWmsOutWarehouseDetailService 
{
    @Autowired
    private VWmsOutWarehouseDetailMapper vWmsOutWarehouseDetailMapper;

    /**
     * 查询物料出库明细
     * 
     * @param materialId 物料出库明细ID
     * @return 物料出库明细
     */
    @Override
    public VWmsOutWarehouseDetail selectVWmsOutWarehouseDetailById(Long materialId)
    {
        return vWmsOutWarehouseDetailMapper.selectVWmsOutWarehouseDetailById(materialId);
    }

    /**
     * 查询物料出库明细列表
     * 
     * @param vWmsOutWarehouseDetail 物料出库明细
     * @return 物料出库明细
     */
    @Override
    public List<VWmsOutWarehouseDetail> selectVWmsOutWarehouseDetailList(VWmsOutWarehouseDetail vWmsOutWarehouseDetail)
    {
        return vWmsOutWarehouseDetailMapper.selectVWmsOutWarehouseDetailList(vWmsOutWarehouseDetail);
    }

    
}
