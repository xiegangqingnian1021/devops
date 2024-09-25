package com.neu.carbon.bid.mapper;

import java.util.List;
import com.neu.carbon.bid.domain.BidEnterpriseBidfile;

/**
 * 企业投标材料Mapper接口
 * 
 * @author neuedu
 * @date 2023-04-21
 */
public interface BidEnterpriseBidfileMapper 
{
    /**
     * 查询企业投标材料
     * 
     * @param id 企业投标材料ID
     * @return 企业投标材料
     */
    public BidEnterpriseBidfile selectBidEnterpriseBidfileById(Long id);

    /**
     * 查询企业投标材料列表
     * 
     * @param bidEnterpriseBidfile 企业投标材料
     * @return 企业投标材料集合
     */
    public List<BidEnterpriseBidfile> selectBidEnterpriseBidfileList(BidEnterpriseBidfile bidEnterpriseBidfile);

    /**
     * 新增企业投标材料
     * 
     * @param bidEnterpriseBidfile 企业投标材料
     * @return 结果
     */
    public int insertBidEnterpriseBidfile(BidEnterpriseBidfile bidEnterpriseBidfile);

    /**
     * 修改企业投标材料
     * 
     * @param bidEnterpriseBidfile 企业投标材料
     * @return 结果
     */
    public int updateBidEnterpriseBidfile(BidEnterpriseBidfile bidEnterpriseBidfile);

    /**
     * 删除企业投标材料
     * 
     * @param id 企业投标材料ID
     * @return 结果
     */
    public int deleteBidEnterpriseBidfileById(Long id);

    /**
     * 批量删除企业投标材料
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBidEnterpriseBidfileByIds(Long[] ids);

    /**
     * 删除企业投标材料
     * @param id
     * @return
     */
    int deleteBidEnterpriseBidfileByPId(Long id);
}
