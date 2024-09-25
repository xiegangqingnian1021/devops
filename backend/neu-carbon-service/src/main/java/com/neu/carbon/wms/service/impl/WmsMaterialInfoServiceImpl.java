package com.neu.carbon.wms.service.impl;

import java.util.List;
import com.neu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.wms.mapper.WmsMaterialInfoMapper;
import com.neu.carbon.wms.domain.WmsMaterialInfo;
import com.neu.carbon.wms.service.IWmsMaterialInfoService;

/**
 * 物料档案管理Service业务层处理
 * 
 * @author neusoft
 * @date 2022-06-23
 */
@Service
public class WmsMaterialInfoServiceImpl implements IWmsMaterialInfoService 
{
    @Autowired
    private WmsMaterialInfoMapper wmsMaterialInfoMapper;

    /**
     * 查询物料档案管理
     * 
     * @param id 物料档案管理ID
     * @return 物料档案管理
     */
    @Override
    public WmsMaterialInfo selectWmsMaterialInfoById(Long id)
    {
        return wmsMaterialInfoMapper.selectWmsMaterialInfoById(id);
    }

    /**
     * 查询物料档案管理列表
     * 
     * @param wmsMaterialInfo 物料档案管理
     * @return 物料档案管理
     */
    @Override
    public List<WmsMaterialInfo> selectWmsMaterialInfoList(WmsMaterialInfo wmsMaterialInfo)
    {
        return wmsMaterialInfoMapper.selectWmsMaterialInfoList(wmsMaterialInfo);
    }

    /**
     * 新增物料档案管理
     * 
     * @param wmsMaterialInfo 物料档案管理
     * @return 结果
     */
    @Override
    public int insertWmsMaterialInfo(WmsMaterialInfo wmsMaterialInfo)
    {
        wmsMaterialInfo.setCreateTime(DateUtils.getNowDate());
        return wmsMaterialInfoMapper.insertWmsMaterialInfo(wmsMaterialInfo);
    }

    /**
     * 修改物料档案管理
     * 
     * @param wmsMaterialInfo 物料档案管理
     * @return 结果
     */
    @Override
    public int updateWmsMaterialInfo(WmsMaterialInfo wmsMaterialInfo)
    {
        wmsMaterialInfo.setUpdateTime(DateUtils.getNowDate());
        return wmsMaterialInfoMapper.updateWmsMaterialInfo(wmsMaterialInfo);
    }

    /**
     * 批量删除物料档案管理
     * 
     * @param ids 需要删除的物料档案管理ID
     * @return 结果
     */
    @Override
    public int deleteWmsMaterialInfoByIds(Long[] ids)
    {
        return wmsMaterialInfoMapper.deleteWmsMaterialInfoByIds(ids);
    }

    /**
     * 删除物料档案管理信息
     * 
     * @param id 物料档案管理ID
     * @return 结果
     */
    @Override
    public int deleteWmsMaterialInfoById(Long id)
    {
        return wmsMaterialInfoMapper.deleteWmsMaterialInfoById(id);
    }
}
