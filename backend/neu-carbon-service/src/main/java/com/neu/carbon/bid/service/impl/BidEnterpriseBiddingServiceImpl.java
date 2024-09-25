package com.neu.carbon.bid.service.impl;

import java.util.List;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.neu.carbon.bid.service.IBidEnterpriseBidfileService;
import com.neu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.bid.mapper.BidEnterpriseBiddingMapper;
import com.neu.carbon.bid.domain.BidEnterpriseBidding;
import com.neu.carbon.bid.service.IBidEnterpriseBiddingService;

/**
 * 企业投标Service业务层处理
 *
 * @author neuedu
 * @date 2023-04-21
 */
@Service
public class BidEnterpriseBiddingServiceImpl implements IBidEnterpriseBiddingService {

    private final BidEnterpriseBiddingMapper bidEnterpriseBiddingMapper;
    private final IBidEnterpriseBidfileService bidfileService;

    @Autowired
    public BidEnterpriseBiddingServiceImpl(BidEnterpriseBiddingMapper bidEnterpriseBiddingMapper, IBidEnterpriseBidfileService bidfileService) {
        this.bidEnterpriseBiddingMapper = bidEnterpriseBiddingMapper;
        this.bidfileService = bidfileService;
    }

    /**
     * 查询企业投标
     *
     * @param id 企业投标ID
     * @return 企业投标
     */
    @Override
    public BidEnterpriseBidding selectBidEnterpriseBiddingById(Long id) {
        return bidEnterpriseBiddingMapper.selectBidEnterpriseBiddingById(id);
    }

    /**
     * 查询企业投标列表
     *
     * @param bidEnterpriseBidding 企业投标
     * @return 企业投标
     */
    @Override
    public List<BidEnterpriseBidding> selectBidEnterpriseBiddingList(BidEnterpriseBidding bidEnterpriseBidding) {
        List<BidEnterpriseBidding> list = bidEnterpriseBiddingMapper.selectBidEnterpriseBiddingList(bidEnterpriseBidding);
        return list;
    }

    /**
     * 新增企业投标
     *
     * @param bidEnterpriseBidding 企业投标
     * @return 结果
     */
    @Override
    public int insertBidEnterpriseBidding(BidEnterpriseBidding bidEnterpriseBidding) {
        bidEnterpriseBidding.setCreateTime(DateUtils.getNowDate());
        bidEnterpriseBidding.setId(IdUtil.getSnowflake(1, 1).nextId());

        saveFiles(bidEnterpriseBidding);


        return bidEnterpriseBiddingMapper.insertBidEnterpriseBidding(bidEnterpriseBidding);
    }

    /**
     * 修改企业投标
     *
     * @param bidEnterpriseBidding 企业投标
     * @return 结果
     */
    @Override
    public int updateBidEnterpriseBidding(BidEnterpriseBidding bidEnterpriseBidding) {
        bidEnterpriseBidding.setUpdateTime(DateUtils.getNowDate());
        int result = bidEnterpriseBiddingMapper.updateBidEnterpriseBidding(bidEnterpriseBidding);
        saveFiles(bidEnterpriseBidding);
        return result;
    }

    private void saveFiles(BidEnterpriseBidding bidEnterpriseBidding) {
        if (CollectionUtil.isNotEmpty(bidEnterpriseBidding.getFilesList())) {
            this.bidfileService.deleteBidEnterpriseBidfileById(bidEnterpriseBidding.getEnterpriseId());
            if (CollectionUtil.isNotEmpty(bidEnterpriseBidding.getFilesList())) {
                bidEnterpriseBidding.getFilesList().forEach(x -> {
                    x.setId(IdUtil.getSnowflake(1, 1).nextId());
                    x.setEnterpriseBidId(bidEnterpriseBidding.getId());
                    x.setCreateTime(bidEnterpriseBidding.getCreateTime());
                    bidfileService.insertBidEnterpriseBidfile(x);
                });
            }
        }
    }

    /**
     * 批量删除企业投标
     *
     * @param ids 需要删除的企业投标ID
     * @return 结果
     */
    @Override
    public int deleteBidEnterpriseBiddingByIds(Long[] ids) {
        return bidEnterpriseBiddingMapper.deleteBidEnterpriseBiddingByIds(ids);
    }

    /**
     * 删除企业投标信息
     *
     * @param id 企业投标ID
     * @return 结果
     */
    @Override
    public int deleteBidEnterpriseBiddingById(Long id) {
        return bidEnterpriseBiddingMapper.deleteBidEnterpriseBiddingById(id);
    }
}
