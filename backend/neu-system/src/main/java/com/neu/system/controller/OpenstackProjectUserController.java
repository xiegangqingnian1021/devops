package com.neu.system.controller;

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
import com.neu.system.domain.OpenstackProjectUser;
import com.neu.system.service.IOpenstackProjectUserService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 租户用户Controller
 * 
 * @author neuedu
 * @date 2024-10-31
 */
@Api(tags = {"租户用户"})
@RestController
@RequestMapping("/system/openstack_project_user")
public class OpenstackProjectUserController extends BaseController
{
    @Autowired
    private IOpenstackProjectUserService openstackProjectUserService;

    /**
     * 查询租户用户列表
     */
    @GetMapping("/list")
    @ApiOperation("查询租户用户列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = OpenstackProjectUser.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(OpenstackProjectUser openstackProjectUser)
    {
        startPage();
        List<OpenstackProjectUser> list = openstackProjectUserService.selectOpenstackProjectUserList(openstackProjectUser);
        return getDataTable(list);
    }

    /**
     * 导出租户用户列表
     */
    @ApiOperation("导出租户用户列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_project_user:export')")
    @Log(title = "租户用户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(OpenstackProjectUser openstackProjectUser)
    {
        List<OpenstackProjectUser> list = openstackProjectUserService.selectOpenstackProjectUserList(openstackProjectUser);
        ExcelUtil<OpenstackProjectUser> util = new ExcelUtil<OpenstackProjectUser>(OpenstackProjectUser.class);
        return util.exportExcel(list, "openstack_project_user");
    }

    /**
     * 获取租户用户详细信息
     */
    @ApiOperation("获取租户用户详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = OpenstackProjectUser.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(openstackProjectUserService.selectOpenstackProjectUserById(id));
    }

    /**
     * 新增租户用户
     */
    @ApiOperation("新增租户用户")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_project_user:add')")
    @Log(title = "租户用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OpenstackProjectUser openstackProjectUser)
    {
        return toAjax(openstackProjectUserService.insertOpenstackProjectUser(openstackProjectUser));
    }

    /**
     * 修改租户用户
     */
    @ApiOperation("修改租户用户")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_project_user:edit')")
    @Log(title = "租户用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OpenstackProjectUser openstackProjectUser)
    {
        return toAjax(openstackProjectUserService.updateOpenstackProjectUser(openstackProjectUser));
    }

    /**
     * 删除租户用户
     */
    @ApiOperation("删除租户用户")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_project_user:remove')")
    @Log(title = "租户用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(openstackProjectUserService.deleteOpenstackProjectUserByIds(ids));
    }
}
