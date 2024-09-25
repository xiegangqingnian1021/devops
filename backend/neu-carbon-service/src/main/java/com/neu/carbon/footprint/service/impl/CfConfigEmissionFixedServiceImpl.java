package com.neu.carbon.footprint.service.impl;

import com.neu.carbon.footprint.domain.CfConfigEmissionFixed;
import com.neu.carbon.footprint.mapper.CfConfigEmissionFixedMapper;
import com.neu.carbon.footprint.service.ICfConfigEmissionFixedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 固定碳排放Service业务层处理
 * 
 * @author neuedu
 * @date 2022-07-22
 */
@Service
public class CfConfigEmissionFixedServiceImpl implements ICfConfigEmissionFixedService {
	@Autowired
	private CfConfigEmissionFixedMapper cfConfigEmissionFixedMapper;

	/**
	 * 查询固定碳排放
	 * 
	 * @param id
	 *            固定碳排放ID
	 * @return 固定碳排放
	 */
	@Override
	public CfConfigEmissionFixed selectCfConfigEmissionFixedById(Long id) {
		return cfConfigEmissionFixedMapper.selectCfConfigEmissionFixedById(id);
	}

	/**
	 * 查询固定碳排放列表
	 * 
	 * @param cfConfigEmissionFixed
	 *            固定碳排放
	 * @return 固定碳排放
	 */
	@Override
	public List<CfConfigEmissionFixed> selectCfConfigEmissionFixedList(CfConfigEmissionFixed cfConfigEmissionFixed) {
		return cfConfigEmissionFixedMapper.selectCfConfigEmissionFixedList(cfConfigEmissionFixed);
	}

	/**
	 * 新增固定碳排放
	 * 
	 * @param cfConfigEmissionFixed
	 *            固定碳排放
	 * @return 结果
	 */
	@Override
	public int insertCfConfigEmissionFixed(CfConfigEmissionFixed cfConfigEmissionFixed) {
		return cfConfigEmissionFixedMapper.insertCfConfigEmissionFixed(cfConfigEmissionFixed);
	}

	/**
	 * 修改固定碳排放
	 * 
	 * @param cfConfigEmissionFixed
	 *            固定碳排放
	 * @return 结果
	 */
	@Override
	public int updateCfConfigEmissionFixed(CfConfigEmissionFixed cfConfigEmissionFixed) {
		return cfConfigEmissionFixedMapper.updateCfConfigEmissionFixed(cfConfigEmissionFixed);
	}

	/**
	 * 批量删除固定碳排放
	 * 
	 * @param ids
	 *            需要删除的固定碳排放ID
	 * @return 结果
	 */
	@Override
	public int deleteCfConfigEmissionFixedByIds(Long[] ids) {
		return cfConfigEmissionFixedMapper.deleteCfConfigEmissionFixedByIds(ids);
	}

	/**
	 * 删除固定碳排放信息
	 * 
	 * @param id
	 *            固定碳排放ID
	 * @return 结果
	 */
	@Override
	public int deleteCfConfigEmissionFixedById(Long id) {
		return cfConfigEmissionFixedMapper.deleteCfConfigEmissionFixedById(id);
	}

	@Override
	public double getPrdWaterConsumeByMonth() {
		CfConfigEmissionFixed cfConfigEmissionFixed = new CfConfigEmissionFixed();
		cfConfigEmissionFixed.setType("3");
		cfConfigEmissionFixed.setCategory("3");
		List<CfConfigEmissionFixed> consumePwList = cfConfigEmissionFixedMapper
				.selectCfConfigEmissionFixedList(cfConfigEmissionFixed);
		double total = 0;
		for (CfConfigEmissionFixed consume : consumePwList) {
			double energy = consume.getEnergy() == null ? 0 : consume.getEnergy();
			total = total + energy;
		}
		return total;
	}

	@Override
	public double getOfficeWaterConsumeByMonth() {
		CfConfigEmissionFixed cfConfigEmissionFixed = new CfConfigEmissionFixed();
		cfConfigEmissionFixed.setType("3");
		cfConfigEmissionFixed.setCategory("4");
		List<CfConfigEmissionFixed> consumePwList = cfConfigEmissionFixedMapper
				.selectCfConfigEmissionFixedList(cfConfigEmissionFixed);
		double total = 0;
		for (CfConfigEmissionFixed consume : consumePwList) {
			double energy = consume.getEnergy() == null ? 0 : consume.getEnergy();
			total = total + energy;
		}
		return total;
	}

	@Override
	public double getOfficePowerConsumeByMonth() {
		CfConfigEmissionFixed cfConfigEmissionFixed = new CfConfigEmissionFixed();
		cfConfigEmissionFixed.setType("3");
		cfConfigEmissionFixed.setCategory("2");
		List<CfConfigEmissionFixed> consumePwList = cfConfigEmissionFixedMapper
				.selectCfConfigEmissionFixedList(cfConfigEmissionFixed);
		double total = 0;
		for (CfConfigEmissionFixed consume : consumePwList) {
			double energy = consume.getEnergy() == null ? 0 : consume.getEnergy();
			total = total + energy;
		}
		return total;
	}
}
