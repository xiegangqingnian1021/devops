package com.neu.carbon.mes.service;

import java.util.List;
import com.neu.carbon.mes.domain.MesProject;

/**
 * 项目维护Service接口
 * 
 * @author neuedu
 * @date 2022-07-07
 */
public interface IMesProjectService 
{
    /**
     * 查询项目维护
     * 
     * @param id 项目维护ID
     * @return 项目维护
     */
    public MesProject selectMesProjectById(Long id);

    /**
     * 查询项目维护列表
     * 
     * @param mesProject 项目维护
     * @return 项目维护集合
     */
    public List<MesProject> selectMesProjectList(MesProject mesProject);

    /**
     * 新增项目维护
     * 
     * @param mesProject 项目维护
     * @return 结果
     */
    public int insertMesProject(MesProject mesProject);

    /**
     * 修改项目维护
     * 
     * @param mesProject 项目维护
     * @return 结果
     */
    public int updateMesProject(MesProject mesProject);

    /**
     * 批量删除项目维护
     * 
     * @param ids 需要删除的项目维护ID
     * @return 结果
     */
    public int deleteMesProjectByIds(Long[] ids);

    /**
     * 删除项目维护信息
     * 
     * @param id 项目维护ID
     * @return 结果
     */
    public int deleteMesProjectById(Long id);
}
