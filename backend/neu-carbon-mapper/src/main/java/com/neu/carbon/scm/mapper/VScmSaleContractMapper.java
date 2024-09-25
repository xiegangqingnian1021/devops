package com.neu.carbon.scm.mapper;

import com.neu.carbon.scm.domain.VScmFinanceReport;
import com.neu.carbon.scm.domain.VScmSaleContract;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 销售合同报表Mapper接口
 * 
 * @author neuedu
 * @date 2022-07-28
 */
public interface VScmSaleContractMapper 
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
     * 查询按月统计销售合同报表列表
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
     * 按季度统计财务报告
     * @param vScmFinanceReport
     * @return
     */
    List<VScmFinanceReport> selectMonthFinanceReport(VScmFinanceReport vScmFinanceReport);

    /**
     * 按月统计财务报告
     * @param vScmFinanceReport
     * @return
     */
    List<VScmFinanceReport> selectQuarterFinanceReport(VScmFinanceReport vScmFinanceReport);

    /**
     * 销售客户榜单
     * @param
     * @return
     */
    List<Map<String,BigDecimal>> selectSaleCustomerRank();

    /**
     * 按月统计销售额
     * @param month
     * @return
     */
    Map<String, BigDecimal> selectSaleStatByMonth(@Param("month") String month);


    /**
     * 大屏销售总览
     * @return
     */
    Map<String,BigDecimal> selectSaleOverall();

}
