package com.neu.carbon.wms.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 移库信息对象 wms_warehouse_material_move
 * 
 * @author neuedu
 * @date 2022-07-05
 */
@ApiModel("移库信息")
public class WmsWarehouseMaterialMove extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 物料 */
    @ApiModelProperty("物料")
    @Excel(name = "物料")
    private Long materialId;

    /** 源仓库 */
    @ApiModelProperty("源仓库")
    @Excel(name = "源仓库")
    private Long sourceWarehouseId;

    /** 源库区 */
    @ApiModelProperty("源库区")
    @Excel(name = "源库区")
    private Long sourceRegionId;

    /** 源库位 */
    @ApiModelProperty("源库位")
    @Excel(name = "源库位")
    private Long sourceLocationId;

    /** 批号 */
    @ApiModelProperty("批号")
    @Excel(name = "批号")
    private String batchNo;

    /** 目标仓库 */
    @ApiModelProperty("目标仓库")
    @Excel(name = "目标仓库")
    private Long targetWarehouseId;

    /** 目标库区 */
    @ApiModelProperty("目标库区")
    @Excel(name = "目标库区")
    private Long targetRegionId;

    /** 目标库位 */
    @ApiModelProperty("目标库位")
    @Excel(name = "目标库位")
    private Long targetLocationId;

    /** 移出数量 */
    @ApiModelProperty("移出数量")
    @Excel(name = "移出数量")
    private Double moveQuantity;

    /** 移库时间 */
    @ApiModelProperty(value="移库时间",example = "2021-09-10 10:20:40")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "移库时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date operateDate;

    /** 执行人 */
    @ApiModelProperty("执行人")
    @Excel(name = "执行人")
    private String operateUser;

    /** 移库原因 */
    @ApiModelProperty("移库原因")
    @Excel(name = "移库原因")
    private String reason;

    /** 状态：0未执行1已执行 */
    @ApiModelProperty("状态：0未执行1已执行")
    @Excel(name = "状态：0未执行1已执行")
    private String status;
    
    /** 物料名称 */
    @ApiModelProperty("物料名称")
    @Excel(name = "物料名称")
    private String materialName;
    
    /** 源仓库 */
    @ApiModelProperty("源仓库名")
    @Excel(name = "源仓库名")
    private String sourceWarehouseName;

    /** 源库区 */
    @ApiModelProperty("源库区名")
    @Excel(name = "源库区名")
    private String sourceRegionName;

    /** 源库位 */
    @ApiModelProperty("源库位名")
    @Excel(name = "源库位名")
    private String sourceLocationName;
    
    /** 源仓库 */
    @ApiModelProperty("目标仓库名")
    @Excel(name = "目标仓库名")
    private String targetWarehouseName;

    /** 源库区 */
    @ApiModelProperty("目标库区名")
    @Excel(name = "目标库区名")
    private String targetRegionName;

    /** 源库位 */
    @ApiModelProperty("目标库位名")
    @Excel(name = "目标库位名")
    private String targetLocationName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }
    public void setSourceWarehouseId(Long sourceWarehouseId) 
    {
        this.sourceWarehouseId = sourceWarehouseId;
    }

    public Long getSourceWarehouseId() 
    {
        return sourceWarehouseId;
    }
    public void setSourceRegionId(Long sourceRegionId) 
    {
        this.sourceRegionId = sourceRegionId;
    }

    public Long getSourceRegionId() 
    {
        return sourceRegionId;
    }
    public void setSourceLocationId(Long sourceLocationId) 
    {
        this.sourceLocationId = sourceLocationId;
    }

    public Long getSourceLocationId() 
    {
        return sourceLocationId;
    }
    public void setBatchNo(String batchNo) 
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo() 
    {
        return batchNo;
    }
    public void setTargetWarehouseId(Long targetWarehouseId) 
    {
        this.targetWarehouseId = targetWarehouseId;
    }

    public Long getTargetWarehouseId() 
    {
        return targetWarehouseId;
    }
    public void setTargetRegionId(Long targetRegionId) 
    {
        this.targetRegionId = targetRegionId;
    }

    public Long getTargetRegionId() 
    {
        return targetRegionId;
    }
    public void setTargetLocationId(Long targetLocationId) 
    {
        this.targetLocationId = targetLocationId;
    }

    public Long getTargetLocationId() 
    {
        return targetLocationId;
    }
    public void setMoveQuantity(Double moveQuantity) 
    {
        this.moveQuantity = moveQuantity;
    }

    public Double getMoveQuantity() 
    {
        return moveQuantity;
    }
    public void setOperateDate(Date operateDate) 
    {
        this.operateDate = operateDate;
    }

    public Date getOperateDate() 
    {
        return operateDate;
    }
    public void setOperateUser(String operateUser) 
    {
        this.operateUser = operateUser;
    }

    public String getOperateUser() 
    {
        return operateUser;
    }
    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getSourceWarehouseName() {
		return sourceWarehouseName;
	}

	public void setSourceWarehouseName(String sourceWarehouseName) {
		this.sourceWarehouseName = sourceWarehouseName;
	}

	public String getSourceRegionName() {
		return sourceRegionName;
	}

	public void setSourceRegionName(String sourceRegionName) {
		this.sourceRegionName = sourceRegionName;
	}

	public String getSourceLocationName() {
		return sourceLocationName;
	}

	public void setSourceLocationName(String sourceLocationName) {
		this.sourceLocationName = sourceLocationName;
	}

	public String getTargetWarehouseName() {
		return targetWarehouseName;
	}

	public void setTargetWarehouseName(String targetWarehouseName) {
		this.targetWarehouseName = targetWarehouseName;
	}

	public String getTargetRegionName() {
		return targetRegionName;
	}

	public void setTargetRegionName(String targetRegionName) {
		this.targetRegionName = targetRegionName;
	}

	public String getTargetLocationName() {
		return targetLocationName;
	}

	public void setTargetLocationName(String targetLocationName) {
		this.targetLocationName = targetLocationName;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("materialId", getMaterialId())
            .append("sourceWarehouseId", getSourceWarehouseId())
            .append("sourceRegionId", getSourceRegionId())
            .append("sourceLocationId", getSourceLocationId())
            .append("batchNo", getBatchNo())
            .append("targetWarehouseId", getTargetWarehouseId())
            .append("targetRegionId", getTargetRegionId())
            .append("targetLocationId", getTargetLocationId())
            .append("moveQuantity", getMoveQuantity())
            .append("operateDate", getOperateDate())
            .append("operateUser", getOperateUser())
            .append("reason", getReason())
            .append("remark", getRemark())
            .append("status", getStatus())
            .toString();
    }
}
