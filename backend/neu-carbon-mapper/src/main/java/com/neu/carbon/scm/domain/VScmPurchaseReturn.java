package com.neu.carbon.scm.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 采购退货对象 v_scm_purchase_return
 * 
 * @author neuedu
 * @date 2022-07-25
 */
@ApiModel("采购退货")
public class VScmPurchaseReturn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物料id */
    @ApiModelProperty("物料id")
    private Long materialId;

    /** 物料名称 */
    @ApiModelProperty("物料名称")
    @Excel(name = "物料名称")
    private String materialName;

    /** 生产批号 */
    @ApiModelProperty("生产批号")
    @Excel(name = "生产批号")
    private String batchNo;

    /** 供应商 */
    @ApiModelProperty("供应商")
    @Excel(name = "供应商")
    private String supplierName;

    /** 退货单号 */
    @ApiModelProperty("退货单号")
    @Excel(name = "退货单号")
    private String returnNo;

    /** 型号 */
    @ApiModelProperty("型号")
    @Excel(name = "型号")
    private String materialModel;


    /** 物料编码 */
    @ApiModelProperty("物料编码")
    @Excel(name = "物料编码")
    private String materialCode;

    /** 规格 */
    @ApiModelProperty("规格")
    @Excel(name = "规格")
    private String materialSpecification;

    /** 单位 */
    @ApiModelProperty("单位")
    @Excel(name = "单位")
    private String materialUnit;

    /** 单价 */
    @ApiModelProperty("单价")
    @Excel(name = "单价")
    private BigDecimal price;

    /** 订货数量 */
    @ApiModelProperty("订货数量")
    @Excel(name = "订货数量")
    private Long bookQuantity;

    /** 到货数量 */
    @ApiModelProperty("到货数量")
    @Excel(name = "到货数量")
    private Long arriveQuantity;

    /** 退货数量 */
    @ApiModelProperty("退货数量")
    @Excel(name = "退货数量")
    private Long returnQuantity;



    /** 退货日期 */
    @ApiModelProperty(value="退货日期",example = "2021-09-10 10:20:40")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "退货日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date returnDate;


    /** 供应商id */
    @ApiModelProperty("供应商id")
    private Long supplierId;



    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
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
    public void setBookQuantity(Long bookQuantity) 
    {
        this.bookQuantity = bookQuantity;
    }

    public Long getBookQuantity() 
    {
        return bookQuantity;
    }
    public void setArriveQuantity(Long arriveQuantity) 
    {
        this.arriveQuantity = arriveQuantity;
    }

    public Long getArriveQuantity() 
    {
        return arriveQuantity;
    }
    public void setReturnQuantity(Long returnQuantity) 
    {
        this.returnQuantity = returnQuantity;
    }

    public Long getReturnQuantity() 
    {
        return returnQuantity;
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
    public void setReturnDate(Date returnDate) 
    {
        this.returnDate = returnDate;
    }

    public Date getReturnDate() 
    {
        return returnDate;
    }
    public void setReturnNo(String returnNo) 
    {
        this.returnNo = returnNo;
    }

    public String getReturnNo() 
    {
        return returnNo;
    }
    public void setSupplierId(Long supplierId) 
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId() 
    {
        return supplierId;
    }
    public void setSupplierName(String supplierName) 
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName() 
    {
        return supplierName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("materialId", getMaterialId())
            .append("batchNo", getBatchNo())
            .append("price", getPrice())
            .append("bookQuantity", getBookQuantity())
            .append("arriveQuantity", getArriveQuantity())
            .append("returnQuantity", getReturnQuantity())
            .append("materialName", getMaterialName())
            .append("materialCode", getMaterialCode())
            .append("materialModel", getMaterialModel())
            .append("materialSpecification", getMaterialSpecification())
            .append("materialUnit", getMaterialUnit())
            .append("returnDate", getReturnDate())
            .append("returnNo", getReturnNo())
            .append("supplierId", getSupplierId())
            .append("supplierName", getSupplierName())
            .toString();
    }
}
