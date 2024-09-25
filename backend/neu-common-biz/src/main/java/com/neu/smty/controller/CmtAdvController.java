package com.neu.smty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neu.common.annotation.Log;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.page.TableDataInfo;
import com.neu.common.enums.BusinessType;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.smty.domain.CmtAdv;
import com.neu.smty.service.ICmtAdvService;

/**
 * 广告信息Controller
 * 
 * @author Daniel
 * @date 2021-07-11
 */
@RestController
@RequestMapping("/adv/adv")
public class CmtAdvController extends BaseController
{
    @Autowired
    private ICmtAdvService cmtAdvService;

    /**
     * 查询广告信息列表
     */
    @PreAuthorize("@ss.hasPermi('adv:adv:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmtAdv cmtAdv)
    {
        startPage();
        List<CmtAdv> list = cmtAdvService.selectCmtAdvList(cmtAdv);
        return getDataTable(list);
    }

    /**
     * 导出广告信息列表
     */
    @PreAuthorize("@ss.hasPermi('adv:adv:export')")
    @Log(title = "广告信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CmtAdv cmtAdv)
    {
        List<CmtAdv> list = cmtAdvService.selectCmtAdvList(cmtAdv);
        ExcelUtil<CmtAdv> util = new ExcelUtil<CmtAdv>(CmtAdv.class);
        return util.exportExcel(list, "adv");
    }

    /**
     * 获取广告信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('adv:adv:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(cmtAdvService.selectCmtAdvById(id));
    }

    /**
     * 新增广告信息
     */
    @PreAuthorize("@ss.hasPermi('adv:adv:add')")
    @Log(title = "广告信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmtAdv cmtAdv)
    {
        return toAjax(cmtAdvService.insertCmtAdv(cmtAdv));
    }

    /**
     * 修改广告信息
     */
    @PreAuthorize("@ss.hasPermi('adv:adv:edit')")
    @Log(title = "广告信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmtAdv cmtAdv)
    {
        return toAjax(cmtAdvService.updateCmtAdv(cmtAdv));
    }

    /**
     * 删除广告信息
     */
    @PreAuthorize("@ss.hasPermi('adv:adv:remove')")
    @Log(title = "广告信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cmtAdvService.deleteCmtAdvByIds(ids));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('common:adv:edit')")
    @Log(title = "广告信息", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody CmtAdv cmtAdv)
    {
        return toAjax(cmtAdvService.updateCmtAdv(cmtAdv));
    }
}
