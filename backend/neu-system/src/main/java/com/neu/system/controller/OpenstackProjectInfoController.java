package com.neu.system.controller;

import java.util.ArrayList;
import java.util.List;

import com.neu.common.config.NeuConfig;
import com.neu.system.domain.OpenstackProjectInfo;
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
import com.neu.system.service.IOpenstackProjectInfoService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

import javax.annotation.Resource;

/**
 * 租户信息Controller
 * 
 * @author neuedu
 * @date 2024-09-23
 */
@Api(tags = {"租户信息"})
@RestController
@RequestMapping("/system/openstack_project_info")
public class OpenstackProjectInfoController extends BaseController
{
    @Autowired
    private IOpenstackProjectInfoService projectInfoService;

    @Resource
    private CommandService commandService;

    /**
     * 查询租户信息列表
     */
    @GetMapping("/list")
    @ApiOperation("查询租户信息列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = OpenstackProjectInfo.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(OpenstackProjectInfo openstackProjectInfo)
    {
        startPage();
        List<OpenstackProjectInfo> list = projectInfoService.selectProjectInfoList(openstackProjectInfo);
        return getDataTable(list);
    }

    /**
     * 导出租户信息列表
     */
    @ApiOperation("导出租户信息列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('system:project_info:export')")
    @Log(title = "租户信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(OpenstackProjectInfo openstackProjectInfo)
    {
        List<OpenstackProjectInfo> list = projectInfoService.selectProjectInfoList(openstackProjectInfo);
        ExcelUtil<OpenstackProjectInfo> util = new ExcelUtil<OpenstackProjectInfo>(OpenstackProjectInfo.class);
        return util.exportExcel(list, "project_info");
    }

    /**
     * 获取租户信息详细信息
     */
    @ApiOperation("获取租户信息详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = OpenstackProjectInfo.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") String projectId)
    {
        return AjaxResult.success(projectInfoService.selectProjectInfoById(projectId));
    }

    /**
     * 新增租户信息
     */
    @ApiOperation("新增租户信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:project_info:add')")
    @Log(title = "租户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OpenstackProjectInfo openstackProjectInfo)
    {
        //访问openstack，获取生成的租户ID，连同其他信息，填入数据库
        String cmd = String.format("ssh %s@%s -p%s " +
                        "'bash /cmd/openstack-project-create.sh %s %s'",
                NeuConfig.getExecUser(),
                NeuConfig.getExecHost(),
                NeuConfig.getExecPort(),
                openstackProjectInfo.getProjectName(),
                openstackProjectInfo.getProjectDescription());

        String res = commandService.executeCommand(cmd);
        if (!res.startsWith("0")){
            return AjaxResult.error(res.split(":")[1]);
        }
        //将OpenStack返回的租户ID填入参数对象，提交给数据库存储
        openstackProjectInfo.setProjectId(res.split(":")[2].replace("\n",""));
        return toAjax(projectInfoService.insertProjectInfo(openstackProjectInfo));
    }

    /**
     * 修改租户信息
     */
    @ApiOperation("修改租户信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:project_info:edit')")
    @Log(title = "租户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OpenstackProjectInfo openstackProjectInfo)
    {
        return toAjax(projectInfoService.updateProjectInfo(openstackProjectInfo));
    }

    /**
     * 删除租户信息
     */
    @ApiOperation("删除租户信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:project_info:remove')")
    @Log(title = "租户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable String[] projectIds)
    {
        //创建容器，保存删除出错记录
        List<String> resList = new ArrayList<>();
        for (String projectId : projectIds){
            //从OpenStack删除租户信息
            String cmd = String.format("ssh %s@%s -p%s " +
                            "'bash /cmd/openstack-project-delete.sh %s'",
                    NeuConfig.getExecUser(),
                    NeuConfig.getExecHost(),
                    NeuConfig.getExecPort(),
                    projectId);
            String res = commandService.executeCommand(cmd);
            if (!res.startsWith("0")){
                //删除错误记录，返回给前端
                resList.add("租户"+projectId+":"+res.split(":")[1]);
                continue;
            }
            //删除后同步数据库删除对应的租户记录
            projectInfoService.deleteProjectInfoById(projectId);
        }
        // resList元素为空即为批量删除租户成功
        return AjaxResult.success(resList);
    }
}
