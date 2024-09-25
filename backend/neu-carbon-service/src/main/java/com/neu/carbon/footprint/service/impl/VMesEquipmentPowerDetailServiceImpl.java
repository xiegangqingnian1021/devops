package com.neu.carbon.footprint.service.impl;

import com.neu.carbon.footprint.domain.VMesEquipmentPowerDetail;
import com.neu.carbon.footprint.mapper.VMesEquipmentPowerDetailMapper;
import com.neu.carbon.footprint.service.IVMesEquipmentPowerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * VIEWService业务层处理
 * 
 * @author neuedu
 * @date 2022-07-31
 */
@Service
public class VMesEquipmentPowerDetailServiceImpl implements IVMesEquipmentPowerDetailService {
	@Autowired
	private VMesEquipmentPowerDetailMapper vMesEquipmentPowerDetailMapper;

	/**
	 * 查询VIEW
	 * 
	 * @param equipmentId
	 *            VIEWID
	 * @return VIEW
	 */
	@Override
	public VMesEquipmentPowerDetail selectVMesEquipmentPowerDetailById(Long equipmentId) {
		return vMesEquipmentPowerDetailMapper.selectVMesEquipmentPowerDetailById(equipmentId);
	}

	/**
	 * 查询VIEW列表
	 * 
	 * @param vMesEquipmentPowerDetail
	 *            VIEW
	 * @return VIEW
	 */
	@Override
	public List<VMesEquipmentPowerDetail> selectVMesEquipmentPowerDetailList(
			VMesEquipmentPowerDetail vMesEquipmentPowerDetail) {
		return vMesEquipmentPowerDetailMapper.selectVMesEquipmentPowerDetailList(vMesEquipmentPowerDetail);
	}

	@Override
	public List<VMesEquipmentPowerDetail> selectEquipmentPowerReportByDay(
			VMesEquipmentPowerDetail vMesEquipmentPowerDetail) {
		return vMesEquipmentPowerDetailMapper.selectEquipmentPowerReportByDay(vMesEquipmentPowerDetail);
	}

}
