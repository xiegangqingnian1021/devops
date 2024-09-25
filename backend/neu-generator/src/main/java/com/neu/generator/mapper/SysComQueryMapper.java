package com.neu.generator.mapper;

import java.util.List;

import com.neu.common.core.domain.entity.SysDictData;
import com.neu.generator.domain.SysComQuery;

/**
 * 通用查询Mapper接口
 * 
 * @author neusoft
 * @date 2021-08-18
 */
public interface SysComQueryMapper 
{
    /**
     * 查询通用查询
     * 
     * @param id 通用查询ID
     * @return 通用查询
     */
    public SysComQuery selectSysComQueryById(Long id);

    /**
     * 查询通用查询列表
     * 
     * @param sysComQuery 通用查询
     * @return 通用查询集合
     */
    public List<SysComQuery> selectSysComQueryList(SysComQuery sysComQuery);

    /**
     * 新增通用查询
     * 
     * @param sysComQuery 通用查询
     * @return 结果
     */
    public int insertSysComQuery(SysComQuery sysComQuery);

    /**
     * 修改通用查询
     * 
     * @param sysComQuery 通用查询
     * @return 结果
     */
    public int updateSysComQuery(SysComQuery sysComQuery);

    /**
     * 删除通用查询
     * 
     * @param id 通用查询ID
     * @return 结果
     */
    public int deleteSysComQueryById(Long id);

    /**
     * 批量删除通用查询
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysComQueryByIds(Long[] ids);
    
    public List<SysDictData> selectQueryData(SysComQuery sysComQuery);
}
