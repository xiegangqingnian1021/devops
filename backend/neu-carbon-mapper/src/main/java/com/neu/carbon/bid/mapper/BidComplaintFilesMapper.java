package com.neu.carbon.bid.mapper;

import java.util.List;
import com.neu.carbon.bid.domain.BidComplaintFiles;

/**
 * 投诉附件Mapper接口
 * 
 * @author neuedu
 * @date 2023-04-21
 */
public interface BidComplaintFilesMapper 
{
    /**
     * 查询投诉附件
     * 
     * @param id 投诉附件ID
     * @return 投诉附件
     */
    public BidComplaintFiles selectBidComplaintFilesById(Long id);

    /**
     * 查询投诉附件列表
     * 
     * @param bidComplaintFiles 投诉附件
     * @return 投诉附件集合
     */
    public List<BidComplaintFiles> selectBidComplaintFilesList(BidComplaintFiles bidComplaintFiles);

    /**
     * 新增投诉附件
     * 
     * @param bidComplaintFiles 投诉附件
     * @return 结果
     */
    public int insertBidComplaintFiles(BidComplaintFiles bidComplaintFiles);

    /**
     * 修改投诉附件
     * 
     * @param bidComplaintFiles 投诉附件
     * @return 结果
     */
    public int updateBidComplaintFiles(BidComplaintFiles bidComplaintFiles);

    /**
     * 删除投诉附件
     * 
     * @param id 投诉附件ID
     * @return 结果
     */
    public int deleteBidComplaintFilesById(Long id);

    /**
     * 批量删除投诉附件
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBidComplaintFilesByIds(Long[] ids);
}
