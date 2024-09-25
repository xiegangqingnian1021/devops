package com.neu.carbon.bid.service.impl;

import java.util.List;

import cn.hutool.core.util.IdUtil;
import com.neu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.bid.mapper.BidComplaintFilesMapper;
import com.neu.carbon.bid.domain.BidComplaintFiles;
import com.neu.carbon.bid.service.IBidComplaintFilesService;

/**
 * 投诉附件Service业务层处理
 * 
 * @author neuedu
 * @date 2023-04-21
 */
@Service
public class BidComplaintFilesServiceImpl implements IBidComplaintFilesService 
{
    @Autowired
    private BidComplaintFilesMapper bidComplaintFilesMapper;

    /**
     * 查询投诉附件
     * 
     * @param id 投诉附件ID
     * @return 投诉附件
     */
    @Override
    public BidComplaintFiles selectBidComplaintFilesById(Long id)
    {
        return bidComplaintFilesMapper.selectBidComplaintFilesById(id);
    }

    /**
     * 查询投诉附件列表
     * 
     * @param bidComplaintFiles 投诉附件
     * @return 投诉附件
     */
    @Override
    public List<BidComplaintFiles> selectBidComplaintFilesList(BidComplaintFiles bidComplaintFiles)
    {
        return bidComplaintFilesMapper.selectBidComplaintFilesList(bidComplaintFiles);
    }

    /**
     * 新增投诉附件
     * 
     * @param bidComplaintFiles 投诉附件
     * @return 结果
     */
    @Override
    public int insertBidComplaintFiles(BidComplaintFiles bidComplaintFiles)
    {
        bidComplaintFiles.setCreateTime(DateUtils.getNowDate());
        bidComplaintFiles.setId(IdUtil.getSnowflake(1, 1).nextId());
        return bidComplaintFilesMapper.insertBidComplaintFiles(bidComplaintFiles);
    }

    /**
     * 修改投诉附件
     * 
     * @param bidComplaintFiles 投诉附件
     * @return 结果
     */
    @Override
    public int updateBidComplaintFiles(BidComplaintFiles bidComplaintFiles)
    {
        bidComplaintFiles.setUpdateTime(DateUtils.getNowDate());
        return bidComplaintFilesMapper.updateBidComplaintFiles(bidComplaintFiles);
    }

    /**
     * 批量删除投诉附件
     * 
     * @param ids 需要删除的投诉附件ID
     * @return 结果
     */
    @Override
    public int deleteBidComplaintFilesByIds(Long[] ids)
    {
        return bidComplaintFilesMapper.deleteBidComplaintFilesByIds(ids);
    }

    /**
     * 删除投诉附件信息
     * 
     * @param id 投诉附件ID
     * @return 结果
     */
    @Override
    public int deleteBidComplaintFilesById(Long id)
    {
        return bidComplaintFilesMapper.deleteBidComplaintFilesById(id);
    }
}
