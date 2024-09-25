package com.neu.generator.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neu.common.annotation.Log;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.domain.entity.SysDictData;
import com.neu.common.core.page.TableDataInfo;
import com.neu.common.enums.BusinessType;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.generator.domain.SysComQuery;
import com.neu.generator.service.ISysComQueryService;

/**
 * 通用查询Controller
 * 
 * @author neusoft
 * @date 2021-08-18
 */
@RestController
@RequestMapping("/query/comQuery")
public class SysComQueryController extends BaseController {
	@Autowired
	private ISysComQueryService sysComQueryService;

	/**
	 * 查询通用查询列表
	 */
	@PreAuthorize("@ss.hasPermi('query:comQuery:list')")
	@GetMapping("/list")
	public TableDataInfo list(SysComQuery sysComQuery) {
		startPage();
		List<SysComQuery> list = sysComQueryService.selectSysComQueryList(sysComQuery);
		return getDataTable(list);
	}

	/**
	 * 测试业务数据
	 * 
	 * @param sysComQuery
	 * @return
	 */
	@PostMapping("/test")
	public AjaxResult testBizData(@RequestBody SysComQuery sysComQuery) {
		List<SysDictData> list = sysComQueryService.selectQueryData(sysComQuery);
		return AjaxResult.success(list);
	}

	/**
	 * 查询业务数据
	 * 
	 * @param code
	 * @return
	 */
	@GetMapping("/data/{code}")
	public AjaxResult queryBizData(@PathVariable String code,Map<String, Object> params) {
		SysComQuery sysComQuery = new SysComQuery();
		sysComQuery.setCode(code);
		List<SysComQuery> queryList = sysComQueryService.selectSysComQueryList(sysComQuery);
		if (queryList == null || queryList.isEmpty()) {
			return AjaxResult.success();
		}
		SysComQuery query = queryList.get(0);
		query.getParams().putAll(params);
		List<SysDictData> list = sysComQueryService.selectQueryData(query);
		return AjaxResult.success(list);
	}

	/**
	 * 检查code唯一性
	 * 
	 * @param code
	 * @return
	 */
	@GetMapping("/check/{code}")
	public AjaxResult checkCode(@PathVariable String code) {
		SysComQuery sysComQuery = new SysComQuery();
		sysComQuery.setCode(code);
		List<SysComQuery> queryList = sysComQueryService.selectSysComQueryList(sysComQuery);
		if (queryList == null || queryList.isEmpty()) {
			return AjaxResult.success("", "1");
		} else {
			return AjaxResult.success("", "0");
		}
	}

	/**
	 * 导出通用查询列表
	 */
	@PreAuthorize("@ss.hasPermi('query:comQuery:export')")
	@Log(title = "通用查询", businessType = BusinessType.EXPORT)
	@GetMapping("/export")
	public AjaxResult export(SysComQuery sysComQuery) {
		List<SysComQuery> list = sysComQueryService.selectSysComQueryList(sysComQuery);
		ExcelUtil<SysComQuery> util = new ExcelUtil<SysComQuery>(SysComQuery.class);
		return util.exportExcel(list, "comQuery");
	}

	/**
	 * 获取通用查询详细信息
	 */
	@PreAuthorize("@ss.hasPermi('query:comQuery:query')")
	@GetMapping(value = "/{id}")
	public AjaxResult getInfo(@PathVariable("id") Long id) {
		return AjaxResult.success(sysComQueryService.selectSysComQueryById(id));
	}

	/**
	 * 新增通用查询
	 */
	@PreAuthorize("@ss.hasPermi('query:comQuery:add')")
	@Log(title = "通用查询", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody SysComQuery sysComQuery) {
		return toAjax(sysComQueryService.insertSysComQuery(sysComQuery));
	}

	/**
	 * 修改通用查询
	 */
	@PreAuthorize("@ss.hasPermi('query:comQuery:edit')")
	@Log(title = "通用查询", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody SysComQuery sysComQuery) {
		return toAjax(sysComQueryService.updateSysComQuery(sysComQuery));
	}

	/**
	 * 删除通用查询
	 */
	@PreAuthorize("@ss.hasPermi('query:comQuery:remove')")
	@Log(title = "通用查询", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public AjaxResult remove(@PathVariable Long[] ids) {
		return toAjax(sysComQueryService.deleteSysComQueryByIds(ids));
	}
}
