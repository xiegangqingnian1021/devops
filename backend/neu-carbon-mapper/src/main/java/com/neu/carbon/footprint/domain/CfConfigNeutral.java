package com.neu.carbon.footprint.domain;

import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 碳中和计算比例对象 cf_config_neutral
 * 
 * @author neuedu
 * @date 2022-07-22
 */
@ApiModel("碳中和计算比例")
public class CfConfigNeutral extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 1千克标准煤=X千克二氧化碳 */
    @ApiModelProperty("1千克标准煤=X千克二氧化碳")
    @Excel(name = "1千克标准煤=X千克二氧化碳")
    private Double coalCo2;

    /** 1度电=X千克二氧化碳 */
    @ApiModelProperty("1度电=X千克二氧化碳")
    @Excel(name = "1度电=X千克二氧化碳")
    private Double electricCo2;

    /** 1吨水=X千克二氧化碳 */
    @ApiModelProperty("1吨水=X千克二氧化碳")
    @Excel(name = "1吨水=X千克二氧化碳")
    private Double waterCo2;

    /** 1kg汽油产生的热量=X千克标准煤产生的热量 */
    @ApiModelProperty("1kg汽油产生的热量=X千克标准煤产生的热量")
    @Excel(name = "1kg汽油产生的热量=X千克标准煤产生的热量")
    private Double oilCoal;

    /** 二氧化碳与碳的比值 */
    @ApiModelProperty("二氧化碳与碳的比值")
    @Excel(name = "二氧化碳与碳的比值")
    private Double co2Ratio;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCoalCo2(Double coalCo2) 
    {
        this.coalCo2 = coalCo2;
    }

    public Double getCoalCo2() 
    {
        return coalCo2;
    }
    public void setElectricCo2(Double electricCo2) 
    {
        this.electricCo2 = electricCo2;
    }

    public Double getElectricCo2() 
    {
        return electricCo2;
    }
    public void setWaterCo2(Double waterCo2) 
    {
        this.waterCo2 = waterCo2;
    }

    public Double getWaterCo2() 
    {
        return waterCo2;
    }
    public void setOilCoal(Double oilCoal) 
    {
        this.oilCoal = oilCoal;
    }

    public Double getOilCoal() 
    {
        return oilCoal;
    }
    public void setCo2Ratio(Double co2Ratio) 
    {
        this.co2Ratio = co2Ratio;
    }

    public Double getCo2Ratio() 
    {
        return co2Ratio;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("coalCo2", getCoalCo2())
            .append("electricCo2", getElectricCo2())
            .append("waterCo2", getWaterCo2())
            .append("oilCoal", getOilCoal())
            .append("co2Ratio", getCo2Ratio())
            .toString();
    }
}
