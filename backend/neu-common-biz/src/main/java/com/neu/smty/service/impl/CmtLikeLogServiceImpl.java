package com.neu.smty.service.impl;

import java.util.List;

import com.neu.common.utils.DateUtils;
import com.neu.smty.domain.CmtLikeLog;
import com.neu.smty.mapper.CmtLikeLogMapper;
import com.neu.smty.service.ICmtLikeLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 点赞日志Service业务层处理
 * 
 * @author neusoft
 * @date 2021-08-04
 */
@Service
public class CmtLikeLogServiceImpl implements ICmtLikeLogService 
{
    @Autowired
    private CmtLikeLogMapper cmtLikeLogMapper;

    /**
     * 查询点赞日志
     * 
     * @param id 点赞日志ID
     * @return 点赞日志
     */
    @Override
    public CmtLikeLog selectCmtLikeLogById(Long id)
    {
        return cmtLikeLogMapper.selectCmtLikeLogById(id);
    }

    /**
     * 查询点赞日志列表
     * 
     * @param cmtLikeLog 点赞日志
     * @return 点赞日志
     */
    @Override
    public List<CmtLikeLog> selectCmtLikeLogList(CmtLikeLog cmtLikeLog)
    {
        return cmtLikeLogMapper.selectCmtLikeLogList(cmtLikeLog);
    }

    /**
     * 新增点赞日志
     * 
     * @param cmtLikeLog 点赞日志
     * @return 结果
     */
    @Override
    public int insertCmtLikeLog(CmtLikeLog cmtLikeLog)
    {
        cmtLikeLog.setCreateTime(DateUtils.getNowDate());
        return cmtLikeLogMapper.insertCmtLikeLog(cmtLikeLog);
    }

    /**
     * 修改点赞日志
     * 
     * @param cmtLikeLog 点赞日志
     * @return 结果
     */
    @Override
    public int updateCmtLikeLog(CmtLikeLog cmtLikeLog)
    {
        return cmtLikeLogMapper.updateCmtLikeLog(cmtLikeLog);
    }

    /**
     * 批量删除点赞日志
     * 
     * @param ids 需要删除的点赞日志ID
     * @return 结果
     */
    @Override
    public int deleteCmtLikeLogByIds(Long[] ids)
    {
        return cmtLikeLogMapper.deleteCmtLikeLogByIds(ids);
    }

    /**
     * 删除点赞日志信息
     * 
     * @param id 点赞日志ID
     * @return 结果
     */
    @Override
    public int deleteCmtLikeLogById(Long id)
    {
        return cmtLikeLogMapper.deleteCmtLikeLogById(id);
    }
}
