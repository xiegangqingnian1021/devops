package com.neu.smty.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;
import com.neu.common.utils.StringUtils;
import com.neu.smty.utils.LocationUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 文章管理对象 cmt_article
 * 
 * @author neusoft
 * @date 2021-07-11
 */
@ApiModel("文章管理")
public class CmtArticle extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 编号 */
	@ApiModelProperty("编号")
	private Long id;

	/** 板块 */
	@ApiModelProperty("板块")
	@Excel(name = "板块")
	private String category;

	/** 标题 */
	@ApiModelProperty("标题")
	@Excel(name = "标题")
	private String title;

	/** 内容 */
	@ApiModelProperty("内容")
	private String content;

	/** 封面 */
	@ApiModelProperty("封面")
	private String cover;

	private String location;

	private Integer commentNum;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getCover() {
		return cover;
	}

	public String getLocation() {
		if (StringUtils.isBlank(location)) {
			return String.valueOf(LocationUtil.getDistance());
		}
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Long getCreateTimeMs() {
		return this.getCreateTime() != null ? this.getCreateTime().getTime() : null;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", getId())
				.append("category", getCategory()).append("title", getTitle()).append("content", getContent())
				.append("cover", getCover()).append("createBy", getCreateBy()).append("createTime", getCreateTime())
				.toString();
	}
}
