package com.neu.carbon.wms.mapper;

import com.neu.carbon.wms.domain.WmsWarehouseMaterial;
import com.neu.carbon.wms.domain.WmsWarehouseMaterialDetail;

import java.util.List;

/**
 * 仓库物料信息Mapper接口
 * 
 * @author neusoft
 * @date 2022-06-24
 */
public interface WmsWarehouseMaterialMapper 
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
     * 删除仓库物料信息
     * 
     * @param id 仓库物料信息ID
     * @return 结果
     */
    public int deleteWmsWarehouseMaterialById(Long id);

    /**
     * 批量删除仓库物料信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmsWarehouseMaterialByIds(Long[] ids);

    /**
     * 批量删除仓库物料明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmsWarehouseMaterialDetailByWhMaterialIds(Long[] ids);
    
    /**
     * 批量新增仓库物料明细
     * 
     * @param wmsWarehouseMaterialDetailList 仓库物料明细列表
     * @return 结果
     */
    public int batchWmsWarehouseMaterialDetail(List<WmsWarehouseMaterialDetail> wmsWarehouseMaterialDetailList);
    

    /**
     * 通过仓库物料信息ID删除仓库物料明细信息
     * 
     * @param id 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmsWarehouseMaterialDetailByWhMaterialId(Long id);
}
