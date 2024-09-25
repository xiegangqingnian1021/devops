package com.neu.carbon.scm.domain;

import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 销售合同报表对象 v_scm_sale_contract
 *
 * @author neuedu
 * @date 2022-07-28
 */
@ApiModel("收支统计")
@Data
public class VScmFinanceReport extends BaseEntity {

    @ApiModelProperty("年份")
    @Excel(name = "年份")
    private String year;

    @ApiModelProperty("季度")
    @Excel(name = "季度")
    private String quarter;

    @ApiModelProperty("月份")
    @Excel(name = "月份")
    private String month;

    @ApiModelProperty("采购合同金额")
    private BigDecimal purchaseContractTotal;

    @ApiModelProperty("采购退款金额")
    private BigDecimal purchaseReturnTotal;


    @ApiModelProperty("销售合同金额")
    private BigDecimal saleContractTotal;

    @ApiModelProperty("销售退款金额")
    private BigDecimal saleReturnTotal;

    @ApiModelProperty("收入总金额")
    private BigDecimal incomeTotal;

    @ApiModelProperty("支出总金额")
    private BigDecimal outcomeTotal;

}
