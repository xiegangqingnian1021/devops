package com.neu.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.system.mapper.OpenstackUserInfoMapper;
import com.neu.system.domain.OpenstackUserInfo;
import com.neu.system.service.IOpenstackUserInfoService;

/**
 * 用户信息Service业务层处理
 * 
 * @author neuedu
 * @date 2024-09-25
 */
@Service
public class OpenstackUserInfoServiceImpl implements IOpenstackUserInfoService 
{
    @Autowired
    private OpenstackUserInfoMapper openstackUserInfoMapper;

    /**
     * 查询用户信息
     * 
     * @param userId 用户信息ID
     * @return 用户信息
     */
    @Override
    public OpenstackUserInfo selectOpenstackUserInfoById(String userId)
    {
        return openstackUserInfoMapper.selectOpenstackUserInfoById(userId);
    }

    /**
     * 查询用户信息列表
     * 
     * @param openstackUserInfo 用户信息
     * @return 用户信息
     */
    @Override
    public List<OpenstackUserInfo> selectOpenstackUserInfoList(OpenstackUserInfo openstackUserInfo)
    {
        return openstackUserInfoMapper.selectOpenstackUserInfoList(openstackUserInfo);
    }

    /**
     * 新增用户信息
     * 
     * @param openstackUserInfo 用户信息
     * @return 结果
     */
    @Override
    public int insertOpenstackUserInfo(OpenstackUserInfo openstackUserInfo)
    {
        return openstackUserInfoMapper.insertOpenstackUserInfo(openstackUserInfo);
    }

    /**
     * 修改用户信息
     * 
     * @param openstackUserInfo 用户信息
     * @return 结果
     */
    @Override
    public int updateOpenstackUserInfo(OpenstackUserInfo openstackUserInfo)
    {
        return openstackUserInfoMapper.updateOpenstackUserInfo(openstackUserInfo);
    }

    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的用户信息ID
     * @return 结果
     */
    @Override
    public int deleteOpenstackUserInfoByIds(String[] userIds)
    {
        return openstackUserInfoMapper.deleteOpenstackUserInfoByIds(userIds);
    }

    /**
     * 删除用户信息信息
     * 
     * @param userId 用户信息ID
     * @return 结果
     */
    @Override
    public int deleteOpenstackUserInfoById(String userId)
    {
        return openstackUserInfoMapper.deleteOpenstackUserInfoById(userId);
    }
}
