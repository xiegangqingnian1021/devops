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
import com.neu.system.domain.OpenstackSubnetInfo;
import com.neu.system.service.IOpenstackSubnetInfoService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

import javax.annotation.Resource;

/**
 * 子网管理Controller
 * 
 * @author neuedu
 * @date 2024-12-05
 */
@Api(tags = {"子网管理"})
@RestController
@RequestMapping("/system/openstack_subnet_info")
public class OpenstackSubnetInfoController extends BaseController
{
    @Autowired
    private IOpenstackSubnetInfoService openstackSubnetInfoService;

    @Resource
    private CommandService commandService;

    /**
     * 查询子网管理列表
     */
    @GetMapping("/list")
    @ApiOperation("查询子网管理列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = OpenstackSubnetInfo.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(OpenstackSubnetInfo openstackSubnetInfo)
    {
        startPage();
        List<OpenstackSubnetInfo> list = openstackSubnetInfoService.selectOpenstackSubnetInfoList(openstackSubnetInfo);
        return getDataTable(list);
    }

    /**
     * 导出子网管理列表
     */
    @ApiOperation("导出子网管理列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_subnet_info:export')")
    @Log(title = "子网管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(OpenstackSubnetInfo openstackSubnetInfo)
    {
        List<OpenstackSubnetInfo> list = openstackSubnetInfoService.selectOpenstackSubnetInfoList(openstackSubnetInfo);
        ExcelUtil<OpenstackSubnetInfo> util = new ExcelUtil<OpenstackSubnetInfo>(OpenstackSubnetInfo.class);
        return util.exportExcel(list, "openstack_subnet_info");
    }

    /**
     * 获取子网管理详细信息
     */
    @ApiOperation("获取子网管理详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = OpenstackSubnetInfo.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{subnetId}")
    public AjaxResult getInfo(@PathVariable("subnetId") String subnetId)
    {
        return AjaxResult.success(openstackSubnetInfoService.selectOpenstackSubnetInfoById(subnetId));
    }

    /**
     * 新增子网管理
     */
    @ApiOperation("新增子网管理")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_subnet_info:add')")
    @Log(title = "子网管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OpenstackSubnetInfo openstackSubnetInfo)
    {
        //1.准备参数
        String networkId = openstackSubnetInfo.getNetworkId();
        String subnetName = openstackSubnetInfo.getSubnetName();
        String projectId = openstackSubnetInfo.getProjectId();
        String subnetRange = openstackSubnetInfo.getSubnetRange();
        String gateway= openstackSubnetInfo.getGateway();

        //2.准备创建子网需要的CMD
        String cmd = String.format("ssh %s@%s -p%s " +
                        "'bash /cmd/openstack-subnet-create.sh %s %s %s %s %s'",
                NeuConfig.getExecUser(),
                NeuConfig.getExecHost(),
                NeuConfig.getExecPort(),
                networkId,
                subnetName,
                projectId,
                subnetRange,
                gateway);

        //3.发起创建子网的请求
        String res = commandService.executeCommand(cmd);
        if (!res.startsWith("0")){
            return AjaxResult.error("子网创建失败",res);
        }

        //4.解析获取的子网ID，将子网数据同步到数据库
        String subnetId = res.split(":")[2].replace("\n", "");
        openstackSubnetInfo.setSubnetId(subnetId);
        openstackSubnetInfoService.insertOpenstackSubnetInfo(openstackSubnetInfo);
        return AjaxResult.success();
    }

    /**
     * 修改子网管理
     */
    @ApiOperation("修改子网管理")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_subnet_info:edit')")
    @Log(title = "子网管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OpenstackSubnetInfo openstackSubnetInfo)
    {
        return toAjax(openstackSubnetInfoService.updateOpenstackSubnetInfo(openstackSubnetInfo));
    }

    /**
     * 删除子网管理
     */
    @ApiOperation("删除子网管理")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_subnet_info:remove')")
    @Log(title = "子网管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{subnetIds}")
    public AjaxResult remove(@PathVariable String[] subnetIds)
    {
        return toAjax(openstackSubnetInfoService.deleteOpenstackSubnetInfoByIds(subnetIds));
    }
}
