package com.neu.carbon.footprint.service;

import com.neu.carbon.footprint.domain.VMesEquipmentPowerDetail;

import java.util.List;

/**
 * VIEWService接口
 * 
 * @author neuedu
 * @date 2022-07-31
 */
public interface IVMesEquipmentPowerDetailService 
{
    /**
     * 查询VIEW
     * 
     * @param equipmentId VIEWID
     * @return VIEW
     */
    public VMesEquipmentPowerDetail selectVMesEquipmentPowerDetailById(Long equipmentId);

    /**
     * 查询VIEW列表
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
