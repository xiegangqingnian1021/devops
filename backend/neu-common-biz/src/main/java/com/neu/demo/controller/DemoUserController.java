package com.neu.demo.controller;

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
import com.neu.common.enums.BusinessType;
import com.neu.demo.domain.DemoUser;
import com.neu.demo.service.IDemoUserService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 演示Controller
 * 
 * @author neusoft
 * @date 2021-08-20
 */
@RestController
@RequestMapping("/demo/demoUser")
public class DemoUserController extends BaseController
{
    @Autowired
    private IDemoUserService demoUserService;

    /**
     * 查询演示列表
     */
    @PreAuthorize("@ss.hasPermi('demo:demoUser:list')")
    @GetMapping("/list")
    public TableDataInfo list(DemoUser demoUser)
    {
        startPage();
        List<DemoUser> list = demoUserService.selectDemoUserList(demoUser);
        return getDataTable(list);
    }

    /**
     * 导出演示列表
     */
    @PreAuthorize("@ss.hasPermi('demo:demoUser:export')")
    @Log(title = "演示", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DemoUser demoUser)
    {
        List<DemoUser> list = demoUserService.selectDemoUserList(demoUser);
        ExcelUtil<DemoUser> util = new ExcelUtil<DemoUser>(DemoUser.class);
        return util.exportExcel(list, "demoUser");
    }

    /**
     * 获取演示详细信息
     */
    @PreAuthorize("@ss.hasPermi('demo:demoUser:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(demoUserService.selectDemoUserById(id));
    }

    /**
     * 新增演示
     */
    @PreAuthorize("@ss.hasPermi('demo:demoUser:add')")
    @Log(title = "演示", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DemoUser demoUser)
    {
        return toAjax(demoUserService.insertDemoUser(demoUser));
    }

    /**
     * 修改演示
     */
    @PreAuthorize("@ss.hasPermi('demo:demoUser:edit')")
    @Log(title = "演示", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DemoUser demoUser)
    {
        return toAjax(demoUserService.updateDemoUser(demoUser));
    }

    /**
     * 删除演示
     */
    @PreAuthorize("@ss.hasPermi('demo:demoUser:remove')")
    @Log(title = "演示", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(demoUserService.deleteDemoUserByIds(ids));
    }
}
