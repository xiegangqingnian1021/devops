package com.neu.carbon.footprint.service;

import com.neu.carbon.footprint.domain.CfConfigEmissionFixed;

import java.util.List;

/**
 * 固定碳排放Service接口
 * 
 * @author neuedu
 * @date 2022-07-22
 */
public interface ICfConfigEmissionFixedService 
{
    /**
     * 查询固定碳排放
     * 
     * @param id 固定碳排放ID
     * @return 固定碳排放
     */
    public CfConfigEmissionFixed selectCfConfigEmissionFixedById(Long id);

    /**
     * 查询固定碳排放列表
     * 
     * @param cfConfigEmissionFixed 固定碳排放
     * @return 固定碳排放集合
     */
    public List<CfConfigEmissionFixed> selectCfConfigEmissionFixedList(CfConfigEmissionFixed cfConfigEmissionFixed);

    /**
     * 新增固定碳排放
     * 
     * @param cfConfigEmissionFixed 固定碳排放
     * @return 结果
     */
    public int insertCfConfigEmissionFixed(CfConfigEmissionFixed cfConfigEmissionFixed);

    /**
     * 修改固定碳排放
     * 
     * @param cfConfigEmissionFixed 固定碳排放
     * @return 结果
     */
    public int updateCfConfigEmissionFixed(CfConfigEmissionFixed cfConfigEmissionFixed);

    /**
     * 批量删除固定碳排放
     * 
     * @param ids 需要删除的固定碳排放ID
     * @return 结果
     */
    public int deleteCfConfigEmissionFixedByIds(Long[] ids);

    /**
     * 删除固定碳排放信息
     * 
     * @param id 固定碳排放ID
     * @return 结果
     */
    public int deleteCfConfigEmissionFixedById(Long id);
    
    /**
     * 获取月度生产用水量
     * @return
     */
    public double getPrdWaterConsumeByMonth();
    
    /**
     * 获取月度办公用水量
     * @return
     */
    public double getOfficeWaterConsumeByMonth();
    
    /**
     * 获取月度办公用电量
     * @return
     */
    public double getOfficePowerConsumeByMonth();
}
