package com.neu.carbon.footprint.mapper;

import com.neu.carbon.footprint.domain.CfConfigEmissionChange;

import java.util.List;

/**
 * 变化碳排放Mapper接口
 * 
 * @author neuedu
 * @date 2022-07-22
 */
public interface CfConfigEmissionChangeMapper 
{
    /**
     * 查询变化碳排放
     * 
     * @param id 变化碳排放ID
     * @return 变化碳排放
     */
    public CfConfigEmissionChange selectCfConfigEmissionChangeById(Long id);

    /**
     * 查询变化碳排放列表
     * 
     * @param cfConfigEmissionChange 变化碳排放
     * @return 变化碳排放集合
     */
    public List<CfConfigEmissionChange> selectCfConfigEmissionChangeList(CfConfigEmissionChange cfConfigEmissionChange);

    /**
     * 新增变化碳排放
     * 
     * @param cfConfigEmissionChange 变化碳排放
     * @return 结果
     */
    public int insertCfConfigEmissionChange(CfConfigEmissionChange cfConfigEmissionChange);

    /**
     * 修改变化碳排放
     * 
     * @param cfConfigEmissionChange 变化碳排放
     * @return 结果
     */
    public int updateCfConfigEmissionChange(CfConfigEmissionChange cfConfigEmissionChange);

    /**
     * 删除变化碳排放
     * 
     * @param id 变化碳排放ID
     * @return 结果
     */
    public int deleteCfConfigEmissionChangeById(Long id);

    /**
     * 批量删除变化碳排放
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCfConfigEmissionChangeByIds(Long[] ids);
}
