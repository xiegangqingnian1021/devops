package com.neu.carbon.bid.service;

import java.util.List;

import com.neu.carbon.bid.domain.BidEnterprise;

/**
 * 投标企业信息Service接口
 *
 * @author neuedu
 * @date 2023-04-21
 */
public interface IBidEnterpriseService {
    /**
     * 查询投标企业信息
     *
     * @param id 投标企业信息ID
     * @return 投标企业信息
     */
    public BidEnterprise selectBidEnterpriseById(Long id);

    /**
     * 查询投标企业信息列表
     *
     * @param bidEnterprise 投标企业信息
     * @return 投标企业信息集合
     */
    public List<BidEnterprise> selectBidEnterpriseList(BidEnterprise bidEnterprise);

    /**
     * 按id数组获取
     *
     * @param ids
     * @return
     */
    List<BidEnterprise> selectBidEnterpriseListByEnterpriseIds(List<Long> ids);

    /**
     * 新增投标企业信息
     *
     * @param bidEnterprise 投标企业信息
     * @return 结果
     */
    public int insertBidEnterprise(BidEnterprise bidEnterprise);

    /**
     * 修改投标企业信息
     *
     * @param bidEnterprise 投标企业信息
     * @return 结果
     */
    public int updateBidEnterprise(BidEnterprise bidEnterprise);

    /**
     * 批量删除投标企业信息
     *
     * @param ids 需要删除的投标企业信息ID
     * @return 结果
     */
    public int deleteBidEnterpriseByIds(Long[] ids);

    /**
     * 删除投标企业信息信息
     *
     * @param id 投标企业信息ID
     * @return 结果
     */
    public int deleteBidEnterpriseById(Long id);
}
