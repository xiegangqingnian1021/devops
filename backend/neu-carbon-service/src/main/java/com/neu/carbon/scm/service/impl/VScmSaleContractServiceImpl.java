package com.neu.carbon.scm.service.impl;

import com.neu.carbon.scm.domain.VScmFinanceReport;
import com.neu.carbon.scm.domain.VScmSaleContract;
import com.neu.carbon.scm.mapper.VScmSaleContractMapper;
import com.neu.carbon.scm.service.IVScmSaleContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 销售合同报表Service业务层处理
 * 
 * @author neuedu
 * @date 2022-07-28
 */
@Service
public class VScmSaleContractServiceImpl implements IVScmSaleContractService 
{
    @Autowired
    private VScmSaleContractMapper vScmSaleContractMapper;

    /**
     * 查询销售合同报表
     * 
     * @param contractId 销售合同报表ID
     * @return 销售合同报表
     */
    @Override
    public VScmSaleContract selectVScmSaleContractById(Long contractId)
    {
        return vScmSaleContractMapper.selectVScmSaleContractById(contractId);
    }

    /**
     * 查询销售合同报表列表
     * 
     * @param vScmSaleContract 销售合同报表
     * @return 销售合同报表
     */
    @Override
    public List<VScmSaleContract> selectVScmSaleContractList(VScmSaleContract vScmSaleContract)
    {
        return vScmSaleContractMapper.selectVScmSaleContractList(vScmSaleContract);
    }

    /**
     * 查询按月统计销售合同报表列表
     *
     * @param vScmSaleContract 销售合同报表
     * @return 销售合同报表集合
     */
    @Override
    public List<VScmSaleContract> selectMonthSaleContractReport(VScmSaleContract vScmSaleContract){
        return vScmSaleContractMapper.selectMonthSaleContractReport(vScmSaleContract);
    }


    /**
     * 查询按季度统计销售合同报表列表
     *
     * @param vScmSaleContract 销售合同报表
     * @return 销售合同报表集合
     */
    @Override
    public List<VScmSaleContract> selectQuarterSaleContractReport(VScmSaleContract vScmSaleContract){
        return vScmSaleContractMapper.selectQuarterSaleContractReport(vScmSaleContract);
    }

    /**
     * 查询按季度统计销售合同报表列表
     *
     * @param vScmSaleContract 销售合同报表
     * @return 销售合同报表集合
     */
    @Override
    public List<VScmSaleContract> selectMonthOrderStatReport(VScmSaleContract vScmSaleContract){
        return vScmSaleContractMapper.selectMonthOrderStatReport(vScmSaleContract);
    }


    /**
     * 订单收款季度统计
     *
     * @param vScmSaleContract 销售合同报表
     * @return 销售合同报表集合
     */
    @Override
    public List<VScmSaleContract> selectQuarterOrderStatReport(VScmSaleContract vScmSaleContract){
        return vScmSaleContractMapper.selectQuarterOrderStatReport(vScmSaleContract);
    }

    /**
     * 财务收支统计
     *
     * @return 销售合同报表集合
     */
    @Override
    public List<VScmFinanceReport> selectMonthFinanceReport(VScmFinanceReport vScmFinanceReport) {
        return vScmSaleContractMapper.selectMonthFinanceReport(vScmFinanceReport);
    }

    /**
     * 财务收支统计
     *
     * @return 销售合同报表集合
     */
    @Override
    public List<VScmFinanceReport> selectQuarterFinanceReport(VScmFinanceReport vScmFinanceReport) {
        return vScmSaleContractMapper.selectQuarterFinanceReport(vScmFinanceReport);
    }

    /**
     * 客户销售排名
     * @return
     */
    @Override
    public List<Map<String, BigDecimal>> selectSaleCustomerRank() {
        return vScmSaleContractMapper.selectSaleCustomerRank();
    }

    /**
     * 按月统计销售额
     * @param month
     * @return
     */
    @Override
    public Map<String, BigDecimal> selectSaleStatByMonth(String month) {
        return vScmSaleContractMapper.selectSaleStatByMonth(month);
    }

    /**
     * 大屏销售总览
     * @return
     */
    @Override
    public Map<String, BigDecimal> selectSaleOverall() {
        return vScmSaleContractMapper.selectSaleOverall();
    }


}
