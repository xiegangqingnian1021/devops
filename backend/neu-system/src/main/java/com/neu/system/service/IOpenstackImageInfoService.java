package com.neu.system.service;

import java.util.List;
import com.neu.system.domain.OpenstackImageInfo;

/**
 * 镜像信息Service接口
 * 
 * @author neuedu
 * @date 2024-11-12
 */
public interface IOpenstackImageInfoService 
{
    /**
     * 查询镜像信息
     * 
     * @param imageId 镜像信息ID
     * @return 镜像信息
     */
    public OpenstackImageInfo selectOpenstackImageInfoById(String imageId);

    /**
     * 查询镜像信息列表
     * 
     * @param openstackImageInfo 镜像信息
     * @return 镜像信息集合
     */
    public List<OpenstackImageInfo> selectOpenstackImageInfoList(OpenstackImageInfo openstackImageInfo);

    /**
     * 新增镜像信息
     * 
     * @param openstackImageInfo 镜像信息
     * @return 结果
     */
    public int insertOpenstackImageInfo(OpenstackImageInfo openstackImageInfo);

    /**
     * 修改镜像信息
     * 
     * @param openstackImageInfo 镜像信息
     * @return 结果
     */
    public int updateOpenstackImageInfo(OpenstackImageInfo openstackImageInfo);

    /**
     * 批量删除镜像信息
     * 
     * @param imageIds 需要删除的镜像信息ID
     * @return 结果
     */
    public int deleteOpenstackImageInfoByIds(String[] imageIds);

    /**
     * 删除镜像信息信息
     * 
     * @param imageId 镜像信息ID
     * @return 结果
     */
    public int deleteOpenstackImageInfoById(String imageId);
}
