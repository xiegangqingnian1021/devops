package com.neu.system.service;

import java.util.List;
import com.neu.system.domain.OpenstackUserInfo;

/**
 * 用户信息Service接口
 * 
 * @author neuedu
 * @date 2024-09-25
 */
public interface IOpenstackUserInfoService 
{
    /**
     * 查询用户信息
     * 
     * @param userId 用户信息ID
     * @return 用户信息
     */
    public OpenstackUserInfo selectOpenstackUserInfoById(String userId);

    /**
     * 查询用户信息列表
     * 
     * @param openstackUserInfo 用户信息
     * @return 用户信息集合
     */
    public List<OpenstackUserInfo> selectOpenstackUserInfoList(OpenstackUserInfo openstackUserInfo);

    /**
     * 新增用户信息
     * 
     * @param openstackUserInfo 用户信息
     * @return 结果
     */
    public int insertOpenstackUserInfo(OpenstackUserInfo openstackUserInfo);

    /**
     * 修改用户信息
     * 
     * @param openstackUserInfo 用户信息
     * @return 结果
     */
    public int updateOpenstackUserInfo(OpenstackUserInfo openstackUserInfo);

    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的用户信息ID
     * @return 结果
     */
    public int deleteOpenstackUserInfoByIds(String[] userIds);

    /**
     * 删除用户信息信息
     * 
     * @param userId 用户信息ID
     * @return 结果
     */
    public int deleteOpenstackUserInfoById(String userId);
}
