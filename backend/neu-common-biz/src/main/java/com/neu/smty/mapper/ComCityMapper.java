package com.neu.smty.mapper;

import java.util.List;

import com.neu.smty.domain.ComCity;

/**
 * 城市信息Mapper接口
 * 
 * @author neusoft
 * @date 2021-08-12
 */
public interface ComCityMapper 
{
    /**
     * 查询城市信息
     * 
     * @param id 城市信息ID
     * @return 城市信息
     */
    public ComCity selectComCityById(Long id);

    /**
     * 查询城市信息列表
     * 
     * @param comCity 城市信息
     * @return 城市信息集合
     */
    public List<ComCity> selectComCityList(ComCity comCity);

    /**
     * 新增城市信息
     * 
     * @param comCity 城市信息
     * @return 结果
     */
    public int insertComCity(ComCity comCity);

    /**
     * 修改城市信息
     * 
     * @param comCity 城市信息
     * @return 结果
     */
    public int updateComCity(ComCity comCity);

    /**
     * 删除城市信息
     * 
     * @param id 城市信息ID
     * @return 结果
     */
    public int deleteComCityById(Long id);

    /**
     * 批量删除城市信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteComCityByIds(Long[] ids);
}
