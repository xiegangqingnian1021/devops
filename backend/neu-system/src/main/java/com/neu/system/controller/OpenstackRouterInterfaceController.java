package com.neu.system.controller;

import java.util.List;

import com.neu.common.config.NeuConfig;
import com.neu.system.service.CommandService;
import org.springframework.data.redis.connection.ReactiveSetCommands;
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
import com.neu.system.domain.OpenstackRouterInterface;
import com.neu.system.service.IOpenstackRouterInterfaceService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

import javax.annotation.Resource;

/**
 * 路由接口Controller
 *
 * @author neuedu
 * @date 2024-12-17
 */
@Api(tags = {"路由接口"})
@RestController
@RequestMapping("/system/openstack_router_interface")
public class OpenstackRouterInterfaceController extends BaseController {
    @Autowired
    private IOpenstackRouterInterfaceService openstackRouterInterfaceService;

    @Resource
    private CommandService commandService;

    /**
     * 查询路由接口列表
     */
    @GetMapping("/list")
    @ApiOperation("查询路由接口列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = OpenstackRouterInterface.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(OpenstackRouterInterface openstackRouterInterface) {
        startPage();
        List<OpenstackRouterInterface> list = openstackRouterInterfaceService.selectOpenstackRouterInterfaceList(openstackRouterInterface);
        return getDataTable(list);
    }

    /**
     * 导出路由接口列表
     */
    @ApiOperation("导出路由接口列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_router_interface:export')")
    @Log(title = "路由接口", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(OpenstackRouterInterface openstackRouterInterface) {
        List<OpenstackRouterInterface> list = openstackRouterInterfaceService.selectOpenstackRouterInterfaceList(openstackRouterInterface);
        ExcelUtil<OpenstackRouterInterface> util = new ExcelUtil<OpenstackRouterInterface>(OpenstackRouterInterface.class);
        return util.exportExcel(list, "openstack_router_interface");
    }

    /**
     * 获取路由接口详细信息
     */
    @ApiOperation("获取路由接口详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = OpenstackRouterInterface.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(openstackRouterInterfaceService.selectOpenstackRouterInterfaceById(id));
    }

    /**
     * 新增路由接口
     */
    @ApiOperation("新增路由接口")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_router_interface:add')")
    @Log(title = "路由接口", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OpenstackRouterInterface openstackRouterInterface) {
        // 1. 准备参数
        String routerId = openstackRouterInterface.getRouterId().split(":")[0];
        String netId = openstackRouterInterface.getNetId();
        String userName = openstackRouterInterface.getUserName();
        String userPwd = openstackRouterInterface.getUserPwd();
        String projectId = openstackRouterInterface.getProjectId().split(":")[0];
        Long isGateway = openstackRouterInterface.getIsGateway();

        // 2.准备CMD
        String cmd = "";
        if (isGateway == 1) {
            // 准备设置网关的CMD
            cmd = String.format("ssh %s@%s -p%s " +
                            "'bash /cmd/openstack-router-set-gateway.sh %s %s %s %s %s'",
                    NeuConfig.getExecUser(),
                    NeuConfig.getExecHost(),
                    NeuConfig.getExecPort(),
                    routerId,
                    netId,
                    userName,
                    userPwd,
                    projectId);

        } else {
            // 准备设置内网的CMD
            cmd = String.format("ssh %s@%s -p%s " +
                            "'bash /cmd/openstack-router-set-interface.sh %s %s %s %s %s'",
                    NeuConfig.getExecUser(),
                    NeuConfig.getExecHost(),
                    NeuConfig.getExecPort(),
                    routerId,
                    netId,
                    userName,
                    userPwd,
                    projectId);
        }

        // 3.发起访问
        String res = commandService.executeCommand(cmd);
        if (!res.startsWith("0")){
            // 执行失败
            return AjaxResult.error(res);
        }

        // 4.同步到数据库
        return toAjax(openstackRouterInterfaceService.insertOpenstackRouterInterface(openstackRouterInterface));
    }

    /**
     * 修改路由接口
     */
    @ApiOperation("修改路由接口")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_router_interface:edit')")
    @Log(title = "路由接口", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OpenstackRouterInterface openstackRouterInterface) {
        return toAjax(openstackRouterInterfaceService.updateOpenstackRouterInterface(openstackRouterInterface));
    }

    /**
     * 删除路由接口
     */
    @ApiOperation("删除路由接口")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_router_interface:remove')")
    @Log(title = "路由接口", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(openstackRouterInterfaceService.deleteOpenstackRouterInterfaceByIds(ids));
    }
}
