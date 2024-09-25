package com.neu.carbon.wms.domain;

import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.List;

/**
 * 仓库物料信息对象 wms_warehouse_material
 * 
 * @author neusoft
 * @date 2022-06-24
 */
@ApiModel("仓库物料信息")
public class WmsWarehouseMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 仓库 */
    @ApiModelProperty("仓库")
    @Excel(name = "仓库")
    private Long warehouseId;
    
    /** 仓库名称 */
    @ApiModelProperty("仓库名称")
    @Excel(name = "仓库名称")
    private String warehouseName;

    /** 物料档案编号 */
    @ApiModelProperty("物料档案编号")
    @Excel(name = "物料档案编号")
    private Long materialId;

    /** 总库存 */
    @ApiModelProperty("总库存")
    @Excel(name = "总库存")
    private Double totalInventory;

    /** 锁定库存 */
    @ApiModelProperty("锁定库存")
    @Excel(name = "锁定库存")
    private Double freezeInventory;

    /** 库存上限 */
    @ApiModelProperty("库存上限")
    @Excel(name = "库存上限")
    private Double maxInventory;

    /** 库存下限 */
    @ApiModelProperty("库存下限")
    @Excel(name = "库存下限")
    private Double minInventory;
    
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

    /** 单价 */
    @ApiModelProperty("物料单价")
    @Excel(name = "物料单价")
    private BigDecimal materialPrice;


    /** 仓库物料明细信息 */
	@ApiModelProperty(hidden = true)
    private List<WmsWarehouseMaterialDetail> wmsWarehouseMaterialDetailList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setWarehouseId(Long warehouseId) 
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId() 
    {
        return warehouseId;
    }
    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }
    public void setTotalInventory(Double totalInventory) 
    {
        this.totalInventory = totalInventory;
    }

    public Double getTotalInventory() 
    {
        return totalInventory;
    }
    public void setFreezeInventory(Double freezeInventory) 
    {
        this.freezeInventory = freezeInventory;
    }

    public Double getFreezeInventory() 
    {
        return freezeInventory;
    }
    public void setMaxInventory(Double maxInventory) 
    {
        this.maxInventory = maxInventory;
    }

    public Double getMaxInventory() 
    {
        return maxInventory;
    }
    public void setMinInventory(Double minInventory) 
    {
        this.minInventory = minInventory;
    }

    public Double getMinInventory() 
    {
        return minInventory;
    }

    public List<WmsWarehouseMaterialDetail> getWmsWarehouseMaterialDetailList()
    {
        return wmsWarehouseMaterialDetailList;
    }

    public void setWmsWarehouseMaterialDetailList(List<WmsWarehouseMaterialDetail> wmsWarehouseMaterialDetailList)
    {
        this.wmsWarehouseMaterialDetailList = wmsWarehouseMaterialDetailList;
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

	public BigDecimal getMaterialPrice() {
		return materialPrice;
	}

	public void setMaterialPrice(BigDecimal materialPrice) {
		this.materialPrice = materialPrice;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("warehouseId", getWarehouseId())
            .append("materialId", getMaterialId())
            .append("totalInventory", getTotalInventory())
            .append("freezeInventory", getFreezeInventory())
            .append("maxInventory", getMaxInventory())
            .append("minInventory", getMinInventory())
            .append("wmsWarehouseMaterialDetailList", getWmsWarehouseMaterialDetailList())
            .toString();
    }
}
