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
import com.neu.smty.domain.ComArea;
import com.neu.smty.service.IComAreaService;

/**
 * 区域信息Controller
 * 
 * @author neusoft
 * @date 2021-08-10
 */
@RestController
@RequestMapping("/location/area")
public class ComAreaController extends BaseController
{
    @Autowired
    private IComAreaService comAreaService;

    /**
     * 查询区域信息列表
     */
    @PreAuthorize("@ss.hasPermi('location:area:list')")
    @GetMapping("/list")
    public TableDataInfo list(ComArea comArea)
    {
        startPage();
        List<ComArea> list = comAreaService.selectComAreaList(comArea);
        return getDataTable(list);
    }

    /**
     * 导出区域信息列表
     */
    @PreAuthorize("@ss.hasPermi('location:area:export')")
    @Log(title = "区域信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ComArea comArea)
    {
        List<ComArea> list = comAreaService.selectComAreaList(comArea);
        ExcelUtil<ComArea> util = new ExcelUtil<ComArea>(ComArea.class);
        return util.exportExcel(list, "area");
    }

    /**
     * 获取区域信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('location:area:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(comAreaService.selectComAreaById(id));
    }

    /**
     * 新增区域信息
     */
    @PreAuthorize("@ss.hasPermi('location:area:add')")
    @Log(title = "区域信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ComArea comArea)
    {
        return toAjax(comAreaService.insertComArea(comArea));
    }

    /**
     * 修改区域信息
     */
    @PreAuthorize("@ss.hasPermi('location:area:edit')")
    @Log(title = "区域信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ComArea comArea)
    {
        return toAjax(comAreaService.updateComArea(comArea));
    }

    /**
     * 删除区域信息
     */
    @PreAuthorize("@ss.hasPermi('location:area:remove')")
    @Log(title = "区域信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(comAreaService.deleteComAreaByIds(ids));
    }
}
