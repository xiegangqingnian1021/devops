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
import com.neu.carbon.bid.domain.BidComplaint;
import com.neu.carbon.bid.service.IBidComplaintService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 违规投诉Controller
 * 
 * @author neuedu
 * @date 2023-04-21
 */
@Api(tags = {"违规投诉"})
@RestController
@RequestMapping("/bid/complaint")
public class BidComplaintController extends BaseController
{
    @Autowired
    private IBidComplaintService bidComplaintService;

    /**
     * 查询违规投诉列表
     */
    @GetMapping("/list")
    @ApiOperation("查询违规投诉列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = BidComplaint.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(BidComplaint bidComplaint)
    {
        startPage();
        List<BidComplaint> list = bidComplaintService.selectBidComplaintList(bidComplaint);
        return getDataTable(list);
    }

    @GetMapping("/front/list")
    @ApiOperation("查询违规投诉列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = BidComplaint.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo frontList(BidComplaint bidComplaint)
    {
        startPage();
        List<BidComplaint> list = bidComplaintService.selectBidComplaintList(bidComplaint);
        return getDataTable(list);
    }

    /**
     * 导出违规投诉列表
     */
    @ApiOperation("导出违规投诉列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('bid:complaint:export')")
    @Log(title = "违规投诉", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BidComplaint bidComplaint)
    {
        List<BidComplaint> list = bidComplaintService.selectBidComplaintList(bidComplaint);
        ExcelUtil<BidComplaint> util = new ExcelUtil<BidComplaint>(BidComplaint.class);
        return util.exportExcel(list, "complaint");
    }

    /**
     * 获取违规投诉详细信息
     */
    @ApiOperation("获取违规投诉详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = BidComplaint.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bidComplaintService.selectBidComplaintById(id));
    }

    @ApiOperation("获取违规投诉详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = BidComplaint.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "front/{id}")
    public AjaxResult frontGetInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bidComplaintService.selectBidComplaintById(id));
    }
    /**
     * 新增违规投诉
     */
    @ApiOperation("新增违规投诉")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:complaint:add')")
    @Log(title = "违规投诉", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BidComplaint bidComplaint)
    {
        return toAjax(bidComplaintService.insertBidComplaint(bidComplaint));
    }


    @PostMapping("/front/add")
    public AjaxResult frontAdd(@RequestBody BidComplaint bidComplaint)
    {
        return toAjax(bidComplaintService.insertBidComplaint(bidComplaint));
    }

    /**
     * 修改违规投诉
     */
    @ApiOperation("修改违规投诉")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:complaint:edit')")
    @Log(title = "违规投诉", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BidComplaint bidComplaint)
    {
        return toAjax(bidComplaintService.updateBidComplaint(bidComplaint));
    }

    /**
     * 删除违规投诉
     */
    @ApiOperation("删除违规投诉")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:complaint:remove')")
    @Log(title = "违规投诉", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bidComplaintService.deleteBidComplaintByIds(ids));
    }
}
