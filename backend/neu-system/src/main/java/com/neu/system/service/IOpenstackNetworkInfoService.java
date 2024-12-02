package com.neu.system.service;

import java.util.List;
import com.neu.system.domain.OpenstackNetworkInfo;

/**
 * 网络管理Service接口
 * 
 * @author neuedu
 * @date 2024-12-02
 */
public interface IOpenstackNetworkInfoService 
{
    /**
     * 查询网络管理
     * 
     * @param networkId 网络管理ID
     * @return 网络管理
     */
    public OpenstackNetworkInfo selectOpenstackNetworkInfoById(String networkId);

    /**
     * 查询网络管理列表
     * 
     * @param openstackNetworkInfo 网络管理
     * @return 网络管理集合
     */
    public List<OpenstackNetworkInfo> selectOpenstackNetworkInfoList(OpenstackNetworkInfo openstackNetworkInfo);

    /**
     * 新增网络管理
     * 
     * @param openstackNetworkInfo 网络管理
     * @return 结果
     */
    public int insertOpenstackNetworkInfo(OpenstackNetworkInfo openstackNetworkInfo);

    /**
     * 修改网络管理
     * 
     * @param openstackNetworkInfo 网络管理
     * @return 结果
     */
    public int updateOpenstackNetworkInfo(OpenstackNetworkInfo openstackNetworkInfo);

    /**
     * 批量删除网络管理
     * 
     * @param networkIds 需要删除的网络管理ID
     * @return 结果
     */
    public int deleteOpenstackNetworkInfoByIds(String[] networkIds);

    /**
     * 删除网络管理信息
     * 
     * @param networkId 网络管理ID
     * @return 结果
     */
    public int deleteOpenstackNetworkInfoById(String networkId);
}
