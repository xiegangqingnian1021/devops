package com.neu.smty.api.cms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.page.TableDataInfo;
import com.neu.smty.api.base.ApiBaseController;
import com.neu.smty.domain.CmtArticleComment;
import com.neu.smty.domain.CmtLikeLog;
import com.neu.smty.service.ICmtArticleCommentService;
import com.neu.smty.service.ICmtLikeLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "内容管理评论相关接口")
@RestController
@RequestMapping("/api/cms/comment")
public class CommentApi extends ApiBaseController {

	private static final String BIZ_CMS = "cms";
	@Autowired
	private ICmtArticleCommentService cmtArticleCommentService;
	@Autowired
	private ICmtLikeLogService cmtLikeLogService;

	@ApiOperation("查询文章评论列表")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "total", value = "总记录数"),
			@DynamicParameter(name = "code", value = "状态码"),
			@DynamicParameter(name = "rows", value = "列表数据（数组类型）", dataTypeClass = CmtArticleComment.class),
			@DynamicParameter(name = "msg", value = "消息内容") })
	@GetMapping("/list")
	public TableDataInfo commentList(CmtArticleComment cmtArticleComment) {
		startPage("t.create_time desc");
		List<CmtArticleComment> list = cmtArticleCommentService.selectCmtArticleCommentList(cmtArticleComment);
		list.stream().forEach(comment -> {
			setIsLike(comment);
			CmtArticleComment reply = new CmtArticleComment();
			reply.setParentId(comment.getId());
			List<CmtArticleComment> replyList = cmtArticleCommentService.selectCmtArticleCommentList(reply);
			comment.setReplyList(replyList);
			comment.setReplyNum(replyList.size());
		});
		return getDataTable(list);
	}

	@ApiOperation("保存文章评论/评论回复")
	@DynamicResponseParameters(properties = {
			@DynamicParameter(name = "cmtArticleComment", value = "评论信息", dataTypeClass = CmtArticleComment.class) })
	@PostMapping
	public AjaxResult add(@RequestBody CmtArticleComment cmtArticleComment) {
		return toAjax(cmtArticleCommentService.insertCmtArticleComment(cmtArticleComment));
	}

	@ApiOperation("获取文章评论/评论详情")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "code", value = "状态码"),
			@DynamicParameter(name = "data", value = "文章评论/评论详情", dataTypeClass = CmtArticleComment.class),
			@DynamicParameter(name = "msg", value = "消息内容") })
	@GetMapping(value = "/{id}")
	public AjaxResult getInfo(@PathVariable("id") Long id) {
		CmtArticleComment comment = cmtArticleCommentService.selectCmtArticleCommentById(id);
		setIsLike(comment);
		CmtArticleComment reply = new CmtArticleComment();
		reply.setParentId(comment.getId());
		List<CmtArticleComment> replyList = cmtArticleCommentService.selectCmtArticleCommentList(reply);
		replyList.stream().forEach(item -> {
			setIsLike(item);
		});
		comment.setReplyList(replyList);
		comment.setReplyNum(replyList.size());
		return AjaxResult.success(comment);
	}

	/**
	 * 设置用户是否点过赞
	 * 
	 * @param comment
	 */
	private void setIsLike(CmtArticleComment comment) {
		CmtLikeLog cmtLikeLog = new CmtLikeLog();
		cmtLikeLog.setBizId(comment.getId());
		cmtLikeLog.setBizType(BIZ_CMS);
		cmtLikeLog.setCreateBy(String.valueOf(this.getUserId()));
		List<CmtLikeLog> likeList = cmtLikeLogService.selectCmtLikeLogList(cmtLikeLog);
		if (likeList != null && !likeList.isEmpty()) {
			comment.setIsLiked("1");
		} else {
			comment.setIsLiked("0");
		}
	}

	@ApiOperation("评论点赞")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "code", value = "状态码"),
			@DynamicParameter(name = "msg", value = "消息内容") })
	@PostMapping("/like/{id}")
	public AjaxResult like(@PathVariable("id") Long id) {
		CmtLikeLog cmtLikeLog = new CmtLikeLog();
		cmtLikeLog.setBizId(id);
		cmtLikeLog.setBizType(BIZ_CMS);
		cmtLikeLogService.insertCmtLikeLog(cmtLikeLog);
		CmtArticleComment comment = cmtArticleCommentService.selectCmtArticleCommentById(id);
		long goodNum = comment.getGoodNum() != null ? comment.getGoodNum() + 1 : 1;
		comment.setGoodNum(goodNum);
		return toAjax(cmtArticleCommentService.updateCmtArticleComment(comment));
	}

	@ApiOperation("评论取消点赞")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "code", value = "状态码"),
			@DynamicParameter(name = "msg", value = "消息内容") })
	@PostMapping("/unlike/{id}")
	public AjaxResult unlike(@PathVariable("id") Long id) {
		CmtLikeLog cmtLikeLog = new CmtLikeLog();
		cmtLikeLog.setBizId(id);
		cmtLikeLog.setBizType(BIZ_CMS);
		cmtLikeLog.setCreateBy(String.valueOf(this.getUserId()));
		List<CmtLikeLog> list = cmtLikeLogService.selectCmtLikeLogList(cmtLikeLog);
		if (list != null && !list.isEmpty()) {
			CmtLikeLog like = list.get(0);
			cmtLikeLogService.deleteCmtLikeLogById(like.getId());
		}
		CmtArticleComment comment = cmtArticleCommentService.selectCmtArticleCommentById(id);
		long goodNum = comment.getGoodNum() != null ? comment.getGoodNum() - 1 : 0;
		comment.setGoodNum(goodNum);
		return toAjax(cmtArticleCommentService.updateCmtArticleComment(comment));
	}
}
