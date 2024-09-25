package com.neu.smty.service;

import java.util.List;

import com.neu.smty.domain.CmtAdv;

/**
 * 广告信息Service接口
 * 
 * @author Daniel
 * @date 2021-07-11
 */
public interface ICmtAdvService 
{
    /**
     * 查询广告信息
     * 
     * @param id 广告信息ID
     * @return 广告信息
     */
    public CmtAdv selectCmtAdvById(Long id);

    /**
     * 查询广告信息列表
     * 
     * @param cmtAdv 广告信息
     * @return 广告信息集合
     */
    public List<CmtAdv> selectCmtAdvList(CmtAdv cmtAdv);

    /**
     * 新增广告信息
     * 
     * @param cmtAdv 广告信息
     * @return 结果
     */
    public int insertCmtAdv(CmtAdv cmtAdv);

    /**
     * 修改广告信息
     * 
     * @param cmtAdv 广告信息
     * @return 结果
     */
    public int updateCmtAdv(CmtAdv cmtAdv);

    /**
     * 批量删除广告信息
     * 
     * @param ids 需要删除的广告信息ID
     * @return 结果
     */
    public int deleteCmtAdvByIds(Long[] ids);

    /**
     * 删除广告信息信息
     * 
     * @param id 广告信息ID
     * @return 结果
     */
    public int deleteCmtAdvById(Long id);
}
