package com.neu.carbon.footprint.domain;

import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 固定碳排放对象 cf_config_emission_fixed
 *
 * @author neuedu
 * @date 2022-07-25
 */
@ApiModel("固定碳排放")
public class CfConfigEmissionFixed extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 能耗项 */
    @ApiModelProperty("能耗项")
    @Excel(name = "能耗项")
    private String name;

    /** 时间类型(1年度排放 2季度排放 3月排放 4日排放) */
    @ApiModelProperty("时间类型(1年度排放 2季度排放 3月排放 4日排放)")
    @Excel(name = "时间类型(1年度排放 2季度排放 3月排放 4日排放)")
    private String type;

    /** 预警阈值 */
    @ApiModelProperty("预警阈值")
    @Excel(name = "预警阈值")
    private Double thresholdValue;
    
    /** 项目类型 */
    @ApiModelProperty("项目类型")
    @Excel(name = "项目类型")
    private String category;
    
    /** 能耗数 */
    @ApiModelProperty("能耗数")
    @Excel(name = "能耗数")
    private Double energy;

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
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setThresholdValue(Double thresholdValue)
    {
        this.thresholdValue = thresholdValue;
    }

    public Double getThresholdValue()
    {
        return thresholdValue;
    }

    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getEnergy() {
		return energy;
	}

	public void setEnergy(Double energy) {
		this.energy = energy;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("type", getType())
                .append("thresholdValue", getThresholdValue())
                .append("remark", getRemark())
                .toString();
    }
}