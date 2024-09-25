package com.neu.carbon.bid.service.impl;

import java.util.Collections;
import java.util.List;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.IdUtil;
import com.neu.common.core.domain.entity.SysUser;
import com.neu.common.utils.DateUtils;
import com.neu.common.utils.SecurityUtils;
import com.neu.common.utils.ip.AddressUtils;
import com.neu.common.utils.ip.IpUtils;
import com.neu.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.bid.mapper.BidEnterpriseMapper;
import com.neu.carbon.bid.domain.BidEnterprise;
import com.neu.carbon.bid.service.IBidEnterpriseService;

/**
 * 投标企业信息Service业务层处理
 *
 * @author neuedu
 * @date 2023-04-21
 */
@Service
public class BidEnterpriseServiceImpl implements IBidEnterpriseService {
    @Autowired
    private BidEnterpriseMapper bidEnterpriseMapper;
    @Autowired
    private ISysUserService userService;

    /**
     * 查询投标企业信息
     *
     * @param id 投标企业信息ID
     * @return 投标企业信息
     */
    @Override
    public BidEnterprise selectBidEnterpriseById(Long id) {
        BidEnterprise bidEnterprise = bidEnterpriseMapper.selectBidEnterpriseById(id);
        return bidEnterprise;
    }

    /**
     * 查询投标企业信息列表
     *
     * @param bidEnterprise 投标企业信息
     * @return 投标企业信息
     */
    @Override
    public List<BidEnterprise> selectBidEnterpriseList(BidEnterprise bidEnterprise) {
        return bidEnterpriseMapper.selectBidEnterpriseList(bidEnterprise);
    }

    @Override
    public List<BidEnterprise> selectBidEnterpriseListByEnterpriseIds(List<Long> ids) {

        return bidEnterpriseMapper.selectBidEnterpriseListByEnterpriseIds(ArrayUtil.toArray(ids, Long.class));
    }

    /**
     * 新增投标企业信息
     *
     * @param bidEnterprise 投标企业信息
     * @return 结果
     */
    @Override
    public int insertBidEnterprise(BidEnterprise bidEnterprise) {
        bidEnterprise.setCreateTime(DateUtils.getNowDate());
        bidEnterprise.setId(IdUtil.getSnowflake(1, 1).nextId());
        SysUser user = new SysUser();
        user.setUserName(bidEnterprise.getUserName());
        user.setPassword(bidEnterprise.getPassword());
        user.setCreateBy(SecurityUtils.getUsername());
        user.setNickName(bidEnterprise.getName());
        user.setRoleIds(new Long[]{4L});
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        userService.insertUser(user);
        return bidEnterpriseMapper.insertBidEnterprise(bidEnterprise);
    }

    /**
     * 修改投标企业信息
     *
     * @param bidEnterprise 投标企业信息
     * @return 结果
     */
    @Override
    public int updateBidEnterprise(BidEnterprise bidEnterprise) {
        bidEnterprise.setUpdateTime(DateUtils.getNowDate());

        return bidEnterpriseMapper.updateBidEnterprise(bidEnterprise);
    }

    /**
     * 批量删除投标企业信息
     *
     * @param ids 需要删除的投标企业信息ID
     * @return 结果
     */
    @Override
    public int deleteBidEnterpriseByIds(Long[] ids) {
        return bidEnterpriseMapper.deleteBidEnterpriseByIds(ids);
    }

    /**
     * 删除投标企业信息信息
     *
     * @param id 投标企业信息ID
     * @return 结果
     */
    @Override
    public int deleteBidEnterpriseById(Long id) {
        return bidEnterpriseMapper.deleteBidEnterpriseById(id);
    }
}
