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
import com.neu.system.domain.OpenstackProjectUser;
import com.neu.system.service.IOpenstackProjectUserService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

import javax.annotation.Resource;

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

    @Resource
    private CommandService commandService;

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
        // 移除关联关系
        // 1. 从数据库中取出当前关联的用户ID
        OpenstackProjectUser projectUser = new OpenstackProjectUser();
        projectUser.setProjectId(openstackProjectUser.getProjectId());
        List<OpenstackProjectUser> selectedProjectUserList =  openstackProjectUserService.selectOpenstackProjectUserList(projectUser);

        //2. 遍历这些列表，参照userIds检查，哪些用户ID是要解除关联的
        for (OpenstackProjectUser item : selectedProjectUserList){
            //检查用户ID是否不存在已选的用户列表
            if (!openstackProjectUser.getUserIds().contains(item.getUserId())){
                // 当前用户已经不在已选用户的列表中，解除关联关系，更新数据库
                String cmd = String.format("ssh %s@%s -p%s " +
                                "'bash /cmd/openstack-project-user-disassociate.sh %s %s %s'",
                        NeuConfig.getExecUser(),
                        NeuConfig.getExecHost(),
                        NeuConfig.getExecPort(),
                        openstackProjectUser.getProjectId(),
                        item.getUserId(),
                        item.getRoleName());
                String res = commandService.executeCommand(cmd);
                if (!res.startsWith("0")){
                    return AjaxResult.error(res);
                }
                //从数据库解除关联
                openstackProjectUserService.deleteOpenstackProjectUserById(item.getId());
            }
        }

        //添加关联
        //1.准备参数
        String projectId = openstackProjectUser.getProjectId();
        List<String> userIds = openstackProjectUser.getUserIds();
        String roleName = openstackProjectUser.getRoleName();

        for (String userId : userIds){
            // 检查用户是否已经与租户进行绑定
            OpenstackProjectUser tmp = new OpenstackProjectUser();
            tmp.setUserId(userId);
            tmp.setProjectId(projectId);
            List<OpenstackProjectUser> selectedData = openstackProjectUserService.selectOpenstackProjectUserList(tmp);
            if (selectedData.size() > 0){
                // 用户与租户已经绑定了
                // 不再做关联处理
                continue;
            }
            //2.构建执行角色绑定的命令
            String cmd = String.format("ssh %s@%s -p%s " +
                            "'bash /cmd/openstack-project-user-associate.sh %s %s %s'",
                    NeuConfig.getExecUser(),
                    NeuConfig.getExecHost(),
                    NeuConfig.getExecPort(),
                    projectId,
                    userId,
                    roleName);

            //3.执行cmd,在OpenStack中租户用户的关联关系
            String res = commandService.executeCommand(cmd);

            //4.判断res的结果
            if (!res.startsWith("0")){
                return AjaxResult.error(res);
            }

            //5.租户与用户关联成功，将数据关系添加到MySQL数据库
            openstackProjectUser.setId(System.currentTimeMillis()+"");
            openstackProjectUser.setUserId(userId);
            openstackProjectUserService.insertOpenstackProjectUser(openstackProjectUser);
        }
        return AjaxResult.success();
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
