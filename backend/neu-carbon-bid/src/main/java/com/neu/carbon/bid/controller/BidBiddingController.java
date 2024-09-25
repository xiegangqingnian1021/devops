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
import com.neu.carbon.bid.domain.BidBidding;
import com.neu.carbon.bid.service.IBidBiddingService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 招投标Controller
 * 
 * @author neuedu
 * @date 2023-04-21
 */
@Api(tags = {"招投标"})
@RestController
@RequestMapping("/bid/bidding")
public class BidBiddingController extends BaseController
{
    @Autowired
    private IBidBiddingService bidBiddingService;

    /**
     * 查询招投标列表
     */
    @GetMapping("/list")
    @ApiOperation("查询招投标列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = BidBidding.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(BidBidding bidBidding)
    {
        startPage();
        List<BidBidding> list = bidBiddingService.selectBidBiddingList(bidBidding);
        return getDataTable(list);
    }

    @GetMapping("/front/list")
    @ApiOperation("前端查询招投标列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = BidBidding.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo frontList(BidBidding bidBidding)
    {
        startPage();
        List<BidBidding> list = bidBiddingService.selectBidBiddingList(bidBidding);
        return getDataTable(list);
    }

    /**
     * 导出招投标列表
     */
    @ApiOperation("导出招投标列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('bid:bidding:export')")
    @Log(title = "招投标", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BidBidding bidBidding)
    {
        List<BidBidding> list = bidBiddingService.selectBidBiddingList(bidBidding);
        ExcelUtil<BidBidding> util = new ExcelUtil<BidBidding>(BidBidding.class);
        return util.exportExcel(list, "bidding");
    }

    /**
     * 获取招投标详细信息
     */
    @ApiOperation("获取招投标详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = BidBidding.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bidBiddingService.selectBidBiddingById(id));
    }

    @ApiOperation("获取招投标详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = BidBidding.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/front/{id}")
    public AjaxResult frontGetInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bidBiddingService.selectBidBiddingById(id));
    }

    /**
     * 新增招投标
     */
    @ApiOperation("新增招投标")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:bidding:add')")
    @Log(title = "招投标", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BidBidding bidBidding)
    {
        return toAjax(bidBiddingService.insertBidBidding(bidBidding));
    }

    /**
     * 修改招投标
     */
    @ApiOperation("修改招投标")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:bidding:edit')")
    @Log(title = "招投标", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BidBidding bidBidding)
    {
        return toAjax(bidBiddingService.updateBidBidding(bidBidding));
    }

    /**
     * 删除招投标
     */
    @ApiOperation("删除招投标")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:bidding:remove')")
    @Log(title = "招投标", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bidBiddingService.deleteBidBiddingByIds(ids));
    }
}
