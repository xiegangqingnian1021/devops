package com.neu.carbon.bid.service.impl;

import java.util.List;

import cn.hutool.core.util.IdUtil;
import com.neu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.bid.mapper.BidBiddingFilesMapper;
import com.neu.carbon.bid.domain.BidBiddingFiles;
import com.neu.carbon.bid.service.IBidBiddingFilesService;

/**
 * 招标信息附件Service业务层处理
 *
 * @author neuedu
 * @date 2023-04-21
 */
@Service
public class BidBiddingFilesServiceImpl implements IBidBiddingFilesService {
    @Autowired
    private BidBiddingFilesMapper bidBiddingFilesMapper;

    /**
     * 查询招标信息附件
     *
     * @param id 招标信息附件ID
     * @return 招标信息附件
     */
    @Override
    public BidBiddingFiles selectBidBiddingFilesById(Long id) {
        return bidBiddingFilesMapper.selectBidBiddingFilesById(id);
    }

    /**
     * 查询招标信息附件列表
     *
     * @param bidBiddingFiles 招标信息附件
     * @return 招标信息附件
     */
    @Override
    public List<BidBiddingFiles> selectBidBiddingFilesList(BidBiddingFiles bidBiddingFiles) {
        return bidBiddingFilesMapper.selectBidBiddingFilesList(bidBiddingFiles);
    }

    /**
     * 新增招标信息附件
     *
     * @param bidBiddingFiles 招标信息附件
     * @return 结果
     */
    @Override
    public int insertBidBiddingFiles(BidBiddingFiles bidBiddingFiles) {
        bidBiddingFiles.setCreateTime(DateUtils.getNowDate());
        bidBiddingFiles.setId(IdUtil.getSnowflake(1, 1).nextId());
        return bidBiddingFilesMapper.insertBidBiddingFiles(bidBiddingFiles);
    }

    /**
     * 修改招标信息附件
     *
     * @param bidBiddingFiles 招标信息附件
     * @return 结果
     */
    @Override
    public int updateBidBiddingFiles(BidBiddingFiles bidBiddingFiles) {
        bidBiddingFiles.setUpdateTime(DateUtils.getNowDate());
        return bidBiddingFilesMapper.updateBidBiddingFiles(bidBiddingFiles);
    }

    /**
     * 批量删除招标信息附件
     *
     * @param ids 需要删除的招标信息附件ID
     * @return 结果
     */
    @Override
    public int deleteBidBiddingFilesByIds(Long[] ids) {
        return bidBiddingFilesMapper.deleteBidBiddingFilesByIds(ids);
    }

    /**
     * 删除招标信息附件信息
     *
     * @param id 招标信息附件ID
     * @return 结果
     */
    @Override
    public int deleteBidBiddingFilesById(Long id) {
        return bidBiddingFilesMapper.deleteBidBiddingFilesById(id);
    }

    /**
     * 删除招标信息附件信息
     *
     * @param id
     * @return
     */
    @Override
    public int deleteBidBiddingFilesByPId(Long id) {
        return bidBiddingFilesMapper.deleteBidBiddingFilesByPId(id);
    }
}
