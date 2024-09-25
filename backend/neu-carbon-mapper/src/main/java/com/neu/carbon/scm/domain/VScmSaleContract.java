package com.neu.carbon.scm.domain;

import java.math.BigDecimal;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 销售合同报表对象 v_scm_sale_contract
 * 
 * @author neuedu
 * @date 2022-07-28
 */
@ApiModel("销售合同报表")
@Data
public class VScmSaleContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long contractId;

    /** 合同编号 */
    @ApiModelProperty("合同编号")
    @Excel(name = "合同编号")
    private String contractNo;

    /** 订单id */
    @ApiModelProperty("订单id")
    private Long orderId;

    /** 订单编号 */
    @ApiModelProperty("订单编号")
    @Excel(name = "订单编号")
    private String orderNo;

    /** 客户id */
    @ApiModelProperty("客户id")
    private Long customerId;

    /** 客户名称 */
    @ApiModelProperty("客户名称")
    @Excel(name = "客户名称")
    private String customerName;

    /** 合同金额 */
    @ApiModelProperty("合同金额")
    @Excel(name = "合同金额")
    private BigDecimal contractAmount;

    /** 合同年份 */
    @ApiModelProperty("合同年份")
    @Excel(name = "合同年份")
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
    @Excel(name = "合同年月")
    private String contractYearMonth;

    /** 合同日期 */
    @ApiModelProperty("合同日期")
    @Excel(name = "合同日期")
    private String contractDate;

    @ApiModelProperty("总金额")
    @Excel(name = "总金额")
    private BigDecimal totalAmount;

}
