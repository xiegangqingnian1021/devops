package com.neu.carbon.report.controller;

import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import com.neu.carbon.footprint.domain.TmpEnergyConsume;
import com.neu.carbon.footprint.service.ICfConfigEmissionChangeService;
import com.neu.carbon.footprint.service.ICfConfigEmissionFixedService;
import com.neu.carbon.footprint.service.ITmpEnergyConsumeService;
import org.springframework.beans.factory.annotation.Autowired;

import com.neu.common.core.controller.BaseController;
import com.neu.common.utils.DateUtils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;

public class BaseReportController extends BaseController {

	@Autowired
	private ITmpEnergyConsumeService tmpEnergyConsumeService;
	@Autowired
	private ICfConfigEmissionChangeService configEmissionChangeService;
	@Autowired
	private ICfConfigEmissionFixedService configEmissionFixedService;

	/**
	 * 模拟生产用水年数据
	 * 
	 * @param yearMonth
	 */
	protected double mockPrdWaterConsumeByYear(String year) {
		double total = 0;
		for (int i = 1; i < 13; i++) {
			String month = i < 10 ? ("0" + i) : (i + "");
			double consume = mockPrdWaterConsumeByMonth(year + "-" + month);
			total = total + consume;
			// 只统计到本年月数据
			if (Integer.parseInt(year) == DateUtil.year(new Date()) && i == DateUtil.month(new Date())) {
				break;
			}
		}
		return total;
	}

	/**
	 * 模拟办公用水年数据
	 * 
	 * @param yearMonth
	 */
	protected double mockOfficeWaterConsumeByYear(String year) {
		double total = 0;
		for (int i = 1; i < 13; i++) {
			String month = i < 10 ? ("0" + i) : (i + "");
			double consume = mockOfficeWaterConsumeByMonth(year + "-" + month);
			total = total + consume;
			// 只统计到本年月数据
			if (Integer.parseInt(year) == DateUtil.year(new Date()) && i == DateUtil.month(new Date())) {
				break;
			}
		}
		return total;
	}

	/**
	 * 模拟办公用电年数据
	 * 
	 * @param yearMonth
	 */
	protected double mockOfficePowerConsumeByYear(String year) {
		double total = 0;
		for (int i = 1; i < 13; i++) {
			String month = i < 10 ? ("0" + i) : (i + "");
			double consume = mockOfficePowerConsumeByMonth(year + "-" + month);
			total = total + consume;
			// 只统计到本年月数据
			if (Integer.parseInt(year) == DateUtil.year(new Date()) && i == DateUtil.month(new Date())) {
				break;
			}
		}
		return total;
	}

	/**
	 * 模拟生产用水月数据
	 * 
	 * @param yearMonth
	 */
	protected double mockPrdWaterConsumeByMonth(String yearMonth) {
		double total = 0;
		String fullDate = yearMonth + "-01";
		TmpEnergyConsume tmpConsume = new TmpEnergyConsume();
		tmpConsume.setMonth(yearMonth);
		tmpConsume.setCategory("2");
		tmpConsume.setType("0");
		tmpConsume.setItemCategory("3");
		List<TmpEnergyConsume> prdPwConsumeList = tmpEnergyConsumeService.selectTmpEnergyConsumeList(tmpConsume);
		if (prdPwConsumeList != null && !prdPwConsumeList.isEmpty()) {
			TmpEnergyConsume monthSavePw = prdPwConsumeList.get(0);
			total = monthSavePw.getEnergy();
		} else {
			Date first = DateUtil.beginOfMonth(DateUtils.parseDate(fullDate));
			int year = DateUtil.year(first);
			double ratio = RandomUtil.randomDouble(0.5, 1, 1, RoundingMode.FLOOR);
			double consumePw = configEmissionFixedService.getPrdWaterConsumeByMonth();
			total = consumePw * ratio;
			// 新增一条临时生产用水量数据
			TmpEnergyConsume newTmpConsume = new TmpEnergyConsume();
			newTmpConsume.setYear(String.valueOf(year));
			newTmpConsume.setMonth(yearMonth);
			newTmpConsume.setCategory("2");
			newTmpConsume.setType("0");
			newTmpConsume.setItemCategory("3");
			newTmpConsume.setEnergy(total);
			tmpEnergyConsumeService.insertTmpEnergyConsume(newTmpConsume);
		}
		return total;
	}

