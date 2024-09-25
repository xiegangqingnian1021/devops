package com.neu.carbon.report.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.neu.carbon.footprint.domain.VMesProductPowerDetail;
import com.neu.carbon.footprint.service.ICfConfigNeutralService;
import com.neu.carbon.footprint.service.IVMesProductPowerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.utils.DateUtils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "数据中心能耗监控图表" })
@RestController
@RequestMapping("/carbonReport/emissionChart")
public class MonitorChartReportController extends BaseReportController {
	@Autowired
	private IVMesProductPowerDetailService vMesProductPowerDetailService;
	@Autowired
	private ICfConfigNeutralService cfConfigNeutralService;

	@GetMapping("/overall")
	@ApiOperation("查询综合能耗图表")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "total", value = "总记录数"),
			@DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
			@DynamicParameter(name = "data", value = "返回业务数据（Map类型）", dataTypeClass = VMesProductPowerDetail.class),
			@DynamicParameter(name = "msg", value = "返回消息内容") })
	public AjaxResult overall() {
		// 获取前5年的统计数据
		VMesProductPowerDetail vMesProductPowerDetail = new VMesProductPowerDetail();
		// 5年前第一天数据
		Date fiveYear = DateUtil.offset(new Date(), DateField.YEAR, -5);
		Date first = DateUtil.beginOfYear(fiveYear);
		vMesProductPowerDetail.getParams().put("beginProductDate", DateUtils.dateTime(first));
		// 今年最后一天数据
		Date last = DateUtil.endOfYear(new Date());
		vMesProductPowerDetail.getParams().put("endProductDate", DateUtils.dateTime(last));
		// 构造图表数据结构
		Map<String, VMesProductPowerDetail> report = new LinkedHashMap<>();
		int thisYear = DateUtil.year(new Date());
		for (int i = 0; i < 5; i++) {
			report.put(String.valueOf(thisYear - i), new VMesProductPowerDetail());
		}
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
			
			report.put(year, power);
		});

		return AjaxResult.success(report);
	}

	@GetMapping("/percent")
	@ApiOperation("查询今年的能耗占比图表")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "total", value = "总记录数"),
			@DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
			@DynamicParameter(name = "data", value = "返回业务数据（Map类型）", dataTypeClass = VMesProductPowerDetail.class),
			@DynamicParameter(name = "msg", value = "返回消息内容") })
	public AjaxResult percent() {
		// 获取今年的统计数据
		VMesProductPowerDetail vMesProductPowerDetail = new VMesProductPowerDetail();
		// 今年第一天数据
		Date first = DateUtil.beginOfYear(new Date());
		vMesProductPowerDetail.getParams().put("beginProductDate", DateUtils.dateTime(first));
		// 今年最后一天数据
		Date last = DateUtil.endOfYear(new Date());
		vMesProductPowerDetail.getParams().put("endProductDate", DateUtils.dateTime(last));
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

			// 计算用碳排放总量
			double totalEmission = power.getTotalCarbonEmission() == null ? 0 : power.getTotalCarbonEmission();
			power.setTotalCarbonEmission(
					totalEmission + totalWaterEmission + totalOfficeWaterEmission + totalOfficePowerEmission);

			// 计算减少碳排放总量
			double totalCarbonSave = (power.getTotalPrdPowerSave() + power.getTotalOfficePowerSave()) * pwRatio;
			power.setTotalCarbonSave(totalCarbonSave);

			// 计算碳排放总量
			double totalPower = power.getTotalPowerConsume() == null ? 0:power.getTotalPowerConsume();
			double totalCarbonEmission = totalPower * pwRatio;
			power.setTotalCarbonEmission(totalCarbonEmission);
		});
		return AjaxResult.success(list);
	}
}
