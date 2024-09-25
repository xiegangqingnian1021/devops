package com.neu.system.service;

import java.util.List;

import com.neu.common.enums.SerialNoPrefix;
import com.neu.system.domain.SysSerialConfig;

/**
 * 流水号配置Service接口
 * 
 * @author neusoft
 * @date 2022-06-23
 */
public interface ISysSerialConfigService {
	/**
	 * 查询流水号配置
	 * 
	 * @param id
	 *            流水号配置ID
	 * @return 流水号配置
	 */
	public SysSerialConfig selectSysSerialConfigById(Long id);

	/**
	 * 查询流水号配置列表
	 * 
	 * @param sysSerialConfig
	 *            流水号配置
	 * @return 流水号配置集合
	 */
	public List<SysSerialConfig> selectSysSerialConfigList(SysSerialConfig sysSerialConfig);

	/**
	 * 新增流水号配置
	 * 
	 * @param sysSerialConfig
	 *            流水号配置
	 * @return 结果
	 */
	public int insertSysSerialConfig(SysSerialConfig sysSerialConfig);

	/**
	 * 修改流水号配置
	 * 
	 * @param sysSerialConfig
	 *            流水号配置
	 * @return 结果
	 */
	public int updateSysSerialConfig(SysSerialConfig sysSerialConfig);

	/**
	 * 批量删除流水号配置
	 * 
	 * @param ids
	 *            需要删除的流水号配置ID
	 * @return 结果
	 */
	public int deleteSysSerialConfigByIds(Long[] ids);

	/**
	 * 删除流水号配置信息
	 * 
	 * @param id
	 *            流水号配置ID
	 * @return 结果
	 */
	public int deleteSysSerialConfigById(Long id);

	/**
	 * 获取流水号
	 * 
	 * @param prefix
	 * @return
	 */
	public String getSerialNo(SerialNoPrefix prefix);
}
