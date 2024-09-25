package com.neu.carbon.footprint.service;

import java.util.List;
import com.neu.carbon.footprint.domain.CfElectricCheckpoint;

/**
 * 能效检测点Service接口
 * 
 * @author neuedu
 * @date 2022-07-22
 */
public interface ICfElectricCheckpointService 
{
    /**
     * 查询能效检测点
     * 
     * @param id 能效检测点ID
     * @return 能效检测点
     */
    public CfElectricCheckpoint selectCfElectricCheckpointById(Long id);

    /**
     * 查询能效检测点列表
     * 
     * @param cfElectricCheckpoint 能效检测点
     * @return 能效检测点集合
     */
    public List<CfElectricCheckpoint> selectCfElectricCheckpointList(CfElectricCheckpoint cfElectricCheckpoint);

    /**
     * 新增能效检测点
     * 
     * @param cfElectricCheckpoint 能效检测点
     * @return 结果
     */
    public int insertCfElectricCheckpoint(CfElectricCheckpoint cfElectricCheckpoint);

    /**
     * 修改能效检测点
     * 
     * @param cfElectricCheckpoint 能效检测点
     * @return 结果
     */
    public int updateCfElectricCheckpoint(CfElectricCheckpoint cfElectricCheckpoint);

    /**
     * 批量删除能效检测点
     * 
     * @param ids 需要删除的能效检测点ID
     * @return 结果
     */
    public int deleteCfElectricCheckpointByIds(Long[] ids);

    /**
     * 删除能效检测点信息
     * 
     * @param id 能效检测点ID
     * @return 结果
     */
    public int deleteCfElectricCheckpointById(Long id);
}
