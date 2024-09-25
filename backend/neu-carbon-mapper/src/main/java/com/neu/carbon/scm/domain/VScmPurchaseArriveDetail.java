package com.neu.carbon.scm.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 采购到货详细报表对象 v_scm_purchase_arrive_detail
 * 
 * @author neuedu
 * @date 2022-08-02
 */
@ApiModel("采购到货详细报表")
public class VScmPurchaseArriveDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同编号 */
    @ApiModelProperty("合同编号")
    @Excel(name = "合同编号")
    private String contractNo;

    /** 供应商id */
    @ApiModelProperty("供应商id")
    private Long supplierId;

    /** 到货id */
    @ApiModelProperty("到货id")
    private Long arriveId;

    /** 供应商名称 */
    @ApiModelProperty("供应商名称")
    @Excel(name = "供应商名称")
    private String supplierName;

    /** 物料id */
    @ApiModelProperty("物料id")
    @Excel(name = "物料id")
    private Long materialId;

    /** 物料名称 */
    @ApiModelProperty("物料名称")
    @Excel(name = "物料名称")
    private String materialName;

    /** 物料编码 */
    @ApiModelProperty("物料编码")
    @Excel(name = "物料编码")
    private String materialCode;

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

    /** 生产批号 */
    @ApiModelProperty("生产批号")
    @Excel(name = "生产批号")
    private String batchNo;

    /** 单价 */
    @ApiModelProperty("单价")
    @Excel(name = "单价")
    private BigDecimal price;

    /** 到货数量 */
    @ApiModelProperty("到货数量")
    @Excel(name = "到货数量")
    private Double arriveQuantity;

    /** 退货数量 */
    @ApiModelProperty("退货数量")
    @Excel(name = "退货数量")
    private Double returnQuantity;

    /** 入库数量 */
    @ApiModelProperty("入库数量")
    @Excel(name = "入库数量")
    private Double inWarehouseQuantity;

    public void setContractNo(String contractNo) 
    {
        this.contractNo = contractNo;
    }

    public String getContractNo() 
    {
        return contractNo;
    }
    public void setSupplierId(Long supplierId) 
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId() 
    {
        return supplierId;
    }
    public void setArriveId(Long arriveId) 
    {
        this.arriveId = arriveId;
    }

    public Long getArriveId() 
    {
        return arriveId;
    }
    public void setSupplierName(String supplierName) 
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName() 
    {
        return supplierName;
    }
    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }
    public void setMaterialName(String materialName) 
    {
        this.materialName = materialName;
    }

    public String getMaterialName() 
    {
        return materialName;
    }
    public void setMaterialCode(String materialCode) 
    {
        this.materialCode = materialCode;
    }

    public String getMaterialCode() 
    {
        return materialCode;
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
    public void setBatchNo(String batchNo) 
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo() 
    {
        return batchNo;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setArriveQuantity(Double arriveQuantity) 
    {
        this.arriveQuantity = arriveQuantity;
    }

    public Double getArriveQuantity() 
    {
        return arriveQuantity;
    }
    public void setReturnQuantity(Double returnQuantity) 
    {
        this.returnQuantity = returnQuantity;
    }

    public Double getReturnQuantity() 
    {
        return returnQuantity;
    }
    public void setInWarehouseQuantity(Double inWarehouseQuantity) 
    {
        this.inWarehouseQuantity = inWarehouseQuantity;
    }

    public Double getInWarehouseQuantity() 
    {
        return inWarehouseQuantity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contractNo", getContractNo())
            .append("supplierId", getSupplierId())
            .append("arriveId", getArriveId())
            .append("supplierName", getSupplierName())
            .append("materialId", getMaterialId())
            .append("materialName", getMaterialName())
            .append("materialCode", getMaterialCode())
            .append("materialModel", getMaterialModel())
            .append("materialSpecification", getMaterialSpecification())
            .append("materialUnit", getMaterialUnit())
            .append("batchNo", getBatchNo())
            .append("price", getPrice())
            .append("arriveQuantity", getArriveQuantity())
            .append("returnQuantity", getReturnQuantity())
            .append("inWarehouseQuantity", getInWarehouseQuantity())
            .toString();
    }
}
