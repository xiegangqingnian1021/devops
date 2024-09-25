package com.neu.carbon.footprint.service.impl;

import com.neu.carbon.footprint.domain.CfConfigNeutral;
import com.neu.carbon.footprint.mapper.CfConfigNeutralMapper;
import com.neu.carbon.footprint.service.ICfConfigNeutralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 碳中和计算比例Service业务层处理
 * 
 * @author neuedu
 * @date 2022-07-22
 */
@Service
public class CfConfigNeutralServiceImpl implements ICfConfigNeutralService {
	@Autowired
	private CfConfigNeutralMapper cfConfigNeutralMapper;

	/**
	 * 查询碳中和计算比例
	 * 
	 * @param id
	 *            碳中和计算比例ID
	 * @return 碳中和计算比例
	 */
	@Override
	public CfConfigNeutral selectCfConfigNeutralById(Long id) {
		return cfConfigNeutralMapper.selectCfConfigNeutralById(id);
	}

	/**
	 * 查询碳中和计算比例列表
	 * 
	 * @param cfConfigNeutral
	 *            碳中和计算比例
	 * @return 碳中和计算比例
	 */
	@Override
	public List<CfConfigNeutral> selectCfConfigNeutralList(CfConfigNeutral cfConfigNeutral) {
		return cfConfigNeutralMapper.selectCfConfigNeutralList(cfConfigNeutral);
	}

	/**
	 * 新增碳中和计算比例
	 * 
	 * @param cfConfigNeutral
	 *            碳中和计算比例
	 * @return 结果
	 */
	@Override
	public int insertCfConfigNeutral(CfConfigNeutral cfConfigNeutral) {
		return cfConfigNeutralMapper.insertCfConfigNeutral(cfConfigNeutral);
	}

	/**
	 * 修改碳中和计算比例
	 * 
	 * @param cfConfigNeutral
	 *            碳中和计算比例
	 * @return 结果
	 */
	@Override
	public int updateCfConfigNeutral(CfConfigNeutral cfConfigNeutral) {
		return cfConfigNeutralMapper.updateCfConfigNeutral(cfConfigNeutral);
	}

	/**
	 * 批量删除碳中和计算比例
	 * 
	 * @param ids
	 *            需要删除的碳中和计算比例ID
	 * @return 结果
	 */
	@Override
	public int deleteCfConfigNeutralByIds(Long[] ids) {
		return cfConfigNeutralMapper.deleteCfConfigNeutralByIds(ids);
	}

	/**
	 * 删除碳中和计算比例信息
	 * 
	 * @param id
	 *            碳中和计算比例ID
	 * @return 结果
	 */
	@Override
	public int deleteCfConfigNeutralById(Long id) {
		return cfConfigNeutralMapper.deleteCfConfigNeutralById(id);
	}

	@Override
	public double getElectricCo2() {
		CfConfigNeutral cfConfigNeutral = new CfConfigNeutral();
		List<CfConfigNeutral> cfList = cfConfigNeutralMapper.selectCfConfigNeutralList(cfConfigNeutral);
		CfConfigNeutral cfConfig = null;
		if (cfList != null && !cfList.isEmpty()) {
			cfConfig = cfList.get(0);
			return cfConfig.getElectricCo2() != null ? cfConfig.getElectricCo2() : 0;
		}
		return 0;
	}

	@Override
	public double getWaterCo2() {
		CfConfigNeutral cfConfigNeutral = new CfConfigNeutral();
		List<CfConfigNeutral> cfList = cfConfigNeutralMapper.selectCfConfigNeutralList(cfConfigNeutral);
		CfConfigNeutral cfConfig = null;
		if (cfList != null && !cfList.isEmpty()) {
			cfConfig = cfList.get(0);
			return cfConfig.getWaterCo2() != null ? cfConfig.getWaterCo2() : 0;
		}
		return 0;
	}
}
