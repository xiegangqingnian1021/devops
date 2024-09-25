package com.neu.smty.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 文章评论对象 cmt_article_comment
 * 
 * @author neusoft
 * @date 2021-07-11
 */
@ApiModel("文章评论")
public class CmtArticleComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 文章标题 */
    @ApiModelProperty("文章标题")
    @Excel(name = "文章标题")
    private Long articleId;

    /** 内容 */
    @ApiModelProperty("内容")
    private String content;

    /** 点赞数 */
    @ApiModelProperty("点赞数")
    @Excel(name = "点赞数")
    private Long goodNum;

    /** 父级id */
    @ApiModelProperty("父级id")
    private Long parentId;
    
    /** 文章标题 */
    @ApiModelProperty("文章标题")
    @Excel(name = "文章标题")
    private String title;
    
    /** 评论人用户账号 */
    @Excel(name = "评论人登录名称")
    private String userName;
    
    /** 评论人用户昵称 */
    @Excel(name = "评论人用户昵称")
    private String nickName;
    
    /** 评论人用户头像 */
    private String avatar;
    
    /** 评论人用户昵称 */
    @Excel(name = "评论人真实姓名")
    private String realName;
    
    /** 回复数 */
    private Integer replyNum;
    
    /** 回复列表 */
    private List<CmtArticleComment> replyList;

    /** 是否点过赞1是0否 */
    private String isLiked;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setArticleId(Long articleId) 
    {
        this.articleId = articleId;
    }

    public Long getArticleId() 
    {
        return articleId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setGoodNum(Long goodNum) 
    {
        this.goodNum = goodNum;
    }

    public Long getGoodNum() 
    {
        return goodNum;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<CmtArticleComment> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<CmtArticleComment> replyList) {
		this.replyList = replyList;
	}

	public Integer getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(Integer replyNum) {
		this.replyNum = replyNum;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIsLiked() {
		return isLiked;
	}

	public void setIsLiked(String isLiked) {
		this.isLiked = isLiked;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("articleId", getArticleId())
            .append("content", getContent())
            .append("goodNum", getGoodNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("parentId", getParentId())
            .toString();
    }
}
