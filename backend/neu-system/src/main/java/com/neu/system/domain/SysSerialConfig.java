package com.neu.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 流水号配置对象 sys_serial_config
 * 
 * @author neusoft
 * @date 2022-06-23
 */
@ApiModel("流水号配置")
public class SysSerialConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 业务类型 */
    @ApiModelProperty("业务类型")
    @Excel(name = "业务类型")
    private String bizType;

    /** 前缀 */
    @ApiModelProperty("前缀")
    @Excel(name = "前缀")
    private String prefix;

    /** 最大号 */
    @ApiModelProperty("最大号")
    @Excel(name = "最大号")
    private Long maxNo;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBizType(String bizType) 
    {
        this.bizType = bizType;
    }

    public String getBizType() 
    {
        return bizType;
    }
    public void setPrefix(String prefix) 
    {
        this.prefix = prefix;
    }

    public String getPrefix() 
    {
        return prefix;
    }
    public void setMaxNo(Long maxNo) 
    {
        this.maxNo = maxNo;
    }

    public Long getMaxNo() 
    {
        return maxNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bizType", getBizType())
            .append("prefix", getPrefix())
            .append("maxNo", getMaxNo())
            .toString();
    }
}
