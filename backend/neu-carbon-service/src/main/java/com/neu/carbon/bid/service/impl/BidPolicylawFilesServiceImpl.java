package com.neu.carbon.bid.service.impl;

import java.util.List;

import cn.hutool.core.util.IdUtil;
import com.neu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.bid.mapper.BidPolicylawFilesMapper;
import com.neu.carbon.bid.domain.BidPolicylawFiles;
import com.neu.carbon.bid.service.IBidPolicylawFilesService;

/**
 * 政策法规附件Service业务层处理
 *
 * @author neuedu
 * @date 2023-04-21
 */
@Service
public class BidPolicylawFilesServiceImpl implements IBidPolicylawFilesService {
    @Autowired
    private BidPolicylawFilesMapper bidPolicylawFilesMapper;

    /**
     * 查询政策法规附件
     *
     * @param id 政策法规附件ID
     * @return 政策法规附件
     */
    @Override
    public BidPolicylawFiles selectBidPolicylawFilesById(Long id) {
        return bidPolicylawFilesMapper.selectBidPolicylawFilesById(id);
    }

    @Override
    public List<BidPolicylawFiles> selectBidPolicylawFilesByPId(Long id) {
        return bidPolicylawFilesMapper.selectBidPolicylawFilesByPId(id);
    }

    /**
     * 查询政策法规附件列表
     *
     * @param bidPolicylawFiles 政策法规附件
     * @return 政策法规附件
     */
    @Override
    public List<BidPolicylawFiles> selectBidPolicylawFilesList(BidPolicylawFiles bidPolicylawFiles) {
        return bidPolicylawFilesMapper.selectBidPolicylawFilesList(bidPolicylawFiles);
    }

    /**
     * 新增政策法规附件
     *
     * @param bidPolicylawFiles 政策法规附件
     * @return 结果
     */
    @Override
    public int insertBidPolicylawFiles(BidPolicylawFiles bidPolicylawFiles) {
        bidPolicylawFiles.setCreateTime(DateUtils.getNowDate());
        bidPolicylawFiles.setId(IdUtil.getSnowflake(1, 1).nextId());
        return bidPolicylawFilesMapper.insertBidPolicylawFiles(bidPolicylawFiles);
    }

    /**
     * 修改政策法规附件
     *
     * @param bidPolicylawFiles 政策法规附件
     * @return 结果
     */
    @Override
    public int updateBidPolicylawFiles(BidPolicylawFiles bidPolicylawFiles) {
        bidPolicylawFiles.setUpdateTime(DateUtils.getNowDate());
        return bidPolicylawFilesMapper.updateBidPolicylawFiles(bidPolicylawFiles);
    }

    /**
     * 批量删除政策法规附件
     *
     * @param ids 需要删除的政策法规附件ID
     * @return 结果
     */
    @Override
    public int deleteBidPolicylawFilesByIds(Long[] ids) {
        return bidPolicylawFilesMapper.deleteBidPolicylawFilesByIds(ids);
    }

    /**
     * 删除政策法规附件信息
     *
     * @param id 政策法规附件ID
     * @return 结果
     */
    @Override
    public int deleteBidPolicylawFilesById(Long id) {
        return bidPolicylawFilesMapper.deleteBidPolicylawFilesById(id);
    }

    @Override
    public int deleteBidPolicylawFilesByPId(Long id) {
        return bidPolicylawFilesMapper.deleteBidPolicylawFilesByPId(id);
    }
}
