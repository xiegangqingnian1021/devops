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
import com.neu.carbon.bid.domain.BidComplaintFiles;
import com.neu.carbon.bid.service.IBidComplaintFilesService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 投诉附件Controller
 * 
 * @author neuedu
 * @date 2023-04-21
 */
@Api(tags = {"投诉附件"})
@RestController
@RequestMapping("/bid/complaint/files")
public class BidComplaintFilesController extends BaseController
{
    @Autowired
    private IBidComplaintFilesService bidComplaintFilesService;

    /**
     * 查询投诉附件列表
     */
    @GetMapping("/list")
    @ApiOperation("查询投诉附件列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = BidComplaintFiles.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(BidComplaintFiles bidComplaintFiles)
    {
        startPage();
        List<BidComplaintFiles> list = bidComplaintFilesService.selectBidComplaintFilesList(bidComplaintFiles);
        return getDataTable(list);
    }

    /**
     * 导出投诉附件列表
     */
    @ApiOperation("导出投诉附件列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('bid:files:export')")
    @Log(title = "投诉附件", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BidComplaintFiles bidComplaintFiles)
    {
        List<BidComplaintFiles> list = bidComplaintFilesService.selectBidComplaintFilesList(bidComplaintFiles);
        ExcelUtil<BidComplaintFiles> util = new ExcelUtil<BidComplaintFiles>(BidComplaintFiles.class);
        return util.exportExcel(list, "files");
    }

    /**
     * 获取投诉附件详细信息
     */
    @ApiOperation("获取投诉附件详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = BidComplaintFiles.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bidComplaintFilesService.selectBidComplaintFilesById(id));
    }

    /**
     * 新增投诉附件
     */
    @ApiOperation("新增投诉附件")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:files:add')")
    @Log(title = "投诉附件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BidComplaintFiles bidComplaintFiles)
    {
        return toAjax(bidComplaintFilesService.insertBidComplaintFiles(bidComplaintFiles));
    }

    /**
     * 修改投诉附件
     */
    @ApiOperation("修改投诉附件")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:files:edit')")
    @Log(title = "投诉附件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BidComplaintFiles bidComplaintFiles)
    {
        return toAjax(bidComplaintFilesService.updateBidComplaintFiles(bidComplaintFiles));
    }

    /**
     * 删除投诉附件
     */
    @ApiOperation("删除投诉附件")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:files:remove')")
    @Log(title = "投诉附件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bidComplaintFilesService.deleteBidComplaintFilesByIds(ids));
    }
}