	/**
	 * 模拟办公用水月数据
	 * 
	 * @param yearMonth
	 */
	protected double mockOfficeWaterConsumeByMonth(String yearMonth) {
		double total = 0;
		String fullDate = yearMonth + "-01";
		TmpEnergyConsume tmpConsume = new TmpEnergyConsume();
		tmpConsume.setMonth(yearMonth);
		tmpConsume.setCategory("2");
		tmpConsume.setType("0");
		tmpConsume.setItemCategory("4");
		List<TmpEnergyConsume> prdPwSaveList = tmpEnergyConsumeService.selectTmpEnergyConsumeList(tmpConsume);
		if (prdPwSaveList != null && !prdPwSaveList.isEmpty()) {
			TmpEnergyConsume monthSavePw = prdPwSaveList.get(0);
			total = monthSavePw.getEnergy();
		} else {
			Date first = DateUtil.beginOfMonth(DateUtils.parseDate(fullDate));
			int year = DateUtil.year(first);
			double ratio = RandomUtil.randomDouble(0.5, 1, 1, RoundingMode.FLOOR);
			double consumePw = configEmissionFixedService.getOfficeWaterConsumeByMonth();
			total = consumePw * ratio;
			// 新增一条临时办公用水量数据
			TmpEnergyConsume newTmpConsume = new TmpEnergyConsume();
			newTmpConsume.setYear(String.valueOf(year));
			newTmpConsume.setMonth(yearMonth);
			newTmpConsume.setCategory("2");
			newTmpConsume.setType("0");
			newTmpConsume.setItemCategory("4");
			newTmpConsume.setEnergy(total);
			tmpEnergyConsumeService.insertTmpEnergyConsume(newTmpConsume);
		}
		return total;
	}

	/**
	 * 模拟办公用电月数据
	 * 
	 * @param yearMonth
	 */
	protected double mockOfficePowerConsumeByMonth(String yearMonth) {
		double total = 0;
		String fullDate = yearMonth + "-01";
		TmpEnergyConsume tmpConsume = new TmpEnergyConsume();
		tmpConsume.setMonth(yearMonth);
		tmpConsume.setCategory("1");
		tmpConsume.setType("0");
		tmpConsume.setItemCategory("2");
		List<TmpEnergyConsume> prdPwSaveList = tmpEnergyConsumeService.selectTmpEnergyConsumeList(tmpConsume);
		if (prdPwSaveList != null && !prdPwSaveList.isEmpty()) {
			TmpEnergyConsume monthSavePw = prdPwSaveList.get(0);
			total = monthSavePw.getEnergy();
		} else {
			Date first = DateUtil.beginOfMonth(DateUtils.parseDate(fullDate));
			int year = DateUtil.year(first);
			double ratio = RandomUtil.randomDouble(0.5, 1, 1, RoundingMode.FLOOR);
			double consumePw = configEmissionFixedService.getOfficePowerConsumeByMonth();
			total = consumePw * ratio;
			// 新增一条临时办公耗电量数据
			TmpEnergyConsume newTmpConsume = new TmpEnergyConsume();
			newTmpConsume.setYear(String.valueOf(year));
			newTmpConsume.setMonth(yearMonth);
			newTmpConsume.setCategory("1");
			newTmpConsume.setType("0");
			newTmpConsume.setItemCategory("2");
			newTmpConsume.setEnergy(total);
			tmpEnergyConsumeService.insertTmpEnergyConsume(newTmpConsume);
		}
		return total;
	}

	/**
	 * 模拟生产节电月数据
	 * 
	 * @param yearMonth
	 */
	protected double mockPrdPowerSaveByMonth(String yearMonth) {
		double total = 0;
		String fullDate = yearMonth + "-01";
		TmpEnergyConsume tmpConsume = new TmpEnergyConsume();
		tmpConsume.setMonth(yearMonth);
		tmpConsume.setCategory("1");
		tmpConsume.setType("1");
		tmpConsume.setItemCategory("3");
		List<TmpEnergyConsume> prdPwSaveList = tmpEnergyConsumeService.selectTmpEnergyConsumeList(tmpConsume);
		if (prdPwSaveList != null && !prdPwSaveList.isEmpty()) {
			TmpEnergyConsume monthSavePw = prdPwSaveList.get(0);
			total = monthSavePw.getEnergy();
		} else {
			Date first = DateUtil.beginOfMonth(DateUtils.parseDate(fullDate));
			Date last = DateUtil.endOfMonth(DateUtils.parseDate(fullDate));
			int year = DateUtil.year(first);
			long days = DateUtil.betweenDay(first, last, true) + 1;
			double ratio = RandomUtil.randomDouble(0.5, 1, 1, RoundingMode.FLOOR);
			double savePw = configEmissionChangeService.getProductPowerSaveAmount((int) days);
			total = savePw * ratio;
			// 新增一条临时生产节电量数据
			TmpEnergyConsume newTmpConsume = new TmpEnergyConsume();
			newTmpConsume.setYear(String.valueOf(year));
			newTmpConsume.setMonth(yearMonth);
			newTmpConsume.setCategory("1");
			newTmpConsume.setType("1");
			newTmpConsume.setItemCategory("3");
			newTmpConsume.setEnergy(total);
			tmpEnergyConsumeService.insertTmpEnergyConsume(newTmpConsume);
		}
		return total;
	}

