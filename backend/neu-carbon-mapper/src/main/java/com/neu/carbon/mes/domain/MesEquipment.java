package com.neu.carbon.mes.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 设备信息对象 mes_equipment
 * 
 * @author neuedu
 * @date 2022-07-07
 */
@ApiModel("设备信息")
public class MesEquipment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 名称 */
    @ApiModelProperty("名称")
    @Excel(name = "名称")
    private String name;

    /** 编码 */
    @ApiModelProperty("编码")
    @Excel(name = "编码")
    private String code;

    /** 型号 */
    @ApiModelProperty("型号")
    @Excel(name = "型号")
    private String model;

    /** 生产日期 */
    @ApiModelProperty(value="生产日期",example = "2021-09-10")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date productDate;

    /** 生产批号 */
    @ApiModelProperty("生产批号")
    @Excel(name = "生产批号")
    private String batchNo;

    /** 制造商 */
    @ApiModelProperty("制造商")
    @Excel(name = "制造商")
    private String manufacturer;

    /** 设备状态：0空闲1使用中2故障 */
    @ApiModelProperty("设备状态：0空闲1使用中2故障")
    @Excel(name = "设备状态：0空闲1使用中2故障")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setModel(String model) 
    {
        this.model = model;
    }

    public String getModel() 
    {
        return model;
    }
    public void setProductDate(Date productDate) 
    {
        this.productDate = productDate;
    }

    public Date getProductDate() 
    {
        return productDate;
    }
    public void setBatchNo(String batchNo) 
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo() 
    {
        return batchNo;
    }
    public void setManufacturer(String manufacturer) 
    {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() 
    {
        return manufacturer;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("code", getCode())
            .append("model", getModel())
            .append("productDate", getProductDate())
            .append("batchNo", getBatchNo())
            .append("manufacturer", getManufacturer())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
