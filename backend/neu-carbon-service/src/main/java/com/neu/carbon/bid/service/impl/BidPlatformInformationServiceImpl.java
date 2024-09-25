package com.neu.carbon.bid.service.impl;

import java.util.List;

import cn.hutool.core.util.IdUtil;
import com.neu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.bid.mapper.BidPlatformInformationMapper;
import com.neu.carbon.bid.domain.BidPlatformInformation;
import com.neu.carbon.bid.service.IBidPlatformInformationService;

/**
 * 平台动态Service业务层处理
 * 
 * @author neuedu
 * @date 2023-04-21
 */
@Service
public class BidPlatformInformationServiceImpl implements IBidPlatformInformationService 
{
    @Autowired
    private BidPlatformInformationMapper bidPlatformInformationMapper;

    /**
     * 查询平台动态
     * 
     * @param id 平台动态ID
     * @return 平台动态
     */
    @Override
    public BidPlatformInformation selectBidPlatformInformationById(Long id)
    {
        return bidPlatformInformationMapper.selectBidPlatformInformationById(id);
    }

    /**
     * 查询平台动态列表
     * 
     * @param bidPlatformInformation 平台动态
     * @return 平台动态
     */
    @Override
    public List<BidPlatformInformation> selectBidPlatformInformationList(BidPlatformInformation bidPlatformInformation)
    {
        return bidPlatformInformationMapper.selectBidPlatformInformationList(bidPlatformInformation);
    }

    /**
     * 新增平台动态
     * 
     * @param bidPlatformInformation 平台动态
     * @return 结果
     */
    @Override
    public int insertBidPlatformInformation(BidPlatformInformation bidPlatformInformation)
    {
        bidPlatformInformation.setCreateTime(DateUtils.getNowDate());
        bidPlatformInformation.setId(IdUtil.getSnowflake(1, 1).nextId());
        return bidPlatformInformationMapper.insertBidPlatformInformation(bidPlatformInformation);
    }

    /**
     * 修改平台动态
     * 
     * @param bidPlatformInformation 平台动态
     * @return 结果
     */
    @Override
    public int updateBidPlatformInformation(BidPlatformInformation bidPlatformInformation)
    {
        bidPlatformInformation.setUpdateTime(DateUtils.getNowDate());
        return bidPlatformInformationMapper.updateBidPlatformInformation(bidPlatformInformation);
    }

    /**
     * 批量删除平台动态
     * 
     * @param ids 需要删除的平台动态ID
     * @return 结果
     */
    @Override
    public int deleteBidPlatformInformationByIds(Long[] ids)
    {
        return bidPlatformInformationMapper.deleteBidPlatformInformationByIds(ids);
    }

    /**
     * 删除平台动态信息
     * 
     * @param id 平台动态ID
     * @return 结果
     */
    @Override
    public int deleteBidPlatformInformationById(Long id)
    {
        return bidPlatformInformationMapper.deleteBidPlatformInformationById(id);
    }
}
