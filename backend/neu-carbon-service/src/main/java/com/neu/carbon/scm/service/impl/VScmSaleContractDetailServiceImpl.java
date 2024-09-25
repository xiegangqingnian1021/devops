package com.neu.carbon.scm.service.impl;

import com.neu.carbon.scm.domain.RepContractDeliveryReturnDetail;
import com.neu.carbon.scm.domain.VScmSaleContractDetail;
import com.neu.carbon.scm.mapper.VScmSaleContractDetailMapper;
import com.neu.carbon.scm.service.IVScmSaleContractDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 销售合同明细Service业务层处理
 *
 * @author neuedu
 * @date 2022-07-27
 */
@Service
public class VScmSaleContractDetailServiceImpl implements IVScmSaleContractDetailService {
    @Autowired
    private VScmSaleContractDetailMapper vScmSaleContractDetailMapper;

    /**
     * 查询销售合同明细列表
     *
     * @param vScmSaleContractDetail 销售合同明细
     * @return 销售合同明细
     */
    @Override
    public List<VScmSaleContractDetail> selectVScmSaleContractDetailList(VScmSaleContractDetail vScmSaleContractDetail) {
        return vScmSaleContractDetailMapper.selectVScmSaleContractDetailList(vScmSaleContractDetail);
    }

    /**
     * 查询销售台账报表
     *
     * @param vScmSaleContractDetail 销售合同明细
     * @return 销售合同明细
     */
    @Override
    public List<VScmSaleContractDetail> selectSaleBookReport(VScmSaleContractDetail vScmSaleContractDetail) {
        return vScmSaleContractDetailMapper.selectSaleBookReport(vScmSaleContractDetail);
    }


    /**
     * 按月份统计销售-发货-退货报表
     *
     * @param repContractDeliveryReturnDetail 销售-发货-退货报表实体
     * @return 销售-发货-退货报表实体
     */
    @Override
    public List<RepContractDeliveryReturnDetail> selectContractDeliveryReturnMonthReport(RepContractDeliveryReturnDetail repContractDeliveryReturnDetail){
        return vScmSaleContractDetailMapper.selectContractDeliveryReturnMonthReport(repContractDeliveryReturnDetail);
    }


    /**
     * 按季度统计销售-发货-退货报表
     *
     * @param repContractDeliveryReturnDetail 销售-发货-退货报表实体
     * @return 销售-发货-退货报表实体
     */
    @Override
    public List<RepContractDeliveryReturnDetail> selectContractDeliveryReturnQuarterReport(RepContractDeliveryReturnDetail repContractDeliveryReturnDetail){
        return vScmSaleContractDetailMapper.selectContractDeliveryReturnQuarterReport(repContractDeliveryReturnDetail);
    }

}
