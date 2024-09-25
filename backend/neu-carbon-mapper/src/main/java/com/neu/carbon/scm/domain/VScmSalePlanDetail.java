package com.neu.carbon.scm.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 销售计划达成率报表对象 v_scm_sale_plan_detail
 * 
 * @author neuedu
 * @date 2022-08-02
 */
@ApiModel("销售计划达成率报表")
@Data
public class VScmSalePlanDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 计划单号 */
    @ApiModelProperty("计划单号")
    @Excel(name = "计划单号")
    private String planNo;

    /** 计划标题 */
    @ApiModelProperty("计划标题")
    @Excel(name = "计划标题")
    private String title;

    /** 计划类型：1年度计划 2季度计划 3月计划 */
    @ApiModelProperty("计划类型：1年度计划 2季度计划 3月计划")
    @Excel(name = "计划类型：1年度计划 2季度计划 3月计划")
    private String type;

    /** 起始日期 */
    @ApiModelProperty(value="起始日期",example = "2021-09-10")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "起始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 结束日期 */
    @ApiModelProperty(value="结束日期",example = "2021-09-10")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 计划销售额 */
    @ApiModelProperty("计划销售额")
    @Excel(name = "计划销售额")
    private BigDecimal targetAmount;

    /** 销售额 */
    @ApiModelProperty("销售额")
    @Excel(name = "销售额")
    private BigDecimal saleAmount;

    /** 产品id */
    @ApiModelProperty("产品id")
    private Long materialId;

    /** 物料名称 */
    @ApiModelProperty("物料名称")
    @Excel(name = "物料名称")
    private String materialName;

    /** 达成状态 0未达成 1已达成 */
    @ApiModelProperty("达成状态 0未达成 1已达成 ")
    @Excel(name = "达成状态 0未达成 1已达成 ")
    private String status = "0";


}
