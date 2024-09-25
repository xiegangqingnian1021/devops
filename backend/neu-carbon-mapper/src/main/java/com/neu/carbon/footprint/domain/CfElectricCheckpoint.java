package com.neu.carbon.footprint.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 能效检测点对象 cf_electric_checkpoint
 * 
 * @author neuedu
 * @date 2022-07-22
 */
@ApiModel("能效检测点")
public class CfElectricCheckpoint extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 监测点名称 */
    @ApiModelProperty("监测点名称")
    @Excel(name = "监测点名称")
    private String name;

    /** 地址 */
    @ApiModelProperty("地址")
    @Excel(name = "地址")
    private String address;

    /** 实时功率 */
    @ApiModelProperty("实时功率")
    @Excel(name = "实时功率")
    private Long rtPower;

    /** 实时电压 */
    @ApiModelProperty("实时电压")
    @Excel(name = "实时电压")
    private Long rtVoltage;

    /** 状态 0未过载 1已过载 */
    @ApiModelProperty("状态 0未过载 1已过载")
    @Excel(name = "状态 0未过载 1已过载")
    private String status;

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
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setRtPower(Long rtPower) 
    {
        this.rtPower = rtPower;
    }

    public Long getRtPower() 
    {
        return rtPower;
    }
    public void setRtVoltage(Long rtVoltage) 
    {
        this.rtVoltage = rtVoltage;
    }

    public Long getRtVoltage() 
    {
        return rtVoltage;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("address", getAddress())
            .append("rtPower", getRtPower())
            .append("rtVoltage", getRtVoltage())
            .append("status", getStatus())
            .append("remark", getRemark())
            .toString();
    }
}
