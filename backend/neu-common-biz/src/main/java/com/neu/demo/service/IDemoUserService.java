package com.neu.demo.service;

import java.util.List;
import com.neu.demo.domain.DemoUser;

/**
 * 演示Service接口
 * 
 * @author neusoft
 * @date 2021-08-20
 */
public interface IDemoUserService 
{
    /**
     * 查询演示
     * 
     * @param id 演示ID
     * @return 演示
     */
    public DemoUser selectDemoUserById(Long id);

    /**
     * 查询演示列表
     * 
     * @param demoUser 演示
     * @return 演示集合
     */
    public List<DemoUser> selectDemoUserList(DemoUser demoUser);

    /**
     * 新增演示
     * 
     * @param demoUser 演示
     * @return 结果
     */
    public int insertDemoUser(DemoUser demoUser);

    /**
     * 修改演示
     * 
     * @param demoUser 演示
     * @return 结果
     */
    public int updateDemoUser(DemoUser demoUser);

    /**
     * 批量删除演示
     * 
     * @param ids 需要删除的演示ID
     * @return 结果
     */
    public int deleteDemoUserByIds(Long[] ids);

    /**
     * 删除演示信息
     * 
     * @param id 演示ID
     * @return 结果
     */
    public int deleteDemoUserById(Long id);
}
