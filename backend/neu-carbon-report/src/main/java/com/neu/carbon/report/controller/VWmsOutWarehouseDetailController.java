package com.neu.carbon.report.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.carbon.wms.domain.VWmsOutWarehouseDetail;
import com.neu.carbon.wms.service.IVWmsOutWarehouseDetailService;
import com.neu.common.annotation.Log;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.page.TableDataInfo;
import com.neu.common.enums.BusinessType;
import com.neu.common.utils.poi.ExcelUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 物料出库明细Controller
 * 
 * @author neuedu
 * @date 2022-07-30
 */
@Api(tags = {"物料出库明细"})
@RestController
@RequestMapping("/wmsReport/outWarehouseDetailReport")
public class VWmsOutWarehouseDetailController extends BaseController
{
    @Autowired
    private IVWmsOutWarehouseDetailService vWmsOutWarehouseDetailService;

    /**
     * 查询物料出库明细列表
     */
    @GetMapping("/list")
    @ApiOperation("查询物料出库明细列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = VWmsOutWarehouseDetail.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(VWmsOutWarehouseDetail vWmsOutWarehouseDetail)
    {
        startPage();
        List<VWmsOutWarehouseDetail> list = vWmsOutWarehouseDetailService.selectVWmsOutWarehouseDetailList(vWmsOutWarehouseDetail);
        return getDataTable(list);
    }

    /**
     * 导出物料出库明细列表
     */
    @ApiOperation("导出物料出库明细列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('wmsReport:outWarehouseDetailReport:export')")
    @Log(title = "物料出库明细", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(VWmsOutWarehouseDetail vWmsOutWarehouseDetail)
    {
        List<VWmsOutWarehouseDetail> list = vWmsOutWarehouseDetailService.selectVWmsOutWarehouseDetailList(vWmsOutWarehouseDetail);
        ExcelUtil<VWmsOutWarehouseDetail> util = new ExcelUtil<VWmsOutWarehouseDetail>(VWmsOutWarehouseDetail.class);
        return util.exportExcel(list, "outWarehouseDetailReport");
    }

    /**
     * 获取物料出库明细详细信息
     */
    @ApiOperation("获取物料出库明细详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = VWmsOutWarehouseDetail.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{materialId}")
    public AjaxResult getInfo(@PathVariable("materialId") Long materialId)
    {
        return AjaxResult.success(vWmsOutWarehouseDetailService.selectVWmsOutWarehouseDetailById(materialId));
    }

    
}
