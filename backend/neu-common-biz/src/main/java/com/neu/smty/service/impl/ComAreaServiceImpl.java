package com.neu.smty.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.smty.domain.ComArea;
import com.neu.smty.mapper.ComAreaMapper;
import com.neu.smty.service.IComAreaService;

/**
 * 区域信息Service业务层处理
 * 
 * @author neusoft
 * @date 2021-08-10
 */
@Service
public class ComAreaServiceImpl implements IComAreaService 
{
    @Autowired
    private ComAreaMapper comAreaMapper;

    /**
     * 查询区域信息
     * 
     * @param id 区域信息ID
     * @return 区域信息
     */
    @Override
    public ComArea selectComAreaById(Long id)
    {
        return comAreaMapper.selectComAreaById(id);
    }

    /**
     * 查询区域信息列表
     * 
     * @param comArea 区域信息
     * @return 区域信息
     */
    @Override
    public List<ComArea> selectComAreaList(ComArea comArea)
    {
        return comAreaMapper.selectComAreaList(comArea);
    }

    /**
     * 新增区域信息
     * 
     * @param comArea 区域信息
     * @return 结果
     */
    @Override
    public int insertComArea(ComArea comArea)
    {
        return comAreaMapper.insertComArea(comArea);
    }

    /**
     * 修改区域信息
     * 
     * @param comArea 区域信息
     * @return 结果
     */
    @Override
    public int updateComArea(ComArea comArea)
    {
        return comAreaMapper.updateComArea(comArea);
    }

    /**
     * 批量删除区域信息
     * 
     * @param ids 需要删除的区域信息ID
     * @return 结果
     */
    @Override
    public int deleteComAreaByIds(Long[] ids)
    {
        return comAreaMapper.deleteComAreaByIds(ids);
    }

    /**
     * 删除区域信息信息
     * 
     * @param id 区域信息ID
     * @return 结果
     */
    @Override
    public int deleteComAreaById(Long id)
    {
        return comAreaMapper.deleteComAreaById(id);
    }
}
