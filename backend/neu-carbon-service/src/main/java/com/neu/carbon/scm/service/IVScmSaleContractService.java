package com.neu.carbon.scm.service;

import com.neu.carbon.scm.domain.VScmFinanceReport;
import com.neu.carbon.scm.domain.VScmSaleContract;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 销售合同报表Service接口
 * 
 * @author neuedu
 * @date 2022-07-28
 */
public interface IVScmSaleContractService 
{
    /**
     * 查询销售合同报表
     * 
     * @param contractId 销售合同报表ID
     * @return 销售合同报表
     */
    public VScmSaleContract selectVScmSaleContractById(Long contractId);

    /**
     * 查询销售合同报表列表
     * 
     * @param vScmSaleContract 销售合同报表
     * @return 销售合同报表集合
     */
    public List<VScmSaleContract> selectVScmSaleContractList(VScmSaleContract vScmSaleContract);

    /**
     * 查询按月统计销售合同报表列表
     *
     * @param vScmSaleContract 销售合同报表
     * @return 销售合同报表集合
     */
    public List<VScmSaleContract> selectMonthSaleContractReport(VScmSaleContract vScmSaleContract);

    /**
     * 查询按季度统计销售合同报表列表
     *
     * @param vScmSaleContract 销售合同报表
     * @return 销售合同报表集合
     */
    public List<VScmSaleContract> selectQuarterSaleContractReport(VScmSaleContract vScmSaleContract);


    /**
     * 订单收款月统计
     *
     * @param vScmSaleContract 销售合同报表
     * @return 销售合同报表集合
     */
    public List<VScmSaleContract> selectMonthOrderStatReport(VScmSaleContract vScmSaleContract);

    /**
     * 订单收款季度统计
     *
     * @param vScmSaleContract 销售合同报表
     * @return 销售合同报表集合
     */
    public List<VScmSaleContract> selectQuarterOrderStatReport(VScmSaleContract vScmSaleContract);



    /**
     * 财务收支月份统计
     *
     * @return 销售合同报表
     */
    public List<VScmFinanceReport> selectMonthFinanceReport(VScmFinanceReport vScmFinanceReport);


    /**
     * 财务收支季度统计
     *
     * @return 销售合同报表
     */
    public List<VScmFinanceReport> selectQuarterFinanceReport(VScmFinanceReport vScmFinanceReport);


    /**
     * 客户销售排名
     * @return
     */
    List<Map<String,BigDecimal>> selectSaleCustomerRank();

    /**
     * 按月统计销售额
     * @param month
     * @return
     */
    public Map<String, BigDecimal> selectSaleStatByMonth(String month);

    /**
     * 大屏销售总览
     * @return
     */
    public Map<String,BigDecimal> selectSaleOverall();

}
