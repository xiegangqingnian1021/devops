package com.neu.system.controller;

import java.util.List;

import com.neu.common.config.NeuConfig;
import com.neu.system.service.CommandService;
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
import com.neu.system.domain.OpenstackRouterInfo;
import com.neu.system.service.IOpenstackRouterInfoService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

import javax.annotation.Resource;

/**
 * 路由信息Controller
 * 
 * @author neuedu
 * @date 2024-12-16
 */
@Api(tags = {"路由信息"})
@RestController
@RequestMapping("/system/openstack_router_info")
public class OpenstackRouterInfoController extends BaseController
{
    @Autowired
    private IOpenstackRouterInfoService openstackRouterInfoService;

    @Resource
    private CommandService commandService;

    /**
     * 查询路由信息列表
     */
    @GetMapping("/list")
    @ApiOperation("查询路由信息列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = OpenstackRouterInfo.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(OpenstackRouterInfo openstackRouterInfo)
    {
        startPage();
        List<OpenstackRouterInfo> list = openstackRouterInfoService.selectOpenstackRouterInfoList(openstackRouterInfo);
        return getDataTable(list);
    }

    /**
     * 导出路由信息列表
     */
    @ApiOperation("导出路由信息列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_router_info:export')")
    @Log(title = "路由信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(OpenstackRouterInfo openstackRouterInfo)
    {
        List<OpenstackRouterInfo> list = openstackRouterInfoService.selectOpenstackRouterInfoList(openstackRouterInfo);
        ExcelUtil<OpenstackRouterInfo> util = new ExcelUtil<OpenstackRouterInfo>(OpenstackRouterInfo.class);
        return util.exportExcel(list, "openstack_router_info");
    }

    /**
     * 获取路由信息详细信息
     */
    @ApiOperation("获取路由信息详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = OpenstackRouterInfo.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{routerId}")
    public AjaxResult getInfo(@PathVariable("routerId") String routerId)
    {
        return AjaxResult.success(openstackRouterInfoService.selectOpenstackRouterInfoById(routerId));
    }

    /**
     * 新增路由信息
     */
    @ApiOperation("新增路由信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_router_info:add')")
    @Log(title = "路由信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OpenstackRouterInfo openstackRouterInfo)
    {
        // 1.准备参数
        String routerName = openstackRouterInfo.getRouterName();
        String routerDescription = openstackRouterInfo.getRouterDescription();
        String projectId = openstackRouterInfo.getProjectId();

        // 2.构建创建路由的CMD
        String cmd = String.format("ssh %s@%s -p%s " +
                        "'bash /cmd/openstack-router-create.sh %s %s %s'",
                NeuConfig.getExecUser(),
                NeuConfig.getExecHost(),
                NeuConfig.getExecPort(),
                routerName,
                routerDescription,
                projectId);

        // 3.发起请求获得生成的路由ID
        String res = commandService.executeCommand(cmd);
        if (!res.startsWith("0")){
            return AjaxResult.error("创建错误", res);
        }
        /* -- 获取生成的路由ID -- */
        String routerId = res.split(":")[2].replace("\n","");
        openstackRouterInfo.setRouterId(routerId);

        // 4. 同步信息到数据库
        return toAjax(openstackRouterInfoService.insertOpenstackRouterInfo(openstackRouterInfo));
    }

    /**
     * 修改路由信息
     */
    @ApiOperation("修改路由信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_router_info:edit')")
    @Log(title = "路由信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OpenstackRouterInfo openstackRouterInfo)
    {
        return toAjax(openstackRouterInfoService.updateOpenstackRouterInfo(openstackRouterInfo));
    }

    /**
     * 删除路由信息
     */
    @ApiOperation("删除路由信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_router_info:remove')")
    @Log(title = "路由信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{routerIds}")
    public AjaxResult remove(@PathVariable String[] routerIds)
    {
        return toAjax(openstackRouterInfoService.deleteOpenstackRouterInfoByIds(routerIds));
    }
}
