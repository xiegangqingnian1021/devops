package com.neu.carbon.wms.controller;

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
import com.neu.carbon.wms.domain.WmsMaterialInfo;
import com.neu.carbon.wms.service.IWmsMaterialInfoService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 物料档案管理Controller
 * 
 * @author neusoft
 * @date 2022-06-24
 */
@Api(tags = {"物料档案管理"})
@RestController
@RequestMapping("/material/materialInfo")
public class WmsMaterialInfoController extends BaseController
{
    @Autowired
    private IWmsMaterialInfoService wmsMaterialInfoService;

    /**
     * 查询物料档案管理列表
     */
    @GetMapping("/list")
    @ApiOperation("查询物料档案管理列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = WmsMaterialInfo.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(WmsMaterialInfo wmsMaterialInfo)
    {
        startPage();
        List<WmsMaterialInfo> list = wmsMaterialInfoService.selectWmsMaterialInfoList(wmsMaterialInfo);
        return getDataTable(list);
    }

    /**
     * 导出物料档案管理列表
     */
    @ApiOperation("导出物料档案管理列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('material:materialInfo:export')")
    @Log(title = "物料档案管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WmsMaterialInfo wmsMaterialInfo)
    {
        List<WmsMaterialInfo> list = wmsMaterialInfoService.selectWmsMaterialInfoList(wmsMaterialInfo);
        ExcelUtil<WmsMaterialInfo> util = new ExcelUtil<WmsMaterialInfo>(WmsMaterialInfo.class);
        return util.exportExcel(list, "materialInfo");
    }

    /**
     * 获取物料档案管理详细信息
     */
    @ApiOperation("获取物料档案管理详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = WmsMaterialInfo.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wmsMaterialInfoService.selectWmsMaterialInfoById(id));
    }

    /**
     * 新增物料档案管理
     */
    @ApiOperation("新增物料档案管理")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('material:materialInfo:add')")
    @Log(title = "物料档案管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmsMaterialInfo wmsMaterialInfo)
    {
        return toAjax(wmsMaterialInfoService.insertWmsMaterialInfo(wmsMaterialInfo));
    }

    /**
     * 修改物料档案管理
     */
    @ApiOperation("修改物料档案管理")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('material:materialInfo:edit')")
    @Log(title = "物料档案管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmsMaterialInfo wmsMaterialInfo)
    {
        return toAjax(wmsMaterialInfoService.updateWmsMaterialInfo(wmsMaterialInfo));
    }

    /**
     * 删除物料档案管理
     */
    @ApiOperation("删除物料档案管理")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('material:materialInfo:remove')")
    @Log(title = "物料档案管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wmsMaterialInfoService.deleteWmsMaterialInfoByIds(ids));
    }
}