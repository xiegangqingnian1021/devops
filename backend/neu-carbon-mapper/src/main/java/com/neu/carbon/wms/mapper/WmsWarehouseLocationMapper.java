package com.neu.carbon.wms.mapper;

import com.neu.carbon.wms.domain.WmsWarehouseLocation;

import java.util.List;

/**
 * 库位管理Mapper接口
 * 
 * @author neusoft
 * @date 2022-06-24
 */
public interface WmsWarehouseLocationMapper 
{
    /**
     * 查询库位管理
     * 
     * @param id 库位管理ID
     * @return 库位管理
     */
    public WmsWarehouseLocation selectWmsWarehouseLocationById(Long id);

    /**
     * 查询库位管理列表
     * 
     * @param wmsWarehouseLocation 库位管理
     * @return 库位管理集合
     */
    public List<WmsWarehouseLocation> selectWmsWarehouseLocationList(WmsWarehouseLocation wmsWarehouseLocation);

    /**
     * 新增库位管理
     * 
     * @param wmsWarehouseLocation 库位管理
     * @return 结果
     */
    public int insertWmsWarehouseLocation(WmsWarehouseLocation wmsWarehouseLocation);

    /**
     * 修改库位管理
     * 
     * @param wmsWarehouseLocation 库位管理
     * @return 结果
     */
    public int updateWmsWarehouseLocation(WmsWarehouseLocation wmsWarehouseLocation);

    /**
     * 删除库位管理
     * 
     * @param id 库位管理ID
     * @return 结果
     */
    public int deleteWmsWarehouseLocationById(Long id);

    /**
     * 批量删除库位管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmsWarehouseLocationByIds(Long[] ids);
}
