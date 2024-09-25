package com.neu.carbon.mes.service;

import java.util.List;
import com.neu.carbon.mes.domain.MesEquipment;

/**
 * 设备信息Service接口
 * 
 * @author neuedu
 * @date 2022-07-07
 */
public interface IMesEquipmentService 
{
    /**
     * 查询设备信息
     * 
     * @param id 设备信息ID
     * @return 设备信息
     */
    public MesEquipment selectMesEquipmentById(Long id);

    /**
     * 查询设备信息列表
     * 
     * @param mesEquipment 设备信息
     * @return 设备信息集合
     */
    public List<MesEquipment> selectMesEquipmentList(MesEquipment mesEquipment);

    /**
     * 新增设备信息
     * 
     * @param mesEquipment 设备信息
     * @return 结果
     */
    public int insertMesEquipment(MesEquipment mesEquipment);

    /**
     * 修改设备信息
     * 
     * @param mesEquipment 设备信息
     * @return 结果
     */
    public int updateMesEquipment(MesEquipment mesEquipment);

    /**
     * 批量删除设备信息
     * 
     * @param ids 需要删除的设备信息ID
     * @return 结果
     */
    public int deleteMesEquipmentByIds(Long[] ids);

    /**
     * 删除设备信息信息
     * 
     * @param id 设备信息ID
     * @return 结果
     */
    public int deleteMesEquipmentById(Long id);
}
