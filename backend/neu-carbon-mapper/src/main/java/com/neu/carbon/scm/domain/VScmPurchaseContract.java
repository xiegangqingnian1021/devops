package com.neu.carbon.scm.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 采购合同报表对象 v_scm_purchase_contract
 * 
 * @author neuedu
 * @date 2022-07-29
 */
@ApiModel("采购合同报表")
public class VScmPurchaseContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long contractId;

    /** 合同编号 */
    @ApiModelProperty("合同编号")
    @Excel(name = "合同编号")
    private String contractNo;

    /** 供应商id */
    @ApiModelProperty("供应商id")
    @Excel(name = "供应商id")
    private Long supplierId;

    /** 申请id */
    @ApiModelProperty("申请id")
    private Long applyId;

    /** 申请编号 */
    @ApiModelProperty("申请编号")
    @Excel(name = "申请编号")
    private String applyNo;

    /** 供应商名称 */
    @ApiModelProperty("供应商名称")
    @Excel(name = "供应商名称")
    private String supplierName;

    /** 合同金额 */
    @ApiModelProperty("合同金额")
    @Excel(name = "合同金额")
    private BigDecimal contractAmount;

    /** 合同年份 */
    @ApiModelProperty("合同年份")
    private String contractYear;

    /** 合同季度 */
    @ApiModelProperty("合同季度")
    @Excel(name = "合同季度")
    private String contractQuarter;

    /** 合同月份 */
    @ApiModelProperty("合同月份")
    @Excel(name = "合同月份")
    private String contractMonth;

    /** 合同年月 */
    @ApiModelProperty("合同年月")
    private String contractYearMonth;

    /** 合同日期 */
    @ApiModelProperty("合同日期")
    private String contractDate;

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
    public void setSupplierId(Long supplierId) 
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId() 
    {
        return supplierId;
    }
    public void setApplyId(Long applyId) 
    {
        this.applyId = applyId;
    }

    public Long getApplyId() 
    {
        return applyId;
    }
    public void setApplyNo(String applyNo) 
    {
        this.applyNo = applyNo;
    }

    public String getApplyNo() 
    {
        return applyNo;
    }
    public void setSupplierName(String supplierName) 
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName() 
    {
        return supplierName;
    }
    public void setContractAmount(BigDecimal contractAmount) 
    {
        this.contractAmount = contractAmount;
    }

    public BigDecimal getContractAmount() 
    {
        return contractAmount;
    }
    public void setContractYear(String contractYear) 
    {
        this.contractYear = contractYear;
    }

    public String getContractYear() 
    {
        return contractYear;
    }
    public void setContractQuarter(String contractQuarter) 
    {
        this.contractQuarter = contractQuarter;
    }

    public String getContractQuarter() 
    {
        return contractQuarter;
    }
    public void setContractMonth(String contractMonth) 
    {
        this.contractMonth = contractMonth;
    }

    public String getContractMonth() 
    {
        return contractMonth;
    }
    public void setContractYearMonth(String contractYearMonth) 
    {
        this.contractYearMonth = contractYearMonth;
    }

    public String getContractYearMonth() 
    {
        return contractYearMonth;
    }
    public void setContractDate(String contractDate) 
    {
        this.contractDate = contractDate;
    }

    public String getContractDate() 
    {
        return contractDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contractId", getContractId())
            .append("contractNo", getContractNo())
            .append("supplierId", getSupplierId())
            .append("applyId", getApplyId())
            .append("applyNo", getApplyNo())
            .append("supplierName", getSupplierName())
            .append("contractAmount", getContractAmount())
            .append("contractYear", getContractYear())
            .append("contractQuarter", getContractQuarter())
            .append("contractMonth", getContractMonth())
            .append("contractYearMonth", getContractYearMonth())
            .append("contractDate", getContractDate())
            .toString();
    }
}
