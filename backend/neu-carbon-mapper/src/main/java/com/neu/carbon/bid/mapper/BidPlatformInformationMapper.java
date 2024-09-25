package com.neu.carbon.bid.mapper;

import java.util.List;
import com.neu.carbon.bid.domain.BidPlatformInformation;

/**
 * 平台动态Mapper接口
 * 
 * @author neuedu
 * @date 2023-04-21
 */
public interface BidPlatformInformationMapper 
{
    /**
     * 查询平台动态
     * 
     * @param id 平台动态ID
     * @return 平台动态
     */
    public BidPlatformInformation selectBidPlatformInformationById(Long id);

    /**
     * 查询平台动态列表
     * 
     * @param bidPlatformInformation 平台动态
     * @return 平台动态集合
     */
    public List<BidPlatformInformation> selectBidPlatformInformationList(BidPlatformInformation bidPlatformInformation);

    /**
     * 新增平台动态
     * 
     * @param bidPlatformInformation 平台动态
     * @return 结果
     */
    public int insertBidPlatformInformation(BidPlatformInformation bidPlatformInformation);

    /**
     * 修改平台动态
     * 
     * @param bidPlatformInformation 平台动态
     * @return 结果
     */
    public int updateBidPlatformInformation(BidPlatformInformation bidPlatformInformation);

    /**
     * 删除平台动态
     * 
     * @param id 平台动态ID
     * @return 结果
     */
    public int deleteBidPlatformInformationById(Long id);

    /**
     * 批量删除平台动态
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBidPlatformInformationByIds(Long[] ids);
}
