package com.neu.carbon.bid.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.neu.carbon.bid.domain.BidEnterprise;
import com.neu.carbon.bid.service.IBidEnterpriseService;
import com.neu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.bid.mapper.BidComplaintMapper;
import com.neu.carbon.bid.domain.BidComplaint;
import com.neu.carbon.bid.service.IBidComplaintService;

/**
 * 违规投诉Service业务层处理
 *
 * @author neuedu
 * @date 2023-04-21
 */
@Service
public class BidComplaintServiceImpl implements IBidComplaintService {

    private final BidComplaintMapper bidComplaintMapper;

    private final IBidEnterpriseService enterpriseService;

    @Autowired
    public BidComplaintServiceImpl(BidComplaintMapper bidComplaintMapper, IBidEnterpriseService enterpriseService) {
        this.bidComplaintMapper = bidComplaintMapper;
        this.enterpriseService = enterpriseService;
    }

    /**
     * 查询违规投诉
     *
     * @param id 违规投诉ID
     * @return 违规投诉
     */
    @Override
    public BidComplaint selectBidComplaintById(Long id) {
        BidComplaint bidComplaint = bidComplaintMapper.selectBidComplaintById(id);
        if (bidComplaint != null) {
            BidEnterprise bidEnterprise = enterpriseService.selectBidEnterpriseById(bidComplaint.getEnterpriseId());
            if (bidEnterprise != null) {
                bidComplaint.setBidEnterprise(bidEnterprise);
                bidComplaint.setEnterpriseName(bidEnterprise.getName());
            }
        }

        return bidComplaint;
    }

    /**
     * 查询违规投诉列表
     *
     * @param bidComplaint 违规投诉
     * @return 违规投诉
     */
    @Override
    public List<BidComplaint> selectBidComplaintList(BidComplaint bidComplaint) {
        List<BidComplaint> list = bidComplaintMapper.selectBidComplaintList(bidComplaint);

        if (CollectionUtil.isNotEmpty(list)) {
            List<Long> enterIds = list.stream().map(BidComplaint::getEnterpriseId).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(enterIds)) {
                List<BidEnterprise> enterprises = enterpriseService.selectBidEnterpriseListByEnterpriseIds(enterIds);

                if (CollectionUtil.isNotEmpty(enterprises)) {
                    list.forEach(x -> {
                        enterprises.stream()
                                .filter(y -> y.getId().equals(x.getEnterpriseId()))
                                .findFirst()
                                .ifPresent(y -> {
                                    x.setBidEnterprise(y);
                                    x.setEnterpriseName(y.getName());
                                });
                    });
                }
            }

        }

        return list;
    }

    /**
     * 新增违规投诉
     *
     * @param bidComplaint 违规投诉
     * @return 结果
     */
    @Override
    public int insertBidComplaint(BidComplaint bidComplaint) {
        bidComplaint.setCreateTime(DateUtils.getNowDate());
        bidComplaint.setId(IdUtil.getSnowflake(1, 1).nextId());
        return bidComplaintMapper.insertBidComplaint(bidComplaint);
    }

    /**
     * 修改违规投诉
     *
     * @param bidComplaint 违规投诉
     * @return 结果
     */
    @Override
    public int updateBidComplaint(BidComplaint bidComplaint) {
        bidComplaint.setUpdateTime(DateUtils.getNowDate());
        return bidComplaintMapper.updateBidComplaint(bidComplaint);
    }

    /**
     * 批量删除违规投诉
     *
     * @param ids 需要删除的违规投诉ID
     * @return 结果
     */
    @Override
    public int deleteBidComplaintByIds(Long[] ids) {
        return bidComplaintMapper.deleteBidComplaintByIds(ids);
    }

    /**
     * 删除违规投诉信息
     *
     * @param id 违规投诉ID
     * @return 结果
     */
    @Override
    public int deleteBidComplaintById(Long id) {
        return bidComplaintMapper.deleteBidComplaintById(id);
    }
}
