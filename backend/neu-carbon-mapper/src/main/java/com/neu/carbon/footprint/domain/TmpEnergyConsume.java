package com.neu.carbon.footprint.domain;

import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 能耗临时表对象 tmp_energy_consume
 * 
 * @author neuedu
 * @date 2022-07-31
 */
@ApiModel("能耗临时表")
public class TmpEnergyConsume extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 类型：0消耗1节约 */
    @ApiModelProperty("类型：0消耗1节约")
    @Excel(name = "类型：0消耗1节约")
    private String type;

    /** 分类：1电2水 */
    @ApiModelProperty("分类：1电2水")
    @Excel(name = "分类：1电2水")
    private String category;

    /** 月份 */
    @ApiModelProperty("月份")
    @Excel(name = "月份")
    private String month;

    /** 日期 */
    @ApiModelProperty("日期")
    @Excel(name = "日期")
    private String day;
    
    /** 年份 */
    @ApiModelProperty("年份")
    @Excel(name = "年份")
    private String year;

    /** 能耗数或节约数 */
    @ApiModelProperty("能耗数或节约数")
    @Excel(name = "能耗数或节约数")
    private Double energy;

    /** 项目类型 */
    @ApiModelProperty("项目类型")
    @Excel(name = "项目类型")
    private String itemCategory;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }
    public void setMonth(String month) 
    {
        this.month = month;
    }

    public String getMonth() 
    {
        return month;
    }
    public void setDay(String day) 
    {
        this.day = day;
    }

    public String getDay() 
    {
        return day;
    }
    public void setEnergy(Double energy) 
    {
        this.energy = energy;
    }

    public Double getEnergy() 
    {
        return energy;
    }
    public void setItemCategory(String itemCategory) 
    {
        this.itemCategory = itemCategory;
    }

    public String getItemCategory() 
    {
        return itemCategory;
    }

    public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("category", getCategory())
            .append("month", getMonth())
            .append("day", getDay())
            .append("energy", getEnergy())
            .append("itemCategory", getItemCategory())
            .toString();
    }
}
