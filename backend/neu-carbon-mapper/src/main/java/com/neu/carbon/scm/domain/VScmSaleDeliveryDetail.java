package com.neu.carbon.scm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 销售发货退货明细报表对象 v_scm_sale_delivery_detail
 * 
 * @author neuedu
 * @date 2022-08-02
 */
@ApiModel("销售发货退货明细报表")
public class VScmSaleDeliveryDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同编号 */
    @ApiModelProperty("合同编号")
    @Excel(name = "合同编号")
    private String contractNo;

    /** 发货id */
    @ApiModelProperty("发货id")
    private Long deliveryId;

    /** 客户id */
    @ApiModelProperty("客户id")
    private Long customerId;

    /** 客户名称 */
    @ApiModelProperty("客户名称")
    @Excel(name = "客户名称")
    private String customerName;

    /** 物料id */
    @ApiModelProperty("物料id")
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

    /** 发货数量 */
    @ApiModelProperty("发货数量")
    @Excel(name = "发货数量")
    private Double quantity;

    /** 退货数量 */
    @ApiModelProperty("退货数量")
    @Excel(name = "退货数量")
    private Double returnQuantity;

    public void setContractNo(String contractNo) 
    {
        this.contractNo = contractNo;
    }

    public String getContractNo() 
    {
        return contractNo;
    }
    public void setDeliveryId(Long deliveryId) 
    {
        this.deliveryId = deliveryId;
    }

    public Long getDeliveryId() 
    {
        return deliveryId;
    }
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
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
    public void setQuantity(Double quantity) 
    {
        this.quantity = quantity;
    }

    public Double getQuantity() 
    {
        return quantity;
    }
    public void setReturnQuantity(Double returnQuantity) 
    {
        this.returnQuantity = returnQuantity;
    }

    public Double getReturnQuantity() 
    {
        return returnQuantity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contractNo", getContractNo())
            .append("deliveryId", getDeliveryId())
            .append("customerId", getCustomerId())
            .append("customerName", getCustomerName())
            .append("materialId", getMaterialId())
            .append("materialName", getMaterialName())
            .append("materialCode", getMaterialCode())
            .append("materialModel", getMaterialModel())
            .append("materialSpecification", getMaterialSpecification())
            .append("materialUnit", getMaterialUnit())
            .append("quantity", getQuantity())
            .append("returnQuantity", getReturnQuantity())
            .toString();
    }
}
