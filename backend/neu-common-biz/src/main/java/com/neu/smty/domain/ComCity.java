package com.neu.smty.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 城市信息对象 com_city
 * 
 * @author neusoft
 * @date 2021-08-12
 */
@ApiModel("城市信息")
public class ComCity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty("${comment}")
    private Long id;

    /** 省 */
    @ApiModelProperty("省")
    @Excel(name = "省")
    private String provinceName;

    /** 城市 */
    @ApiModelProperty("城市")
    @Excel(name = "城市")
    private String name;

    /** 开头字母 */
    @ApiModelProperty("开头字母")
    @Excel(name = "开头字母")
    private String letter;

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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setLetter(String letter) 
    {
        this.letter = letter;
    }

    public String getLetter() 
    {
        return letter;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("provinceName", getProvinceName())
            .append("name", getName())
            .append("letter", getLetter())
            .toString();
    }
}
