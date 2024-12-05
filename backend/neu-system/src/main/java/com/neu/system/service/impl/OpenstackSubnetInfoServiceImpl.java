package com.neu.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.system.mapper.OpenstackSubnetInfoMapper;
import com.neu.system.domain.OpenstackSubnetInfo;
import com.neu.system.service.IOpenstackSubnetInfoService;

/**
 * 子网管理Service业务层处理
 * 
 * @author neuedu
 * @date 2024-12-05
 */
@Service
public class OpenstackSubnetInfoServiceImpl implements IOpenstackSubnetInfoService 
{
    @Autowired
    private OpenstackSubnetInfoMapper openstackSubnetInfoMapper;

    /**
     * 查询子网管理
     * 
     * @param subnetId 子网管理ID
     * @return 子网管理
     */
    @Override
    public OpenstackSubnetInfo selectOpenstackSubnetInfoById(String subnetId)
    {
        return openstackSubnetInfoMapper.selectOpenstackSubnetInfoById(subnetId);
    }

    /**
     * 查询子网管理列表
     * 
     * @param openstackSubnetInfo 子网管理
     * @return 子网管理
     */
    @Override
    public List<OpenstackSubnetInfo> selectOpenstackSubnetInfoList(OpenstackSubnetInfo openstackSubnetInfo)
    {
        return openstackSubnetInfoMapper.selectOpenstackSubnetInfoList(openstackSubnetInfo);
    }

    /**
     * 新增子网管理
     * 
     * @param openstackSubnetInfo 子网管理
     * @return 结果
     */
    @Override
    public int insertOpenstackSubnetInfo(OpenstackSubnetInfo openstackSubnetInfo)
    {
        return openstackSubnetInfoMapper.insertOpenstackSubnetInfo(openstackSubnetInfo);
    }

    /**
     * 修改子网管理
     * 
     * @param openstackSubnetInfo 子网管理
     * @return 结果
     */
    @Override
    public int updateOpenstackSubnetInfo(OpenstackSubnetInfo openstackSubnetInfo)
    {
        return openstackSubnetInfoMapper.updateOpenstackSubnetInfo(openstackSubnetInfo);
    }

    /**
     * 批量删除子网管理
     * 
     * @param subnetIds 需要删除的子网管理ID
     * @return 结果
     */
    @Override
    public int deleteOpenstackSubnetInfoByIds(String[] subnetIds)
    {
        return openstackSubnetInfoMapper.deleteOpenstackSubnetInfoByIds(subnetIds);
    }

    /**
     * 删除子网管理信息
     * 
     * @param subnetId 子网管理ID
     * @return 结果
     */
    @Override
    public int deleteOpenstackSubnetInfoById(String subnetId)
    {
        return openstackSubnetInfoMapper.deleteOpenstackSubnetInfoById(subnetId);
    }
}
