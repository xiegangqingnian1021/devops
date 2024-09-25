package com.neu.carbon.wms.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 仓库物料明细对象 wms_warehouse_material_detail
 * 
 * @author neusoft
 * @date 2022-06-29
 */
@ApiModel("仓库物料明细")
public class WmsWarehouseMaterialDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 仓库 */
    @ApiModelProperty("仓库")
    @Excel(name = "仓库")
    private Long whId;

    /** 库区 */
    @ApiModelProperty("库区")
    @Excel(name = "库区")
    private Long whRegionId;

    /** 库位 */
    @ApiModelProperty("库位")
    @Excel(name = "库位")
    private Long whLocationId;

    /** 物料 */
    @ApiModelProperty("物料")
    @Excel(name = "物料")
    private Long whMaterialId;

    /** 序列号 */
    @ApiModelProperty("序列号")
    @Excel(name = "序列号")
    private String serialNo;

    /** 生产日期 */
    @ApiModelProperty(value="生产日期",example = "2021-09-10")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date productDate;

    /** 生产厂家 */
    @ApiModelProperty("生产厂家")
    @Excel(name = "生产厂家")
    private String manufacturer;

    /** 生产批号 */
    @ApiModelProperty("生产批号")
    @Excel(name = "生产批号")
    private String batchNo;

    /** 库存量 */
    @ApiModelProperty("库存量")
    @Excel(name = "库存量")
    private Double inventory;

    /** 锁定库存 */
    @ApiModelProperty("锁定库存")
    @Excel(name = "锁定库存")
    private Double lockInventory;
    
    /** 物料id */
    @ApiModelProperty("物料id")
    @Excel(name = "物料id")
    private Long materialId;
    
    /** 物料编码 */
    @ApiModelProperty("物料编码")
    @Excel(name = "物料编码")
    private String materialCode;

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
    
    /** 仓库 */
    @ApiModelProperty("仓库")
    @Excel(name = "仓库")
    private Long warehouseId;
    
    /** 仓库名称 */
    @ApiModelProperty("仓库名称")
    @Excel(name = "仓库名称")
    private String warehouseName;
    
    /** 库区名称 */
    @ApiModelProperty("库区名称")
    @Excel(name = "库区名称")
    private String whRegionName;
    
    /** 库位名称 */
    @ApiModelProperty("库位名称")
    @Excel(name = "库位名称")
    private String whLocationName;
    
    /** 库位名称 */
    @ApiModelProperty("物料分类id")
    @Excel(name = "物料分类id")
    private Long materialCategoryId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
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
    public void setWhMaterialId(Long whMaterialId) 
    {
        this.whMaterialId = whMaterialId;
    }

    public Long getWhMaterialId() 
    {
        return whMaterialId;
    }
    public void setSerialNo(String serialNo) 
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo() 
    {
        return serialNo;
    }
    public void setProductDate(Date productDate) 
    {
        this.productDate = productDate;
    }

    public Date getProductDate() 
    {
        return productDate;
    }
    public void setManufacturer(String manufacturer) 
    {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() 
    {
        return manufacturer;
    }
    public void setBatchNo(String batchNo) 
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo() 
    {
        return batchNo;
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

    public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
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

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getWhRegionName() {
		return whRegionName;
	}

	public void setWhRegionName(String whRegionName) {
		this.whRegionName = whRegionName;
	}

	public String getWhLocationName() {
		return whLocationName;
	}

	public void setWhLocationName(String whLocationName) {
		this.whLocationName = whLocationName;
	}

	public Long getMaterialCategoryId() {
		return materialCategoryId;
	}

	public void setMaterialCategoryId(Long materialCategoryId) {
		this.materialCategoryId = materialCategoryId;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("whId", getWhId())
            .append("whRegionId", getWhRegionId())
            .append("whLocationId", getWhLocationId())
            .append("whMaterialId", getWhMaterialId())
            .append("serialNo", getSerialNo())
            .append("productDate", getProductDate())
            .append("manufacturer", getManufacturer())
            .append("batchNo", getBatchNo())
            .append("inventory", getInventory())
            .append("lockInventory", getLockInventory())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}