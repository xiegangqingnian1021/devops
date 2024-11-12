package com.neu.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.system.mapper.OpenstackImageInfoMapper;
import com.neu.system.domain.OpenstackImageInfo;
import com.neu.system.service.IOpenstackImageInfoService;

/**
 * 镜像信息Service业务层处理
 * 
 * @author neuedu
 * @date 2024-11-12
 */
@Service
public class OpenstackImageInfoServiceImpl implements IOpenstackImageInfoService 
{
    @Autowired
    private OpenstackImageInfoMapper openstackImageInfoMapper;

    /**
     * 查询镜像信息
     * 
     * @param imageId 镜像信息ID
     * @return 镜像信息
     */
    @Override
    public OpenstackImageInfo selectOpenstackImageInfoById(String imageId)
    {
        return openstackImageInfoMapper.selectOpenstackImageInfoById(imageId);
    }

    /**
     * 查询镜像信息列表
     * 
     * @param openstackImageInfo 镜像信息
     * @return 镜像信息
     */
    @Override
    public List<OpenstackImageInfo> selectOpenstackImageInfoList(OpenstackImageInfo openstackImageInfo)
    {
        return openstackImageInfoMapper.selectOpenstackImageInfoList(openstackImageInfo);
    }

    /**
     * 新增镜像信息
     * 
     * @param openstackImageInfo 镜像信息
     * @return 结果
     */
    @Override
    public int insertOpenstackImageInfo(OpenstackImageInfo openstackImageInfo)
    {
        return openstackImageInfoMapper.insertOpenstackImageInfo(openstackImageInfo);
    }

    /**
     * 修改镜像信息
     * 
     * @param openstackImageInfo 镜像信息
     * @return 结果
     */
    @Override
    public int updateOpenstackImageInfo(OpenstackImageInfo openstackImageInfo)
    {
        return openstackImageInfoMapper.updateOpenstackImageInfo(openstackImageInfo);
    }

    /**
     * 批量删除镜像信息
     * 
     * @param imageIds 需要删除的镜像信息ID
     * @return 结果
     */
    @Override
    public int deleteOpenstackImageInfoByIds(String[] imageIds)
    {
        return openstackImageInfoMapper.deleteOpenstackImageInfoByIds(imageIds);
    }

    /**
     * 删除镜像信息信息
     * 
     * @param imageId 镜像信息ID
     * @return 结果
     */
    @Override
    public int deleteOpenstackImageInfoById(String imageId)
    {
        return openstackImageInfoMapper.deleteOpenstackImageInfoById(imageId);
    }
}
