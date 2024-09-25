package com.neu.carbon.footprint.service;

import com.neu.carbon.footprint.domain.CfConfigEmissionChange;

import java.util.List;

/**
 * 变化碳排放Service接口
 * 
 * @author neuedu
 * @date 2022-07-22
 */
public interface ICfConfigEmissionChangeService 
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
     * 批量删除变化碳排放
     * 
     * @param ids 需要删除的变化碳排放ID
     * @return 结果
     */
    public int deleteCfConfigEmissionChangeByIds(Long[] ids);

    /**
     * 删除变化碳排放信息
     * 
     * @param id 变化碳排放ID
     * @return 结果
     */
    public int deleteCfConfigEmissionChangeById(Long id);
    
    /**
     * 获取生产节电数
     * @param days
     * @return
     */
    public double getProductPowerSaveAmount(int days);
    
    /**
     * 获取办公节电数
     * @param days
     * @return
     */
    public double getOfficePowerSaveAmount(int days);
    
    /**
     * 获取生产节水数
     * @param days
     * @return
     */
    public double getProductWaterSaveAmount(int days);
    
    /**
     * 获取办公节水数
     * @param days
     * @return
     */
    public double getOfficeWaterSaveAmount(int days);
}
