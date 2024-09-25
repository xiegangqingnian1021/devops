package com.neu.smty.api.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.page.TableDataInfo;
import com.neu.common.utils.StringUtils;
import com.neu.smty.api.base.ApiBaseController;
import com.neu.smty.domain.ComArea;
import com.neu.smty.domain.ComCity;
import com.neu.smty.service.IComAreaService;
import com.neu.smty.service.IComCityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "地理位置相关接口")
@RestController
@RequestMapping("/api/location")
public class LocationApi extends ApiBaseController {
	@Autowired
	private IComAreaService comAreaService;
	@Autowired
	private IComCityService comCityService;

	/**
	 * 查询区域信息列表
	 */
	@ApiOperation("查询城市区域列表")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "total", value = "总记录数"),
			@DynamicParameter(name = "code", value = "状态码"),
			@DynamicParameter(name = "rows", value = "列表数据（数组类型）", dataTypeClass = ComArea.class),
			@DynamicParameter(name = "msg", value = "消息内容") })
	@GetMapping("/area/list")
	public TableDataInfo listArea(ComArea comArea) {
		startPage();
		List<ComArea> list = comAreaService.selectComAreaList(comArea);
		return getDataTable(list);
	}

	@ApiOperation("获取当前人地理位置信息")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "code", value = "状态码"),
			@DynamicParameter(name = "data", value = "位置信息"), @DynamicParameter(name = "msg", value = "消息内容") })
	@GetMapping("/gps")
	public AjaxResult getLocation() {
		Map<String, String> location = new HashedMap<>();
		location.put("location", "大连市");
		return AjaxResult.success(location);
	}

	@ApiOperation("按开头字母顺序获取城市树")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "total", value = "总记录数"),
			@DynamicParameter(name = "code", value = "状态码"), @DynamicParameter(name = "data", value = "城市树"),
			@DynamicParameter(name = "msg", value = "消息内容") })
	@GetMapping("/city/tree")
	public AjaxResult getCityTree() {
		startPage("letter asc");
		ComCity comCity = new ComCity();
		List<ComCity> cityList = comCityService.selectComCityList(comCity);
		Map<String, List<ComCity>> tree = cityList.stream().filter(city -> !StringUtils.isBlank(city.getLetter()))
				.collect(Collectors.groupingBy(ComCity::getLetter));
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		for (String letter : tree.keySet()) {
			Map<String, Object> kv = new HashedMap<>();
			kv.put("letter", letter);
			kv.put("data", tree.get(letter));
			treeList.add(kv);
		}
		return AjaxResult.success(treeList);
	}
}
