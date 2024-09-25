package com.neu.carbon.report.controller;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neu.carbon.footprint.domain.VMesEquipmentPowerDetail;
import com.neu.carbon.footprint.service.ICfConfigNeutralService;
import com.neu.carbon.footprint.service.IVMesEquipmentPowerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.common.annotation.Log;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.page.TableDataInfo;
import com.neu.common.enums.BusinessType;
import com.neu.common.utils.poi.ExcelUtil;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * VIEWController
 * 
 * @author neuedu
 * @date 2022-07-31
 */
@Api(tags = { "设备能耗数据采集" })
@RestController
@RequestMapping("/carbonReport/equipmentPowerReport")
public class VMesEquipmentPowerDetailController extends BaseController {
	@Autowired
	private IVMesEquipmentPowerDetailService vMesEquipmentPowerDetailService;
	@Autowired
	private ICfConfigNeutralService cfConfigNeutralService;

	/**
	 * 查询设备能耗汇总报表
	 */
	@GetMapping("/list")
	@ApiOperation("查询设备能耗数据采集报表")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "total", value = "总记录数"),
			@DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
			@DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = VMesEquipmentPowerDetail.class),
			@DynamicParameter(name = "msg", value = "返回消息内容") })
	public TableDataInfo list(VMesEquipmentPowerDetail vMesEquipmentPowerDetail) {
		startPage();
		List<VMesEquipmentPowerDetail> list = vMesEquipmentPowerDetailService
				.selectVMesEquipmentPowerDetailList(vMesEquipmentPowerDetail);
		// 获取碳中和耗电计算比例
		double ratio = cfConfigNeutralService.getElectricCo2();
		// 计算单位碳排放和总碳排放
		list.stream().forEach(power -> {
			double consume = power.getPowerConsume() == null ? 0 : power.getPowerConsume();
			double totalConsume = power.getTotalPowerConsume() == null ? 0 : power.getTotalPowerConsume();
			// 单位碳排放
			double carbon = consume * ratio;
			// 总碳排放
			double totalCarbon = totalConsume * ratio;
			power.setCarbonEmission(carbon);
			power.setTotalCarbonEmission(totalCarbon);
		});
		return getDataTable(list);
	}

	/**
	 * 查询设备日能耗报表
	 */
	@GetMapping("/day")
	@ApiOperation("查询设备能耗汇总报表")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "total", value = "总记录数"),
			@DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
			@DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = VMesEquipmentPowerDetail.class),
			@DynamicParameter(name = "msg", value = "返回消息内容") })
	public TableDataInfo reportByDay(VMesEquipmentPowerDetail vMesEquipmentPowerDetail) {
		startPage();
		List<VMesEquipmentPowerDetail> list = vMesEquipmentPowerDetailService
				.selectEquipmentPowerReportByDay(vMesEquipmentPowerDetail);
		// 获取碳中和耗电计算比例
		double ratio = cfConfigNeutralService.getElectricCo2();
		// 计算单位碳排放和总碳排放
		list.stream().forEach(power -> {
			// 单位碳排放
			double carbon = power.getPowerConsume() == null ? 0 : power.getPowerConsume() * ratio;
			// 总碳排放
			double totalCarbon = power.getTotalPowerConsume() == null ? 0 : power.getTotalPowerConsume() * ratio;
			power.setCarbonEmission(carbon);
			power.setTotalCarbonEmission(totalCarbon);
		});
		return getDataTable(list);
	}

	/**
	 * 查询设备小时能耗报表
	 */
	@GetMapping("/hour")
	@ApiOperation("查询设备小时能耗报表")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "total", value = "总记录数"),
			@DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
			@DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = VMesEquipmentPowerDetail.class),
			@DynamicParameter(name = "msg", value = "返回消息内容") })
	public TableDataInfo reportByHour(VMesEquipmentPowerDetail vMesEquipmentPowerDetail) {
		List<VMesEquipmentPowerDetail> hourList = new ArrayList<VMesEquipmentPowerDetail>();
		Date date = vMesEquipmentPowerDetail.getProductDate();
		String today = DateUtil.today();
		Date now = DateUtil.parse(today);
		// 查询日期为空默认查询前一天数据
		if (date == null) {
			Date before = DateUtil.offset(DateUtil.parse(today), DateField.DAY_OF_MONTH, -1);
			vMesEquipmentPowerDetail.setProductDate(before);
		} else {
			// 当天以及未来数据不采集
			if (date.compareTo(now) >= 0) {
				return getDataTable(hourList);
			}
		}
		List<VMesEquipmentPowerDetail> list = vMesEquipmentPowerDetailService
				.selectEquipmentPowerReportByDay(vMesEquipmentPowerDetail);
		for (VMesEquipmentPowerDetail dayPower : list) {
			// 模拟小时能耗数据
			double totalPwConsume = dayPower.getTotalPowerConsume();
			for (int i = 0; i < 24; i++) {
				VMesEquipmentPowerDetail hourPower = new VMesEquipmentPowerDetail();
				BeanUtil.copyProperties(dayPower, hourPower);
				hourPower.setHour(String.valueOf(i + 1));
				double hourConsume = RandomUtil.randomDouble(0, totalPwConsume / 24, 2, RoundingMode.FLOOR);
				hourPower.setTotalPowerConsume(hourConsume);
				hourList.add(hourPower);
			}
		}
		// 获取碳中和耗电计算比例
		double ratio = cfConfigNeutralService.getElectricCo2();
		// 计算单位碳排放和总碳排放
		hourList.stream().forEach(power -> {
			// 单位碳排放
			double carbon = power.getPowerConsume() * ratio;
			// 总碳排放
			double totalCarbon = NumberUtil.round(power.getTotalPowerConsume() * ratio, 2).doubleValue();
			power.setCarbonEmission(carbon);
			power.setTotalCarbonEmission(totalCarbon);
		});
		return getDataTable(hourList);
	}

	/**
	 * 导出设备能耗数据采集报表
	 */
	@ApiOperation("导出设备能耗数据采集报表")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
			@DynamicParameter(name = "msg", value = "文件名") })
	@PreAuthorize("@ss.hasPermi('carbonReport:equipmentPowerReport:export')")
	@Log(title = "设备能耗数据采集", businessType = BusinessType.EXPORT)
	@GetMapping("/export")
	public AjaxResult export(VMesEquipmentPowerDetail vMesEquipmentPowerDetail) {
		List<VMesEquipmentPowerDetail> list = vMesEquipmentPowerDetailService
				.selectVMesEquipmentPowerDetailList(vMesEquipmentPowerDetail);
		// 获取碳中和耗电计算比例
		double ratio = cfConfigNeutralService.getElectricCo2();
		// 计算单位碳排放和总碳排放
		list.stream().forEach(power -> {
			// 单位碳排放
			double carbon = power.getPowerConsume() * ratio;
			// 总碳排放
			double totalCarbon = power.getTotalPowerConsume() * ratio;
			power.setCarbonEmission(carbon);
			power.setTotalCarbonEmission(totalCarbon);
		});
		ExcelUtil<VMesEquipmentPowerDetail> util = new ExcelUtil<VMesEquipmentPowerDetail>(
				VMesEquipmentPowerDetail.class);
		return util.exportExcel(list, "equipmentPowerReport");
	}

	/**
	 * 获取VIEW详细信息
	 */
	@ApiOperation("获取设备能耗数据采集详细信息")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
			@DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = VMesEquipmentPowerDetail.class),
			@DynamicParameter(name = "msg", value = "返回消息内容") })
	@GetMapping(value = "/{equipmentId}")
	public AjaxResult getInfo(@PathVariable("equipmentId") Long equipmentId) {
		return AjaxResult.success(vMesEquipmentPowerDetailService.selectVMesEquipmentPowerDetailById(equipmentId));
	}
}
