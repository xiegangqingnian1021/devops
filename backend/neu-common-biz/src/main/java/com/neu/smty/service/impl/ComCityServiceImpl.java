package com.neu.smty.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.smty.domain.ComCity;
import com.neu.smty.mapper.ComCityMapper;
import com.neu.smty.service.IComCityService;

/**
 * 城市信息Service业务层处理
 * 
 * @author neusoft
 * @date 2021-08-12
 */
@Service
public class ComCityServiceImpl implements IComCityService 
{
    @Autowired
    private ComCityMapper comCityMapper;

    /**
     * 查询城市信息
     * 
     * @param id 城市信息ID
     * @return 城市信息
     */
    @Override
    public ComCity selectComCityById(Long id)
    {
        return comCityMapper.selectComCityById(id);
    }

    /**
     * 查询城市信息列表
     * 
     * @param comCity 城市信息
     * @return 城市信息
     */
    @Override
    public List<ComCity> selectComCityList(ComCity comCity)
    {
        return comCityMapper.selectComCityList(comCity);
    }

    /**
     * 新增城市信息
     * 
     * @param comCity 城市信息
     * @return 结果
     */
    @Override
    public int insertComCity(ComCity comCity)
    {
        return comCityMapper.insertComCity(comCity);
    }

    /**
     * 修改城市信息
     * 
     * @param comCity 城市信息
     * @return 结果
     */
    @Override
    public int updateComCity(ComCity comCity)
    {
        return comCityMapper.updateComCity(comCity);
    }

    /**
     * 批量删除城市信息
     * 
     * @param ids 需要删除的城市信息ID
     * @return 结果
     */
    @Override
    public int deleteComCityByIds(Long[] ids)
    {
        return comCityMapper.deleteComCityByIds(ids);
    }

    /**
     * 删除城市信息信息
     * 
     * @param id 城市信息ID
     * @return 结果
     */
    @Override
    public int deleteComCityById(Long id)
    {
        return comCityMapper.deleteComCityById(id);
    }
}
