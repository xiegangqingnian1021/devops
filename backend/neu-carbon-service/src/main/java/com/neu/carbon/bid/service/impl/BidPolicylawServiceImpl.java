package com.neu.carbon.bid.service.impl;

import java.util.List;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.neu.carbon.bid.domain.BidPolicylawFiles;
import com.neu.carbon.bid.service.IBidPolicylawFilesService;
import com.neu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.bid.mapper.BidPolicylawMapper;
import com.neu.carbon.bid.domain.BidPolicylaw;
import com.neu.carbon.bid.service.IBidPolicylawService;

/**
 * 政策法规Service业务层处理
 *
 * @author neuedu
 * @date 2023-04-21
 */
@Service
public class BidPolicylawServiceImpl implements IBidPolicylawService {

    private final BidPolicylawMapper bidPolicylawMapper;
    private final IBidPolicylawFilesService filesService;

    @Autowired
    public BidPolicylawServiceImpl(BidPolicylawMapper bidPolicylawMapper, IBidPolicylawFilesService filesService) {
        this.bidPolicylawMapper = bidPolicylawMapper;
        this.filesService = filesService;
    }

    /**
     * 查询政策法规
     *
     * @param id 政策法规ID
     * @return 政策法规
     */
    @Override
    public BidPolicylaw selectBidPolicylawById(Long id) {
        BidPolicylaw bidPolicylaw = bidPolicylawMapper.selectBidPolicylawById(id);
        List<BidPolicylawFiles> filesList = this.filesService.selectBidPolicylawFilesByPId(id);

        if (CollectionUtil.isNotEmpty(filesList)) {
            bidPolicylaw.setFilesList(filesList);
        }

        return bidPolicylaw;
    }

    /**
     * 查询政策法规列表
     *
     * @param bidPolicylaw 政策法规
     * @return 政策法规
     */
    @Override
    public List<BidPolicylaw> selectBidPolicylawList(BidPolicylaw bidPolicylaw) {
        return bidPolicylawMapper.selectBidPolicylawList(bidPolicylaw);
    }

    /**
     * 新增政策法规
     *
     * @param bidPolicylaw 政策法规
     * @return 结果
     */
    @Override
    public int insertBidPolicylaw(BidPolicylaw bidPolicylaw) {
        bidPolicylaw.setCreateTime(DateUtils.getNowDate());
        bidPolicylaw.setId(IdUtil.getSnowflake(1, 1).nextId());
        saveFiles(bidPolicylaw);
        return bidPolicylawMapper.insertBidPolicylaw(bidPolicylaw);
    }

    /**
     * 修改政策法规
     *
     * @param bidPolicylaw 政策法规
     * @return 结果
     */
    @Override
    public int updateBidPolicylaw(BidPolicylaw bidPolicylaw) {
        bidPolicylaw.setUpdateTime(DateUtils.getNowDate());
        saveFiles(bidPolicylaw);
        return bidPolicylawMapper.updateBidPolicylaw(bidPolicylaw);
    }

    private void saveFiles(BidPolicylaw bidPolicylaw) {
        this.filesService.deleteBidPolicylawFilesByPId(bidPolicylaw.getId());
        if (CollectionUtil.isNotEmpty(bidPolicylaw.getFilesList())) {
            bidPolicylaw.getFilesList().forEach(x -> {
                x.setId(IdUtil.getSnowflake(1, 1).nextId());
                x.setPolicyId(bidPolicylaw.getId());
                x.setCreateTime(bidPolicylaw.getCreateTime());
                filesService.insertBidPolicylawFiles(x);
            });
        }
    }

    /**
     * 批量删除政策法规
     *
     * @param ids 需要删除的政策法规ID
     * @return 结果
     */
    @Override
    public int deleteBidPolicylawByIds(Long[] ids) {
        return bidPolicylawMapper.deleteBidPolicylawByIds(ids);
    }

    /**
     * 删除政策法规信息
     *
     * @param id 政策法规ID
     * @return 结果
     */
    @Override
    public int deleteBidPolicylawById(Long id) {
        return bidPolicylawMapper.deleteBidPolicylawById(id);
    }
}
