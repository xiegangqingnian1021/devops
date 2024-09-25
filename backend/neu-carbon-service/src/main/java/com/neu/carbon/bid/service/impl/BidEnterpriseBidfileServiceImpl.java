package com.neu.carbon.bid.service.impl;

import java.util.List;

import cn.hutool.core.util.IdUtil;
import com.neu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.bid.mapper.BidEnterpriseBidfileMapper;
import com.neu.carbon.bid.domain.BidEnterpriseBidfile;
import com.neu.carbon.bid.service.IBidEnterpriseBidfileService;

/**
 * 企业投标材料Service业务层处理
 *
 * @author neuedu
 * @date 2023-04-21
 */
@Service
public class BidEnterpriseBidfileServiceImpl implements IBidEnterpriseBidfileService {
    @Autowired
    private BidEnterpriseBidfileMapper bidEnterpriseBidfileMapper;

    /**
     * 查询企业投标材料
     *
     * @param id 企业投标材料ID
     * @return 企业投标材料
     */
    @Override
    public BidEnterpriseBidfile selectBidEnterpriseBidfileById(Long id) {
        return bidEnterpriseBidfileMapper.selectBidEnterpriseBidfileById(id);
    }

    /**
     * 查询企业投标材料列表
     *
     * @param bidEnterpriseBidfile 企业投标材料
     * @return 企业投标材料
     */
    @Override
    public List<BidEnterpriseBidfile> selectBidEnterpriseBidfileList(BidEnterpriseBidfile bidEnterpriseBidfile) {
        List<BidEnterpriseBidfile> result = bidEnterpriseBidfileMapper.selectBidEnterpriseBidfileList(bidEnterpriseBidfile);
        return result;
    }

    /**
     * 新增企业投标材料
     *
     * @param bidEnterpriseBidfile 企业投标材料
     * @return 结果
     */
    @Override
    public int insertBidEnterpriseBidfile(BidEnterpriseBidfile bidEnterpriseBidfile) {
        bidEnterpriseBidfile.setCreateTime(DateUtils.getNowDate());
        bidEnterpriseBidfile.setId(IdUtil.getSnowflake(1, 1).nextId());
        return bidEnterpriseBidfileMapper.insertBidEnterpriseBidfile(bidEnterpriseBidfile);
    }

    /**
     * 修改企业投标材料
     *
     * @param bidEnterpriseBidfile 企业投标材料
     * @return 结果
     */
    @Override
    public int updateBidEnterpriseBidfile(BidEnterpriseBidfile bidEnterpriseBidfile) {
        bidEnterpriseBidfile.setUpdateTime(DateUtils.getNowDate());
        return bidEnterpriseBidfileMapper.updateBidEnterpriseBidfile(bidEnterpriseBidfile);
    }

    /**
     * 批量删除企业投标材料
     *
     * @param ids 需要删除的企业投标材料ID
     * @return 结果
     */
    @Override
    public int deleteBidEnterpriseBidfileByIds(Long[] ids) {
        return bidEnterpriseBidfileMapper.deleteBidEnterpriseBidfileByIds(ids);
    }

    /**
     * 删除企业投标材料信息
     *
     * @param id 企业投标材料ID
     * @return 结果
     */
    @Override
    public int deleteBidEnterpriseBidfileById(Long id) {
        return bidEnterpriseBidfileMapper.deleteBidEnterpriseBidfileById(id);
    }

    @Override
    public int deleteBidEnterpriseBidfileByPId(Long id) {
        return bidEnterpriseBidfileMapper.deleteBidEnterpriseBidfileByPId(id);
    }
}
