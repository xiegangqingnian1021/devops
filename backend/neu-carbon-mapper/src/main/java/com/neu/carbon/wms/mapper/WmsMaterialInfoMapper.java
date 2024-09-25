package com.neu.carbon.wms.mapper;

import java.util.List;
import com.neu.carbon.wms.domain.WmsMaterialInfo;

/**
 * 物料档案管理Mapper接口
 * 
 * @author neusoft
 * @date 2022-06-23
 */
public interface WmsMaterialInfoMapper 
{
    /**
     * 查询物料档案管理
     * 
     * @param id 物料档案管理ID
     * @return 物料档案管理
     */
    public WmsMaterialInfo selectWmsMaterialInfoById(Long id);

    /**
     * 查询物料档案管理列表
     * 
     * @param wmsMaterialInfo 物料档案管理
     * @return 物料档案管理集合
     */
    public List<WmsMaterialInfo> selectWmsMaterialInfoList(WmsMaterialInfo wmsMaterialInfo);

    /**
     * 新增物料档案管理
     * 
     * @param wmsMaterialInfo 物料档案管理
     * @return 结果
     */
    public int insertWmsMaterialInfo(WmsMaterialInfo wmsMaterialInfo);

    /**
     * 修改物料档案管理
     * 
     * @param wmsMaterialInfo 物料档案管理
     * @return 结果
     */
    public int updateWmsMaterialInfo(WmsMaterialInfo wmsMaterialInfo);

    /**
     * 删除物料档案管理
     * 
     * @param id 物料档案管理ID
     * @return 结果
     */
    public int deleteWmsMaterialInfoById(Long id);

    /**
     * 批量删除物料档案管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmsMaterialInfoByIds(Long[] ids);
}
