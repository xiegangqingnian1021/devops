package com.neu.carbon.bid.controller;

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
import com.neu.carbon.bid.domain.BidPlatformInformation;
import com.neu.carbon.bid.service.IBidPlatformInformationService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 平台动态Controller
 * 
 * @author neuedu
 * @date 2023-04-21
 */
@Api(tags = {"平台动态"})
@RestController
@RequestMapping("/bid/information")
public class BidPlatformInformationController extends BaseController
{
    @Autowired
    private IBidPlatformInformationService bidPlatformInformationService;

    /**
     * 查询平台动态列表
     */
    @GetMapping("/list")
    @ApiOperation("查询平台动态列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = BidPlatformInformation.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(BidPlatformInformation bidPlatformInformation)
    {
        startPage();
        List<BidPlatformInformation> list = bidPlatformInformationService.selectBidPlatformInformationList(bidPlatformInformation);
        return getDataTable(list);
    }

    @GetMapping("/front/list")
    @ApiOperation("查询平台动态列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = BidPlatformInformation.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo frontList(BidPlatformInformation bidPlatformInformation)
    {
        startPage();
        List<BidPlatformInformation> list = bidPlatformInformationService.selectBidPlatformInformationList(bidPlatformInformation);
        return getDataTable(list);
    }

    /**
     * 导出平台动态列表
     */
    @ApiOperation("导出平台动态列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('bid:information:export')")
    @Log(title = "平台动态", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BidPlatformInformation bidPlatformInformation)
    {
        List<BidPlatformInformation> list = bidPlatformInformationService.selectBidPlatformInformationList(bidPlatformInformation);
        ExcelUtil<BidPlatformInformation> util = new ExcelUtil<BidPlatformInformation>(BidPlatformInformation.class);
        return util.exportExcel(list, "information");
    }

    /**
     * 获取平台动态详细信息
     */
    @ApiOperation("获取平台动态详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = BidPlatformInformation.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bidPlatformInformationService.selectBidPlatformInformationById(id));
    }

    /**
     * 获取平台动态详细信息
     */
    @ApiOperation("获取平台动态详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = BidPlatformInformation.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/front/{id}")
    public AjaxResult frontGetInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bidPlatformInformationService.selectBidPlatformInformationById(id));
    }

    /**
     * 新增平台动态
     */
    @ApiOperation("新增平台动态")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:information:add')")
    @Log(title = "平台动态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BidPlatformInformation bidPlatformInformation)
    {
        return toAjax(bidPlatformInformationService.insertBidPlatformInformation(bidPlatformInformation));
    }

    /**
     * 修改平台动态
     */
    @ApiOperation("修改平台动态")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:information:edit')")
    @Log(title = "平台动态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BidPlatformInformation bidPlatformInformation)
    {
        return toAjax(bidPlatformInformationService.updateBidPlatformInformation(bidPlatformInformation));
    }

    /**
     * 删除平台动态
     */
    @ApiOperation("删除平台动态")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:information:remove')")
    @Log(title = "平台动态", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bidPlatformInformationService.deleteBidPlatformInformationByIds(ids));
    }
}
