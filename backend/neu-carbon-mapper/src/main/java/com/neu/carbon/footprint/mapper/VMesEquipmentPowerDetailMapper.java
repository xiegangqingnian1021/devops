package com.neu.carbon.footprint.mapper;

import com.neu.carbon.footprint.domain.VMesEquipmentPowerDetail;

import java.util.List;

/**
 * 设备能耗Mapper接口
 * 
 * @author neuedu
 * @date 2022-07-31
 */
public interface VMesEquipmentPowerDetailMapper 
{
    /**
     * 查询设备能耗
     * 
     * @param equipmentId VIEWID
     * @return VIEW
     */
    public VMesEquipmentPowerDetail selectVMesEquipmentPowerDetailById(Long equipmentId);

    /**
     * 查询设备能耗报表
     * 
     * @param vMesEquipmentPowerDetail VIEW
     * @return VIEW集合
     */
    public List<VMesEquipmentPowerDetail> selectVMesEquipmentPowerDetailList(VMesEquipmentPowerDetail vMesEquipmentPowerDetail);

    /**
     * 查询设备日能耗报表
     * @param vMesEquipmentPowerDetail
     * @return
     */
    public List<VMesEquipmentPowerDetail> selectEquipmentPowerReportByDay(VMesEquipmentPowerDetail vMesEquipmentPowerDetail);
}
