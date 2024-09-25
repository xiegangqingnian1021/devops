package com.neu.carbon.footprint.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.footprint.mapper.CfElectricCheckpointMapper;
import com.neu.carbon.footprint.domain.CfElectricCheckpoint;
import com.neu.carbon.footprint.service.ICfElectricCheckpointService;

/**
 * 能效检测点Service业务层处理
 * 
 * @author neuedu
 * @date 2022-07-22
 */
@Service
public class CfElectricCheckpointServiceImpl implements ICfElectricCheckpointService 
{
    @Autowired
    private CfElectricCheckpointMapper cfElectricCheckpointMapper;

    /**
     * 查询能效检测点
     * 
     * @param id 能效检测点ID
     * @return 能效检测点
     */
    @Override
    public CfElectricCheckpoint selectCfElectricCheckpointById(Long id)
    {
        return cfElectricCheckpointMapper.selectCfElectricCheckpointById(id);
    }

    /**
     * 查询能效检测点列表
     * 
     * @param cfElectricCheckpoint 能效检测点
     * @return 能效检测点
     */
    @Override
    public List<CfElectricCheckpoint> selectCfElectricCheckpointList(CfElectricCheckpoint cfElectricCheckpoint)
    {
        return cfElectricCheckpointMapper.selectCfElectricCheckpointList(cfElectricCheckpoint);
    }

    /**
     * 新增能效检测点
     * 
     * @param cfElectricCheckpoint 能效检测点
     * @return 结果
     */
    @Override
    public int insertCfElectricCheckpoint(CfElectricCheckpoint cfElectricCheckpoint)
    {
        return cfElectricCheckpointMapper.insertCfElectricCheckpoint(cfElectricCheckpoint);
    }

    /**
     * 修改能效检测点
     * 
     * @param cfElectricCheckpoint 能效检测点
     * @return 结果
     */
    @Override
    public int updateCfElectricCheckpoint(CfElectricCheckpoint cfElectricCheckpoint)
    {
        return cfElectricCheckpointMapper.updateCfElectricCheckpoint(cfElectricCheckpoint);
    }

    /**
     * 批量删除能效检测点
     * 
     * @param ids 需要删除的能效检测点ID
     * @return 结果
     */
    @Override
    public int deleteCfElectricCheckpointByIds(Long[] ids)
    {
        return cfElectricCheckpointMapper.deleteCfElectricCheckpointByIds(ids);
    }

    /**
     * 删除能效检测点信息
     * 
     * @param id 能效检测点ID
     * @return 结果
     */
    @Override
    public int deleteCfElectricCheckpointById(Long id)
    {
        return cfElectricCheckpointMapper.deleteCfElectricCheckpointById(id);
    }
}
