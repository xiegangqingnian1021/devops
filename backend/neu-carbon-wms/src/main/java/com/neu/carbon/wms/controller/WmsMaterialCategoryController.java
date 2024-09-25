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
import com.neu.carbon.wms.domain.WmsMaterialCategory;
import com.neu.carbon.wms.service.IWmsMaterialCategoryService;
import com.neu.common.utils.poi.ExcelUtil;

/**
 * 物料分类Controller
 * 
 * @author neusoft
 * @date 2022-06-24
 */
@Api(tags = {"物料分类"})
@RestController
@RequestMapping("/material/materialCategory")
public class WmsMaterialCategoryController extends BaseController
{
    @Autowired
    private IWmsMaterialCategoryService wmsMaterialCategoryService;

    /**
     * 查询物料分类列表
     */
    @PreAuthorize("@ss.hasPermi('material:materialCategory:list')")
    @GetMapping("/list")
    @ApiOperation("查询物料分类列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据（数组类型）", dataTypeClass = WmsMaterialCategory.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public AjaxResult list(WmsMaterialCategory wmsMaterialCategory)
    {
        List<WmsMaterialCategory> list = wmsMaterialCategoryService.selectWmsMaterialCategoryList(wmsMaterialCategory);
        return AjaxResult.success(list);
    }

    /**
     * 导出物料分类列表
     */
    @ApiOperation("导出物料分类列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('material:materialCategory:export')")
    @Log(title = "物料分类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WmsMaterialCategory wmsMaterialCategory)
    {
        List<WmsMaterialCategory> list = wmsMaterialCategoryService.selectWmsMaterialCategoryList(wmsMaterialCategory);
        ExcelUtil<WmsMaterialCategory> util = new ExcelUtil<WmsMaterialCategory>(WmsMaterialCategory.class);
        return util.exportExcel(list, "materialCategory");
    }

    /**
     * 获取物料分类详细信息
     */
    @ApiOperation("获取物料分类详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = WmsMaterialCategory.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('material:materialCategory:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wmsMaterialCategoryService.selectWmsMaterialCategoryById(id));
    }

    /**
     * 新增物料分类
     */
    @ApiOperation("新增物料分类")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('material:materialCategory:add')")
    @Log(title = "物料分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmsMaterialCategory wmsMaterialCategory)
    {
        return toAjax(wmsMaterialCategoryService.insertWmsMaterialCategory(wmsMaterialCategory));
    }

    /**
     * 修改物料分类
     */
    @ApiOperation("修改物料分类")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('material:materialCategory:edit')")
    @Log(title = "物料分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmsMaterialCategory wmsMaterialCategory)
    {
        return toAjax(wmsMaterialCategoryService.updateWmsMaterialCategory(wmsMaterialCategory));
    }

    /**
     * 删除物料分类
     */
    @ApiOperation("删除物料分类")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('material:materialCategory:remove')")
    @Log(title = "物料分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wmsMaterialCategoryService.deleteWmsMaterialCategoryByIds(ids));
    }
}