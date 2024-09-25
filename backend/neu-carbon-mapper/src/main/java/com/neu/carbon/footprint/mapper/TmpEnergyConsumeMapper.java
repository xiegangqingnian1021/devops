package com.neu.carbon.footprint.mapper;

import com.neu.carbon.footprint.domain.TmpEnergyConsume;

import java.util.List;

/**
 * 能耗临时表Mapper接口
 * 
 * @author neuedu
 * @date 2022-07-31
 */
public interface TmpEnergyConsumeMapper 
{
    /**
     * 查询能耗临时表
     * 
     * @param id 能耗临时表ID
     * @return 能耗临时表
     */
    public TmpEnergyConsume selectTmpEnergyConsumeById(Long id);

    /**
     * 查询能耗临时表列表
     * 
     * @param tmpEnergyConsume 能耗临时表
     * @return 能耗临时表集合
     */
    public List<TmpEnergyConsume> selectTmpEnergyConsumeList(TmpEnergyConsume tmpEnergyConsume);

    /**
     * 新增能耗临时表
     * 
     * @param tmpEnergyConsume 能耗临时表
     * @return 结果
     */
    public int insertTmpEnergyConsume(TmpEnergyConsume tmpEnergyConsume);

    /**
     * 修改能耗临时表
     * 
     * @param tmpEnergyConsume 能耗临时表
     * @return 结果
     */
    public int updateTmpEnergyConsume(TmpEnergyConsume tmpEnergyConsume);

    /**
     * 删除能耗临时表
     * 
     * @param id 能耗临时表ID
     * @return 结果
     */
    public int deleteTmpEnergyConsumeById(Long id);

    /**
     * 批量删除能耗临时表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTmpEnergyConsumeByIds(Long[] ids);
}
