package com.neu.carbon.wms.mapper;

import java.util.List;
import com.neu.carbon.wms.domain.WmsMaterialCategory;

/**
 * 物料分类Mapper接口
 * 
 * @author neusoft
 * @date 2022-06-23
 */
public interface WmsMaterialCategoryMapper 
{
    /**
     * 查询物料分类
     * 
     * @param id 物料分类ID
     * @return 物料分类
     */
    public WmsMaterialCategory selectWmsMaterialCategoryById(Long id);

    /**
     * 查询物料分类列表
     * 
     * @param wmsMaterialCategory 物料分类
     * @return 物料分类集合
     */
    public List<WmsMaterialCategory> selectWmsMaterialCategoryList(WmsMaterialCategory wmsMaterialCategory);

    /**
     * 新增物料分类
     * 
     * @param wmsMaterialCategory 物料分类
     * @return 结果
     */
    public int insertWmsMaterialCategory(WmsMaterialCategory wmsMaterialCategory);

    /**
     * 修改物料分类
     * 
     * @param wmsMaterialCategory 物料分类
     * @return 结果
     */
    public int updateWmsMaterialCategory(WmsMaterialCategory wmsMaterialCategory);

    /**
     * 删除物料分类
     * 
     * @param id 物料分类ID
     * @return 结果
     */
    public int deleteWmsMaterialCategoryById(Long id);

    /**
     * 批量删除物料分类
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmsMaterialCategoryByIds(Long[] ids);
}
