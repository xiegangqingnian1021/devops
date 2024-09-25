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
import com.neu.carbon.bid.domain.BidEnterpriseBidding;
import com.neu.carbon.bid.service.IBidEnterpriseBiddingService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 企业投标Controller
 * 
 * @author neuedu
 * @date 2023-04-21
 */
@Api(tags = {"企业投标"})
@RestController
@RequestMapping("/bid/enterprise/bidding")
public class BidEnterpriseBiddingController extends BaseController
{
    @Autowired
    private IBidEnterpriseBiddingService bidEnterpriseBiddingService;

    /**
     * 查询企业投标列表
     */
    @GetMapping("/list")
    @ApiOperation("查询企业投标列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = BidEnterpriseBidding.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(BidEnterpriseBidding bidEnterpriseBidding)
    {
        startPage();
        List<BidEnterpriseBidding> list = bidEnterpriseBiddingService.selectBidEnterpriseBiddingList(bidEnterpriseBidding);
        return getDataTable(list);
    }

    @GetMapping("/front/list")
    public TableDataInfo frontList(BidEnterpriseBidding bidEnterpriseBidding)
    {
        startPage();
        List<BidEnterpriseBidding> list = bidEnterpriseBiddingService.selectBidEnterpriseBiddingList(bidEnterpriseBidding);
        return getDataTable(list);
    }

    /**
     * 导出企业投标列表
     */
    @ApiOperation("导出企业投标列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('bid:enterprisebidding:export')")
    @Log(title = "企业投标", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BidEnterpriseBidding bidEnterpriseBidding)
    {
        List<BidEnterpriseBidding> list = bidEnterpriseBiddingService.selectBidEnterpriseBiddingList(bidEnterpriseBidding);
        ExcelUtil<BidEnterpriseBidding> util = new ExcelUtil<BidEnterpriseBidding>(BidEnterpriseBidding.class);
        return util.exportExcel(list, "bidding");
    }

    /**
     * 获取企业投标详细信息
     */
    @ApiOperation("获取企业投标详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = BidEnterpriseBidding.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bidEnterpriseBiddingService.selectBidEnterpriseBiddingById(id));
    }


    @GetMapping(value = "/front/{id}")
    public AjaxResult frontGetInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bidEnterpriseBiddingService.selectBidEnterpriseBiddingById(id));
    }

    /**
     * 新增企业投标
     */
    @ApiOperation("新增企业投标")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:enterprisebidding:add')")
    @Log(title = "企业投标", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BidEnterpriseBidding bidEnterpriseBidding)
    {
        return toAjax(bidEnterpriseBiddingService.insertBidEnterpriseBidding(bidEnterpriseBidding));
    }

    @ApiOperation("新增企业投标")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PostMapping("/front/add")
    public AjaxResult frontAdd(@RequestBody BidEnterpriseBidding bidEnterpriseBidding)
    {
        return toAjax(bidEnterpriseBiddingService.insertBidEnterpriseBidding(bidEnterpriseBidding));
    }

    /**
     * 修改企业投标
     */
    @ApiOperation("修改企业投标")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:enterprisebidding:edit')")
    @Log(title = "企业投标", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BidEnterpriseBidding bidEnterpriseBidding)
    {
        return toAjax(bidEnterpriseBiddingService.updateBidEnterpriseBidding(bidEnterpriseBidding));
    }


    @PostMapping("/front/edit")
    public AjaxResult frontEdit(@RequestBody BidEnterpriseBidding bidEnterpriseBidding)
    {
        return toAjax(bidEnterpriseBiddingService.updateBidEnterpriseBidding(bidEnterpriseBidding));
    }

    /**
     * 删除企业投标
     */
    @ApiOperation("删除企业投标")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:enterprisebidding:remove')")
    @Log(title = "企业投标", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bidEnterpriseBiddingService.deleteBidEnterpriseBiddingByIds(ids));
    }
}
