package com.neu.carbon.footprint.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.neu.common.annotation.Log;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.enums.BusinessType;
import com.neu.carbon.footprint.domain.CfElectricCheckpoint;
import com.neu.carbon.footprint.service.ICfElectricCheckpointService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 能效检测点Controller
 * 
 * @author neuedu
 * @date 2022-07-22
 */
@Api(tags = {"能效检测点"})
@RestController
@RequestMapping("/cfElectric/checkpoint")
public class CfElectricCheckpointController extends BaseController
{
    @Autowired
    private ICfElectricCheckpointService cfElectricCheckpointService;

    /**
     * 查询能效检测点列表
     */
    @GetMapping("/list")
    @ApiOperation("查询能效检测点列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = CfElectricCheckpoint.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(CfElectricCheckpoint cfElectricCheckpoint)
    {
        startPage();
        List<CfElectricCheckpoint> list = cfElectricCheckpointService.selectCfElectricCheckpointList(cfElectricCheckpoint);
        return getDataTable(list);
    }

    /**
     * 导出能效检测点列表
     */
    @ApiOperation("导出能效检测点列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('cfElectric:checkpoint:export')")
    @Log(title = "能效检测点", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CfElectricCheckpoint cfElectricCheckpoint)
    {
        List<CfElectricCheckpoint> list = cfElectricCheckpointService.selectCfElectricCheckpointList(cfElectricCheckpoint);
        ExcelUtil<CfElectricCheckpoint> util = new ExcelUtil<CfElectricCheckpoint>(CfElectricCheckpoint.class);
        return util.exportExcel(list, "checkpoint");
    }

    /**
     * 获取能效检测点详细信息
     */
    @ApiOperation("获取能效检测点详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = CfElectricCheckpoint.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(cfElectricCheckpointService.selectCfElectricCheckpointById(id));
    }

    /**
     * 新增能效检测点
     */
    @ApiOperation("新增能效检测点")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('cfElectric:checkpoint:add')")
    @Log(title = "能效检测点", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CfElectricCheckpoint cfElectricCheckpoint)
    {
        return toAjax(cfElectricCheckpointService.insertCfElectricCheckpoint(cfElectricCheckpoint));
    }

    /**
     * 修改能效检测点
     */
    @ApiOperation("修改能效检测点")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('cfElectric:checkpoint:edit')")
    @Log(title = "能效检测点", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CfElectricCheckpoint cfElectricCheckpoint)
    {
        return toAjax(cfElectricCheckpointService.updateCfElectricCheckpoint(cfElectricCheckpoint));
    }

    /**
     * 删除能效检测点
     */
    @ApiOperation("删除能效检测点")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('cfElectric:checkpoint:remove')")
    @Log(title = "能效检测点", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cfElectricCheckpointService.deleteCfElectricCheckpointByIds(ids));
    }
}
