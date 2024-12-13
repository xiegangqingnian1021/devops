package com.neu.system.service;

import java.util.List;

import com.neu.system.domain.OpenstackProjectInfo;

/**
 * 租户信息Service接口
 * 
 * @author neuedu
 * @date 2024-09-23
 */
public interface IOpenstackProjectInfoService
{
    /**
     * 查询租户信息
     * 
     * @param projectId 租户信息ID
     * @return 租户信息
     */
    public OpenstackProjectInfo selectProjectInfoById(String projectId);

    /**
     * 查询租户信息列表
     * 
     * @param openstackProjectInfo 租户信息
     * @return 租户信息集合
     */
    public List<OpenstackProjectInfo> selectProjectInfoList(OpenstackProjectInfo openstackProjectInfo);

    /**
     * 新增租户信息
     * 
     * @param openstackProjectInfo 租户信息
     * @return 结果
     */
    public int insertProjectInfo(OpenstackProjectInfo openstackProjectInfo);

    /**
     * 修改租户信息
     * 
     * @param openstackProjectInfo 租户信息
     * @return 结果
     */
    public int updateProjectInfo(OpenstackProjectInfo openstackProjectInfo);

    /**
     * 批量删除租户信息
     * 
     * @param projectIds 需要删除的租户信息ID
     * @return 结果
     */
    public int deleteProjectInfoByIds(String[] projectIds);

    /**
     * 删除租户信息信息
     * 
     * @param projectId 租户信息ID
     * @return 结果
     */
    public int deleteProjectInfoById(String projectId);
}
