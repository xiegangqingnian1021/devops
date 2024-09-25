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
import com.neu.carbon.bid.domain.BidPolicylawFiles;
import com.neu.carbon.bid.service.IBidPolicylawFilesService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 政策法规附件Controller
 * 
 * @author neuedu
 * @date 2023-04-21
 */
@Api(tags = {"政策法规附件"})
@RestController
@RequestMapping("/bid/policylaw/files")
public class BidPolicylawFilesController extends BaseController
{
    @Autowired
    private IBidPolicylawFilesService bidPolicylawFilesService;

    /**
     * 查询政策法规附件列表
     */
    @GetMapping("/list")
    @ApiOperation("查询政策法规附件列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = BidPolicylawFiles.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(BidPolicylawFiles bidPolicylawFiles)
    {
        startPage();
        List<BidPolicylawFiles> list = bidPolicylawFilesService.selectBidPolicylawFilesList(bidPolicylawFiles);
        return getDataTable(list);
    }

    /**
     * 导出政策法规附件列表
     */
    @ApiOperation("导出政策法规附件列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('bid:files:export')")
    @Log(title = "政策法规附件", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BidPolicylawFiles bidPolicylawFiles)
    {
        List<BidPolicylawFiles> list = bidPolicylawFilesService.selectBidPolicylawFilesList(bidPolicylawFiles);
        ExcelUtil<BidPolicylawFiles> util = new ExcelUtil<BidPolicylawFiles>(BidPolicylawFiles.class);
        return util.exportExcel(list, "files");
    }

    /**
     * 获取政策法规附件详细信息
     */
    @ApiOperation("获取政策法规附件详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = BidPolicylawFiles.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bidPolicylawFilesService.selectBidPolicylawFilesById(id));
    }

    /**
     * 新增政策法规附件
     */
    @ApiOperation("新增政策法规附件")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:files:add')")
    @Log(title = "政策法规附件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BidPolicylawFiles bidPolicylawFiles)
    {
        return toAjax(bidPolicylawFilesService.insertBidPolicylawFiles(bidPolicylawFiles));
    }

    /**
     * 修改政策法规附件
     */
    @ApiOperation("修改政策法规附件")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:files:edit')")
    @Log(title = "政策法规附件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BidPolicylawFiles bidPolicylawFiles)
    {
        return toAjax(bidPolicylawFilesService.updateBidPolicylawFiles(bidPolicylawFiles));
    }

    /**
     * 删除政策法规附件
     */
    @ApiOperation("删除政策法规附件")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:files:remove')")
    @Log(title = "政策法规附件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bidPolicylawFilesService.deleteBidPolicylawFilesByIds(ids));
    }
}
