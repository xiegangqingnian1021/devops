package com.neu.carbon.footprint.mapper;

import com.neu.carbon.footprint.domain.CfConfigNeutral;

import java.util.List;

/**
 * 碳中和计算比例Mapper接口
 * 
 * @author neuedu
 * @date 2022-07-22
 */
public interface CfConfigNeutralMapper 
{
    /**
     * 查询碳中和计算比例
     * 
     * @param id 碳中和计算比例ID
     * @return 碳中和计算比例
     */
    public CfConfigNeutral selectCfConfigNeutralById(Long id);

    /**
     * 查询碳中和计算比例列表
     * 
     * @param cfConfigNeutral 碳中和计算比例
     * @return 碳中和计算比例集合
     */
    public List<CfConfigNeutral> selectCfConfigNeutralList(CfConfigNeutral cfConfigNeutral);

    /**
     * 新增碳中和计算比例
     * 
     * @param cfConfigNeutral 碳中和计算比例
     * @return 结果
     */
    public int insertCfConfigNeutral(CfConfigNeutral cfConfigNeutral);

    /**
     * 修改碳中和计算比例
     * 
     * @param cfConfigNeutral 碳中和计算比例
     * @return 结果
     */
    public int updateCfConfigNeutral(CfConfigNeutral cfConfigNeutral);

    /**
     * 删除碳中和计算比例
     * 
     * @param id 碳中和计算比例ID
     * @return 结果
     */
    public int deleteCfConfigNeutralById(Long id);

    /**
     * 批量删除碳中和计算比例
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCfConfigNeutralByIds(Long[] ids);
}
