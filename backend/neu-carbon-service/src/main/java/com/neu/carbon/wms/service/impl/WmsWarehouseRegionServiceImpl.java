package com.neu.carbon.wms.service.impl;

import com.neu.carbon.wms.domain.WmsWarehouseRegion;
import com.neu.carbon.wms.mapper.WmsWarehouseRegionMapper;
import com.neu.carbon.wms.service.IWmsWarehouseRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 库区管理Service业务层处理
 * 
 * @author neusoft
 * @date 2022-06-24
 */
@Service
public class WmsWarehouseRegionServiceImpl implements IWmsWarehouseRegionService 
{
    @Autowired
    private WmsWarehouseRegionMapper wmsWarehouseRegionMapper;

    /**
     * 查询库区管理
     * 
     * @param id 库区管理ID
     * @return 库区管理
     */
    @Override
    public WmsWarehouseRegion selectWmsWarehouseRegionById(Long id)
    {
        return wmsWarehouseRegionMapper.selectWmsWarehouseRegionById(id);
    }

    /**
     * 查询库区管理列表
     * 
     * @param wmsWarehouseRegion 库区管理
     * @return 库区管理
     */
    @Override
    public List<WmsWarehouseRegion> selectWmsWarehouseRegionList(WmsWarehouseRegion wmsWarehouseRegion)
    {
        return wmsWarehouseRegionMapper.selectWmsWarehouseRegionList(wmsWarehouseRegion);
    }

    /**
     * 新增库区管理
     * 
     * @param wmsWarehouseRegion 库区管理
     * @return 结果
     */
    @Override
    public int insertWmsWarehouseRegion(WmsWarehouseRegion wmsWarehouseRegion)
    {
        return wmsWarehouseRegionMapper.insertWmsWarehouseRegion(wmsWarehouseRegion);
    }

    /**
     * 修改库区管理
     * 
     * @param wmsWarehouseRegion 库区管理
     * @return 结果
     */
    @Override
    public int updateWmsWarehouseRegion(WmsWarehouseRegion wmsWarehouseRegion)
    {
        return wmsWarehouseRegionMapper.updateWmsWarehouseRegion(wmsWarehouseRegion);
    }

    /**
     * 批量删除库区管理
     * 
     * @param ids 需要删除的库区管理ID
     * @return 结果
     */
    @Override
    public int deleteWmsWarehouseRegionByIds(Long[] ids)
    {
        return wmsWarehouseRegionMapper.deleteWmsWarehouseRegionByIds(ids);
    }

    /**
     * 删除库区管理信息
     * 
     * @param id 库区管理ID
     * @return 结果
     */
    @Override
    public int deleteWmsWarehouseRegionById(Long id)
    {
        return wmsWarehouseRegionMapper.deleteWmsWarehouseRegionById(id);
    }
}
