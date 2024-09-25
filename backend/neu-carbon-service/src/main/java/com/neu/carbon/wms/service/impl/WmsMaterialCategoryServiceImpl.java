package com.neu.carbon.wms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.carbon.wms.domain.WmsMaterialCategory;
import com.neu.carbon.wms.mapper.WmsMaterialCategoryMapper;
import com.neu.carbon.wms.service.IWmsMaterialCategoryService;

/**
 * 物料分类Service业务层处理
 * 
 * @author neusoft
 * @date 2022-06-23
 */
@Service
public class WmsMaterialCategoryServiceImpl implements IWmsMaterialCategoryService 
{
    @Autowired
    private WmsMaterialCategoryMapper wmsMaterialCategoryMapper;

    /**
     * 查询物料分类
     * 
     * @param id 物料分类ID
     * @return 物料分类
     */
    @Override
    public WmsMaterialCategory selectWmsMaterialCategoryById(Long id)
    {
        return wmsMaterialCategoryMapper.selectWmsMaterialCategoryById(id);
    }

    /**
     * 查询物料分类列表
     * 
     * @param wmsMaterialCategory 物料分类
     * @return 物料分类
     */
    @Override
    public List<WmsMaterialCategory> selectWmsMaterialCategoryList(WmsMaterialCategory wmsMaterialCategory)
    {
        return wmsMaterialCategoryMapper.selectWmsMaterialCategoryList(wmsMaterialCategory);
    }

    /**
     * 新增物料分类
     * 
     * @param wmsMaterialCategory 物料分类
     * @return 结果
     */
    @Override
    public int insertWmsMaterialCategory(WmsMaterialCategory wmsMaterialCategory)
    {
        return wmsMaterialCategoryMapper.insertWmsMaterialCategory(wmsMaterialCategory);
    }

    /**
     * 修改物料分类
     * 
     * @param wmsMaterialCategory 物料分类
     * @return 结果
     */
    @Override
    public int updateWmsMaterialCategory(WmsMaterialCategory wmsMaterialCategory)
    {
        return wmsMaterialCategoryMapper.updateWmsMaterialCategory(wmsMaterialCategory);
    }

    /**
     * 批量删除物料分类
     * 
     * @param ids 需要删除的物料分类ID
     * @return 结果
     */
    @Override
    public int deleteWmsMaterialCategoryByIds(Long[] ids)
    {
        return wmsMaterialCategoryMapper.deleteWmsMaterialCategoryByIds(ids);
    }

    /**
     * 删除物料分类信息
     * 
     * @param id 物料分类ID
     * @return 结果
     */
    @Override
    public int deleteWmsMaterialCategoryById(Long id)
    {
        return wmsMaterialCategoryMapper.deleteWmsMaterialCategoryById(id);
    }
}
