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
import com.neu.system.domain.OpenstackUserInfo;
import com.neu.system.service.IOpenstackUserInfoService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

import javax.annotation.Resource;

/**
 * 用户信息Controller
 * 
 * @author neuedu
 * @date 2024-09-25
 */
@Api(tags = {"用户信息"})
@RestController
@RequestMapping("/system/openstack_user_info")
public class OpenstackUserInfoController extends BaseController
{
    @Autowired
    private IOpenstackUserInfoService openstackUserInfoService;

    @Resource
    private CommandService commandService;

    /**
     * 查询用户信息列表
     */
    @GetMapping("/list")
    @ApiOperation("查询用户信息列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = OpenstackUserInfo.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(OpenstackUserInfo openstackUserInfo)
    {
        startPage();
        List<OpenstackUserInfo> list = openstackUserInfoService.selectOpenstackUserInfoList(openstackUserInfo);
        return getDataTable(list);
    }

    /**
     * 导出用户信息列表
     */
    @ApiOperation("导出用户信息列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_user_info:export')")
    @Log(title = "用户信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(OpenstackUserInfo openstackUserInfo)
    {
        List<OpenstackUserInfo> list = openstackUserInfoService.selectOpenstackUserInfoList(openstackUserInfo);
        ExcelUtil<OpenstackUserInfo> util = new ExcelUtil<OpenstackUserInfo>(OpenstackUserInfo.class);
        return util.exportExcel(list, "openstack_user_info");
    }

    /**
     * 获取用户信息详细信息
     */
    @ApiOperation("获取用户信息详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = OpenstackUserInfo.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") String userId)
    {
        return AjaxResult.success(openstackUserInfoService.selectOpenstackUserInfoById(userId));
    }

    /**
     * 新增用户信息
     */
    @ApiOperation("新增用户信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_user_info:add')")
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OpenstackUserInfo openstackUserInfo)
    {
        //1.准备创建用户所需要的参数
        String userName = openstackUserInfo.getUserName();
        String userPwd = openstackUserInfo.getUserPwd();
        String userEmail = openstackUserInfo.getUserEmail();
        //2.准备Linux命令
        String cmd = String.format("ssh %s@%s -p%s " +
                "'bash /cmd/openstack-user-create.sh %s %s %s'",
                NeuConfig.getExecUser(),
                NeuConfig.getExecHost(),
                NeuConfig.getExecPort(),
                userName,
                userPwd,
                userEmail
                );
        //3.向Linux发起访问获取响应结果
        String res = commandService.executeCommand(cmd);
        //4.解析结果提取用户ID
        if (!res.startsWith("0")){
            //创建用户失败
            return AjaxResult.error(res.split(":")[1]);
        }
        String userId = res.split(":")[2];
        openstackUserInfo.setUserId(userId);
        return toAjax(openstackUserInfoService.insertOpenstackUserInfo(openstackUserInfo));
    }

    /**
     * 修改用户信息
     */
    @ApiOperation("修改用户信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_user_info:edit')")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OpenstackUserInfo openstackUserInfo)
    {
        return toAjax(openstackUserInfoService.updateOpenstackUserInfo(openstackUserInfo));
    }

    /**
     * 删除用户信息
     */
    @ApiOperation("删除用户信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_user_info:remove')")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable String[] userIds)
    {
        return toAjax(openstackUserInfoService.deleteOpenstackUserInfoByIds(userIds));
    }
}
