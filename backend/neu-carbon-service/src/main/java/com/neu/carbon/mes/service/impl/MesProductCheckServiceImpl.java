package com.neu.carbon.mes.service.impl;

import com.neu.carbon.mes.domain.MesProductCheck;
import com.neu.carbon.mes.mapper.MesProductCheckMapper;
import com.neu.carbon.mes.service.IMesProductCheckService;
import com.neu.common.constant.UserConstants;
import com.neu.common.enums.SerialNoPrefix;
import com.neu.smty.utils.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 产品检验单Service业务层处理
 *
 * @author neuedu
 * @date 2022-07-12
 */
@Service
public class MesProductCheckServiceImpl implements IMesProductCheckService {
    @Autowired
    private MesProductCheckMapper mesProductCheckMapper;

    /**
     * 查询产品检验单
     *
     * @param id 产品检验单ID
     * @return 产品检验单
     */
    @Override
    public MesProductCheck selectMesProductCheckById(Long id) {
        return mesProductCheckMapper.selectMesProductCheckById(id);
    }

    /**
     * 查询产品检验单列表
     *
     * @param mesProductCheck 产品检验单
     * @return 产品检验单
     */
    @Override
    public List<MesProductCheck> selectMesProductCheckList(MesProductCheck mesProductCheck) {
        return mesProductCheckMapper.selectMesProductCheckList(mesProductCheck);
    }

    /**
     * 新增产品检验单
     *
     * @param mesProductCheck 产品检验单
     * @return 结果
     */
    @Override
    public int insertMesProductCheck(MesProductCheck mesProductCheck) {
        mesProductCheck.setSerialNo(CodeUtil.getSerialNo(SerialNoPrefix.CPJY));
        mesProductCheck.setCheckDate(new Date());
        return mesProductCheckMapper.insertMesProductCheck(mesProductCheck);
    }

    /**
     * 修改产品检验单
     *
     * @param mesProductCheck 产品检验单
     * @return 结果
     */
    @Override
    public int updateMesProductCheck(MesProductCheck mesProductCheck) {
        return mesProductCheckMapper.updateMesProductCheck(mesProductCheck);
    }

    /**
     * 批量删除产品检验单
     *
     * @param ids 需要删除的产品检验单ID
     * @return 结果
     */
    @Override
    public int deleteMesProductCheckByIds(Long[] ids) {
        return mesProductCheckMapper.deleteMesProductCheckByIds(ids);
    }

    /**
     * 删除产品检验单信息
     *
     * @param id 产品检验单ID
     * @return 结果
     */
    @Override
    public int deleteMesProductCheckById(Long id) {
        return mesProductCheckMapper.deleteMesProductCheckById(id);
    }

    @Override
    public List<MesProductCheck> selectQualifiedRateReport(MesProductCheck mesProductCheck) {

        List<MesProductCheck> list = mesProductCheckMapper.selectMesProductCheckList(mesProductCheck);
        for (MesProductCheck productCheck : list) {
            if (UserConstants.MES_CHECK_PASSED.equals(productCheck.getCheckResult())) {
                productCheck.setQualifiedRate(1D);
            } else {
                productCheck.setQualifiedRate(productCheck.getCheckQuantity() / productCheck.getProductQuantity());
            }
        }
        return list;
    }

}
