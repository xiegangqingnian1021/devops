package com.neu.carbon.report.controller;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.carbon.scm.domain.VScmSaleContractDetail;
import com.neu.carbon.scm.domain.VScmSalePlanDetail;
import com.neu.carbon.scm.service.IVScmSaleContractDetailService;
import com.neu.carbon.scm.service.IVScmSalePlanDetailService;
import com.neu.common.annotation.Log;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.page.TableDataInfo;
import com.neu.common.enums.BusinessType;
import com.neu.common.utils.DateUtils;
import com.neu.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 销售计划达成率报表Controller
 *
 * @author neuedu
 * @date 2022-08-02
 */
@Api(tags = {"销售计划达成率报表"})
@RestController
@RequestMapping("/saleReport/salePlanDetailReport")
public class VScmSalePlanDetailController extends BaseController {
    @Autowired
    private IVScmSalePlanDetailService vScmSalePlanDetailService;
    @Autowired
    private IVScmSaleContractDetailService vScmSaleContractDetailService;

    /**
     * 查询销售计划达成率报表列表
     */
    @GetMapping("/list")
    @ApiOperation("查询销售计划达成率报表列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = VScmSalePlanDetail.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(VScmSalePlanDetail vScmSalePlanDetail) {
        startPage();
        List<VScmSalePlanDetail> list = vScmSalePlanDetailService.selectVScmSalePlanDetailList(vScmSalePlanDetail);
        list.stream().forEach(item -> {
            VScmSaleContractDetail con = new VScmSaleContractDetail();
            con.getParams().put("beginSignDate", DateUtils.dateTime(item.getStartDate()));
            con.getParams().put("endSignDate", DateUtils.dateTime(item.getEndDate()));
            con.setMaterialId(item.getMaterialId());
            List<VScmSaleContractDetail> detailList = vScmSaleContractDetailService.selectVScmSaleContractDetailList(con);
            BigDecimal saleAmount = new BigDecimal("0");
            for (VScmSaleContractDetail vScmSaleContractDetail : detailList) {
                saleAmount = saleAmount.add(vScmSaleContractDetail.getContractAmount());
            }
            item.setSaleAmount(saleAmount);
        });
        return getDataTable(list);
    }

    /**
     * 导出销售计划达成率报表列表
     */
    @ApiOperation("导出销售计划达成率报表列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('saleReport:salePlanDetailReport:export')")
    @Log(title = "销售计划达成率报表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(VScmSalePlanDetail vScmSalePlanDetail) {
        List<VScmSalePlanDetail> list = vScmSalePlanDetailService.selectVScmSalePlanDetailList(vScmSalePlanDetail);
        list.stream().forEach(item -> {
            VScmSaleContractDetail con = new VScmSaleContractDetail();
            con.getParams().put("beginSignDate", DateUtils.dateTime(item.getStartDate()));
            con.getParams().put("endSignDate", DateUtils.dateTime(item.getEndDate()));
            con.setMaterialId(item.getMaterialId());
            List<VScmSaleContractDetail> detailList = vScmSaleContractDetailService.selectVScmSaleContractDetailList(con);
            BigDecimal saleAmount = new BigDecimal("0");
            for (VScmSaleContractDetail vScmSaleContractDetail : detailList) {
                saleAmount = saleAmount.add(vScmSaleContractDetail.getContractAmount());
            }
            item.setSaleAmount(saleAmount);
            if (saleAmount.compareTo(item.getTargetAmount()) >= 0) {
                item.setStatus("1");
            }
        });
        ExcelUtil<VScmSalePlanDetail> util = new ExcelUtil<VScmSalePlanDetail>(VScmSalePlanDetail.class);
        return util.exportExcel(list, "salePlanDetailReport");
    }

}
