package com.neu.carbon.scm.service.impl;

import com.neu.carbon.scm.domain.VScmSalePlanDetail;
import com.neu.carbon.scm.mapper.VScmSalePlanDetailMapper;
import com.neu.carbon.scm.service.IVScmSalePlanDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 销售计划达成率报表Service业务层处理
 * 
 * @author neuedu
 * @date 2022-08-02
 */
@Service
public class VScmSalePlanDetailServiceImpl implements IVScmSalePlanDetailService 
{
    @Autowired
    private VScmSalePlanDetailMapper vScmSalePlanDetailMapper;

    /**
     * 查询销售计划达成率报表列表
     * 
     * @param vScmSalePlanDetail 销售计划达成率报表
     * @return 销售计划达成率报表
     */
    @Override
    public List<VScmSalePlanDetail> selectVScmSalePlanDetailList(VScmSalePlanDetail vScmSalePlanDetail)
    {
        return vScmSalePlanDetailMapper.selectVScmSalePlanDetailList(vScmSalePlanDetail);
    }

    /**
     * 按月统计计划销售额与实际销售额
     * @param month
     * @return
     */
    @Override
    public Map<String, BigDecimal> selectAchieveRateByMonth(String month) {
        return vScmSalePlanDetailMapper.selectAchieveRateByMonth(month);
    }


}
