package com.neu.carbon.wms.service;

import com.neu.carbon.wms.domain.WmsWarehouseMaterial;

import java.util.List;

/**
 * 仓库物料信息Service接口
 * 
 * @author neusoft
 * @date 2022-06-24
 */
public interface IWmsWarehouseMaterialService 
{
    /**
     * 查询仓库物料信息
     * 
     * @param id 仓库物料信息ID
     * @return 仓库物料信息
     */
    public WmsWarehouseMaterial selectWmsWarehouseMaterialById(Long id);

    /**
     * 查询仓库物料信息列表
     * 
     * @param wmsWarehouseMaterial 仓库物料信息
     * @return 仓库物料信息集合
     */
    public List<WmsWarehouseMaterial> selectWmsWarehouseMaterialList(WmsWarehouseMaterial wmsWarehouseMaterial);

    /**
     * 新增仓库物料信息
     * 
     * @param wmsWarehouseMaterial 仓库物料信息
     * @return 结果
     */
    public int insertWmsWarehouseMaterial(WmsWarehouseMaterial wmsWarehouseMaterial);

    /**
     * 修改仓库物料信息
     * 
     * @param wmsWarehouseMaterial 仓库物料信息
     * @return 结果
     */
    public int updateWmsWarehouseMaterial(WmsWarehouseMaterial wmsWarehouseMaterial);

    /**
     * 批量删除仓库物料信息
     * 
     * @param ids 需要删除的仓库物料信息ID
     * @return 结果
     */
    public int deleteWmsWarehouseMaterialByIds(Long[] ids);

    /**
     * 删除仓库物料信息信息
     * 
     * @param id 仓库物料信息ID
     * @return 结果
     */
    public int deleteWmsWarehouseMaterialById(Long id);
    
    /**
     * 更新物料总库存
     * @param id
     */
    public void updateMaterialTotalInventory(Long id) ;
    
    /**
     * 更新物料总库存
     * @param id
     */
    public void updateMaterialTotalInventory(Long warehouseId,Long materialId) ;
}
