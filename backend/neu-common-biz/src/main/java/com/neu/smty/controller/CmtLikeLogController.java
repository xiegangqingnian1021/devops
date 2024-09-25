package com.neu.smty.controller;

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

import com.neu.common.annotation.Log;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.page.TableDataInfo;
import com.neu.common.enums.BusinessType;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.smty.domain.CmtLikeLog;
import com.neu.smty.service.ICmtLikeLogService;

/**
 * 点赞日志Controller
 * 
 * @author neusoft
 * @date 2021-08-04
 */
@RestController
@RequestMapping("/cms/likLog")
public class CmtLikeLogController extends BaseController
{
    @Autowired
    private ICmtLikeLogService cmtLikeLogService;

    /**
     * 查询点赞日志列表
     */
    @PreAuthorize("@ss.hasPermi('cms:likLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmtLikeLog cmtLikeLog)
    {
        startPage();
        List<CmtLikeLog> list = cmtLikeLogService.selectCmtLikeLogList(cmtLikeLog);
        return getDataTable(list);
    }

    /**
     * 导出点赞日志列表
     */
    @PreAuthorize("@ss.hasPermi('cms:likLog:export')")
    @Log(title = "点赞日志", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CmtLikeLog cmtLikeLog)
    {
        List<CmtLikeLog> list = cmtLikeLogService.selectCmtLikeLogList(cmtLikeLog);
        ExcelUtil<CmtLikeLog> util = new ExcelUtil<CmtLikeLog>(CmtLikeLog.class);
        return util.exportExcel(list, "likLog");
    }

    /**
     * 获取点赞日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('cms:likLog:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(cmtLikeLogService.selectCmtLikeLogById(id));
    }

    /**
     * 新增点赞日志
     */
    @PreAuthorize("@ss.hasPermi('cms:likLog:add')")
    @Log(title = "点赞日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmtLikeLog cmtLikeLog)
    {
        return toAjax(cmtLikeLogService.insertCmtLikeLog(cmtLikeLog));
    }

    /**
     * 修改点赞日志
     */
    @PreAuthorize("@ss.hasPermi('cms:likLog:edit')")
    @Log(title = "点赞日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmtLikeLog cmtLikeLog)
    {
        return toAjax(cmtLikeLogService.updateCmtLikeLog(cmtLikeLog));
    }

    /**
     * 删除点赞日志
     */
    @PreAuthorize("@ss.hasPermi('cms:likLog:remove')")
    @Log(title = "点赞日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cmtLikeLogService.deleteCmtLikeLogByIds(ids));
    }
}
