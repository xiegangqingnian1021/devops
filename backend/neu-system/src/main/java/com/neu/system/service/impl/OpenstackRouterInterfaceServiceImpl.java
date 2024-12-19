package com.neu.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.system.mapper.OpenstackRouterInterfaceMapper;
import com.neu.system.domain.OpenstackRouterInterface;
import com.neu.system.service.IOpenstackRouterInterfaceService;

/**
 * 路由接口Service业务层处理
 * 
 * @author neuedu
 * @date 2024-12-17
 */
@Service
public class OpenstackRouterInterfaceServiceImpl implements IOpenstackRouterInterfaceService 
{
    @Autowired
    private OpenstackRouterInterfaceMapper openstackRouterInterfaceMapper;

    /**
     * 查询路由接口
     * 
     * @param id 路由接口ID
     * @return 路由接口
     */
    @Override
    public OpenstackRouterInterface selectOpenstackRouterInterfaceById(Long id)
    {
        return openstackRouterInterfaceMapper.selectOpenstackRouterInterfaceById(id);
    }

    /**
     * 查询路由接口列表
     * 
     * @param openstackRouterInterface 路由接口
     * @return 路由接口
     */
    @Override
    public List<OpenstackRouterInterface> selectOpenstackRouterInterfaceList(OpenstackRouterInterface openstackRouterInterface)
    {
        return openstackRouterInterfaceMapper.selectOpenstackRouterInterfaceList(openstackRouterInterface);
    }

    /**
     * 新增路由接口
     * 
     * @param openstackRouterInterface 路由接口
     * @return 结果
     */
    @Override
    public int insertOpenstackRouterInterface(OpenstackRouterInterface openstackRouterInterface)
    {
        return openstackRouterInterfaceMapper.insertOpenstackRouterInterface(openstackRouterInterface);
    }

    /**
     * 修改路由接口
     * 
     * @param openstackRouterInterface 路由接口
     * @return 结果
     */
    @Override
    public int updateOpenstackRouterInterface(OpenstackRouterInterface openstackRouterInterface)
    {
        return openstackRouterInterfaceMapper.updateOpenstackRouterInterface(openstackRouterInterface);
    }

    /**
     * 批量删除路由接口
     * 
     * @param ids 需要删除的路由接口ID
     * @return 结果
     */
    @Override
    public int deleteOpenstackRouterInterfaceByIds(Long[] ids)
    {
        return openstackRouterInterfaceMapper.deleteOpenstackRouterInterfaceByIds(ids);
    }

    /**
     * 删除路由接口信息
     * 
     * @param id 路由接口ID
     * @return 结果
     */
    @Override
    public int deleteOpenstackRouterInterfaceById(Long id)
    {
        return openstackRouterInterfaceMapper.deleteOpenstackRouterInterfaceById(id);
    }
}
