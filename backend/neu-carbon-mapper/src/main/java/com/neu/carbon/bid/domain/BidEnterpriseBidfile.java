package com.neu.carbon.bid.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 企业投标材料对象 bid_enterprise_bidfile
 * 
 * @author neuedu
 * @date 2023-04-21
 */
@ApiModel("企业投标材料")
public class BidEnterpriseBidfile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /** 企业投标报名Id */
    @ApiModelProperty("企业投标报名Id")
    @Excel(name = "企业投标报名Id")
    private Long enterpriseBidId;

    /** 文件名称 */
    @ApiModelProperty("文件名称")
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件路径 */
    @ApiModelProperty("文件路径")
    @Excel(name = "文件路径")
    private String filePath;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setEnterpriseBidId(Long enterpriseBidId) 
    {
        this.enterpriseBidId = enterpriseBidId;
    }

    public Long getEnterpriseBidId() 
    {
        return enterpriseBidId;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("enterpriseBidId", getEnterpriseBidId())
            .append("fileName", getFileName())
            .append("filePath", getFilePath())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
