package com.neu.carbon.wms.service.impl;

import com.neu.carbon.wms.domain.WmsWarehouseLocation;
import com.neu.carbon.wms.mapper.WmsWarehouseLocationMapper;
import com.neu.carbon.wms.service.IWmsWarehouseLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 库位管理Service业务层处理
 * 
 * @author neusoft
 * @date 2022-06-24
 */
@Service
public class WmsWarehouseLocationServiceImpl implements IWmsWarehouseLocationService 
{
    @Autowired
    private WmsWarehouseLocationMapper wmsWarehouseLocationMapper;

    /**
     * 查询库位管理
     * 
     * @param id 库位管理ID
     * @return 库位管理
     */
    @Override
    public WmsWarehouseLocation selectWmsWarehouseLocationById(Long id)
    {
        return wmsWarehouseLocationMapper.selectWmsWarehouseLocationById(id);
    }

    /**
     * 查询库位管理列表
     * 
     * @param wmsWarehouseLocation 库位管理
     * @return 库位管理
     */
    @Override
    public List<WmsWarehouseLocation> selectWmsWarehouseLocationList(WmsWarehouseLocation wmsWarehouseLocation)
    {
        return wmsWarehouseLocationMapper.selectWmsWarehouseLocationList(wmsWarehouseLocation);
    }

    /**
     * 新增库位管理
     * 
     * @param wmsWarehouseLocation 库位管理
     * @return 结果
     */
    @Override
    public int insertWmsWarehouseLocation(WmsWarehouseLocation wmsWarehouseLocation)
    {
        return wmsWarehouseLocationMapper.insertWmsWarehouseLocation(wmsWarehouseLocation);
    }

    /**
     * 修改库位管理
     * 
     * @param wmsWarehouseLocation 库位管理
     * @return 结果
     */
    @Override
    public int updateWmsWarehouseLocation(WmsWarehouseLocation wmsWarehouseLocation)
    {
        return wmsWarehouseLocationMapper.updateWmsWarehouseLocation(wmsWarehouseLocation);
    }

    /**
     * 批量删除库位管理
     * 
     * @param ids 需要删除的库位管理ID
     * @return 结果
     */
    @Override
    public int deleteWmsWarehouseLocationByIds(Long[] ids)
    {
        return wmsWarehouseLocationMapper.deleteWmsWarehouseLocationByIds(ids);
    }

    /**
     * 删除库位管理信息
     * 
     * @param id 库位管理ID
     * @return 结果
     */
    @Override
    public int deleteWmsWarehouseLocationById(Long id)
    {
        return wmsWarehouseLocationMapper.deleteWmsWarehouseLocationById(id);
    }
}
