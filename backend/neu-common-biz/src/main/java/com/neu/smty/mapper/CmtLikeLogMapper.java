package com.neu.smty.mapper;

import java.util.List;

import com.neu.smty.domain.CmtLikeLog;

/**
 * 点赞日志Mapper接口
 * 
 * @author neusoft
 * @date 2021-08-04
 */
public interface CmtLikeLogMapper 
{
    /**
     * 查询点赞日志
     * 
     * @param id 点赞日志ID
     * @return 点赞日志
     */
    public CmtLikeLog selectCmtLikeLogById(Long id);

    /**
     * 查询点赞日志列表
     * 
     * @param cmtLikeLog 点赞日志
     * @return 点赞日志集合
     */
    public List<CmtLikeLog> selectCmtLikeLogList(CmtLikeLog cmtLikeLog);

    /**
     * 新增点赞日志
     * 
     * @param cmtLikeLog 点赞日志
     * @return 结果
     */
    public int insertCmtLikeLog(CmtLikeLog cmtLikeLog);

    /**
     * 修改点赞日志
     * 
     * @param cmtLikeLog 点赞日志
     * @return 结果
     */
    public int updateCmtLikeLog(CmtLikeLog cmtLikeLog);

    /**
     * 删除点赞日志
     * 
     * @param id 点赞日志ID
     * @return 结果
     */
    public int deleteCmtLikeLogById(Long id);

    /**
     * 批量删除点赞日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmtLikeLogByIds(Long[] ids);
}
