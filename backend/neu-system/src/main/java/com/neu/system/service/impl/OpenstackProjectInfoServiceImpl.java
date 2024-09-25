package com.neu.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.system.mapper.OpenstackProjectInfoMapper;
import com.neu.system.domain.OpenstackProjectInfo;
import com.neu.system.service.IOpenstackProjectInfoService;

/**
 * 租户信息Service业务层处理
 * 
 * @author neuedu
 * @date 2024-09-23
 */
@Service
public class OpenstackProjectInfoServiceImpl implements IOpenstackProjectInfoService
{
    @Autowired
    private OpenstackProjectInfoMapper openstackProjectInfoMapper;

    /**
     * 查询租户信息
     * 
     * @param projectId 租户信息ID
     * @return 租户信息
     */
    @Override
    public OpenstackProjectInfo selectProjectInfoById(String projectId)
    {
        return openstackProjectInfoMapper.selectProjectInfoById(projectId);
    }

    /**
     * 查询租户信息列表
     * 
     * @param openstackProjectInfo 租户信息
     * @return 租户信息
     */
    @Override
    public List<OpenstackProjectInfo> selectProjectInfoList(OpenstackProjectInfo openstackProjectInfo)
    {
        return openstackProjectInfoMapper.selectProjectInfoList(openstackProjectInfo);
    }

    /**
     * 新增租户信息
     * 
     * @param openstackProjectInfo 租户信息
     * @return 结果
     */
    @Override
    public int insertProjectInfo(OpenstackProjectInfo openstackProjectInfo)
    {
        return openstackProjectInfoMapper.insertProjectInfo(openstackProjectInfo);
    }

    /**
     * 修改租户信息
     * 
     * @param openstackProjectInfo 租户信息
     * @return 结果
     */
    @Override
    public int updateProjectInfo(OpenstackProjectInfo openstackProjectInfo)
    {
        return openstackProjectInfoMapper.updateProjectInfo(openstackProjectInfo);
    }

    /**
     * 批量删除租户信息
     * 
     * @param projectIds 需要删除的租户信息ID
     * @return 结果
     */
    @Override
    public int deleteProjectInfoByIds(String[] projectIds)
    {
        return openstackProjectInfoMapper.deleteProjectInfoByIds(projectIds);
    }

    /**
     * 删除租户信息信息
     * 
     * @param projectId 租户信息ID
     * @return 结果
     */
    @Override
    public int deleteProjectInfoById(String projectId)
    {
        return openstackProjectInfoMapper.deleteProjectInfoById(projectId);
    }
}
