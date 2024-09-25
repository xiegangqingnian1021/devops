package com.neu.carbon.mes.service;

import com.neu.carbon.mes.domain.MesProductCheck;

import java.util.List;

/**
 * 产品检验单Service接口
 * 
 * @author neuedu
 * @date 2022-07-12
 */
public interface IMesProductCheckService 
{
    /**
     * 查询产品检验单
     * 
     * @param id 产品检验单ID
     * @return 产品检验单
     */
    public MesProductCheck selectMesProductCheckById(Long id);

    /**
     * 查询产品检验单列表
     * 
     * @param mesProductCheck 产品检验单
     * @return 产品检验单集合
     */
    public List<MesProductCheck> selectMesProductCheckList(MesProductCheck mesProductCheck);

    /**
     * 新增产品检验单
     * 
     * @param mesProductCheck 产品检验单
     * @return 结果
     */
    public int insertMesProductCheck(MesProductCheck mesProductCheck);

    /**
     * 修改产品检验单
     * 
     * @param mesProductCheck 产品检验单
     * @return 结果
     */
    public int updateMesProductCheck(MesProductCheck mesProductCheck);

    /**
     * 批量删除产品检验单
     * 
     * @param ids 需要删除的产品检验单ID
     * @return 结果
     */
    public int deleteMesProductCheckByIds(Long[] ids);

    /**
     * 删除产品检验单信息
     * 
     * @param id 产品检验单ID
     * @return 结果
     */
    public int deleteMesProductCheckById(Long id);


    /**
     * 查询产品检验单列表
     *
     * @param mesProductCheck 产品检验单
     * @return 产品检验单集合
     */
    public List<MesProductCheck> selectQualifiedRateReport(MesProductCheck mesProductCheck);
}
