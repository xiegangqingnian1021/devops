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
import com.neu.smty.domain.CmtArticleComment;
import com.neu.smty.service.ICmtArticleCommentService;

/**
 * 文章评论Controller
 * 
 * @author neusoft
 * @date 2021-07-11
 */
@RestController
@RequestMapping("/cms/comment")
public class CmtArticleCommentController extends BaseController
{
    @Autowired
    private ICmtArticleCommentService cmtArticleCommentService;

    /**
     * 查询文章评论列表
     */
    @PreAuthorize("@ss.hasPermi('cms:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmtArticleComment cmtArticleComment)
    {
        startPage();
        List<CmtArticleComment> list = cmtArticleCommentService.selectCmtArticleCommentList(cmtArticleComment);
        return getDataTable(list);
    }

    /**
     * 导出文章评论列表
     */
    @PreAuthorize("@ss.hasPermi('cms:comment:export')")
    @Log(title = "文章评论", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CmtArticleComment cmtArticleComment)
    {
        List<CmtArticleComment> list = cmtArticleCommentService.selectCmtArticleCommentList(cmtArticleComment);
        ExcelUtil<CmtArticleComment> util = new ExcelUtil<CmtArticleComment>(CmtArticleComment.class);
        return util.exportExcel(list, "comment");
    }

    /**
     * 获取文章评论详细信息
     */
    @PreAuthorize("@ss.hasPermi('cms:comment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(cmtArticleCommentService.selectCmtArticleCommentById(id));
    }

    /**
     * 新增文章评论
     */
    @PreAuthorize("@ss.hasPermi('cms:comment:add')")
    @Log(title = "文章评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmtArticleComment cmtArticleComment)
    {
        return toAjax(cmtArticleCommentService.insertCmtArticleComment(cmtArticleComment));
    }

    /**
     * 修改文章评论
     */
    @PreAuthorize("@ss.hasPermi('cms:comment:edit')")
    @Log(title = "文章评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmtArticleComment cmtArticleComment)
    {
        return toAjax(cmtArticleCommentService.updateCmtArticleComment(cmtArticleComment));
    }

    /**
     * 删除文章评论
     */
    @PreAuthorize("@ss.hasPermi('cms:comment:remove')")
    @Log(title = "文章评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cmtArticleCommentService.deleteCmtArticleCommentByIds(ids));
    }
}
