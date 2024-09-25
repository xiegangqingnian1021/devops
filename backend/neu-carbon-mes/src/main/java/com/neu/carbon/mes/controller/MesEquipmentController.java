package com.neu.carbon.mes.controller;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.carbon.mes.domain.MesEquipment;
import com.neu.carbon.mes.service.IMesEquipmentService;
import com.neu.common.annotation.Log;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.page.TableDataInfo;
import com.neu.common.enums.BusinessType;
import com.neu.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 设备信息Controller
 *
 * @author neuedu
 * @date 2022-07-07
 */
@Api(tags = {"制造执行MES-设备信息"})
@RestController
@RequestMapping("/baseInfo/equipmentInfo")
public class MesEquipmentController extends BaseController {
    @Autowired
    private IMesEquipmentService mesEquipmentService;

    /**
     * 查询设备信息列表
     */
    @GetMapping("/list")
    @ApiOperation("查询设备信息列表")
    @DynamicResponseParameters(name = "baseInfoEquipmentInfoList", properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = MesEquipment.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(MesEquipment mesEquipment) {
        startPage();
        List<MesEquipment> list = mesEquipmentService.selectMesEquipmentList(mesEquipment);
        return getDataTable(list);
    }

    /**
     * 导出设备信息列表
     */
    @ApiOperation("导出设备信息列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('baseInfo:equipmentInfo:export')")
    @Log(title = "设备信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MesEquipment mesEquipment) {
        List<MesEquipment> list = mesEquipmentService.selectMesEquipmentList(mesEquipment);
        ExcelUtil<MesEquipment> util = new ExcelUtil<MesEquipment>(MesEquipment.class);
        return util.exportExcel(list, "equipmentInfo");
    }

    /**
     * 获取设备信息详细信息
     */
    @ApiOperation("获取设备信息详细信息")
    @DynamicResponseParameters(name = "baseInfoEquipmentInfoGet", properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = MesEquipment.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(mesEquipmentService.selectMesEquipmentById(id));
    }

    /**
     * 新增设备信息
     */
    @ApiOperation("新增设备信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('baseInfo:equipmentInfo:add')")
    @Log(title = "设备信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesEquipment mesEquipment) {
        return toAjax(mesEquipmentService.insertMesEquipment(mesEquipment));
    }

    /**
     * 修改设备信息
     */
    @ApiOperation("修改设备信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('baseInfo:equipmentInfo:edit')")
    @Log(title = "设备信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesEquipment mesEquipment) {
        return toAjax(mesEquipmentService.updateMesEquipment(mesEquipment));
    }

    /**
     * 删除设备信息
     */
    @ApiOperation("删除设备信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('baseInfo:equipmentInfo:remove')")
    @Log(title = "设备信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(mesEquipmentService.deleteMesEquipmentByIds(ids));
    }
}
