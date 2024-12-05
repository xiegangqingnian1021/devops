package com.neu.system.service;

import java.util.List;
import com.neu.system.domain.OpenstackSubnetInfo;

/**
 * 子网管理Service接口
 * 
 * @author neuedu
 * @date 2024-12-05
 */
public interface IOpenstackSubnetInfoService 
{
    /**
     * 查询子网管理
     * 
     * @param subnetId 子网管理ID
     * @return 子网管理
     */
    public OpenstackSubnetInfo selectOpenstackSubnetInfoById(String subnetId);

    /**
     * 查询子网管理列表
     * 
     * @param openstackSubnetInfo 子网管理
     * @return 子网管理集合
     */
    public List<OpenstackSubnetInfo> selectOpenstackSubnetInfoList(OpenstackSubnetInfo openstackSubnetInfo);

    /**
     * 新增子网管理
     * 
     * @param openstackSubnetInfo 子网管理
     * @return 结果
     */
    public int insertOpenstackSubnetInfo(OpenstackSubnetInfo openstackSubnetInfo);

    /**
     * 修改子网管理
     * 
     * @param openstackSubnetInfo 子网管理
     * @return 结果
     */
    public int updateOpenstackSubnetInfo(OpenstackSubnetInfo openstackSubnetInfo);

    /**
     * 批量删除子网管理
     * 
     * @param subnetIds 需要删除的子网管理ID
     * @return 结果
     */
    public int deleteOpenstackSubnetInfoByIds(String[] subnetIds);

    /**
     * 删除子网管理信息
     * 
     * @param subnetId 子网管理ID
     * @return 结果
     */
    public int deleteOpenstackSubnetInfoById(String subnetId);
}
