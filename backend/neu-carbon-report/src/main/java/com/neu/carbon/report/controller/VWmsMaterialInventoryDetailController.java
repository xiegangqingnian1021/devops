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
import com.neu.carbon.wms.domain.VWmsMaterialInventoryDetail;
import com.neu.carbon.wms.service.IVWmsMaterialInventoryDetailService;
import com.neu.common.annotation.Log;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.page.TableDataInfo;
import com.neu.common.enums.BusinessType;
import com.neu.common.utils.poi.ExcelUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 库存明细报表Controller
 * 
 * @author neuedu
 * @date 2022-07-26
 */
@Api(tags = {"库存明细报表"})
@RestController
@RequestMapping("/wmsReport/materialInventoryDetailReport")
public class VWmsMaterialInventoryDetailController extends BaseController
{
    @Autowired
    private IVWmsMaterialInventoryDetailService vWmsMaterialInventoryDetailService;

    /**
     * 查询库存明细报表列表
     */
    @GetMapping("/list")
    @ApiOperation("查询库存明细报表列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = VWmsMaterialInventoryDetail.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(VWmsMaterialInventoryDetail vWmsMaterialInventoryDetail)
    {
        startPage();
        List<VWmsMaterialInventoryDetail> list = vWmsMaterialInventoryDetailService.selectVWmsMaterialInventoryDetailList(vWmsMaterialInventoryDetail);
        return getDataTable(list);
    }

    /**
     * 导出库存明细报表列表
     */
    @ApiOperation("导出库存明细报表列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('wmsReport:materialInventoryDetailReport:export')")
    @Log(title = "库存明细报表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(VWmsMaterialInventoryDetail vWmsMaterialInventoryDetail)
    {
        List<VWmsMaterialInventoryDetail> list = vWmsMaterialInventoryDetailService.selectVWmsMaterialInventoryDetailList(vWmsMaterialInventoryDetail);
        ExcelUtil<VWmsMaterialInventoryDetail> util = new ExcelUtil<VWmsMaterialInventoryDetail>(VWmsMaterialInventoryDetail.class);
        return util.exportExcel(list, "materialInventoryDetailReport");
    }

    /**
     * 获取库存明细报表详细信息
     */
    @ApiOperation("获取库存明细报表详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = VWmsMaterialInventoryDetail.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{whId}")
    public AjaxResult getInfo(@PathVariable("whId") Long whId)
    {
        return AjaxResult.success(vWmsMaterialInventoryDetailService.selectVWmsMaterialInventoryDetailById(whId));
    }
}
