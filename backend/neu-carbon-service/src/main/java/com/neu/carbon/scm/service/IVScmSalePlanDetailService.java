package com.neu.carbon.scm.service;

import com.neu.carbon.scm.domain.VScmSalePlanDetail;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 销售计划达成率报表Service接口
 * 
 * @author neuedu
 * @date 2022-08-02
 */
public interface IVScmSalePlanDetailService 
{

    /**
     * 查询销售计划达成率报表列表
     * 
     * @param vScmSalePlanDetail 销售计划达成率报表
     * @return 销售计划达成率报表集合
     */
    public List<VScmSalePlanDetail> selectVScmSalePlanDetailList(VScmSalePlanDetail vScmSalePlanDetail);


    /**
     * 按月统计计划销售额与实际销售额
     * @param month
     * @return
     */
    public Map<String, BigDecimal> selectAchieveRateByMonth(String month);

}
