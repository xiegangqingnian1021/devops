package com.neu.smty.api.cms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.page.TableDataInfo;
import com.neu.smty.api.base.ApiBaseController;
import com.neu.smty.domain.CmtArticle;
import com.neu.smty.domain.CmtArticleComment;
import com.neu.smty.service.ICmtArticleCommentService;
import com.neu.smty.service.ICmtArticleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "内容管理文章相关接口")
@RestController
@RequestMapping("/api/cms/article")
public class ArticleApi extends ApiBaseController {
	@Autowired
	private ICmtArticleService cmtArticleService;
	@Autowired
	private ICmtArticleCommentService cmtArticleCommentService;

	@ApiOperation("查询文章列表")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "total", value = "总记录数"),
			@DynamicParameter(name = "code", value = "状态码"),
			@DynamicParameter(name = "rows", value = "列表数据（数组类型）", dataTypeClass = CmtArticle.class),
			@DynamicParameter(name = "msg", value = "消息内容") })
	@GetMapping("/list")
	public TableDataInfo articleList(CmtArticle cmtArticle) {
		startPage("create_time desc");
		List<CmtArticle> list = cmtArticleService.selectCmtArticleList(cmtArticle);
		list.stream().forEach(item -> {
			CmtArticleComment cmtArticleComment = new CmtArticleComment();
			cmtArticleComment.setArticleId(item.getId());
			List<CmtArticleComment> commentList = cmtArticleCommentService
					.selectCmtArticleCommentList(cmtArticleComment);
			item.setCommentNum(commentList.size());
		});
		return getDataTable(list);
	}

	@ApiOperation("查询文章详情")
	@DynamicResponseParameters(properties = { @DynamicParameter(name = "code", value = "状态码"),
			@DynamicParameter(name = "data", value = "文章详情信息", dataTypeClass = CmtArticle.class),
			@DynamicParameter(name = "msg", value = "消息内容") })
	@GetMapping(value = "/detail/{id}")
	public AjaxResult getInfo(@PathVariable("id") Long id) {
		CmtArticle atrticle = cmtArticleService.selectCmtArticleById(id);
		CmtArticleComment cmtArticleComment = new CmtArticleComment();
		cmtArticleComment.setArticleId(id);
		List<CmtArticleComment> commentList = cmtArticleCommentService.selectCmtArticleCommentList(cmtArticleComment);
		atrticle.setCommentNum(commentList.size());
		return AjaxResult.success(atrticle);
	}
}
