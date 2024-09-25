package com.neu.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.system.mapper.ProjectInfoMapper;
import com.neu.system.domain.ProjectInfo;
import com.neu.system.service.IProjectInfoService;

/**
 * 租户信息Service业务层处理
 * 
 * @author neuedu
 * @date 2024-09-23
 */
@Service
public class ProjectInfoServiceImpl implements IProjectInfoService 
{
    @Autowired
    private ProjectInfoMapper projectInfoMapper;

    /**
     * 查询租户信息
     * 
     * @param projectId 租户信息ID
     * @return 租户信息
     */
    @Override
    public ProjectInfo selectProjectInfoById(String projectId)
    {
        return projectInfoMapper.selectProjectInfoById(projectId);
    }

    /**
     * 查询租户信息列表
     * 
     * @param projectInfo 租户信息
     * @return 租户信息
     */
    @Override
    public List<ProjectInfo> selectProjectInfoList(ProjectInfo projectInfo)
    {
        return projectInfoMapper.selectProjectInfoList(projectInfo);
    }

    /**
     * 新增租户信息
     * 
     * @param projectInfo 租户信息
     * @return 结果
     */
    @Override
    public int insertProjectInfo(ProjectInfo projectInfo)
    {
        return projectInfoMapper.insertProjectInfo(projectInfo);
    }

    /**
     * 修改租户信息
     * 
     * @param projectInfo 租户信息
     * @return 结果
     */
    @Override
    public int updateProjectInfo(ProjectInfo projectInfo)
    {
        return projectInfoMapper.updateProjectInfo(projectInfo);
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
        return projectInfoMapper.deleteProjectInfoByIds(projectIds);
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
        return projectInfoMapper.deleteProjectInfoById(projectId);
    }
}
