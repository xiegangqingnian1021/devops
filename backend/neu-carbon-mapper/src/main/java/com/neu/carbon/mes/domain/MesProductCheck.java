package com.neu.carbon.mes.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 产品检验单对象 mes_product_check
 * 
 * @author neuedu
 * @date 2022-07-12
 */
@ApiModel("产品检验单")
@Data
public class MesProductCheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 流水号 */
    @ApiModelProperty("流水号")
    @Excel(name = "流水号")
    private String serialNo;

    /** 计划编号 */
    @ApiModelProperty("计划编号")
    @Excel(name = "计划编号")
    private String productPlanNo;

    /** 完工单id */
    @ApiModelProperty("完工单id")
    private Long productFinishId;

    /** 完工单号 */
    @ApiModelProperty("完工单号")
    @Excel(name = "完工单号")
    private String productFinishNo;

    /** 作业名称 */
    @ApiModelProperty("作业名称")
    @Excel(name = "作业名称")
    private String productJobName;

    @ApiModelProperty("生产线名称")
    @Excel(name = "产线名称")
    private String productLineName;

    /** 生产计划编号 */
    @ApiModelProperty("生产计划编号")
    private Long productPlanId;

    /** 计划排产编号 */
    @ApiModelProperty("计划排产编号")
    private Long productScheduleId;

    /** 生产线编号 */
    @ApiModelProperty("生产线编号")
    private Long productLineId;

    /** 生产作业编号 */
    @ApiModelProperty("生产作业编号")
    private Long productJobId;

    /** 物料档案编号 */
    @ApiModelProperty("物料档案编号")
    private Long materialId;

    /** 检验批次 */
    @ApiModelProperty("检验批次")
    @Excel(name = "检验批次")
    private String materialBatchNo;

    /** 物料名称 */
    @ApiModelProperty("产品名称")
    @Excel(name = "产品名称")
    private String materialName;

    /** 生产日期 */
    @ApiModelProperty(value="生产日期",example = "2021-09-10")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date productDate;

    @ApiModelProperty("检验项")
    @Excel(name = "检验项")
    private String checkItemName;

    /** 生产数量 */
    @ApiModelProperty("生产数量")
    @Excel(name = "生产数量")
    private Double productQuantity;

    /** 检验标准编号 */
    @ApiModelProperty("检验标准编号")
    private Long checkStandardId;

    /** 抽检数量 */
    @ApiModelProperty("抽检数量")
    @Excel(name = "抽检数量")
    private Double checkQuantity;

    /** 检验值 */
    @ApiModelProperty("检验值")
    @Excel(name = "检验值")
    private Double checkValue;

    /** 标准值上限 */
    @ApiModelProperty("标准值上限")
    @Excel(name = "标准值上限")
    private Double maxValue;

    /** 标准值下限 */
    @ApiModelProperty("标准值下限")
    @Excel(name = "标准值下限")
    private Double minValue;

    /** 检验结果：0不合格1合格 */
    @ApiModelProperty("检验结果：0不合格1合格")
    @Excel(name = "检验结果", dictType = "mes_check_result")
    private String checkResult;

    /** 检验人 */
    @ApiModelProperty("检验人")
    private String checkUser;

    /** 检验时间 */
    @ApiModelProperty(value="检验时间",example = "2021-09-10 10:20:40")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "检验时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date checkDate;

    /** 物料编码 */
    @ApiModelProperty("物料编码")
    private String materialCode;

    /** 型号 */
    @ApiModelProperty("物料型号")
    private String materialModel;

    /** 规格 */
    @ApiModelProperty("物料规格")
    private String materialSpecification;

    /** 单位 */
    @ApiModelProperty("物料单位")
    private String materialUnit;


    @ApiModelProperty("合格率")
    private Double qualifiedRate;

}
