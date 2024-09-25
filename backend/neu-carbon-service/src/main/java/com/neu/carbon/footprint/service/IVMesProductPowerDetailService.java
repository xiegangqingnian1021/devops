package com.neu.carbon.footprint.service;

import com.neu.carbon.footprint.domain.VMesProductPowerDetail;

import java.util.List;

/**
 * VIEWService接口
 * 
 * @author neuedu
 * @date 2022-07-31
 */
public interface IVMesProductPowerDetailService 
{
    /**
     * 查询VIEW
     * 
     * @param planId VIEWID
     * @return VIEW
     */
    public VMesProductPowerDetail selectVMesProductPowerDetailById(Long planId);

    /**
     * 查询VIEW列表
     * 
     * @param vMesProductPowerDetail VIEW
     * @return VIEW集合
     */
    public List<VMesProductPowerDetail> selectVMesProductPowerDetailList(VMesProductPowerDetail vMesProductPowerDetail);

    /**
     * 生产能耗月度报表
     * @param vMesProductPowerDetail
     * @return
     */
    public List<VMesProductPowerDetail> selectProductPowerReportByMonth(VMesProductPowerDetail vMesProductPowerDetail);

    /**
     * 生产能耗年度报表
     * @param vMesProductPowerDetail
     * @return
     */
    public List<VMesProductPowerDetail> selectProductPowerReportByYear(VMesProductPowerDetail vMesProductPowerDetail);

    /**
     * 获取统计的月份
     * @param vMesProductPowerDetail
     * @return
     */
    public List<VMesProductPowerDetail> selectReportMonth(VMesProductPowerDetail vMesProductPowerDetail);
    
    /**
     * 生产能耗日报表
     * @param vMesProductPowerDetail
     * @return
     */
    public List<VMesProductPowerDetail> selectProductPowerReportByDay(VMesProductPowerDetail vMesProductPowerDetail);
}
