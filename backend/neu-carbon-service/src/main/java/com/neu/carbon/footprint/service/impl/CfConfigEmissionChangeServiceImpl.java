package com.neu.carbon.footprint.service.impl;

import com.neu.carbon.footprint.domain.CfConfigEmissionChange;
import com.neu.carbon.footprint.mapper.CfConfigEmissionChangeMapper;
import com.neu.carbon.footprint.service.ICfConfigEmissionChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 变化碳排放Service业务层处理
 * 
 * @author neuedu
 * @date 2022-07-22
 */
@Service
public class CfConfigEmissionChangeServiceImpl implements ICfConfigEmissionChangeService {
	@Autowired
	private CfConfigEmissionChangeMapper cfConfigEmissionChangeMapper;

	/**
	 * 查询变化碳排放
	 * 
	 * @param id
	 *            变化碳排放ID
	 * @return 变化碳排放
	 */
	@Override
	public CfConfigEmissionChange selectCfConfigEmissionChangeById(Long id) {
		return cfConfigEmissionChangeMapper.selectCfConfigEmissionChangeById(id);
	}

	/**
	 * 查询变化碳排放列表
	 * 
	 * @param cfConfigEmissionChange
	 *            变化碳排放
	 * @return 变化碳排放
	 */
	@Override
	public List<CfConfigEmissionChange> selectCfConfigEmissionChangeList(
			CfConfigEmissionChange cfConfigEmissionChange) {
		return cfConfigEmissionChangeMapper.selectCfConfigEmissionChangeList(cfConfigEmissionChange);
	}

	/**
	 * 新增变化碳排放
	 * 
	 * @param cfConfigEmissionChange
	 *            变化碳排放
	 * @return 结果
	 */
	@Override
	public int insertCfConfigEmissionChange(CfConfigEmissionChange cfConfigEmissionChange) {
		return cfConfigEmissionChangeMapper.insertCfConfigEmissionChange(cfConfigEmissionChange);
	}

	/**
	 * 修改变化碳排放
	 * 
	 * @param cfConfigEmissionChange
	 *            变化碳排放
	 * @return 结果
	 */
	@Override
	public int updateCfConfigEmissionChange(CfConfigEmissionChange cfConfigEmissionChange) {
		return cfConfigEmissionChangeMapper.updateCfConfigEmissionChange(cfConfigEmissionChange);
	}

	/**
	 * 批量删除变化碳排放
	 * 
	 * @param ids
	 *            需要删除的变化碳排放ID
	 * @return 结果
	 */
	@Override
	public int deleteCfConfigEmissionChangeByIds(Long[] ids) {
		return cfConfigEmissionChangeMapper.deleteCfConfigEmissionChangeByIds(ids);
	}

	/**
	 * 删除变化碳排放信息
	 * 
	 * @param id
	 *            变化碳排放ID
	 * @return 结果
	 */
	@Override
	public int deleteCfConfigEmissionChangeById(Long id) {
		return cfConfigEmissionChangeMapper.deleteCfConfigEmissionChangeById(id);
	}

	@Override
	public double getProductPowerSaveAmount(int days) {
		return calcSavePower("3", days);
	}

	@Override
	public double getOfficePowerSaveAmount(int days) {
		return calcSavePower("4", days);
	}

	@Override
	public double getProductWaterSaveAmount(int days) {
		return calcSaveWater("1", days);
	}

	@Override
	public double getOfficeWaterSaveAmount(int days) {
		return calcSaveWater("2", days);
	}

	/**
	 * 计算节约电量
	 * @param category
	 * @param days
	 * @return
	 */
	private double calcSavePower(String category, int days) {
		CfConfigEmissionChange cfConfigEmissionChange = new CfConfigEmissionChange();
		cfConfigEmissionChange.setCategory(category);
		List<CfConfigEmissionChange> cfgList = cfConfigEmissionChangeMapper
				.selectCfConfigEmissionChangeList(cfConfigEmissionChange);
		double total = 0;
		for (CfConfigEmissionChange cfg : cfgList) {
			double daySave = cfg.getDaySaveElectric() == null ? 0 : cfg.getDaySaveElectric();
			total = total + daySave * days;
		}
		return total;
	}

	/**
	 * 计算节约水量
	 * @param category
	 * @param days
	 * @return
	 */
	private double calcSaveWater(String category, int days) {
		CfConfigEmissionChange cfConfigEmissionChange = new CfConfigEmissionChange();
		cfConfigEmissionChange.setCategory(category);
		List<CfConfigEmissionChange> cfgList = cfConfigEmissionChangeMapper
				.selectCfConfigEmissionChangeList(cfConfigEmissionChange);
		double total = 0;
		for (CfConfigEmissionChange cfg : cfgList) {
			double daySave = cfg.getDaySaveWater() == null ? 0 : cfg.getDaySaveElectric();
			total = total + daySave * days;
		}
		return total;
	}
}
