package com.neu.carbon.report.controller;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.carbon.scm.service.IVScmSaleContractService;
import com.neu.carbon.scm.service.IVScmSalePlanDetailService;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Api(tags = {"大屏图标展示"})
@RestController
@RequestMapping("/carbonReport")
public class BigScreenReport {

    @Autowired
    private IVScmSaleContractService vScmSaleContractService;
    @Autowired
    private IVScmSalePlanDetailService vScmSalePlanDetailService;


    @GetMapping("/saleOverall")
    @ApiOperation("销售总览：本年、本月、本日销售额")
    @DynamicResponseParameters(properties = {@DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据（{todayTotal:12,yearTotal:199,monthTotal:20}）"),
            @DynamicParameter(name = "msg", value = "返回消息内容")})
    public AjaxResult saleOverall() {
        Map<String, BigDecimal> map = vScmSaleContractService.selectSaleOverall();
        return AjaxResult.success(map);
    }


    @GetMapping("/saleCustomer/ranking")
    @ApiOperation("客户销售排名")
    @DynamicResponseParameters(properties = {@DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据List<map>, map={name:上汽,value：1000000}>"),
            @DynamicParameter(name = "msg", value = "返回消息内容")})
    public AjaxResult saleCustomerRank() {
        List<Map<String, BigDecimal>> list = vScmSaleContractService.selectSaleCustomerRank();
        return AjaxResult.success(list);
    }


    @GetMapping("/saleStat")
    @ApiOperation("销售统计")
    @DynamicResponseParameters(properties = {@DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据dateList, numList, numList2"),
            @DynamicParameter(name = "msg", value = "返回消息内容")})
    public AjaxResult saleStat() {
        List<String> dateList = new ArrayList<>();
        for (int i = 5; i >= 0; i--) {
            Date month = DateUtils.addMonths(new Date(), -1 * i);
            dateList.add(DateUtils.parseDateToStr(DateUtils.YYYY_MM, month));
        }
        List<BigDecimal> numList = new ArrayList<>();
        List<BigDecimal> numList2 = new ArrayList<>();
        for (String month : dateList) {
            Map<String, BigDecimal> map = vScmSaleContractService.selectSaleStatByMonth(month);
            numList.add(map.get("contractTotal").divide(new BigDecimal(10000)).setScale(2, RoundingMode.HALF_UP));
            numList2.add(map.get("orderTotal").divide(new BigDecimal(10000)).setScale(2, RoundingMode.HALF_UP));
        }
        Map<String, Object> result = new HashMap<>();
        result.put("dateList", dateList);
        result.put("numList", numList);
        result.put("numList2", numList2);
        return AjaxResult.success(result);
    }


    @GetMapping("/salePlan/achieveRate")
    @ApiOperation("销售计划完成率")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据barData 已售金额列表,lineData 月计划销售额列表, category 时间月份轴列表, rateData 达成率列表"),
            @DynamicParameter(name = "msg", value = "返回消息内容")})
    public AjaxResult salePlanAchieveRate() {
        List<String> category = new ArrayList<>();
        for (int i = 11; i >= 0; i--) {
            Date month = DateUtils.addMonths(new Date(), -1 * i);
            category.add(DateUtils.parseDateToStr(DateUtils.YYYY_MM, month));
        }
        List<BigDecimal> barData = new ArrayList<>();
        List<BigDecimal> lineData = new ArrayList<>();
        List<String> rateData = new ArrayList<>();
        for (String month : category) {
            Map<String, BigDecimal> map = vScmSalePlanDetailService.selectAchieveRateByMonth(month);
            BigDecimal contractTotal = map.get("contractTotal");
            BigDecimal planTotal = map.get("planTotal");
            barData.add(contractTotal.divide(new BigDecimal(10000)).setScale(2, RoundingMode.HALF_UP));
            lineData.add(planTotal.divide(new BigDecimal(10000)).setScale(2, RoundingMode.HALF_UP));
            if (planTotal.compareTo(BigDecimal.ZERO) == 0) {
                if (contractTotal.compareTo(BigDecimal.ZERO) == 0) {
                    rateData.add("0");
                } else {
                    rateData.add("100");
                }
            } else {
                double rate = (contractTotal.divide(planTotal,RoundingMode.HALF_UP)).doubleValue() * 100;
                rateData.add(rate >= 100 ? "100" : String.valueOf((int) rate));
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("barData", barData);
        result.put("lineData", lineData);
        result.put("rateData", rateData);
        result.put("category", category);
        return AjaxResult.success(result);
    }


}
