package com.neu.carbon.wms.service.impl;

import com.neu.carbon.wms.domain.WmsWarehouseMaterial;
import com.neu.carbon.wms.domain.WmsWarehouseMaterialDetail;
import com.neu.carbon.wms.mapper.WmsWarehouseMaterialMapper;
import com.neu.carbon.wms.service.IWmsWarehouseMaterialService;
import com.neu.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 仓库物料信息Service业务层处理
 * 
 * @author neusoft
 * @date 2022-06-24
 */
@Service
public class WmsWarehouseMaterialServiceImpl implements IWmsWarehouseMaterialService 
{
    @Autowired
    private WmsWarehouseMaterialMapper wmsWarehouseMaterialMapper; 

    /**
     * 查询仓库物料信息
     * 
     * @param id 仓库物料信息ID
     * @return 仓库物料信息
     */
    @Override
    public WmsWarehouseMaterial selectWmsWarehouseMaterialById(Long id)
    {
        return wmsWarehouseMaterialMapper.selectWmsWarehouseMaterialById(id);
    }

    /**
     * 查询仓库物料信息列表
     * 
     * @param wmsWarehouseMaterial 仓库物料信息
     * @return 仓库物料信息
     */
    @Override
    public List<WmsWarehouseMaterial> selectWmsWarehouseMaterialList(WmsWarehouseMaterial wmsWarehouseMaterial)
    {
        return wmsWarehouseMaterialMapper.selectWmsWarehouseMaterialList(wmsWarehouseMaterial);
    }

    /**
     * 新增仓库物料信息
     * 
     * @param wmsWarehouseMaterial 仓库物料信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertWmsWarehouseMaterial(WmsWarehouseMaterial wmsWarehouseMaterial)
    {
        int rows = wmsWarehouseMaterialMapper.insertWmsWarehouseMaterial(wmsWarehouseMaterial);
        insertWmsWarehouseMaterialDetail(wmsWarehouseMaterial);
        return rows;
    }

    /**
     * 修改仓库物料信息
     * 
     * @param wmsWarehouseMaterial 仓库物料信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateWmsWarehouseMaterial(WmsWarehouseMaterial wmsWarehouseMaterial)
    {
        wmsWarehouseMaterialMapper.deleteWmsWarehouseMaterialDetailByWhMaterialId(wmsWarehouseMaterial.getId());
        insertWmsWarehouseMaterialDetail(wmsWarehouseMaterial);
        return wmsWarehouseMaterialMapper.updateWmsWarehouseMaterial(wmsWarehouseMaterial);
    }

    /**
     * 批量删除仓库物料信息
     * 
     * @param ids 需要删除的仓库物料信息ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteWmsWarehouseMaterialByIds(Long[] ids)
    {
        wmsWarehouseMaterialMapper.deleteWmsWarehouseMaterialDetailByWhMaterialIds(ids);
        return wmsWarehouseMaterialMapper.deleteWmsWarehouseMaterialByIds(ids);
    }

    /**
     * 删除仓库物料信息信息
     * 
     * @param id 仓库物料信息ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteWmsWarehouseMaterialById(Long id)
    {
        wmsWarehouseMaterialMapper.deleteWmsWarehouseMaterialDetailByWhMaterialId(id);
        return wmsWarehouseMaterialMapper.deleteWmsWarehouseMaterialById(id);
    }

    /**
     * 新增仓库物料明细信息
     * 
     * @param wmsWarehouseMaterial 仓库物料信息对象
     */
    public void insertWmsWarehouseMaterialDetail(WmsWarehouseMaterial wmsWarehouseMaterial)
    {
        List<WmsWarehouseMaterialDetail> wmsWarehouseMaterialDetailList = wmsWarehouseMaterial.getWmsWarehouseMaterialDetailList();
        Long id = wmsWarehouseMaterial.getId();
        if (StringUtils.isNotNull(wmsWarehouseMaterialDetailList))
        {
            List<WmsWarehouseMaterialDetail> list = new ArrayList<WmsWarehouseMaterialDetail>();
            for (WmsWarehouseMaterialDetail wmsWarehouseMaterialDetail : wmsWarehouseMaterialDetailList)
            {
                wmsWarehouseMaterialDetail.setWhMaterialId(id);
                wmsWarehouseMaterialDetail.setWhId(wmsWarehouseMaterial.getWarehouseId());
                list.add(wmsWarehouseMaterialDetail);
            }
            if (list.size() > 0)
            {
                wmsWarehouseMaterialMapper.batchWmsWarehouseMaterialDetail(list);
            }
        }
    }
    
    @Transactional
	@Override
	public void updateMaterialTotalInventory(Long id) {
    	updateWarehouseInventory(id);
	}

	private void updateWarehouseInventory(Long id) {
		WmsWarehouseMaterial whMaterial = wmsWarehouseMaterialMapper.selectWmsWarehouseMaterialById(id);
    	List<WmsWarehouseMaterialDetail> whMaterialDetail = whMaterial.getWmsWarehouseMaterialDetailList();
    	double total = 0;
    	double lock = 0;
    	if(whMaterialDetail!=null&&!whMaterialDetail.isEmpty()) {
    		for(WmsWarehouseMaterialDetail detail:whMaterialDetail)
    		{
    			double mtotal = detail.getInventory()==null?0:detail.getInventory();
    			double mlock = detail.getLockInventory()==null?0:detail.getLockInventory();
    			total = total+mtotal;
    			lock = lock+mlock;
    		}
    	}
    	whMaterial.setTotalInventory(total);
		whMaterial.setFreezeInventory(lock);
    	wmsWarehouseMaterialMapper.updateWmsWarehouseMaterial(whMaterial);
	}
    
    @Transactional
	@Override
	public void updateMaterialTotalInventory(Long warehouseId, Long materialId) {
    	WmsWarehouseMaterial cond = new WmsWarehouseMaterial();
    	cond.setWarehouseId(warehouseId);
    	cond.setMaterialId(materialId);
    	List<WmsWarehouseMaterial> whMatreialList = wmsWarehouseMaterialMapper.selectWmsWarehouseMaterialList(cond);
    	if(whMatreialList==null||whMatreialList.isEmpty()) {
    		return;
    	}
    	WmsWarehouseMaterial whMaterial = whMatreialList.get(0);
    	updateWarehouseInventory(whMaterial.getId());
    }
}
