package com.neu.carbon.wms.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.carbon.wms.domain.VWmsInWarehouseDetail;
import com.neu.carbon.wms.mapper.VWmsInWarehouseDetailMapper;
import com.neu.carbon.wms.service.IVWmsInWarehouseDetailService;

/**
 * 入库明细报表Service业务层处理
 * 
 * @author neuedu
 * @date 2022-07-27
 */
@Service
public class VWmsInWarehouseDetailServiceImpl implements IVWmsInWarehouseDetailService 
{
    @Autowired
    private VWmsInWarehouseDetailMapper vWmsInWarehouseDetailMapper;

    /**
     * 查询入库明细报表
     * 
     * @param materialId 入库明细报表ID
     * @return 入库明细报表
     */
    @Override
    public VWmsInWarehouseDetail selectVWmsInWarehouseDetailById(Long materialId)
    {
        return vWmsInWarehouseDetailMapper.selectVWmsInWarehouseDetailById(materialId);
    }

    /**
     * 查询入库明细报表列表
     * 
     * @param vWmsInWarehouseDetail 入库明细报表
     * @return 入库明细报表
     */
    @Override
    public List<VWmsInWarehouseDetail> selectVWmsInWarehouseDetailList(VWmsInWarehouseDetail vWmsInWarehouseDetail)
    {
        return vWmsInWarehouseDetailMapper.selectVWmsInWarehouseDetailList(vWmsInWarehouseDetail);
    }
}
