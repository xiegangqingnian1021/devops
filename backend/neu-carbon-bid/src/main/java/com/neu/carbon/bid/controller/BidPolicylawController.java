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
import com.neu.carbon.bid.domain.BidPolicylaw;
import com.neu.carbon.bid.service.IBidPolicylawService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 政策法规Controller
 * 
 * @author neuedu
 * @date 2023-04-21
 */
@Api(tags = {"政策法规"})
@RestController
@RequestMapping("/bid/policylaw")
public class BidPolicylawController extends BaseController
{
    @Autowired
    private IBidPolicylawService bidPolicylawService;

    /**
     * 查询政策法规列表
     */
    @GetMapping("/list")
    @ApiOperation("查询政策法规列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = BidPolicylaw.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(BidPolicylaw bidPolicylaw)
    {
        startPage();
        List<BidPolicylaw> list = bidPolicylawService.selectBidPolicylawList(bidPolicylaw);
        return getDataTable(list);
    }

    @GetMapping("/front/list")
    @ApiOperation("查询政策法规列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = BidPolicylaw.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo frontList(BidPolicylaw bidPolicylaw)
    {
        startPage();
        List<BidPolicylaw> list = bidPolicylawService.selectBidPolicylawList(bidPolicylaw);
        return getDataTable(list);
    }

    /**
     * 导出政策法规列表
     */
    @ApiOperation("导出政策法规列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('bid:policylaw:export')")
    @Log(title = "政策法规", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BidPolicylaw bidPolicylaw)
    {
        List<BidPolicylaw> list = bidPolicylawService.selectBidPolicylawList(bidPolicylaw);
        ExcelUtil<BidPolicylaw> util = new ExcelUtil<BidPolicylaw>(BidPolicylaw.class);
        return util.exportExcel(list, "policylaw");
    }

    /**
     * 获取政策法规详细信息
     */
    @ApiOperation("获取政策法规详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = BidPolicylaw.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bidPolicylawService.selectBidPolicylawById(id));
    }

    @ApiOperation("获取政策法规详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = BidPolicylaw.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/front/{id}")
    public AjaxResult frontGetInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bidPolicylawService.selectBidPolicylawById(id));
    }
    /**
     * 新增政策法规
     */
    @ApiOperation("新增政策法规")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:policylaw:add')")
    @Log(title = "政策法规", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BidPolicylaw bidPolicylaw)
    {
        return toAjax(bidPolicylawService.insertBidPolicylaw(bidPolicylaw));
    }

    /**
     * 修改政策法规
     */
    @ApiOperation("修改政策法规")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:policylaw:edit')")
    @Log(title = "政策法规", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BidPolicylaw bidPolicylaw)
    {
        return toAjax(bidPolicylawService.updateBidPolicylaw(bidPolicylaw));
    }

    /**
     * 删除政策法规
     */
    @ApiOperation("删除政策法规")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:policylaw:remove')")
    @Log(title = "政策法规", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bidPolicylawService.deleteBidPolicylawByIds(ids));
    }
}
