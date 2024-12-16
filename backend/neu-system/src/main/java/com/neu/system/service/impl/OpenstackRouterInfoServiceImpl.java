package com.neu.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.system.mapper.OpenstackRouterInfoMapper;
import com.neu.system.domain.OpenstackRouterInfo;
import com.neu.system.service.IOpenstackRouterInfoService;

/**
 * 路由信息Service业务层处理
 * 
 * @author neuedu
 * @date 2024-12-16
 */
@Service
public class OpenstackRouterInfoServiceImpl implements IOpenstackRouterInfoService 
{
    @Autowired
    private OpenstackRouterInfoMapper openstackRouterInfoMapper;

    /**
     * 查询路由信息
     * 
     * @param routerId 路由信息ID
     * @return 路由信息
     */
    @Override
    public OpenstackRouterInfo selectOpenstackRouterInfoById(String routerId)
    {
        return openstackRouterInfoMapper.selectOpenstackRouterInfoById(routerId);
    }

    /**
     * 查询路由信息列表
     * 
     * @param openstackRouterInfo 路由信息
     * @return 路由信息
     */
    @Override
    public List<OpenstackRouterInfo> selectOpenstackRouterInfoList(OpenstackRouterInfo openstackRouterInfo)
    {
        return openstackRouterInfoMapper.selectOpenstackRouterInfoList(openstackRouterInfo);
    }

    /**
     * 新增路由信息
     * 
     * @param openstackRouterInfo 路由信息
     * @return 结果
     */
    @Override
    public int insertOpenstackRouterInfo(OpenstackRouterInfo openstackRouterInfo)
    {
        return openstackRouterInfoMapper.insertOpenstackRouterInfo(openstackRouterInfo);
    }

    /**
     * 修改路由信息
     * 
     * @param openstackRouterInfo 路由信息
     * @return 结果
     */
    @Override
    public int updateOpenstackRouterInfo(OpenstackRouterInfo openstackRouterInfo)
    {
        return openstackRouterInfoMapper.updateOpenstackRouterInfo(openstackRouterInfo);
    }

    /**
     * 批量删除路由信息
     * 
     * @param routerIds 需要删除的路由信息ID
     * @return 结果
     */
    @Override
    public int deleteOpenstackRouterInfoByIds(String[] routerIds)
    {
        return openstackRouterInfoMapper.deleteOpenstackRouterInfoByIds(routerIds);
    }

    /**
     * 删除路由信息信息
     * 
     * @param routerId 路由信息ID
     * @return 结果
     */
    @Override
    public int deleteOpenstackRouterInfoById(String routerId)
    {
        return openstackRouterInfoMapper.deleteOpenstackRouterInfoById(routerId);
    }
}
