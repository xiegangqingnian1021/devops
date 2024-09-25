package com.neu.carbon.wms.domain;

import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 库区管理对象 wms_warehouse_region
 * 
 * @author neusoft
 * @date 2022-06-24
 */
@ApiModel("库区管理")
public class WmsWarehouseRegion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    @Excel(name = "编号")
    private Long id;

    /** 仓库编号 */
    @ApiModelProperty("仓库编号")
    private Long warehouseId;

    /** 编码 */
    @ApiModelProperty("编码")
    @Excel(name = "编码")
    private String code;

    /** 库区名称 */
    @ApiModelProperty("库区名称")
    @Excel(name = "库区名称")
    private String name;
    
    @ApiModelProperty("仓库名称")
    private String warehouseName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setWarehouseId(Long warehouseId) 
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId() 
    {
        return warehouseId;
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

    public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("warehouseId", getWarehouseId())
            .append("code", getCode())
            .append("name", getName())
            .toString();
    }
}
