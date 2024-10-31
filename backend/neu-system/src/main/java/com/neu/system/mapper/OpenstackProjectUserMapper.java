package com.neu.system.mapper;

import java.util.List;
import com.neu.system.domain.OpenstackProjectUser;

/**
 * 租户用户Mapper接口
 * 
 * @author neuedu
 * @date 2024-10-31
 */
public interface OpenstackProjectUserMapper 
{
    /**
     * 查询租户用户
     * 
     * @param id 租户用户ID
     * @return 租户用户
     */
    public OpenstackProjectUser selectOpenstackProjectUserById(String id);

    /**
     * 查询租户用户列表
     * 
     * @param openstackProjectUser 租户用户
     * @return 租户用户集合
     */
    public List<OpenstackProjectUser> selectOpenstackProjectUserList(OpenstackProjectUser openstackProjectUser);

    /**
     * 新增租户用户
     * 
     * @param openstackProjectUser 租户用户
     * @return 结果
     */
    public int insertOpenstackProjectUser(OpenstackProjectUser openstackProjectUser);

    /**
     * 修改租户用户
     * 
     * @param openstackProjectUser 租户用户
     * @return 结果
     */
    public int updateOpenstackProjectUser(OpenstackProjectUser openstackProjectUser);

    /**
     * 删除租户用户
     * 
     * @param id 租户用户ID
     * @return 结果
     */
    public int deleteOpenstackProjectUserById(String id);

    /**
     * 批量删除租户用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOpenstackProjectUserByIds(String[] ids);
}
