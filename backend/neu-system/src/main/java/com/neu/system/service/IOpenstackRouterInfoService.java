package com.neu.system.service;

import java.util.List;
import com.neu.system.domain.OpenstackRouterInfo;

/**
 * 路由信息Service接口
 * 
 * @author neuedu
 * @date 2024-12-16
 */
public interface IOpenstackRouterInfoService 
{
    /**
     * 查询路由信息
     * 
     * @param routerId 路由信息ID
     * @return 路由信息
     */
    public OpenstackRouterInfo selectOpenstackRouterInfoById(String routerId);

    /**
     * 查询路由信息列表
     * 
     * @param openstackRouterInfo 路由信息
     * @return 路由信息集合
     */
    public List<OpenstackRouterInfo> selectOpenstackRouterInfoList(OpenstackRouterInfo openstackRouterInfo);

    /**
     * 新增路由信息
     * 
     * @param openstackRouterInfo 路由信息
     * @return 结果
     */
    public int insertOpenstackRouterInfo(OpenstackRouterInfo openstackRouterInfo);

    /**
     * 修改路由信息
     * 
     * @param openstackRouterInfo 路由信息
     * @return 结果
     */
    public int updateOpenstackRouterInfo(OpenstackRouterInfo openstackRouterInfo);

    /**
     * 批量删除路由信息
     * 
     * @param routerIds 需要删除的路由信息ID
     * @return 结果
     */
    public int deleteOpenstackRouterInfoByIds(String[] routerIds);

    /**
     * 删除路由信息信息
     * 
     * @param routerId 路由信息ID
     * @return 结果
     */
    public int deleteOpenstackRouterInfoById(String routerId);
}
