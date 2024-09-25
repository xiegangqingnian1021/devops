package com.neu.carbon.wms.domain;

import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 库位管理对象 wms_warehouse_location
 * 
 * @author neusoft
 * @date 2022-06-24
 */
@ApiModel("库位管理")
public class WmsWarehouseLocation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 编码 */
    @ApiModelProperty("编码")
    @Excel(name = "编码")
    private String code;

    /** 库区编码 */
    @ApiModelProperty("库区编码")
    @Excel(name = "库区编码")
    private Long regionId;

    /** 库位名称 */
    @ApiModelProperty("库位名称")
    @Excel(name = "库位名称")
    private String name;
    
    @ApiModelProperty("仓库名称")
    private String warehouseName;
    
    @ApiModelProperty("仓区名称")
    private String regionName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setRegionId(Long regionId) 
    {
        this.regionId = regionId;
    }

    public Long getRegionId() 
    {
        return regionId;
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

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("regionId", getRegionId())
            .append("name", getName())
            .toString();
    }
}
