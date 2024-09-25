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
 * VIEW对象 v_mes_product_power_detail
 * 
 * @author neuedu
 * @date 2022-07-31
 */
@ApiModel("VMesProductPowerDetailView")
public class VMesProductPowerDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 计划编号 */
    @ApiModelProperty("计划编号")
    @Excel(name = "计划编号")
    private Long planId;

    /** BOM单编号 */
    @ApiModelProperty("BOM单编号")
    @Excel(name = "BOM单编号")
    private Long bomId;

    /** 订单编号 */
    @ApiModelProperty("订单编号")
    @Excel(name = "订单编号")
    private Long orderId;

    /** 生产日期 */
    @ApiModelProperty(value="生产日期",example = "2021-09-10")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date productDate;

    /** 物料编号 */
    @ApiModelProperty("物料编号")
    @Excel(name = "物料编号")
    private Long materialId;

    /** 待产数量 */
    @ApiModelProperty("待产数量")
    @Excel(name = "待产数量")
    private Double requireQuantity;

    /** 已产数量 */
    @ApiModelProperty("已产数量")
    @Excel(name = "已产数量")
    private Double productQuantity;

    /** 工艺编号 */
    @ApiModelProperty("工艺编号")
    @Excel(name = "工艺编号")
    private Long processId;

    /** 生产线编号 */
    @ApiModelProperty("生产线编号")
    @Excel(name = "生产线编号")
    private Long productLineId;

    /** 耗电量 */
    @ApiModelProperty("单位耗电量")
    @Excel(name = "单位耗电量")
    private Double powerConsume;

    /** 工艺名称 */
    @ApiModelProperty("工艺名称")
    @Excel(name = "工艺名称")
    private String processName;

    /** 生产总耗电量 */
    @ApiModelProperty("生产总耗电量")
    @Excel(name = "生产总耗电量")
    private Double totalPowerConsume;

    /** 生产线名称 */
    @ApiModelProperty("生产线名称")
    @Excel(name = "生产线名称")
    private String productLineName;
    
    /** 生产总节电量 */
    @ApiModelProperty("生产总节电量")
    @Excel(name = "生产总节电量")
    private Double totalPrdPowerSave;
    
    /** 办公总节电量 */
    @ApiModelProperty("办公总节电量")
    @Excel(name = "办公总节电量")
    private Double totalOfficePowerSave;
    
    @ApiModelProperty("月份")
    @Excel(name = "月份")
    private String productMonth;
    
    @ApiModelProperty("年份")
    @Excel(name = "年份")
    private String productYear;
    
    /** 碳排放总量 */
    @ApiModelProperty("碳排放总量")
    @Excel(name = "碳排放总量")
    private Double totalCarbonEmission;
    
    /** 减少碳排放总量 */
    @ApiModelProperty("减少碳排放总量")
    @Excel(name = "减少碳排放总量")
    private Double totalCarbonSave;
    
    /** 单位碳排放量 */
    @ApiModelProperty("单位碳排放量")
    @Excel(name = "单位碳排放量")
    private Double carbonEmission;
    
    /** 物料名称 */
    @ApiModelProperty("物料名称")
    @Excel(name = "物料名称")
    private String materialName;

    /** 型号 */
    @ApiModelProperty("物料型号")
    @Excel(name = "物料型号")
    private String materialModel;

    /** 规格 */
    @ApiModelProperty("物料规格")
    @Excel(name = "物料规格")
    private String materialSpecification;

    /** 单位 */
    @ApiModelProperty("物料单位")
    @Excel(name = "物料单位")
    private String materialUnit;
    
    /** 生产总用水量 */
    @ApiModelProperty("生产总用水量")
    @Excel(name = "生产总用水量")
    private Double totalWaterConsume;
    
    /** 办公总用水量 */
    @ApiModelProperty("办公总用水量")
    @Excel(name = "办公总用水量")
    private Double totalOfficeWaterConsume;
    
    /** 办公总用电量 */
    @ApiModelProperty("办公总用电量")
    @Excel(name = "办公总用电量")
    private Double totalOfficePowerConsume;
    
    /** 总库存 */
    @ApiModelProperty("总库存")
    @Excel(name = "总库存")
    private Double totalInventory;

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

    public Double getTotalPrdPowerSave() {
		return totalPrdPowerSave;
	}

	public void setTotalPrdPowerSave(Double totalPowerSave) {
		this.totalPrdPowerSave = totalPowerSave;
	}

	public String getProductMonth() {
		return productMonth;
	}

	public void setProductMonth(String productMonth) {
		this.productMonth = productMonth;
	}

	public Double getTotalOfficePowerSave() {
		return totalOfficePowerSave;
	}

	public void setTotalOfficePowerSave(Double totalOfficePowerSave) {
		this.totalOfficePowerSave = totalOfficePowerSave;
	}

	public Double getTotalCarbonEmission() {
		return totalCarbonEmission;
	}

	public void setTotalCarbonEmission(Double totalCarbonEmission) {
		this.totalCarbonEmission = totalCarbonEmission;
	}

	public Double getTotalCarbonSave() {
		return totalCarbonSave;
	}

	public void setTotalCarbonSave(Double totalCarbonSave) {
		this.totalCarbonSave = totalCarbonSave;
	}

	public String getProductYear() {
		return productYear;
	}

	public void setProductYear(String productYear) {
		this.productYear = productYear;
	}

	public Double getCarbonEmission() {
		return carbonEmission;
	}

	public void setCarbonEmission(Double carbonEmission) {
		this.carbonEmission = carbonEmission;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialModel() {
		return materialModel;
	}

	public void setMaterialModel(String materialModel) {
		this.materialModel = materialModel;
	}

	public String getMaterialSpecification() {
		return materialSpecification;
	}

	public void setMaterialSpecification(String materialSpecification) {
		this.materialSpecification = materialSpecification;
	}

	public String getMaterialUnit() {
		return materialUnit;
	}

	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}

	public Double getTotalWaterConsume() {
		return totalWaterConsume;
	}

	public void setTotalWaterConsume(Double totalWaterConsume) {
		this.totalWaterConsume = totalWaterConsume;
	}

	public Double getTotalOfficeWaterConsume() {
		return totalOfficeWaterConsume;
	}

	public void setTotalOfficeWaterConsume(Double totalOfficeWaterConsume) {
		this.totalOfficeWaterConsume = totalOfficeWaterConsume;
	}

	public Double getTotalOfficePowerConsume() {
		return totalOfficePowerConsume;
	}

	public void setTotalOfficePowerConsume(Double totalOfficePowerConsume) {
		this.totalOfficePowerConsume = totalOfficePowerConsume;
	}

	public Double getTotalInventory() {
		return totalInventory;
	}

	public void setTotalInventory(Double totalInventory) {
		this.totalInventory = totalInventory;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
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
