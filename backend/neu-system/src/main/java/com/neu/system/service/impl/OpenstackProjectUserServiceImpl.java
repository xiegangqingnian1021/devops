package com.neu.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.system.mapper.OpenstackProjectUserMapper;
import com.neu.system.domain.OpenstackProjectUser;
import com.neu.system.service.IOpenstackProjectUserService;

/**
 * 租户用户Service业务层处理
 * 
 * @author neuedu
 * @date 2024-10-31
 */
@Service
public class OpenstackProjectUserServiceImpl implements IOpenstackProjectUserService 
{
    @Autowired
    private OpenstackProjectUserMapper openstackProjectUserMapper;

    /**
     * 查询租户用户
     * 
     * @param id 租户用户ID
     * @return 租户用户
     */
    @Override
    public OpenstackProjectUser selectOpenstackProjectUserById(String id)
    {
        return openstackProjectUserMapper.selectOpenstackProjectUserById(id);
    }

    /**
     * 查询租户用户列表
     * 
     * @param openstackProjectUser 租户用户
     * @return 租户用户
     */
    @Override
    public List<OpenstackProjectUser> selectOpenstackProjectUserList(OpenstackProjectUser openstackProjectUser)
    {
        return openstackProjectUserMapper.selectOpenstackProjectUserList(openstackProjectUser);
    }

    /**
     * 新增租户用户
     * 
     * @param openstackProjectUser 租户用户
     * @return 结果
     */
    @Override
    public int insertOpenstackProjectUser(OpenstackProjectUser openstackProjectUser)
    {
        return openstackProjectUserMapper.insertOpenstackProjectUser(openstackProjectUser);
    }

    /**
     * 修改租户用户
     * 
     * @param openstackProjectUser 租户用户
     * @return 结果
     */
    @Override
    public int updateOpenstackProjectUser(OpenstackProjectUser openstackProjectUser)
    {
        return openstackProjectUserMapper.updateOpenstackProjectUser(openstackProjectUser);
    }

    /**
     * 批量删除租户用户
     * 
     * @param ids 需要删除的租户用户ID
     * @return 结果
     */
    @Override
    public int deleteOpenstackProjectUserByIds(String[] ids)
    {
        return openstackProjectUserMapper.deleteOpenstackProjectUserByIds(ids);
    }

    /**
     * 删除租户用户信息
     * 
     * @param id 租户用户ID
     * @return 结果
     */
    @Override
    public int deleteOpenstackProjectUserById(String id)
    {
        return openstackProjectUserMapper.deleteOpenstackProjectUserById(id);
    }
}
