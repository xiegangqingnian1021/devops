package com.neu.carbon.footprint.service.impl;

import com.neu.carbon.footprint.domain.TmpEnergyConsume;
import com.neu.carbon.footprint.mapper.TmpEnergyConsumeMapper;
import com.neu.carbon.footprint.service.ITmpEnergyConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 能耗临时表Service业务层处理
 * 
 * @author neuedu
 * @date 2022-07-31
 */
@Service
public class TmpEnergyConsumeServiceImpl implements ITmpEnergyConsumeService 
{
    @Autowired
    private TmpEnergyConsumeMapper tmpEnergyConsumeMapper;

    /**
     * 查询能耗临时表
     * 
     * @param id 能耗临时表ID
     * @return 能耗临时表
     */
    @Override
    public TmpEnergyConsume selectTmpEnergyConsumeById(Long id)
    {
        return tmpEnergyConsumeMapper.selectTmpEnergyConsumeById(id);
    }

    /**
     * 查询能耗临时表列表
     * 
     * @param tmpEnergyConsume 能耗临时表
     * @return 能耗临时表
     */
    @Override
    public List<TmpEnergyConsume> selectTmpEnergyConsumeList(TmpEnergyConsume tmpEnergyConsume)
    {
        return tmpEnergyConsumeMapper.selectTmpEnergyConsumeList(tmpEnergyConsume);
    }

    /**
     * 新增能耗临时表
     * 
     * @param tmpEnergyConsume 能耗临时表
     * @return 结果
     */
    @Override
    public int insertTmpEnergyConsume(TmpEnergyConsume tmpEnergyConsume)
    {
        return tmpEnergyConsumeMapper.insertTmpEnergyConsume(tmpEnergyConsume);
    }

    /**
     * 修改能耗临时表
     * 
     * @param tmpEnergyConsume 能耗临时表
     * @return 结果
     */
    @Override
    public int updateTmpEnergyConsume(TmpEnergyConsume tmpEnergyConsume)
    {
        return tmpEnergyConsumeMapper.updateTmpEnergyConsume(tmpEnergyConsume);
    }

    /**
     * 批量删除能耗临时表
     * 
     * @param ids 需要删除的能耗临时表ID
     * @return 结果
     */
    @Override
    public int deleteTmpEnergyConsumeByIds(Long[] ids)
    {
        return tmpEnergyConsumeMapper.deleteTmpEnergyConsumeByIds(ids);
    }

    /**
     * 删除能耗临时表信息
     * 
     * @param id 能耗临时表ID
     * @return 结果
     */
    @Override
    public int deleteTmpEnergyConsumeById(Long id)
    {
        return tmpEnergyConsumeMapper.deleteTmpEnergyConsumeById(id);
    }
}
