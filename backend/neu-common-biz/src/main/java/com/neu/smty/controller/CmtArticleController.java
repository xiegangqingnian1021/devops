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
import com.neu.smty.domain.CmtArticle;
import com.neu.smty.service.ICmtArticleService;

/**
 * 文章管理Controller
 * 
 * @author neusoft
 * @date 2021-07-11
 */
@RestController
@RequestMapping("/cms/article")
public class CmtArticleController extends BaseController
{
    @Autowired
    private ICmtArticleService cmtArticleService;

    /**
     * 查询文章管理列表
     */
    @PreAuthorize("@ss.hasPermi('cms:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmtArticle cmtArticle)
    {
        startPage();
        List<CmtArticle> list = cmtArticleService.selectCmtArticleList(cmtArticle);
        return getDataTable(list);
    }

    /**
     * 导出文章管理列表
     */
    @PreAuthorize("@ss.hasPermi('cms:article:export')")
    @Log(title = "文章管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CmtArticle cmtArticle)
    {
        List<CmtArticle> list = cmtArticleService.selectCmtArticleList(cmtArticle);
        ExcelUtil<CmtArticle> util = new ExcelUtil<CmtArticle>(CmtArticle.class);
        return util.exportExcel(list, "article");
    }

    /**
     * 获取文章管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('cms:article:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(cmtArticleService.selectCmtArticleById(id));
    }

    /**
     * 新增文章管理
     */
    @PreAuthorize("@ss.hasPermi('cms:article:add')")
    @Log(title = "文章管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmtArticle cmtArticle)
    {
        return toAjax(cmtArticleService.insertCmtArticle(cmtArticle));
    }

    /**
     * 修改文章管理
     */
    @PreAuthorize("@ss.hasPermi('cms:article:edit')")
    @Log(title = "文章管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmtArticle cmtArticle)
    {
        return toAjax(cmtArticleService.updateCmtArticle(cmtArticle));
    }

    /**
     * 删除文章管理
     */
    @PreAuthorize("@ss.hasPermi('cms:article:remove')")
    @Log(title = "文章管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cmtArticleService.deleteCmtArticleByIds(ids));
    }
}
