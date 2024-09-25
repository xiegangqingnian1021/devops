package com.neu.carbon.report.controller;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.carbon.mes.domain.MesProductCheck;
import com.neu.carbon.mes.service.IMesProductCheckService;
import com.neu.common.annotation.Log;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.page.TableDataInfo;
import com.neu.common.enums.BusinessType;
import com.neu.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 生产检验报表统计Controller
 *
 * @author neuedu
 * @date 2022-07-25
 */
@Api(tags = {"生产检验报表统计"})
@RestController
@RequestMapping("/report/mesCheck")
public class MesCheckReportController extends BaseController {
    @Autowired
    private IMesProductCheckService mesProductCheckService;

    @GetMapping("/productCheck/list")
    @ApiOperation("查询生产检验列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = MesProductCheck.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(MesProductCheck mesProductCheck) {
        startPage();
        List<MesProductCheck> list = mesProductCheckService.selectQualifiedRateReport(mesProductCheck);
        return getDataTable(list);
    }

    @ApiOperation("导出生产检验报表列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @Log(title = "产品检验单", businessType = BusinessType.EXPORT)
    @GetMapping("/productCheck/export")
    public AjaxResult export(MesProductCheck mesProductCheck) {
        List<MesProductCheck> list = mesProductCheckService.selectQualifiedRateReport(mesProductCheck);
        ExcelUtil<MesProductCheck> util = new ExcelUtil<MesProductCheck>(MesProductCheck.class);
        return util.exportExcel(list, "productCheck");
    }




}
