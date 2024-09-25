package com.neu.smty.service.impl;

import java.util.List;

import com.neu.common.utils.DateUtils;
import com.neu.smty.domain.CmtArticle;
import com.neu.smty.mapper.CmtArticleMapper;
import com.neu.smty.service.ICmtArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 文章管理Service业务层处理
 * 
 * @author neusoft
 * @date 2021-07-11
 */
@Service
public class CmtArticleServiceImpl implements ICmtArticleService 
{
    @Autowired
    private CmtArticleMapper cmtArticleMapper;

    /**
     * 查询文章管理
     * 
     * @param id 文章管理ID
     * @return 文章管理
     */
    @Override
    public CmtArticle selectCmtArticleById(Long id)
    {
        return cmtArticleMapper.selectCmtArticleById(id);
    }

    /**
     * 查询文章管理列表
     * 
     * @param cmtArticle 文章管理
     * @return 文章管理
     */
    @Override
    public List<CmtArticle> selectCmtArticleList(CmtArticle cmtArticle)
    {
        return cmtArticleMapper.selectCmtArticleList(cmtArticle);
    }

    /**
     * 新增文章管理
     * 
     * @param cmtArticle 文章管理
     * @return 结果
     */
    @Override
    public int insertCmtArticle(CmtArticle cmtArticle)
    {
        cmtArticle.setCreateTime(DateUtils.getNowDate());
        return cmtArticleMapper.insertCmtArticle(cmtArticle);
    }

    /**
     * 修改文章管理
     * 
     * @param cmtArticle 文章管理
     * @return 结果
     */
    @Override
    public int updateCmtArticle(CmtArticle cmtArticle)
    {
        return cmtArticleMapper.updateCmtArticle(cmtArticle);
    }

    /**
     * 批量删除文章管理
     * 
     * @param ids 需要删除的文章管理ID
     * @return 结果
     */
    @Override
    public int deleteCmtArticleByIds(Long[] ids)
    {
        return cmtArticleMapper.deleteCmtArticleByIds(ids);
    }

    /**
     * 删除文章管理信息
     * 
     * @param id 文章管理ID
     * @return 结果
     */
    @Override
    public int deleteCmtArticleById(Long id)
    {
        return cmtArticleMapper.deleteCmtArticleById(id);
    }
}
