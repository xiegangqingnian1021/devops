package com.neu.carbon.report.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.neu.carbon.footprint.domain.VMesProductPowerDetail;
import com.neu.carbon.footprint.service.ICfConfigNeutralService;
import com.neu.carbon.footprint.service.IVMesProductPowerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.common.annotation.Log;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.page.TableDataInfo;
import com.neu.common.enums.BusinessType;
import com.neu.common.utils.DateUtils;
import com.neu.common.utils.poi.ExcelUtil;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 生产能耗报表Controller
 * 
 * @author neuedu
 * @date 2022-07-31
 */
@Api(tags = { "生产能耗报表" })
@RestController
@RequestMapping("/carbonReport/productPowerReport")
public class VMesProductPowerDetailController extends BaseReportController {
	@Autowired
	private IVMesProductPowerDetailService vMesProductPowerDetailService;
	@Autowired
	private ICfConfigNeutralService cfConfigNeutralService;

	/**
	 * 查询产品能耗报表
	 * 
	 * @param vMesProductPowerDetail
	 * @return
	 */
	@GetMapping("/list")
	@ApiOperation("查询产品能耗报表")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "total", value = "总记录数"),
			@DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
			@DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = VMesProductPowerDetail.class),
			@DynamicParameter(name = "msg", value = "返回消息内容") })
	public TableDataInfo list(VMesProductPowerDetail vMesProductPowerDetail) {
		startPage();
		List<VMesProductPowerDetail> list = vMesProductPowerDetailService
				.selectVMesProductPowerDetailList(vMesProductPowerDetail);
		// 获取碳中和耗电计算比例
		double ratio = cfConfigNeutralService.getElectricCo2();
		list.stream().forEach(power -> {
			try {
				// 单位碳排放
				double carbon = power.getPowerConsume() * ratio;
				// 计算碳排放总量
				double totalCarbonEmission = power.getTotalPowerConsume() * ratio;
				power.setCarbonEmission(carbon);
				power.setTotalCarbonEmission(totalCarbonEmission);
			}catch (Exception ex) {
				logger.error(ex.getMessage(),ex);
			}

		});
		return getDataTable(list);
	}

	/**
	 * 查询生产能耗月度报表
	 */
	@GetMapping("/month")
	@ApiOperation("查询生产能耗月度报表")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "total", value = "总记录数"),
			@DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
			@DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = VMesProductPowerDetail.class),
			@DynamicParameter(name = "msg", value = "返回消息内容") })
	public TableDataInfo reportByMonth(VMesProductPowerDetail vMesProductPowerDetail) {
		startPage();

		Object end = vMesProductPowerDetail.getParams().get("endProductDate");
		Object start = vMesProductPowerDetail.getParams().get("beginProductDate");
		Date now = DateUtil.date();
		if (StrUtil.isEmptyIfStr(start)) {
			// 默认查询本月第一天
			Date first = DateUtil.beginOfMonth(now);
			vMesProductPowerDetail.getParams().put("beginProductDate", DateUtils.dateTime(first));
		}
		if (!StrUtil.isEmptyIfStr(end)) {
			// 将查询条件的结束月份转化为该月的最后一天
			Date date = DateUtils.parseDate(end);
			if (now.compareTo(date) < 0) {
				date = now;
			}
			Date last = DateUtil.endOfMonth(date);
			vMesProductPowerDetail.getParams().put("endProductDate", DateUtils.dateTime(last));
		} else {
			// 默认查询本月最后一天
			Date last = DateUtil.endOfMonth(now);
			vMesProductPowerDetail.getParams().put("endProductDate", DateUtils.dateTime(last));
		}
		List<VMesProductPowerDetail> list = vMesProductPowerDetailService
				.selectProductPowerReportByMonth(vMesProductPowerDetail);
		// 获取碳中和耗电计算比例
		double pwRatio = cfConfigNeutralService.getElectricCo2();
		double waterRatio = cfConfigNeutralService.getWaterCo2();
		list.stream().forEach(power -> {
			String yearMonth = power.getProductMonth();
			// 获取该月份生产节电量
			double prdSave = mockPrdPowerSaveByMonth(yearMonth);
			power.setTotalPrdPowerSave(prdSave);
			// 获取该月份办公节电量
			double officeSave = mockOfficePowerSaveByMonth(yearMonth);
			power.setTotalOfficePowerSave(officeSave);

			// 获取生产用水月数据
			double totalWaterConsume = this.mockPrdWaterConsumeByMonth(yearMonth);
			double totalWaterEmission = totalWaterConsume * waterRatio;
			power.setTotalWaterConsume(totalWaterConsume);

			// 获取办公用水月数据
			double totalOfficeWaterConsume = this.mockOfficeWaterConsumeByMonth(yearMonth);
			double totalOfficeWaterEmission = totalOfficeWaterConsume * waterRatio;
			power.setTotalOfficeWaterConsume(totalOfficeWaterConsume);

			// 获取办公用电月数据
			double totalOfficePowerConsume = this.mockOfficePowerConsumeByMonth(yearMonth);
			double totalOfficePowerEmission = totalOfficePowerConsume * pwRatio;
			power.setTotalOfficePowerConsume(totalOfficePowerConsume);

			// 计算碳排放总量
			double prdPowerConsume = power.getTotalPowerConsume() == null ? 0 : power.getTotalPowerConsume();
			double totalCarbonEmission = prdPowerConsume * pwRatio;
			power.setTotalCarbonEmission(
					totalCarbonEmission + totalWaterEmission + totalOfficeWaterEmission + totalOfficePowerEmission);
			// 计算减少碳排放总量
			double totalCarbonSave = (power.getTotalPrdPowerSave() + power.getTotalOfficePowerSave()) * pwRatio;
			power.setTotalCarbonSave(totalCarbonSave);
		});
		return getDataTable(list);
	}

	/**
	 * 查询生产能耗年度报表
	 */
	@GetMapping("/year")
	@ApiOperation("查询生产能耗年度报表")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "total", value = "总记录数"),
			@DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
			@DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = VMesProductPowerDetail.class),
			@DynamicParameter(name = "msg", value = "返回消息内容") })
	public TableDataInfo reportByYear(VMesProductPowerDetail vMesProductPowerDetail) {
		Object end = vMesProductPowerDetail.getParams().get("endProductDate");
		Object start = vMesProductPowerDetail.getParams().get("beginProductDate");
		Date now = DateUtil.date();
		if (StrUtil.isEmptyIfStr(start)) {
			// 默认查询今年第一天数据
			Date first = DateUtil.beginOfYear(new Date());
			vMesProductPowerDetail.getParams().put("beginProductDate", DateUtils.dateTime(first));
		}
		if (!StrUtil.isEmptyIfStr(end)) {
			// 将查询条件的结束月份转化为该年的最后一天
			Date date = DateUtils.parseDate(end);
			if (now.compareTo(date) < 0) {
				date = now;
			}
			Date last = DateUtil.endOfYear(date);
			vMesProductPowerDetail.getParams().put("endProductDate", DateUtils.dateTime(last));
		} else {
			// 默认查询今年最后一天数据
			Date last = DateUtil.endOfYear(new Date());
			vMesProductPowerDetail.getParams().put("endProductDate", DateUtils.dateTime(last));
		}
		// 获取统计的月份
		List<VMesProductPowerDetail> monthList = vMesProductPowerDetailService
				.selectReportMonth(vMesProductPowerDetail);
		List<String> reportMonthList = monthList.stream().map(month -> month.getProductMonth())
				.collect(Collectors.toList());
		// 产生模拟月份数据
		reportMonthList.stream().forEach(month -> {
			mockPrdPowerSaveByMonth(month);
			mockOfficePowerSaveByMonth(month);
		});
		startPage();
		List<VMesProductPowerDetail> list = vMesProductPowerDetailService
				.selectProductPowerReportByYear(vMesProductPowerDetail);
		// 获取碳中和耗电计算比例
		double pwRatio = cfConfigNeutralService.getElectricCo2();
		double waterRatio = cfConfigNeutralService.getWaterCo2();
		list.stream().forEach(power -> {
			String year = power.getProductYear();
			// 获取该年生产节电数据
			double prdSaveTotal = mockPrdPowerSaveByYear(year);
			power.setTotalPrdPowerSave(prdSaveTotal);

			// 获取该年办公节电数据
			double officeSaveTotal = mockOfficePowerSaveByYear(year);
			power.setTotalOfficePowerSave(officeSaveTotal);

			// 获取生产用水年数据
			double totalWaterConsume = this.mockPrdWaterConsumeByYear(year);
			double totalWaterEmission = totalWaterConsume * waterRatio;
			power.setTotalWaterConsume(totalWaterConsume);

			// 获取办公用水年数据
			double totalOfficeWaterConsume = this.mockOfficeWaterConsumeByYear(year);
			double totalOfficeWaterEmission = totalOfficeWaterConsume * waterRatio;
			power.setTotalOfficeWaterConsume(totalOfficeWaterConsume);

			// 获取办公用电年数据
			double totalOfficePowerConsume = this.mockOfficePowerConsumeByYear(year);
			double totalOfficePowerEmission = totalOfficePowerConsume * pwRatio;
			power.setTotalOfficePowerConsume(totalOfficePowerConsume);

			// 计算碳排放总量
			double prdPowerConsume = power.getTotalPowerConsume() == null ? 0 : power.getTotalPowerConsume();
			double totalCarbonEmission = prdPowerConsume * pwRatio;
			power.setTotalCarbonEmission(
					totalCarbonEmission + totalWaterEmission + totalOfficeWaterEmission + totalOfficePowerEmission);
			// 计算减少碳排放总量
			double totalCarbonSave = (power.getTotalPrdPowerSave() + power.getTotalOfficePowerSave()) * pwRatio;
			power.setTotalCarbonSave(totalCarbonSave);
		});
		return getDataTable(list);
	}

	/**
	 * 查询生产能耗日报表
	 */
	@GetMapping("/day")
	@ApiOperation("查询生产能耗日报表")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "total", value = "总记录数"),
			@DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
			@DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = VMesProductPowerDetail.class),
			@DynamicParameter(name = "msg", value = "返回消息内容") })
	public TableDataInfo reportByDay(VMesProductPowerDetail vMesProductPowerDetail) {
		startPage();
		// 默认查询今天数据
		if (vMesProductPowerDetail.getProductDate() == null) {
			Date today = DateUtil.parse(DateUtil.today());
			vMesProductPowerDetail.setProductDate(today);
		}
		List<VMesProductPowerDetail> list = vMesProductPowerDetailService
				.selectProductPowerReportByDay(vMesProductPowerDetail);
		// 获取碳中和耗电计算比例
		double pwRatio = cfConfigNeutralService.getElectricCo2();
		double waterRatio = cfConfigNeutralService.getWaterCo2();
		list.stream().forEach(power -> {
			int days = DateUtil.dayOfMonth(DateUtil.endOfMonth(power.getProductDate()));
			String yearMonth = DateUtil.format(power.getProductDate(), "yyyy-MM");
			// 获取生产用水月数据
			double totalWaterConsume = this.mockPrdWaterConsumeByMonth(yearMonth);
			double totalWaterEmission = totalWaterConsume * waterRatio;
			totalWaterEmission = mockDaysEmission(days, totalWaterEmission, "3");
			power.setTotalWaterConsume(totalWaterConsume);

			// 获取办公用水月数据
			double totalOfficeWaterConsume = this.mockOfficeWaterConsumeByMonth(yearMonth);
			double totalOfficeWaterEmission = totalOfficeWaterConsume * waterRatio;
			totalOfficeWaterEmission = mockDaysEmission(days, totalOfficeWaterEmission, "4");
			power.setTotalOfficeWaterConsume(totalOfficeWaterConsume);

			// 获取办公用电月数据
			double totalOfficePowerConsume = this.mockOfficePowerConsumeByMonth(yearMonth);
			double totalOfficePowerEmission = totalOfficePowerConsume * pwRatio;
			totalOfficePowerEmission = mockDaysEmission(days, totalOfficePowerEmission, "2");
			power.setTotalOfficePowerConsume(totalOfficePowerConsume);

			// 计算碳排放总量
			double prdPowerConsume = power.getTotalPowerConsume() == null ? 0 : power.getTotalPowerConsume();
			double totalCarbonEmission = prdPowerConsume * pwRatio;
			power.setTotalCarbonEmission(
					totalCarbonEmission + totalWaterEmission + totalOfficeWaterEmission + totalOfficePowerEmission);
		});
		return getDataTable(list);
	}

	/**
	 * 导出生产能耗报表列表
	 */
	@ApiOperation("导出生产能耗报表列表")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
			@DynamicParameter(name = "msg", value = "文件名") })
	@Log(title = "生产能耗报表", businessType = BusinessType.EXPORT)
	@GetMapping("/export")
	public AjaxResult export(VMesProductPowerDetail vMesProductPowerDetail) {
		List<VMesProductPowerDetail> list = vMesProductPowerDetailService
				.selectVMesProductPowerDetailList(vMesProductPowerDetail);
		ExcelUtil<VMesProductPowerDetail> util = new ExcelUtil<VMesProductPowerDetail>(VMesProductPowerDetail.class);
		return util.exportExcel(list, "productPowerReport");
	}

	/**
	 * 获取生产能耗报表详细信息
	 */
	@ApiOperation("获取生产能耗报表详细信息")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
			@DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = VMesProductPowerDetail.class),
			@DynamicParameter(name = "msg", value = "返回消息内容") })
	@GetMapping(value = "/{planId}")
	public AjaxResult getInfo(@PathVariable("planId") Long planId) {
		return AjaxResult.success(vMesProductPowerDetailService.selectVMesProductPowerDetailById(planId));
	}
}
