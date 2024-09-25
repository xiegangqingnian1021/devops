package com.neu.carbon.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.mes.mapper.MesFactoryModelDetailMapper;
import com.neu.carbon.mes.domain.MesFactoryModelDetail;
import com.neu.carbon.mes.service.IMesFactoryModelDetailService;

/**
 * 工厂建模明细Service业务层处理
 * 
 * @author neuedu
 * @date 2022-07-07
 */
@Service
public class MesFactoryModelDetailServiceImpl implements IMesFactoryModelDetailService 
{
    @Autowired
    private MesFactoryModelDetailMapper mesFactoryModelDetailMapper;

    /**
     * 查询工厂建模明细
     * 
     * @param id 工厂建模明细ID
     * @return 工厂建模明细
     */
    @Override
    public MesFactoryModelDetail selectMesFactoryModelDetailById(Long id)
    {
        return mesFactoryModelDetailMapper.selectMesFactoryModelDetailById(id);
    }

    /**
     * 查询工厂建模明细列表
     * 
     * @param mesFactoryModelDetail 工厂建模明细
     * @return 工厂建模明细
     */
    @Override
    public List<MesFactoryModelDetail> selectMesFactoryModelDetailList(MesFactoryModelDetail mesFactoryModelDetail)
    {
        return mesFactoryModelDetailMapper.selectMesFactoryModelDetailList(mesFactoryModelDetail);
    }

    /**
     * 新增工厂建模明细
     * 
     * @param mesFactoryModelDetail 工厂建模明细
     * @return 结果
     */
    @Override
    public int insertMesFactoryModelDetail(MesFactoryModelDetail mesFactoryModelDetail)
    {
        return mesFactoryModelDetailMapper.insertMesFactoryModelDetail(mesFactoryModelDetail);
    }

    /**
     * 修改工厂建模明细
     * 
     * @param mesFactoryModelDetail 工厂建模明细
     * @return 结果
     */
    @Override
    public int updateMesFactoryModelDetail(MesFactoryModelDetail mesFactoryModelDetail)
    {
        return mesFactoryModelDetailMapper.updateMesFactoryModelDetail(mesFactoryModelDetail);
    }

    /**
     * 批量删除工厂建模明细
     * 
     * @param ids 需要删除的工厂建模明细ID
     * @return 结果
     */
    @Override
    public int deleteMesFactoryModelDetailByIds(Long[] ids)
    {
        return mesFactoryModelDetailMapper.deleteMesFactoryModelDetailByIds(ids);
    }

    /**
     * 删除工厂建模明细信息
     * 
     * @param id 工厂建模明细ID
     * @return 结果
     */
    @Override
    public int deleteMesFactoryModelDetailById(Long id)
    {
        return mesFactoryModelDetailMapper.deleteMesFactoryModelDetailById(id);
    }
}