	/**
	 * 模拟办公节电月数据
	 * 
	 * @param yearMonth
	 */
	protected double mockOfficePowerSaveByMonth(String yearMonth) {
		double total = 0;
		String fullDate = yearMonth + "-01";
		TmpEnergyConsume tmpConsume = new TmpEnergyConsume();
		tmpConsume.setMonth(yearMonth);
		tmpConsume.setCategory("1");
		tmpConsume.setType("1");
		tmpConsume.setItemCategory("4");
		List<TmpEnergyConsume> officePwSaveList = tmpEnergyConsumeService.selectTmpEnergyConsumeList(tmpConsume);
		if (officePwSaveList != null && !officePwSaveList.isEmpty()) {
			TmpEnergyConsume monthSavePw = officePwSaveList.get(0);
			total = monthSavePw.getEnergy();
		} else {
			Date first = DateUtil.beginOfMonth(DateUtils.parseDate(fullDate));
			Date last = DateUtil.endOfMonth(DateUtils.parseDate(fullDate));
			int year = DateUtil.year(first);
			long days = DateUtil.betweenDay(first, last, true) + 1;
			double savePw = configEmissionChangeService.getOfficePowerSaveAmount((int) days);
			double ratio = RandomUtil.randomDouble(0.5, 1, 1, RoundingMode.FLOOR);
			total = savePw * ratio;
			// 新增一条临时办公节电量数据
			TmpEnergyConsume newTmpConsume = new TmpEnergyConsume();
			newTmpConsume.setYear(String.valueOf(year));
			newTmpConsume.setMonth(yearMonth);
			newTmpConsume.setCategory("1");
			newTmpConsume.setType("1");
			newTmpConsume.setItemCategory("4");
			newTmpConsume.setEnergy(total);
			tmpEnergyConsumeService.insertTmpEnergyConsume(newTmpConsume);
		}
		return total;
	}

	protected double mockOfficePowerSaveByYear(String year) {
		double total = 0;
		for (int i = 1; i < 13; i++) {
			String month = i < 10 ? ("0" + i) : (i + "");
			double consume = mockOfficePowerSaveByMonth(year + "-" + month);
			total = total + consume;
			// 只统计到本年月数据
			if (Integer.parseInt(year) == DateUtil.year(new Date()) && i == DateUtil.month(new Date())) {
				break;
			}
		}
		return total;
	}

	protected double mockPrdPowerSaveByYear(String year) {
		double total = 0;
		for (int i = 1; i < 13; i++) {
			String month = i < 10 ? ("0" + i) : (i + "");
			double consume = mockPrdPowerSaveByMonth(year + "-" + month);
			total = total + consume;
			// 只统计到本年月数据
			if (Integer.parseInt(year) == DateUtil.year(new Date()) && i == DateUtil.month(new Date())) {
				break;
			}
		}
		return total;
	}

	protected double mockDaysEmission(int days, double monthEmission, String itemCategory) {
		double mockConsume = 0;
		TmpEnergyConsume tmpConsume = new TmpEnergyConsume();
		tmpConsume.setDay(DateUtil.today());
		tmpConsume.setCategory("1");
		tmpConsume.setType("0");
		tmpConsume.setItemCategory(itemCategory);
		List<TmpEnergyConsume> emissionList = tmpEnergyConsumeService.selectTmpEnergyConsumeList(tmpConsume);
		if (emissionList != null && !emissionList.isEmpty()) {
			TmpEnergyConsume emission = emissionList.get(0);
			mockConsume = emission.getEnergy();
		} else {
			double ratio = RandomUtil.randomDouble(0.5, 1, 2, RoundingMode.FLOOR);
			mockConsume = NumberUtil.round((monthEmission / days) * ratio, 2).doubleValue();
			// 新增一条临时碳排放数据
			TmpEnergyConsume newTmpConsume = new TmpEnergyConsume();
			newTmpConsume.setDay(DateUtil.today());
			newTmpConsume.setCategory("1");
			newTmpConsume.setType("0");
			newTmpConsume.setItemCategory(itemCategory);
			newTmpConsume.setEnergy(mockConsume);
			tmpEnergyConsumeService.insertTmpEnergyConsume(newTmpConsume);
		}
		return mockConsume;
	}
}