package com.neu.carbon.footprint.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * VIEW对象 v_mes_equipment_power_detail
 * 
 * @author neuedu
 * @date 2022-07-31
 */
@ApiModel("VMesEquipmentPowerDetailView")
public class VMesEquipmentPowerDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备编号 */
    @ApiModelProperty("设备编号")
    private Long equipmentId;

    /** 设备名称 */
    @ApiModelProperty("设备名称")
    @Excel(name = "设备名称")
    private String equipmentName;

    /** 计划编号 */
    @ApiModelProperty("计划编号")
    private Long planId;

    /** BOM单编号 */
    @ApiModelProperty("BOM单编号")
    private Long bomId;

    /** 订单编号 */
    @ApiModelProperty("订单编号")
    private Long orderId;

    /** 生产日期 */
	@ApiModelProperty(value="生产日期",example = "2021-09-10 10:20:40")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date productDate;

    /** 物料 */
    @ApiModelProperty("物料")
    private Long materialId;

    /** 待产数量 */
    @ApiModelProperty("待产数量")
    private Double requireQuantity;

    /** 已产数量 */
    @ApiModelProperty("已产数量")
    private Double productQuantity;

    /** 工艺 */
    @ApiModelProperty("工艺")
    private Long processId;

    /** 生产线 */
    @ApiModelProperty("生产线")
    private Long productLineId;

    /** 单位耗电量 */
    @ApiModelProperty("单位耗电量")
    @Excel(name = "单位耗电量")
    private Double powerConsume;

    /** 工艺名称 */
    @ApiModelProperty("工艺名称")
    @Excel(name = "工艺名称")
    private String processName;

    /** 总耗电量 */
    @ApiModelProperty("总耗电量")
    @Excel(name = "总耗电量")
    private Double totalPowerConsume;

    /** 生产线名称 */
    @ApiModelProperty("生产线名称")
    @Excel(name = "生产线名称")
    private String productLineName;
    
    /** 单位碳排放量 */
    @ApiModelProperty("单位碳排放量")
    @Excel(name = "单位碳排放量")
    private Double carbonEmission;
    
    /** 总碳排放量 */
    @ApiModelProperty("总碳排放量")
    @Excel(name = "总碳排放量")
    private Double totalCarbonEmission;
    
    private String hour;

    public void setEquipmentId(Long equipmentId) 
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId() 
    {
        return equipmentId;
    }
    public void setEquipmentName(String equipmentName) 
    {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentName() 
    {
        return equipmentName;
    }
    public void setPlanId(Long planId) 
    {
        this.planId = planId;
    }

    public Long getPlanId() 
    {
        return planId;
    }
    public void setBomId(Long bomId) 
    {
        this.bomId = bomId;
    }

    public Long getBomId() 
    {
        return bomId;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setProductDate(Date productDate) 
    {
        this.productDate = productDate;
    }

    public Date getProductDate() 
    {
        return productDate;
    }
    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }
    public void setRequireQuantity(Double requireQuantity) 
    {
        this.requireQuantity = requireQuantity;
    }

    public Double getRequireQuantity() 
    {
        return requireQuantity;
    }
    public void setProductQuantity(Double productQuantity) 
    {
        this.productQuantity = productQuantity;
    }

    public Double getProductQuantity() 
    {
        return productQuantity;
    }
    public void setProcessId(Long processId) 
    {
        this.processId = processId;
    }

    public Long getProcessId() 
    {
        return processId;
    }
    public void setProductLineId(Long productLineId) 
    {
        this.productLineId = productLineId;
    }

    public Long getProductLineId() 
    {
        return productLineId;
    }
    public void setPowerConsume(Double powerConsume) 
    {
        this.powerConsume = powerConsume;
    }

    public Double getPowerConsume() 
    {
        return powerConsume;
    }
    public void setProcessName(String processName) 
    {
        this.processName = processName;
    }

    public String getProcessName() 
    {
        return processName;
    }
    public void setTotalPowerConsume(Double totalPowerConsume) 
    {
        this.totalPowerConsume = totalPowerConsume;
    }

    public Double getTotalPowerConsume() 
    {
        return totalPowerConsume;
    }
    public void setProductLineName(String productLineName) 
    {
        this.productLineName = productLineName;
    }

    public String getProductLineName() 
    {
        return productLineName;
    }

    public Double getCarbonEmission() {
		return carbonEmission;
	}

	public void setCarbonEmission(Double carbonEmission) {
		this.carbonEmission = carbonEmission;
	}

	public Double getTotalCarbonEmission() {
		return totalCarbonEmission;
	}

	public void setTotalCarbonEmission(Double totalCarbonEmission) {
		this.totalCarbonEmission = totalCarbonEmission;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("equipmentId", getEquipmentId())
            .append("equipmentName", getEquipmentName())
            .append("planId", getPlanId())
            .append("bomId", getBomId())
            .append("orderId", getOrderId())
            .append("productDate", getProductDate())
            .append("materialId", getMaterialId())
            .append("requireQuantity", getRequireQuantity())
            .append("productQuantity", getProductQuantity())
            .append("processId", getProcessId())
            .append("productLineId", getProductLineId())
            .append("powerConsume", getPowerConsume())
            .append("processName", getProcessName())
            .append("totalPowerConsume", getTotalPowerConsume())
            .append("productLineName", getProductLineName())
            .toString();
    }
}
