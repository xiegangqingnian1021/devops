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
import com.neu.smty.domain.ComCity;
import com.neu.smty.service.IComCityService;

/**
 * 城市信息Controller
 * 
 * @author neusoft
 * @date 2021-08-12
 */
@RestController
@RequestMapping("/location/city")
public class ComCityController extends BaseController
{
    @Autowired
    private IComCityService comCityService;

    /**
     * 查询城市信息列表
     */
    @PreAuthorize("@ss.hasPermi('location:city:list')")
    @GetMapping("/list")
    public TableDataInfo list(ComCity comCity)
    {
        startPage();
        List<ComCity> list = comCityService.selectComCityList(comCity);
        return getDataTable(list);
    }

    /**
     * 导出城市信息列表
     */
    @PreAuthorize("@ss.hasPermi('location:city:export')")
    @Log(title = "城市信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ComCity comCity)
    {
        List<ComCity> list = comCityService.selectComCityList(comCity);
        ExcelUtil<ComCity> util = new ExcelUtil<ComCity>(ComCity.class);
        return util.exportExcel(list, "city");
    }

    /**
     * 获取城市信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('location:city:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(comCityService.selectComCityById(id));
    }

    /**
     * 新增城市信息
     */
    @PreAuthorize("@ss.hasPermi('location:city:add')")
    @Log(title = "城市信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ComCity comCity)
    {
        return toAjax(comCityService.insertComCity(comCity));
    }

    /**
     * 修改城市信息
     */
    @PreAuthorize("@ss.hasPermi('location:city:edit')")
    @Log(title = "城市信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ComCity comCity)
    {
        return toAjax(comCityService.updateComCity(comCity));
    }

    /**
     * 删除城市信息
     */
    @PreAuthorize("@ss.hasPermi('location:city:remove')")
    @Log(title = "城市信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(comCityService.deleteComCityByIds(ids));
    }
}
