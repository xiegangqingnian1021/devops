package com.neu.carbon.footprint.service.impl;

import com.neu.carbon.footprint.domain.VMesProductPowerDetail;
import com.neu.carbon.footprint.mapper.VMesProductPowerDetailMapper;
import com.neu.carbon.footprint.service.IVMesProductPowerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * VIEWService业务层处理
 * 
 * @author neuedu
 * @date 2022-07-31
 */
@Service
public class VMesProductPowerDetailServiceImpl implements IVMesProductPowerDetailService 
{
    @Autowired
    private VMesProductPowerDetailMapper vMesProductPowerDetailMapper;

    /**
     * 查询VIEW
     * 
     * @param planId VIEWID
     * @return VIEW
     */
    @Override
    public VMesProductPowerDetail selectVMesProductPowerDetailById(Long planId)
    {
        return vMesProductPowerDetailMapper.selectVMesProductPowerDetailById(planId);
    }

    /**
     * 查询VIEW列表
     * 
     * @param vMesProductPowerDetail VIEW
     * @return VIEW
     */
    @Override
    public List<VMesProductPowerDetail> selectVMesProductPowerDetailList(VMesProductPowerDetail vMesProductPowerDetail)
    {
        return vMesProductPowerDetailMapper.selectVMesProductPowerDetailList(vMesProductPowerDetail);
    }

	@Override
	public List<VMesProductPowerDetail> selectProductPowerReportByMonth(VMesProductPowerDetail vMesProductPowerDetail) {
		return vMesProductPowerDetailMapper.selectProductPowerReportByMonth(vMesProductPowerDetail);
	}

	@Override
	public List<VMesProductPowerDetail> selectProductPowerReportByYear(VMesProductPowerDetail vMesProductPowerDetail) {
		return vMesProductPowerDetailMapper.selectProductPowerReportByYear(vMesProductPowerDetail);
	}

	@Override
	public List<VMesProductPowerDetail> selectReportMonth(VMesProductPowerDetail vMesProductPowerDetail) {
		return vMesProductPowerDetailMapper.selectReportMonth(vMesProductPowerDetail);
	}

	@Override
	public List<VMesProductPowerDetail> selectProductPowerReportByDay(VMesProductPowerDetail vMesProductPowerDetail) {
		return vMesProductPowerDetailMapper.selectProductPowerReportByDay(vMesProductPowerDetail);
	}
}
