package com.neu.carbon.scm.domain;

import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 采购-入库-退货报表实体
 * 
 * @author neuedu
 * @date 2022-07-29
 */
@ApiModel("采购-入库-退货报表实体")
@Data
public class RepContractArriveReturnDetail extends BaseEntity
{
    /** 供应商ID */
    @ApiModelProperty("供应商ID")
    private Long supplierId;

    /** 合同年份 */
    @ApiModelProperty("合同年份")
    @Excel(name = "合同年份")
    private String contractYear;

    /** 合同月份 */
    @ApiModelProperty("合同月份")
    @Excel(name = "合同月份")
    private String contractMonth;

    /** 合同季度 */
    @ApiModelProperty("合同季度")
    @Excel(name = "合同季度")
    private String contractQuarter;

    /** 物料id */
    @ApiModelProperty("物料id")
    private Long materialId;

    /** 物料名称 */
    @ApiModelProperty("物料名称")
    @Excel(name = "物料名称")
    private String materialName;

    /** 采购数量 */
    @ApiModelProperty("采购数量")
    @Excel(name = "采购数量")
    private Double purchaseNum;

    /** 到货数量 */
    @ApiModelProperty("到货数量")
    @Excel(name = "到货数量")
    private Double arriveNum;

    /** 退货数量 */
    @ApiModelProperty("退货数量")
    @Excel(name = "退货数量")
    private Double returnNum;

    /** 入库数量 */
    @ApiModelProperty("入库数量")
    @Excel(name = "入库数量")
    private Double inWarehouseNum;

    /** 采购总金额 */
    @ApiModelProperty("采购总金额")
    @Excel(name = "采购总金额")
    private BigDecimal purchaseAmount;

    /** 退款金额 */
    @ApiModelProperty("退款金额")
    @Excel(name = "退款金额")
    private BigDecimal returnAmount;

}
