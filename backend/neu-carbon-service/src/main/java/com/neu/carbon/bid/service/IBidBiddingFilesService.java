package com.neu.carbon.bid.service;

import java.util.List;
import com.neu.carbon.bid.domain.BidBiddingFiles;

/**
 * 招标信息附件Service接口
 * 
 * @author neuedu
 * @date 2023-04-21
 */
public interface IBidBiddingFilesService 
{
    /**
     * 查询招标信息附件
     * 
     * @param id 招标信息附件ID
     * @return 招标信息附件
     */
    public BidBiddingFiles selectBidBiddingFilesById(Long id);

    /**
     * 查询招标信息附件列表
     * 
     * @param bidBiddingFiles 招标信息附件
     * @return 招标信息附件集合
     */
    public List<BidBiddingFiles> selectBidBiddingFilesList(BidBiddingFiles bidBiddingFiles);

    /**
     * 新增招标信息附件
     * 
     * @param bidBiddingFiles 招标信息附件
     * @return 结果
     */
    public int insertBidBiddingFiles(BidBiddingFiles bidBiddingFiles);

    /**
     * 修改招标信息附件
     * 
     * @param bidBiddingFiles 招标信息附件
     * @return 结果
     */
    public int updateBidBiddingFiles(BidBiddingFiles bidBiddingFiles);

    /**
     * 批量删除招标信息附件
     * 
     * @param ids 需要删除的招标信息附件ID
     * @return 结果
     */
    public int deleteBidBiddingFilesByIds(Long[] ids);

    /**
     * 删除招标信息附件信息
     * 
     * @param id 招标信息附件ID
     * @return 结果
     */
    public int deleteBidBiddingFilesById(Long id);

    /**
     * @param id
     * @return
     */
    int deleteBidBiddingFilesByPId(Long id);
}
