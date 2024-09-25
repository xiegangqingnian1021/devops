package com.neu.carbon.wms.service.impl;

import com.neu.carbon.wms.domain.WmsWarehouse;
import com.neu.carbon.wms.mapper.WmsWarehouseMapper;
import com.neu.carbon.wms.service.IWmsWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 仓库管理Service业务层处理
 * 
 * @author neusoft
 * @date 2022-06-24
 */
@Service
public class WmsWarehouseServiceImpl implements IWmsWarehouseService 
{
    @Autowired
    private WmsWarehouseMapper wmsWarehouseMapper;

    /**
     * 查询仓库管理
     * 
     * @param id 仓库管理ID
     * @return 仓库管理
     */
    @Override
    public WmsWarehouse selectWmsWarehouseById(Long id)
    {
        return wmsWarehouseMapper.selectWmsWarehouseById(id);
    }

    /**
     * 查询仓库管理列表
     * 
     * @param wmsWarehouse 仓库管理
     * @return 仓库管理
     */
    @Override
    public List<WmsWarehouse> selectWmsWarehouseList(WmsWarehouse wmsWarehouse)
    {
        return wmsWarehouseMapper.selectWmsWarehouseList(wmsWarehouse);
    }

    /**
     * 新增仓库管理
     * 
     * @param wmsWarehouse 仓库管理
     * @return 结果
     */
    @Override
    public int insertWmsWarehouse(WmsWarehouse wmsWarehouse)
    {
        return wmsWarehouseMapper.insertWmsWarehouse(wmsWarehouse);
    }

    /**
     * 修改仓库管理
     * 
     * @param wmsWarehouse 仓库管理
     * @return 结果
     */
    @Override
    public int updateWmsWarehouse(WmsWarehouse wmsWarehouse)
    {
        return wmsWarehouseMapper.updateWmsWarehouse(wmsWarehouse);
    }

    /**
     * 批量删除仓库管理
     * 
     * @param ids 需要删除的仓库管理ID
     * @return 结果
     */
    @Override
    public int deleteWmsWarehouseByIds(Long[] ids)
    {
        return wmsWarehouseMapper.deleteWmsWarehouseByIds(ids);
    }

    /**
     * 删除仓库管理信息
     * 
     * @param id 仓库管理ID
     * @return 结果
     */
    @Override
    public int deleteWmsWarehouseById(Long id)
    {
        return wmsWarehouseMapper.deleteWmsWarehouseById(id);
    }
}
