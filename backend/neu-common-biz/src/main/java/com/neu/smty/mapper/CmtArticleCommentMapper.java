package com.neu.smty.mapper;

import java.util.List;

import com.neu.smty.domain.CmtArticleComment;

/**
 * 文章评论Mapper接口
 * 
 * @author neusoft
 * @date 2021-07-11
 */
public interface CmtArticleCommentMapper 
{
    /**
     * 查询文章评论
     * 
     * @param id 文章评论ID
     * @return 文章评论
     */
    public CmtArticleComment selectCmtArticleCommentById(Long id);

    /**
     * 查询文章评论列表
     * 
     * @param cmtArticleComment 文章评论
     * @return 文章评论集合
     */
    public List<CmtArticleComment> selectCmtArticleCommentList(CmtArticleComment cmtArticleComment);

    /**
     * 新增文章评论
     * 
     * @param cmtArticleComment 文章评论
     * @return 结果
     */
    public int insertCmtArticleComment(CmtArticleComment cmtArticleComment);

    /**
     * 修改文章评论
     * 
     * @param cmtArticleComment 文章评论
     * @return 结果
     */
    public int updateCmtArticleComment(CmtArticleComment cmtArticleComment);

    /**
     * 删除文章评论
     * 
     * @param id 文章评论ID
     * @return 结果
     */
    public int deleteCmtArticleCommentById(Long id);

    /**
     * 批量删除文章评论
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmtArticleCommentByIds(Long[] ids);
}
