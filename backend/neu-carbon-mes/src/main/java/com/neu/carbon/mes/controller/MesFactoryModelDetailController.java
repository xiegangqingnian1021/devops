package com.neu.carbon.mes.controller;

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
import com.neu.carbon.mes.domain.MesFactoryModelDetail;
import com.neu.carbon.mes.service.IMesFactoryModelDetailService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 工厂建模明细Controller
 * 
 * @author neuedu
 * @date 2022-07-07
 */
@Api(tags = {"工厂建模明细"})
@RestController
@RequestMapping("/mesModel/factoryModelDetail")
public class MesFactoryModelDetailController extends BaseController
{
    @Autowired
    private IMesFactoryModelDetailService mesFactoryModelDetailService;

    /**
     * 查询工厂建模明细列表
     */
    @GetMapping("/list")
    @ApiOperation("查询工厂建模明细列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = MesFactoryModelDetail.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(MesFactoryModelDetail mesFactoryModelDetail)
    {
        startPage();
        List<MesFactoryModelDetail> list = mesFactoryModelDetailService.selectMesFactoryModelDetailList(mesFactoryModelDetail);
        return getDataTable(list);
    }

    /**
     * 导出工厂建模明细列表
     */
    @ApiOperation("导出工厂建模明细列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('mesModel:factoryModelDetail:export')")
    @Log(title = "工厂建模明细", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MesFactoryModelDetail mesFactoryModelDetail)
    {
        List<MesFactoryModelDetail> list = mesFactoryModelDetailService.selectMesFactoryModelDetailList(mesFactoryModelDetail);
        ExcelUtil<MesFactoryModelDetail> util = new ExcelUtil<MesFactoryModelDetail>(MesFactoryModelDetail.class);
        return util.exportExcel(list, "factoryModelDetail");
    }

    /**
     * 获取工厂建模明细详细信息
     */
    @ApiOperation("获取工厂建模明细详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = MesFactoryModelDetail.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(mesFactoryModelDetailService.selectMesFactoryModelDetailById(id));
    }

    /**
     * 新增工厂建模明细
     */
    @ApiOperation("新增工厂建模明细")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('mesModel:factoryModelDetail:add')")
    @Log(title = "工厂建模明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesFactoryModelDetail mesFactoryModelDetail)
    {
        return toAjax(mesFactoryModelDetailService.insertMesFactoryModelDetail(mesFactoryModelDetail));
    }

    /**
     * 修改工厂建模明细
     */
    @ApiOperation("修改工厂建模明细")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('mesModel:factoryModelDetail:edit')")
    @Log(title = "工厂建模明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesFactoryModelDetail mesFactoryModelDetail)
    {
        return toAjax(mesFactoryModelDetailService.updateMesFactoryModelDetail(mesFactoryModelDetail));
    }

    /**
     * 删除工厂建模明细
     */
    @ApiOperation("删除工厂建模明细")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('mesModel:factoryModelDetail:remove')")
    @Log(title = "工厂建模明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mesFactoryModelDetailService.deleteMesFactoryModelDetailByIds(ids));
    }
}
