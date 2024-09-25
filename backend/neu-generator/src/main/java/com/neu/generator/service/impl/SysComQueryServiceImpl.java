package com.neu.generator.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.generator.mapper.SysComQueryMapper;
import com.neu.common.core.domain.entity.SysDictData;
import com.neu.common.utils.StringUtils;
import com.neu.generator.domain.SysComQuery;
import com.neu.generator.service.ISysComQueryService;

import lombok.extern.slf4j.Slf4j;

/**
 * 通用查询Service业务层处理
 * 
 * @author neusoft
 * @date 2021-08-18
 */
@Service
@Slf4j
public class SysComQueryServiceImpl implements ISysComQueryService {

	private static final String PT_LEFT = "#{";
	private static final String PT_RIGHT = "}";

	@Autowired
	private SysComQueryMapper sysComQueryMapper;

	/**
	 * 查询通用查询
	 * 
	 * @param id
	 *            通用查询ID
	 * @return 通用查询
	 */
	@Override
	public SysComQuery selectSysComQueryById(Long id) {
		return sysComQueryMapper.selectSysComQueryById(id);
	}

	/**
	 * 查询通用查询列表
	 * 
	 * @param sysComQuery
	 *            通用查询
	 * @return 通用查询
	 */
	@Override
	public List<SysComQuery> selectSysComQueryList(SysComQuery sysComQuery) {
		return sysComQueryMapper.selectSysComQueryList(sysComQuery);
	}

	/**
	 * 新增通用查询
	 * 
	 * @param sysComQuery
	 *            通用查询
	 * @return 结果
	 */
	@Override
	public int insertSysComQuery(SysComQuery sysComQuery) {
		return sysComQueryMapper.insertSysComQuery(sysComQuery);
	}

	/**
	 * 修改通用查询
	 * 
	 * @param sysComQuery
	 *            通用查询
	 * @return 结果
	 */
	@Override
	public int updateSysComQuery(SysComQuery sysComQuery) {
		return sysComQueryMapper.updateSysComQuery(sysComQuery);
	}

	/**
	 * 批量删除通用查询
	 * 
	 * @param ids
	 *            需要删除的通用查询ID
	 * @return 结果
	 */
	@Override
	public int deleteSysComQueryByIds(Long[] ids) {
		return sysComQueryMapper.deleteSysComQueryByIds(ids);
	}

	/**
	 * 删除通用查询信息
	 * 
	 * @param id
	 *            通用查询ID
	 * @return 结果
	 */
	@Override
	public int deleteSysComQueryById(Long id) {
		return sysComQueryMapper.deleteSysComQueryById(id);
	}

	@Override
	public List<SysDictData> selectQueryData(SysComQuery sysComQuery) {
		String sql = sysComQuery.getCustomSql();
		String ftSql = formatSql(sql, sysComQuery.getParams());
		sysComQuery.setCustomSql(ftSql);
		return sysComQueryMapper.selectQueryData(sysComQuery);
	}

	/**
	 * 替换sql参数
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	private String formatSql(String sql, Map<String, Object> params) {
		String ftSql = sql;
		for (String key : params.keySet()) {
			Object val = params.get(key);
			String pattern = PT_LEFT + key + PT_RIGHT;
			if (val instanceof String) {
				String place = "'" + val + "'";
				ftSql = StringUtils.replace(ftSql, pattern, place);
			}
			if (val instanceof Long || val instanceof Integer) {
				String place = String.valueOf(val);
				ftSql = StringUtils.replace(ftSql, pattern, place);
			} else {
				log.error("替换通用查询sql参数{}发生错误：目前只支持字符串类型和整形参数", key);
			}
		}
		return ftSql;
	}
}
