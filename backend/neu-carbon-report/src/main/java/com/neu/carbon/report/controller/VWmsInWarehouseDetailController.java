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
import com.neu.carbon.wms.domain.VWmsInWarehouseDetail;
import com.neu.carbon.wms.service.IVWmsInWarehouseDetailService;
import com.neu.common.annotation.Log;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.page.TableDataInfo;
import com.neu.common.enums.BusinessType;
import com.neu.common.utils.poi.ExcelUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 入库明细报表Controller
 * 
 * @author neuedu
 * @date 2022-07-27
 */
@Api(tags = {"入库明细报表"})
@RestController
@RequestMapping("/wmsReport/inWarehouseDetailReport")
public class VWmsInWarehouseDetailController extends BaseController
{
    @Autowired
    private IVWmsInWarehouseDetailService vWmsInWarehouseDetailService;

    /**
     * 查询入库明细报表列表
     */
    @GetMapping("/list")
    @ApiOperation("查询入库明细报表列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = VWmsInWarehouseDetail.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(VWmsInWarehouseDetail vWmsInWarehouseDetail)
    {
        startPage();
        List<VWmsInWarehouseDetail> list = vWmsInWarehouseDetailService.selectVWmsInWarehouseDetailList(vWmsInWarehouseDetail);
        return getDataTable(list);
    }

    /**
     * 导出入库明细报表列表
     */
    @ApiOperation("导出入库明细报表列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('wmsReport:inWarehouseDetailReport:export')")
    @Log(title = "入库明细报表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(VWmsInWarehouseDetail vWmsInWarehouseDetail)
    {
        List<VWmsInWarehouseDetail> list = vWmsInWarehouseDetailService.selectVWmsInWarehouseDetailList(vWmsInWarehouseDetail);
        ExcelUtil<VWmsInWarehouseDetail> util = new ExcelUtil<VWmsInWarehouseDetail>(VWmsInWarehouseDetail.class);
        return util.exportExcel(list, "inWarehouseDetailReport");
    }

    /**
     * 获取入库明细报表详细信息
     */
    @ApiOperation("获取入库明细报表详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = VWmsInWarehouseDetail.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{materialId}")
    public AjaxResult getInfo(@PathVariable("materialId") Long materialId)
    {
        return AjaxResult.success(vWmsInWarehouseDetailService.selectVWmsInWarehouseDetailById(materialId));
    }
}
