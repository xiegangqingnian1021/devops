package com.neu.carbon.scm.domain;

import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 销售-发货-退货报表实体 RepSaleDeliveryReturn
 * 
 * @author neuedu
 * @date 2022-07-29
 */
@ApiModel("销售-发货-退货报表实体")
@Data
public class RepContractDeliveryReturnDetail extends BaseEntity
{
    /** 客户ID */
    @ApiModelProperty("客户ID")
    private Long customerId;

    /** 年份 */
    @ApiModelProperty("年份")
    private String contractYear;

    /** 季度 */
    @ApiModelProperty("季度")
    private String contractQuarter;

    /** 月份 */
    @ApiModelProperty("月份")
    private String contractMonth;

    /** 物料id */
    @ApiModelProperty("物料id")
    private Long materialId;

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

    /** 物料名称 */
    @ApiModelProperty("物料名称")
    @Excel(name = "物料名称")
    private String materialName;

    /** 销售数量 */
    @ApiModelProperty("销售数量")
    @Excel(name = "销售数量")
    private Double saleNum;

    /** 已收金额 */
    @ApiModelProperty("已收金额")
    @Excel(name = "已收金额")
    private Double incomeAmount;

    /** 已发数量 */
    @ApiModelProperty("已发数量")
    @Excel(name = "已发数量")
    private Double deliveryNum;

    /** 退货数量 */
    @ApiModelProperty("退货数量")
    @Excel(name = "退货数量")
    private Double returnNum;

    /** 退款金额 */
    @ApiModelProperty("退款金额")
    @Excel(name = "退款金额")
    private Double returnAmount;

}
