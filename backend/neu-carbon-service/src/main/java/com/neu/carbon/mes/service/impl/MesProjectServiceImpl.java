package com.neu.carbon.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.mes.mapper.MesProjectMapper;
import com.neu.carbon.mes.domain.MesProject;
import com.neu.carbon.mes.service.IMesProjectService;

/**
 * 项目维护Service业务层处理
 * 
 * @author neuedu
 * @date 2022-07-07
 */
@Service
public class MesProjectServiceImpl implements IMesProjectService 
{
    @Autowired
    private MesProjectMapper mesProjectMapper;

    /**
     * 查询项目维护
     * 
     * @param id 项目维护ID
     * @return 项目维护
     */
    @Override
    public MesProject selectMesProjectById(Long id)
    {
        return mesProjectMapper.selectMesProjectById(id);
    }

    /**
     * 查询项目维护列表
     * 
     * @param mesProject 项目维护
     * @return 项目维护
     */
    @Override
    public List<MesProject> selectMesProjectList(MesProject mesProject)
    {
        return mesProjectMapper.selectMesProjectList(mesProject);
    }

    /**
     * 新增项目维护
     * 
     * @param mesProject 项目维护
     * @return 结果
     */
    @Override
    public int insertMesProject(MesProject mesProject)
    {
        return mesProjectMapper.insertMesProject(mesProject);
    }

    /**
     * 修改项目维护
     * 
     * @param mesProject 项目维护
     * @return 结果
     */
    @Override
    public int updateMesProject(MesProject mesProject)
    {
        return mesProjectMapper.updateMesProject(mesProject);
    }

    /**
     * 批量删除项目维护
     * 
     * @param ids 需要删除的项目维护ID
     * @return 结果
     */
    @Override
    public int deleteMesProjectByIds(Long[] ids)
    {
        return mesProjectMapper.deleteMesProjectByIds(ids);
    }

    /**
     * 删除项目维护信息
     * 
     * @param id 项目维护ID
     * @return 结果
     */
    @Override
    public int deleteMesProjectById(Long id)
    {
        return mesProjectMapper.deleteMesProjectById(id);
    }
}
