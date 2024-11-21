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
import com.neu.system.domain.OpenstackImageInfo;
import com.neu.system.service.IOpenstackImageInfoService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

import javax.annotation.Resource;

/**
 * 镜像信息Controller
 *
 * @author neuedu
 * @date 2024-11-12
 */
@Api(tags = {"镜像信息"})
@RestController
@RequestMapping("/system/openstack_image_info")
public class OpenstackImageInfoController extends BaseController {
    @Autowired
    private IOpenstackImageInfoService openstackImageInfoService;

    @Resource
    private CommandService commandService;

    /**
     * 查询镜像信息列表
     */
    @GetMapping("/list")
    @ApiOperation("查询镜像信息列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = OpenstackImageInfo.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(OpenstackImageInfo openstackImageInfo) {
        startPage();
        List<OpenstackImageInfo> list = openstackImageInfoService.selectOpenstackImageInfoList(openstackImageInfo);
        return getDataTable(list);
    }

    /**
     * 导出镜像信息列表
     */
    @ApiOperation("导出镜像信息列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_image_info:export')")
    @Log(title = "镜像信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(OpenstackImageInfo openstackImageInfo) {
        List<OpenstackImageInfo> list = openstackImageInfoService.selectOpenstackImageInfoList(openstackImageInfo);
        ExcelUtil<OpenstackImageInfo> util = new ExcelUtil<OpenstackImageInfo>(OpenstackImageInfo.class);
        return util.exportExcel(list, "openstack_image_info");
    }

    /**
     * 获取镜像信息详细信息
     */
    @ApiOperation("获取镜像信息详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = OpenstackImageInfo.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{imageId}")
    public AjaxResult getInfo(@PathVariable("imageId") String imageId) {
        return AjaxResult.success(openstackImageInfoService.selectOpenstackImageInfoById(imageId));
    }

    /**
     * 新增镜像信息
     */
    @ApiOperation("新增镜像信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_image_info:add')")
    @Log(title = "镜像信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OpenstackImageInfo openstackImageInfo) {
        // 1.准备参数
        String projectId = openstackImageInfo.getProjectId(); //租户ID
        String imageName = openstackImageInfo.getImageName(); //镜像名称
        String diskFormat = openstackImageInfo.getDiskFormat(); //磁盘格式
        String containerFormat = openstackImageInfo.getContainerFormat(); // 容器格式
        Long minRam = openstackImageInfo.getMinRam(); // 最小内存 设置默认为0 无限制
        Long minDisk = openstackImageInfo.getMinDisk(); //最小磁盘 设置默认为0 无限制
        Long isPrivate = openstackImageInfo.getIsPrivate(); //是否私有 0 公共 1 私有 默认 0
        Long isProtected = openstackImageInfo.getIsProtected(); //是否受保护 0 不受保护 1 受保护 默认0
        String imagePath = openstackImageInfo.getImagePath(); //镜像路径 ，这里需要先通过上传接口上传镜像
        // 将imagePath改为绝对路径
        imagePath = imagePath.replace("/profile", NeuConfig.getProfile());

        // 取出镜像扩展名
        String[] tmp = imagePath.split("\\.");
        String imagePrefix = tmp[tmp.length - 1];
        String openStackImagePath = "/root/"+System.currentTimeMillis()+"."+imagePrefix;
        // 执行上传
        String cmdScp = String.format("scp -P%s %s %s@%s:%s &> /dev/null",
                NeuConfig.getExecPort(),
                imagePath,
                NeuConfig.getExecUser(),
                NeuConfig.getExecHost(),
                openStackImagePath);
        commandService.executeCommand(cmdScp);

        // 2.构建CMD
        String cmd = String.format("ssh %s@%s -p%s " +
                        "'bash /cmd/openstack-image-create.sh %s %s %s %s %s %s %s %s %s'",
                NeuConfig.getExecUser(),
                NeuConfig.getExecHost(),
                NeuConfig.getExecPort(),
                projectId,
                imageName,
                diskFormat,
                containerFormat,
                minRam,
                minDisk,
                isPrivate,
                isProtected,
                openStackImagePath
        );
        // 3.执行创建
        String res = commandService.executeCommand(cmd);
        if (!res.startsWith("0")){
            //脚本结果不以0开头即失败
            return AjaxResult.error(res);
        }
        // 4.更新数据库
        // 获取生成的镜像ID
        String imageId = res.split(":")[2].replace("\n","");
        openstackImageInfo.setImageId(imageId);
        return toAjax(openstackImageInfoService.insertOpenstackImageInfo(openstackImageInfo));
    }

    /**
     * 修改镜像信息
     */
    @ApiOperation("修改镜像信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_image_info:edit')")
    @Log(title = "镜像信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OpenstackImageInfo openstackImageInfo) {
        return toAjax(openstackImageInfoService.updateOpenstackImageInfo(openstackImageInfo));
    }

    /**
     * 删除镜像信息
     */
    @ApiOperation("删除镜像信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('system:openstack_image_info:remove')")
    @Log(title = "镜像信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{imageIds}")
    public AjaxResult remove(@PathVariable String[] imageIds) {
        for (String imageId : imageIds){
            String cmd = String.format("ssh %s@%s -p%s " +
                            "'bash /cmd/openstack-image-delete.sh %s'",
                    NeuConfig.getExecUser(),
                    NeuConfig.getExecHost(),
                    NeuConfig.getExecPort(),
                    imageId);
            String res = commandService.executeCommand(cmd);
            if (res.startsWith("1") || res.startsWith("2")){
                return AjaxResult.error(res);
            }
            openstackImageInfoService.deleteOpenstackImageInfoById(imageId);
        }
        return AjaxResult.success();
    }
}
