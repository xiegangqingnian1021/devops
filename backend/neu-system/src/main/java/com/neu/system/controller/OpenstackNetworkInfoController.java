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
import com.neu.system.domain.OpenstackNetworkInfo;
import com.neu.system.service.IOpenstackNetworkInfoService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

import javax.annotation.Resource;

/**
 * 网络管理Controller
 * 
 * @author neuedu
 * @date 2024-12-02
 */
@Api(tags = {"网络管理"})
@RestController
@RequestMapping("/system/network_info")
public class OpenstackNetworkInfoController extends BaseController
{
    @Autowired
    private IOpenstackNetworkInfoService openstackNetworkInfoService;

    @Resource
    private CommandService commandService;

    /**
     * 查询网络管理列表
     */
    @GetMapping("/list")
    @ApiOperation("查询网络管理列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = OpenstackNetworkInfo.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(OpenstackNetworkInfo openstackNetworkInfo)
    {
        startPage();
        List<OpenstackNetworkInfo> list = openstackNetworkInfoService.selectOpenstackNetworkInfoList(openstackNetworkInfo);
        return getDataTable(list);
    }

    /**
     * 导出网络管理列表
     */
    @ApiOperation("导出网络管理列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('system:network_info:export')")
    @Log(title = "网络管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(OpenstackNetworkInfo openstackNetworkInfo)
    {
        List<OpenstackNetworkInfo> list = openstackNetworkInfoService.selectOpenstackNetworkInfoList(openstackNetworkInfo);
        ExcelUtil<OpenstackNetworkInfo> util = new ExcelUtil<OpenstackNetworkInfo>(OpenstackNetworkInfo.class);
        return util.exportExcel(list, "network_info");
    }

    /**
     * 获取网络管理详细信息
     */
    @ApiOperation("获取网络管理详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = OpenstackNetworkInfo.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{networkId}")
    public AjaxResult getInfo(@PathVariable("networkId") String networkId)
    {
        return AjaxResult.success(openstackNetworkInfoService.selectOpenstackNetworkInfoById(networkId));
    }

    /**
     * 新增网络管理
     */
    @ApiOperation("新增网络管理")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:network_info:add')")
    @Log(title = "网络管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OpenstackNetworkInfo openstackNetworkInfo)
    {
        //1.准备参数
        String networkName = openstackNetworkInfo.getNetworkName();
        String projectId = openstackNetworkInfo.getProjectId();
        Long isInternal = openstackNetworkInfo.getIsInternal();
        //2.访问脚本
        String cmd = String.format("ssh %s@%s -p%s " +
                        "'bash /cmd/openstack-network-create.sh %s %s %s'",
                NeuConfig.getExecUser(),
                NeuConfig.getExecHost(),
                NeuConfig.getExecPort(),
                networkName,
                projectId,
                isInternal);
        String res = commandService.executeCommand(cmd);
        //3.获取网络ID和其他信息更新数据库表
        /* -- 判断 应答结果 -- */
        if (!res.startsWith("0")){
            return AjaxResult.error("创建错误", res);
        }
        /* -- 获取生成的网络ID -- */
        String networkId = res.split(":")[2].replace("\n","");
        openstackNetworkInfo.setNetworkId(networkId);
        /* -- 将生成的网络结果更新到数据库 -- */
        return toAjax(openstackNetworkInfoService.insertOpenstackNetworkInfo(openstackNetworkInfo));
    }

    /**
     * 修改网络管理
     */
    @ApiOperation("修改网络管理")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:network_info:edit')")
    @Log(title = "网络管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OpenstackNetworkInfo openstackNetworkInfo)
    {
        return toAjax(openstackNetworkInfoService.updateOpenstackNetworkInfo(openstackNetworkInfo));
    }

    /**
     * 删除网络管理
     */
    @ApiOperation("删除网络管理")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:network_info:remove')")
    @Log(title = "网络管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{networkIds}")
    public AjaxResult remove(@PathVariable String[] networkIds)
    {
        return toAjax(openstackNetworkInfoService.deleteOpenstackNetworkInfoByIds(networkIds));
    }
}
