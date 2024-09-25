package com.neu.generator.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 通用查询对象 sys_com_query
 * 
 * @author neusoft
 * @date 2021-08-19
 */
@ApiModel("通用查询")
public class SysComQuery extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 名称 */
    @ApiModelProperty("名称")
    @Excel(name = "名称")
    private String name;

    /** 关键字 */
    @ApiModelProperty("关键字")
    @Excel(name = "关键字")
    private String code;

    /** sql语句 */
    @ApiModelProperty("sql语句")
    @Excel(name = "sql语句")
    private String customSql;

    /** 通用查询类型 */
    @ApiModelProperty("通用查询类型")
    @Excel(name = "通用查询类型")
    private String type;

    /** 配置项 */
    @ApiModelProperty("配置项")
    private String config;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setCustomSql(String customSql) 
    {
        this.customSql = customSql;
    }

    public String getCustomSql() 
    {
        return customSql;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setConfig(String config) 
    {
        this.config = config;
    }

    public String getConfig() 
    {
        return config;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("code", getCode())
            .append("remark", getRemark())
            .append("customSql", getCustomSql())
            .append("type", getType())
            .append("config", getConfig())
            .toString();
    }
}
