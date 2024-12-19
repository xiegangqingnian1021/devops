package com.neu.system.mapper;

import java.util.List;
import com.neu.system.domain.OpenstackRouterInterface;

/**
 * 路由接口Mapper接口
 * 
 * @author neuedu
 * @date 2024-12-17
 */
public interface OpenstackRouterInterfaceMapper 
{
    /**
     * 查询路由接口
     * 
     * @param id 路由接口ID
     * @return 路由接口
     */
    public OpenstackRouterInterface selectOpenstackRouterInterfaceById(Long id);

    /**
     * 查询路由接口列表
     * 
     * @param openstackRouterInterface 路由接口
     * @return 路由接口集合
     */
    public List<OpenstackRouterInterface> selectOpenstackRouterInterfaceList(OpenstackRouterInterface openstackRouterInterface);

    /**
     * 新增路由接口
     * 
     * @param openstackRouterInterface 路由接口
     * @return 结果
     */
    public int insertOpenstackRouterInterface(OpenstackRouterInterface openstackRouterInterface);

    /**
     * 修改路由接口
     * 
     * @param openstackRouterInterface 路由接口
     * @return 结果
     */
    public int updateOpenstackRouterInterface(OpenstackRouterInterface openstackRouterInterface);

    /**
     * 删除路由接口
     * 
     * @param id 路由接口ID
     * @return 结果
     */
    public int deleteOpenstackRouterInterfaceById(Long id);

    /**
     * 批量删除路由接口
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOpenstackRouterInterfaceByIds(Long[] ids);
}
