package com.neu.carbon.wms.mapper;

import com.neu.carbon.wms.domain.WmsWarehouse;

import java.util.List;

/**
 * 仓库管理Mapper接口
 * 
 * @author neusoft
 * @date 2022-06-24
 */
public interface WmsWarehouseMapper 
{
    /**
     * 查询仓库管理
     * 
     * @param id 仓库管理ID
     * @return 仓库管理
     */
    public WmsWarehouse selectWmsWarehouseById(Long id);

    /**
     * 查询仓库管理列表
     * 
     * @param wmsWarehouse 仓库管理
     * @return 仓库管理集合
     */
    public List<WmsWarehouse> selectWmsWarehouseList(WmsWarehouse wmsWarehouse);

    /**
     * 新增仓库管理
     * 
     * @param wmsWarehouse 仓库管理
     * @return 结果
     */
    public int insertWmsWarehouse(WmsWarehouse wmsWarehouse);

    /**
     * 修改仓库管理
     * 
     * @param wmsWarehouse 仓库管理
     * @return 结果
     */
    public int updateWmsWarehouse(WmsWarehouse wmsWarehouse);

    /**
     * 删除仓库管理
     * 
     * @param id 仓库管理ID
     * @return 结果
     */
    public int deleteWmsWarehouseById(Long id);

    /**
     * 批量删除仓库管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmsWarehouseByIds(Long[] ids);
}
