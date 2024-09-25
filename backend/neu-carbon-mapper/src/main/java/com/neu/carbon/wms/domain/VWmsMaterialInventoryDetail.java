package com.neu.carbon.wms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 库存明细报表对象 v_wms_material_inventory_detail
 * 
 * @author neuedu
 * @date 2022-07-26
 */
@ApiModel("库存明细报表")
public class VWmsMaterialInventoryDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 仓库编号 */
    @ApiModelProperty("仓库编号")
    private Long whId;

    /** 库区编号 */
    @ApiModelProperty("库区编号")
    private Long whRegionId;

    /** 库位编号 */
    @ApiModelProperty("库位编号")
    private Long whLocationId;

    /** 库存量 */
    @ApiModelProperty("库存量")
    @Excel(name = "库存量")
    private Double inventory;

    /** 锁定库存 */
    @ApiModelProperty("锁定库存")
    @Excel(name = "锁定库存")
    private Double lockInventory;

    /** 生产批号 */
    @ApiModelProperty("生产批号")
    @Excel(name = "生产批号")
    private String batchNo;

    /** 生产厂家 */
    @ApiModelProperty("生产厂家")
    @Excel(name = "生产厂家")
    private String manufacturer;

    /** 物料档案编号 */
    @ApiModelProperty("物料档案编号")
    private Long materialId;

    /** 库存上限 */
    @ApiModelProperty("库存上限")
    @Excel(name = "库存上限")
    private Long maxInventory;

    /** 库存下限 */
    @ApiModelProperty("库存下限")
    @Excel(name = "库存下限")
    private Long minInventory;

    /** 物料名称 */
    @ApiModelProperty("物料名称")
    @Excel(name = "物料名称")
    private String materialName;

    /** 型号 */
    @ApiModelProperty("型号")
    @Excel(name = "型号")
    private String materialModel;

    /** 规格 */
    @ApiModelProperty("规格")
    @Excel(name = "规格")
    private String materialSpecification;

    /** 单位 */
    @ApiModelProperty("单位")
    @Excel(name = "单位")
    private String materialUnit;

    /** 仓库名 */
    @ApiModelProperty("仓库名")
    @Excel(name = "仓库名")
    private String whName;

    /** 库区名称 */
    @ApiModelProperty("库区名称")
    @Excel(name = "库区名称")
    private String whRegionName;

    /** 库位名称 */
    @ApiModelProperty("库位名称")
    @Excel(name = "库位名称")
    private String whLocationName;

    public void setWhId(Long whId) 
    {
        this.whId = whId;
    }

    public Long getWhId() 
    {
        return whId;
    }
    public void setWhRegionId(Long whRegionId) 
    {
        this.whRegionId = whRegionId;
    }

    public Long getWhRegionId() 
    {
        return whRegionId;
    }
    public void setWhLocationId(Long whLocationId) 
    {
        this.whLocationId = whLocationId;
    }

    public Long getWhLocationId() 
    {
        return whLocationId;
    }
    public void setInventory(Double inventory) 
    {
        this.inventory = inventory;
    }

    public Double getInventory() 
    {
        return inventory;
    }
    public void setLockInventory(Double lockInventory) 
    {
        this.lockInventory = lockInventory;
    }

    public Double getLockInventory() 
    {
        return lockInventory;
    }
    public void setBatchNo(String batchNo) 
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo() 
    {
        return batchNo;
    }
    public void setManufacturer(String manufacturer) 
    {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() 
    {
        return manufacturer;
    }
    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }
    public void setMaxInventory(Long maxInventory) 
    {
        this.maxInventory = maxInventory;
    }

    public Long getMaxInventory() 
    {
        return maxInventory;
    }
    public void setMinInventory(Long minInventory) 
    {
        this.minInventory = minInventory;
    }

    public Long getMinInventory() 
    {
        return minInventory;
    }
    public void setMaterialName(String materialName) 
    {
        this.materialName = materialName;
    }

    public String getMaterialName() 
    {
        return materialName;
    }
    public void setMaterialModel(String materialModel) 
    {
        this.materialModel = materialModel;
    }

    public String getMaterialModel() 
    {
        return materialModel;
    }
    public void setMaterialSpecification(String materialSpecification) 
    {
        this.materialSpecification = materialSpecification;
    }

    public String getMaterialSpecification() 
    {
        return materialSpecification;
    }
    public void setMaterialUnit(String materialUnit) 
    {
        this.materialUnit = materialUnit;
    }

    public String getMaterialUnit() 
    {
        return materialUnit;
    }
    public void setWhName(String whName) 
    {
        this.whName = whName;
    }

    public String getWhName() 
    {
        return whName;
    }
    public void setWhRegionName(String whRegionName) 
    {
        this.whRegionName = whRegionName;
    }

    public String getWhRegionName() 
    {
        return whRegionName;
    }
    public void setWhLocationName(String whLocationName) 
    {
        this.whLocationName = whLocationName;
    }

    public String getWhLocationName() 
    {
        return whLocationName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("whId", getWhId())
            .append("whRegionId", getWhRegionId())
            .append("whLocationId", getWhLocationId())
            .append("inventory", getInventory())
            .append("lockInventory", getLockInventory())
            .append("batchNo", getBatchNo())
            .append("manufacturer", getManufacturer())
            .append("materialId", getMaterialId())
            .append("maxInventory", getMaxInventory())
            .append("minInventory", getMinInventory())
            .append("materialName", getMaterialName())
            .append("materialModel", getMaterialModel())
            .append("materialSpecification", getMaterialSpecification())
            .append("materialUnit", getMaterialUnit())
            .append("whName", getWhName())
            .append("whRegionName", getWhRegionName())
            .append("whLocationName", getWhLocationName())
            .toString();
    }
}
