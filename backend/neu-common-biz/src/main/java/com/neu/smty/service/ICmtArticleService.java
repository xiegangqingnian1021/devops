package com.neu.smty.service;

import java.util.List;

import com.neu.smty.domain.CmtArticle;

/**
 * 文章管理Service接口
 * 
 * @author neusoft
 * @date 2021-07-11
 */
public interface ICmtArticleService 
{
    /**
     * 查询文章管理
     * 
     * @param id 文章管理ID
     * @return 文章管理
     */
    public CmtArticle selectCmtArticleById(Long id);

    /**
     * 查询文章管理列表
     * 
     * @param cmtArticle 文章管理
     * @return 文章管理集合
     */
    public List<CmtArticle> selectCmtArticleList(CmtArticle cmtArticle);

    /**
     * 新增文章管理
     * 
     * @param cmtArticle 文章管理
     * @return 结果
     */
    public int insertCmtArticle(CmtArticle cmtArticle);

    /**
     * 修改文章管理
     * 
     * @param cmtArticle 文章管理
     * @return 结果
     */
    public int updateCmtArticle(CmtArticle cmtArticle);

    /**
     * 批量删除文章管理
     * 
     * @param ids 需要删除的文章管理ID
     * @return 结果
     */
    public int deleteCmtArticleByIds(Long[] ids);

    /**
     * 删除文章管理信息
     * 
     * @param id 文章管理ID
     * @return 结果
     */
    public int deleteCmtArticleById(Long id);
}
