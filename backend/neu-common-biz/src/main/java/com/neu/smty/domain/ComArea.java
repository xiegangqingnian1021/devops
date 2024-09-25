package com.neu.smty.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 区域信息对象 com_area
 * 
 * @author neusoft
 * @date 2021-08-10
 */
@ApiModel("区域信息")
public class ComArea extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty("${comment}")
    private Long id;

    /** $column.columnComment */
    @ApiModelProperty("${comment}")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String provinceName;

    /** $column.columnComment */
    @ApiModelProperty("${comment}")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String cityName;

    /** $column.columnComment */
    @ApiModelProperty("${comment}")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String name;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProvinceName(String provinceName) 
    {
        this.provinceName = provinceName;
    }

    public String getProvinceName() 
    {
        return provinceName;
    }
    public void setCityName(String cityName) 
    {
        this.cityName = cityName;
    }

    public String getCityName() 
    {
        return cityName;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("provinceName", getProvinceName())
            .append("cityName", getCityName())
            .append("name", getName())
            .toString();
    }
}
