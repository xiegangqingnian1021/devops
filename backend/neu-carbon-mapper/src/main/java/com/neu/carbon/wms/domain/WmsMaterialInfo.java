package com.neu.carbon.wms.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 物料档案管理对象 wms_material_info
 * 
 * @author neusoft
 * @date 2022-06-23
 */
@ApiModel("物料档案管理")
public class WmsMaterialInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 分类编号 */
    @ApiModelProperty("分类编号")
    @Excel(name = "分类编号")
    private Long categoryId;

    /** 物料编码 */
    @ApiModelProperty("物料编码")
    @Excel(name = "物料编码")
    private String code;

    /** 物料名称 */
    @ApiModelProperty("物料名称")
    @Excel(name = "物料名称")
    private String name;

    /** 型号 */
    @ApiModelProperty("型号")
    @Excel(name = "型号")
    private String model;

    /** 规格 */
    @ApiModelProperty("规格")
    @Excel(name = "规格")
    private String specification;

    /** 单位 */
    @ApiModelProperty("单位")
    @Excel(name = "单位")
    private String unit;

    /** 单价 */
    @ApiModelProperty("单价")
    @Excel(name = "单价")
    private BigDecimal price;

    /** 类型 */
    @ApiModelProperty("类型")
    @Excel(name = "类型")
    private String type;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setModel(String model) 
    {
        this.model = model;
    }

    public String getModel() 
    {
        return model;
    }
    public void setSpecification(String specification) 
    {
        this.specification = specification;
    }

    public String getSpecification() 
    {
        return specification;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("categoryId", getCategoryId())
            .append("code", getCode())
            .append("name", getName())
            .append("model", getModel())
            .append("specification", getSpecification())
            .append("unit", getUnit())
            .append("price", getPrice())
            .append("type", getType())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
