package com.neu.carbon.wms.service;

import com.neu.carbon.wms.domain.WmsWarehouseRegion;

import java.util.List;

/**
 * 库区管理Service接口
 * 
 * @author neusoft
 * @date 2022-06-24
 */
public interface IWmsWarehouseRegionService 
{
    /**
     * 查询库区管理
     * 
     * @param id 库区管理ID
     * @return 库区管理
     */
    public WmsWarehouseRegion selectWmsWarehouseRegionById(Long id);

    /**
     * 查询库区管理列表
     * 
     * @param wmsWarehouseRegion 库区管理
     * @return 库区管理集合
     */
    public List<WmsWarehouseRegion> selectWmsWarehouseRegionList(WmsWarehouseRegion wmsWarehouseRegion);

    /**
     * 新增库区管理
     * 
     * @param wmsWarehouseRegion 库区管理
     * @return 结果
     */
    public int insertWmsWarehouseRegion(WmsWarehouseRegion wmsWarehouseRegion);

    /**
     * 修改库区管理
     * 
     * @param wmsWarehouseRegion 库区管理
     * @return 结果
     */
    public int updateWmsWarehouseRegion(WmsWarehouseRegion wmsWarehouseRegion);

    /**
     * 批量删除库区管理
     * 
     * @param ids 需要删除的库区管理ID
     * @return 结果
     */
    public int deleteWmsWarehouseRegionByIds(Long[] ids);

    /**
     * 删除库区管理信息
     * 
     * @param id 库区管理ID
     * @return 结果
     */
    public int deleteWmsWarehouseRegionById(Long id);
}
