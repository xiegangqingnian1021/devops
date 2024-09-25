package com.neu.smty.service.impl;

import java.util.List;

import com.neu.common.utils.DateUtils;
import com.neu.smty.domain.CmtArticleComment;
import com.neu.smty.mapper.CmtArticleCommentMapper;
import com.neu.smty.service.ICmtArticleCommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文章评论Service业务层处理
 * 
 * @author neusoft
 * @date 2021-07-11
 */
@Service
public class CmtArticleCommentServiceImpl implements ICmtArticleCommentService {
	@Autowired
	private CmtArticleCommentMapper cmtArticleCommentMapper;

	/**
	 * 查询文章评论
	 * 
	 * @param id
	 *            文章评论ID
	 * @return 文章评论
	 */
	@Override
	public CmtArticleComment selectCmtArticleCommentById(Long id) {
		return cmtArticleCommentMapper.selectCmtArticleCommentById(id);
	}

	/**
	 * 查询文章评论列表
	 * 
	 * @param cmtArticleComment
	 *            文章评论
	 * @return 文章评论
	 */
	@Override
	public List<CmtArticleComment> selectCmtArticleCommentList(CmtArticleComment cmtArticleComment) {
		return cmtArticleCommentMapper.selectCmtArticleCommentList(cmtArticleComment);
	}

	/**
	 * 新增文章评论
	 * 
	 * @param cmtArticleComment
	 *            文章评论
	 * @return 结果
	 */
	@Override
	public int insertCmtArticleComment(CmtArticleComment cmtArticleComment) {
		cmtArticleComment.setCreateTime(DateUtils.getNowDate());
		return cmtArticleCommentMapper.insertCmtArticleComment(cmtArticleComment);
	}

	/**
	 * 修改文章评论
	 * 
	 * @param cmtArticleComment
	 *            文章评论
	 * @return 结果
	 */
	@Override
	public int updateCmtArticleComment(CmtArticleComment cmtArticleComment) {
		return cmtArticleCommentMapper.updateCmtArticleComment(cmtArticleComment);
	}

	/**
	 * 批量删除文章评论
	 * 
	 * @param ids
	 *            需要删除的文章评论ID
	 * @return 结果
	 */
	@Override
	@Transactional
	public int deleteCmtArticleCommentByIds(Long[] ids) {
		for (Long id : ids) {
			CmtArticleComment cmtArticleComment = new CmtArticleComment();
			cmtArticleComment.setParentId(id);
			List<CmtArticleComment> replyList = cmtArticleCommentMapper.selectCmtArticleCommentList(cmtArticleComment);
			if (replyList != null && !replyList.isEmpty()) {
				replyList.stream().forEach(reply -> {
					cmtArticleCommentMapper.deleteCmtArticleCommentById(reply.getId());
				});
			}
		}
		return cmtArticleCommentMapper.deleteCmtArticleCommentByIds(ids);
	}

	/**
	 * 删除文章评论信息
	 * 
	 * @param id
	 *            文章评论ID
	 * @return 结果
	 */
	@Override
	@Transactional
	public int deleteCmtArticleCommentById(Long id) {
		CmtArticleComment cmtArticleComment = new CmtArticleComment();
		cmtArticleComment.setParentId(id);
		List<CmtArticleComment> replyList = cmtArticleCommentMapper.selectCmtArticleCommentList(cmtArticleComment);
		if (replyList != null && !replyList.isEmpty()) {
			replyList.stream().forEach(reply -> {
				cmtArticleCommentMapper.deleteCmtArticleCommentById(reply.getId());
			});
		}
		return cmtArticleCommentMapper.deleteCmtArticleCommentById(id);
	}
}
