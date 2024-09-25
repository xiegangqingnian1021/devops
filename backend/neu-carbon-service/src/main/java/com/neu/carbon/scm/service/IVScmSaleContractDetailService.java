package com.neu.carbon.scm.service;

import com.neu.carbon.scm.domain.RepContractDeliveryReturnDetail;
import com.neu.carbon.scm.domain.VScmSaleContractDetail;

import java.util.List;

/**
 * 销售合同明细Service接口
 * 
 * @author neuedu
 * @date 2022-07-27
 */
public interface IVScmSaleContractDetailService 
{

    /**
     * 查询销售合同明细列表
     * 
     * @param vScmSaleContractDetail 销售合同明细
     * @return 销售合同明细集合
     */
    public List<VScmSaleContractDetail> selectVScmSaleContractDetailList(VScmSaleContractDetail vScmSaleContractDetail);

    /**
     * 查询销售台账报表
     *
     * @param vScmSaleContractDetail 销售合同明细
     * @return 销售合同明细集合
     */
    public List<VScmSaleContractDetail> selectSaleBookReport(VScmSaleContractDetail vScmSaleContractDetail);


    /**
     * 按月份统计销售-发货-退货报表
     *
     * @param repContractDeliveryReturnDetail 销售-发货-退货报表实体
     * @return 销售-发货-退货报表实体
     */
    List<RepContractDeliveryReturnDetail> selectContractDeliveryReturnMonthReport(RepContractDeliveryReturnDetail repContractDeliveryReturnDetail);

    /**
     * 按季度统计销售-发货-退货报表
     *
     * @param repContractDeliveryReturnDetail 销售-发货-退货报表实体
     * @return 销售-发货-退货报表实体
     */
    List<RepContractDeliveryReturnDetail> selectContractDeliveryReturnQuarterReport(RepContractDeliveryReturnDetail repContractDeliveryReturnDetail);


}
