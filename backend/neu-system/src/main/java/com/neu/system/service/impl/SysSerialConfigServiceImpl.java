package com.neu.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neu.common.enums.SerialNoPrefix;
import com.neu.common.exception.CustomException;
import com.neu.common.utils.DateUtils;
import com.neu.system.domain.SysSerialConfig;
import com.neu.system.mapper.SysSerialConfigMapper;
import com.neu.system.service.ISysSerialConfigService;

import cn.hutool.core.util.StrUtil;

/**
 * 流水号配置Service业务层处理
 * 
 * @author neusoft
 * @date 2022-06-23
 */
@Service
public class SysSerialConfigServiceImpl implements ISysSerialConfigService {

	private static final Object LOCK = new Object();

	@Autowired
	private SysSerialConfigMapper sysSerialConfigMapper;

	/**
	 * 查询流水号配置
	 * 
	 * @param id
	 *            流水号配置ID
	 * @return 流水号配置
	 */
	@Override
	public SysSerialConfig selectSysSerialConfigById(Long id) {
		return sysSerialConfigMapper.selectSysSerialConfigById(id);
	}

	/**
	 * 查询流水号配置列表
	 * 
	 * @param sysSerialConfig
	 *            流水号配置
	 * @return 流水号配置
	 */
	@Override
	public List<SysSerialConfig> selectSysSerialConfigList(SysSerialConfig sysSerialConfig) {
		return sysSerialConfigMapper.selectSysSerialConfigList(sysSerialConfig);
	}

	/**
	 * 新增流水号配置
	 * 
	 * @param sysSerialConfig
	 *            流水号配置
	 * @return 结果
	 */
	@Override
	public int insertSysSerialConfig(SysSerialConfig sysSerialConfig) {
		return sysSerialConfigMapper.insertSysSerialConfig(sysSerialConfig);
	}

	/**
	 * 修改流水号配置
	 * 
	 * @param sysSerialConfig
	 *            流水号配置
	 * @return 结果
	 */
	@Override
	public int updateSysSerialConfig(SysSerialConfig sysSerialConfig) {
		return sysSerialConfigMapper.updateSysSerialConfig(sysSerialConfig);
	}

	/**
	 * 批量删除流水号配置
	 * 
	 * @param ids
	 *            需要删除的流水号配置ID
	 * @return 结果
	 */
	@Override
	public int deleteSysSerialConfigByIds(Long[] ids) {
		return sysSerialConfigMapper.deleteSysSerialConfigByIds(ids);
	}

	/**
	 * 删除流水号配置信息
	 * 
	 * @param id
	 *            流水号配置ID
	 * @return 结果
	 */
	@Override
	public int deleteSysSerialConfigById(Long id) {
		return sysSerialConfigMapper.deleteSysSerialConfigById(id);
	}

	@Transactional
	@Override
	public String getSerialNo(SerialNoPrefix prefix) {
		SysSerialConfig sysSerialConfig = new SysSerialConfig();
		sysSerialConfig.setPrefix(prefix.name());
		long newNo = 0;
		String pre = null;
		synchronized (LOCK) {
			List<SysSerialConfig> configList = sysSerialConfigMapper.selectSysSerialConfigList(sysSerialConfig);
			if (configList == null || configList.isEmpty()) {
				throw new CustomException("获取流水号失败：未配置流水号规则");
			}
			SysSerialConfig config = configList.get(0);
			newNo = config.getMaxNo() + 1;
			pre = config.getPrefix();
			config.setMaxNo(newNo);
			sysSerialConfigMapper.updateSysSerialConfig(config);
		}
		String serialNo = pre + DateUtils.dateTimeNow("yyyyMMdd") + StrUtil.padPre(String.valueOf(newNo), 6, "0");
		return serialNo;
	}
}
