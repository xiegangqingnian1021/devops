package com.neu.carbon.bid.service.impl;

import java.util.List;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.neu.carbon.bid.domain.BidBiddingFiles;
import com.neu.carbon.bid.domain.BidEnterpriseBidding;
import com.neu.carbon.bid.service.IBidBiddingFilesService;
import com.neu.carbon.bid.service.IBidEnterpriseBiddingService;
import com.neu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.bid.mapper.BidBiddingMapper;
import com.neu.carbon.bid.domain.BidBidding;
import com.neu.carbon.bid.service.IBidBiddingService;

/**
 * 招投标Service业务层处理
 *
 * @author neuedu
 * @date 2023-04-21
 */
@Service
public class BidBiddingServiceImpl implements IBidBiddingService {

    private final BidBiddingMapper bidBiddingMapper;
    private final IBidBiddingFilesService bidBiddingFilesService;

    private final IBidEnterpriseBiddingService enterpriseBiddingService;

    private static final int bidCategory = 2;

    @Autowired
    public BidBiddingServiceImpl(BidBiddingMapper bidBiddingMapper
            , IBidBiddingFilesService bidBiddingFilesService
            , IBidEnterpriseBiddingService enterpriseBiddingService
    ) {
        this.bidBiddingMapper = bidBiddingMapper;
        this.bidBiddingFilesService = bidBiddingFilesService;
        this.enterpriseBiddingService = enterpriseBiddingService;
    }


    /**
     * 查询招投标
     *
     * @param id 招投标ID
     * @return 招投标
     */
    @Override
    public BidBidding selectBidBiddingById(Long id) {
        BidBidding bidBidding = bidBiddingMapper.selectBidBiddingById(id);
        BidBiddingFiles bidBiddingFiles = new BidBiddingFiles();
        if (bidBidding != null) {
            bidBiddingFiles.setBidId(bidBidding.getId());
            List<BidBiddingFiles> filesList = bidBiddingFilesService.selectBidBiddingFilesList(bidBiddingFiles);
            bidBidding.setFilesList(filesList);
        }

        return bidBidding;
    }

    /**
     * 查询招投标列表
     *
     * @param bidBidding 招投标
     * @return 招投标
     */
    @Override
    public List<BidBidding> selectBidBiddingList(BidBidding bidBidding) {
        return bidBiddingMapper.selectBidBiddingList(bidBidding);
    }

    /**
     * 新增招投标
     *
     * @param bidBidding 招投标
     * @return 结果
     */
    @Override
    public int insertBidBidding(BidBidding bidBidding) {
        bidBidding.setCreateTime(DateUtils.getNowDate());
        bidBidding.setId(IdUtil.getSnowflake(1, 1).nextId());
        int result = bidBiddingMapper.insertBidBidding(bidBidding);
        saveFiles(bidBidding);

        return result;
    }

    /**
     * 修改招投标
     *
     * @param bidBidding 招投标
     * @return 结果
     */
    @Override
    public int updateBidBidding(BidBidding bidBidding) {
        bidBidding.setUpdateTime(DateUtils.getNowDate());
        saveFiles(bidBidding);
        if (bidBidding.getCategory() == bidCategory) {
            if (bidBidding.getStatus() != null) {
                //待开标 0 资质审核 1 已过期 2 开标结果 3
                BidEnterpriseBidding bidEnterpriseBidding = new BidEnterpriseBidding();
                List<BidEnterpriseBidding> list = enterpriseBiddingService
                        .selectBidEnterpriseBiddingList(bidEnterpriseBidding);
                if (list.stream().noneMatch(x -> x.getStatus().equals(bidBidding.getStatus()))) {
                    list.forEach(x -> {
                        x.setStatus(bidBidding.getStatus());
                        enterpriseBiddingService.updateBidEnterpriseBidding(x);
                    });
                }
            }
        }
        return bidBiddingMapper.updateBidBidding(bidBidding);
    }

    /**
     * 保存文件
     *
     * @param bidBidding
     */
    private void saveFiles(BidBidding bidBidding) {
        bidBiddingFilesService.deleteBidBiddingFilesByPId(bidBidding.getId());

        if (CollectionUtil.isNotEmpty(bidBidding.getFilesList())) {
            bidBidding.getFilesList().forEach(item -> {
                item.setId(IdUtil.getSnowflake(1, 1).nextId());
                item.setCreateTime(DateUtils.getNowDate());
                item.setBidId(bidBidding.getId());
                bidBiddingFilesService.insertBidBiddingFiles(item);
            });
        }
    }

    /**
     * 批量删除招投标
     *
     * @param ids 需要删除的招投标ID
     * @return 结果
     */
    @Override
    public int deleteBidBiddingByIds(Long[] ids) {
        return bidBiddingMapper.deleteBidBiddingByIds(ids);
    }

    /**
     * 删除招投标信息
     *
     * @param id 招投标ID
     * @return 结果
     */
    @Override
    public int deleteBidBiddingById(Long id) {
        return bidBiddingMapper.deleteBidBiddingById(id);
    }
}
