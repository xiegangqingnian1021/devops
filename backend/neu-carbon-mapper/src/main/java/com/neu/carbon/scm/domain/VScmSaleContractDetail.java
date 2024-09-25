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
 * 销售合同明细对象 v_scm_sale_contract_detail
 * 
 * @author neuedu
 * @date 2022-07-27
 */
@ApiModel("销售合同明细")
public class VScmSaleContractDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同id */
    @ApiModelProperty("合同id")
    private Long contractId;

    /** 合同编号 */
    @ApiModelProperty("合同编号")
    @Excel(name = "合同编号")
    private String contractNo;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long orderId;

    /** 订单编号 */
    @ApiModelProperty("订单编号")
    @Excel(name = "订单编号")
    private String orderNo;

    /** 客户id */
    @ApiModelProperty("客户id")
    private Long customerId;

    /** 合同金额 */
    @ApiModelProperty("合同金额")
    @Excel(name = "合同金额")
    private BigDecimal contractAmount;

    /** 签订日期 */
    @ApiModelProperty(value="签订日期",example = "2021-09-10 10:20:40")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "签订日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date signDate;

    /** 客户名称 */
    @ApiModelProperty("客户名称")
    @Excel(name = "客户名称")
    private String customerName;

    /** 物料id */
    @ApiModelProperty("物料id")
    private Long materialId;

    /** 单价 */
    @ApiModelProperty("单价")
    @Excel(name = "单价")
    private BigDecimal price;

    /** 订货数量 */
    @ApiModelProperty("订货数量")
    @Excel(name = "订货数量")
    private Double quantity;

    /** 金额 */
    @ApiModelProperty("金额")
    @Excel(name = "金额")
    private BigDecimal amount;

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

    /** 退款金额 */
    @ApiModelProperty("退款金额")
    private BigDecimal returnTotal = new BigDecimal("0");

    public void setContractId(Long contractId) 
    {
        this.contractId = contractId;
    }

    public Long getContractId() 
    {
        return contractId;
    }
    public void setContractNo(String contractNo) 
    {
        this.contractNo = contractNo;
    }

    public String getContractNo() 
    {
        return contractNo;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setOrderNo(String orderNo) 
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo() 
    {
        return orderNo;
    }
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setContractAmount(BigDecimal contractAmount) 
    {
        this.contractAmount = contractAmount;
    }

    public BigDecimal getContractAmount() 
    {
        return contractAmount;
    }
    public void setSignDate(Date signDate) 
    {
        this.signDate = signDate;
    }

    public Date getSignDate() 
    {
        return signDate;
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
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setQuantity(Double quantity) 
    {
        this.quantity = quantity;
    }

    public Double getQuantity() 
    {
        return quantity;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
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

    public BigDecimal getReturnTotal() {
        return returnTotal;
    }

    public void setReturnTotal(BigDecimal returnTotal) {
        this.returnTotal = returnTotal;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contractId", getContractId())
            .append("contractNo", getContractNo())
            .append("orderId", getOrderId())
            .append("orderNo", getOrderNo())
            .append("customerId", getCustomerId())
            .append("contractAmount", getContractAmount())
            .append("signDate", getSignDate())
            .append("customerName", getCustomerName())
            .append("materialId", getMaterialId())
            .append("price", getPrice())
            .append("quantity", getQuantity())
            .append("amount", getAmount())
            .append("materialName", getMaterialName())
            .append("materialModel", getMaterialModel())
            .append("materialSpecification", getMaterialSpecification())
            .append("materialUnit", getMaterialUnit())
            .toString();
    }
}
