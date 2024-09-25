package com.neu.smty.mapper;

import java.util.List;

import com.neu.smty.domain.ComArea;

/**
 * 区域信息Mapper接口
 * 
 * @author neusoft
 * @date 2021-08-10
 */
public interface ComAreaMapper 
{
    /**
     * 查询区域信息
     * 
     * @param id 区域信息ID
     * @return 区域信息
     */
    public ComArea selectComAreaById(Long id);

    /**
     * 查询区域信息列表
     * 
     * @param comArea 区域信息
     * @return 区域信息集合
     */
    public List<ComArea> selectComAreaList(ComArea comArea);

    /**
     * 新增区域信息
     * 
     * @param comArea 区域信息
     * @return 结果
     */
    public int insertComArea(ComArea comArea);

    /**
     * 修改区域信息
     * 
     * @param comArea 区域信息
     * @return 结果
     */
    public int updateComArea(ComArea comArea);

    /**
     * 删除区域信息
     * 
     * @param id 区域信息ID
     * @return 结果
     */
    public int deleteComAreaById(Long id);

    /**
     * 批量删除区域信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteComAreaByIds(Long[] ids);
}
