package com.neu.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.demo.mapper.DemoUserMapper;
import com.neu.demo.domain.DemoUser;
import com.neu.demo.service.IDemoUserService;

/**
 * 演示Service业务层处理
 * 
 * @author neusoft
 * @date 2021-08-20
 */
@Service
public class DemoUserServiceImpl implements IDemoUserService 
{
    @Autowired
    private DemoUserMapper demoUserMapper;

    /**
     * 查询演示
     * 
     * @param id 演示ID
     * @return 演示
     */
    @Override
    public DemoUser selectDemoUserById(Long id)
    {
        return demoUserMapper.selectDemoUserById(id);
    }

    /**
     * 查询演示列表
     * 
     * @param demoUser 演示
     * @return 演示
     */
    @Override
    public List<DemoUser> selectDemoUserList(DemoUser demoUser)
    {
        return demoUserMapper.selectDemoUserList(demoUser);
    }

    /**
     * 新增演示
     * 
     * @param demoUser 演示
     * @return 结果
     */
    @Override
    public int insertDemoUser(DemoUser demoUser)
    {
        return demoUserMapper.insertDemoUser(demoUser);
    }

    /**
     * 修改演示
     * 
     * @param demoUser 演示
     * @return 结果
     */
    @Override
    public int updateDemoUser(DemoUser demoUser)
    {
        return demoUserMapper.updateDemoUser(demoUser);
    }

    /**
     * 批量删除演示
     * 
     * @param ids 需要删除的演示ID
     * @return 结果
     */
    @Override
    public int deleteDemoUserByIds(Long[] ids)
    {
        return demoUserMapper.deleteDemoUserByIds(ids);
    }

    /**
     * 删除演示信息
     * 
     * @param id 演示ID
     * @return 结果
     */
    @Override
    public int deleteDemoUserById(Long id)
    {
        return demoUserMapper.deleteDemoUserById(id);
    }
}
