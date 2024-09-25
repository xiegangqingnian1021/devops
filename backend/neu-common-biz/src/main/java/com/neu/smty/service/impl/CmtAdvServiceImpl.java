package com.neu.smty.service.impl;

import java.util.List;

import com.neu.common.utils.DateUtils;
import com.neu.smty.domain.CmtAdv;
import com.neu.smty.mapper.CmtAdvMapper;
import com.neu.smty.service.ICmtAdvService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 广告信息Service业务层处理
 * 
 * @author Daniel
 * @date 2021-07-11
 */
@Service
public class CmtAdvServiceImpl implements ICmtAdvService 
{
    @Autowired
    private CmtAdvMapper cmtAdvMapper;

    /**
     * 查询广告信息
     * 
     * @param id 广告信息ID
     * @return 广告信息
     */
    @Override
    public CmtAdv selectCmtAdvById(Long id)
    {
        return cmtAdvMapper.selectCmtAdvById(id);
    }

    /**
     * 查询广告信息列表
     * 
     * @param cmtAdv 广告信息
     * @return 广告信息
     */
    @Override
    public List<CmtAdv> selectCmtAdvList(CmtAdv cmtAdv)
    {
        return cmtAdvMapper.selectCmtAdvList(cmtAdv);
    }

    /**
     * 新增广告信息
     * 
     * @param cmtAdv 广告信息
     * @return 结果
     */
    @Override
    public int insertCmtAdv(CmtAdv cmtAdv)
    {
        cmtAdv.setCreateTime(DateUtils.getNowDate());
        return cmtAdvMapper.insertCmtAdv(cmtAdv);
    }

    /**
     * 修改广告信息
     * 
     * @param cmtAdv 广告信息
     * @return 结果
     */
    @Override
    public int updateCmtAdv(CmtAdv cmtAdv)
    {
        cmtAdv.setUpdateTime(DateUtils.getNowDate());
        return cmtAdvMapper.updateCmtAdv(cmtAdv);
    }

    /**
     * 批量删除广告信息
     * 
     * @param ids 需要删除的广告信息ID
     * @return 结果
     */
    @Override
    public int deleteCmtAdvByIds(Long[] ids)
    {
        return cmtAdvMapper.deleteCmtAdvByIds(ids);
    }

    /**
     * 删除广告信息信息
     * 
     * @param id 广告信息ID
     * @return 结果
     */
    @Override
    public int deleteCmtAdvById(Long id)
    {
        return cmtAdvMapper.deleteCmtAdvById(id);
    }
}
