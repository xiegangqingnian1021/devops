package com.neu.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.system.mapper.OpenstackNetworkInfoMapper;
import com.neu.system.domain.OpenstackNetworkInfo;
import com.neu.system.service.IOpenstackNetworkInfoService;

/**
 * 网络管理Service业务层处理
 * 
 * @author neuedu
 * @date 2024-12-02
 */
@Service
public class OpenstackNetworkInfoServiceImpl implements IOpenstackNetworkInfoService 
{
    @Autowired
    private OpenstackNetworkInfoMapper openstackNetworkInfoMapper;

    /**
     * 查询网络管理
     * 
     * @param networkId 网络管理ID
     * @return 网络管理
     */
    @Override
    public OpenstackNetworkInfo selectOpenstackNetworkInfoById(String networkId)
    {
        return openstackNetworkInfoMapper.selectOpenstackNetworkInfoById(networkId);
    }

    /**
     * 查询网络管理列表
     * 
     * @param openstackNetworkInfo 网络管理
     * @return 网络管理
     */
    @Override
    public List<OpenstackNetworkInfo> selectOpenstackNetworkInfoList(OpenstackNetworkInfo openstackNetworkInfo)
    {
        return openstackNetworkInfoMapper.selectOpenstackNetworkInfoList(openstackNetworkInfo);
    }

    /**
     * 新增网络管理
     * 
     * @param openstackNetworkInfo 网络管理
     * @return 结果
     */
    @Override
    public int insertOpenstackNetworkInfo(OpenstackNetworkInfo openstackNetworkInfo)
    {
        return openstackNetworkInfoMapper.insertOpenstackNetworkInfo(openstackNetworkInfo);
    }

    /**
     * 修改网络管理
     * 
     * @param openstackNetworkInfo 网络管理
     * @return 结果
     */
    @Override
    public int updateOpenstackNetworkInfo(OpenstackNetworkInfo openstackNetworkInfo)
    {
        return openstackNetworkInfoMapper.updateOpenstackNetworkInfo(openstackNetworkInfo);
    }

    /**
     * 批量删除网络管理
     * 
     * @param networkIds 需要删除的网络管理ID
     * @return 结果
     */
    @Override
    public int deleteOpenstackNetworkInfoByIds(String[] networkIds)
    {
        return openstackNetworkInfoMapper.deleteOpenstackNetworkInfoByIds(networkIds);
    }

    /**
     * 删除网络管理信息
     * 
     * @param networkId 网络管理ID
     * @return 结果
     */
    @Override
    public int deleteOpenstackNetworkInfoById(String networkId)
    {
        return openstackNetworkInfoMapper.deleteOpenstackNetworkInfoById(networkId);
    }
}
