package com.neu.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 镜像信息对象 openstack_image_info
 * 
 * @author neuedu
 * @date 2024-11-12
 */
@ApiModel("镜像信息")
public class OpenstackImageInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 镜像ID */
    @ApiModelProperty("镜像ID")
    @Excel(name = "镜像ID")
    private String imageId;

    /** 镜像名称 */
    @ApiModelProperty("镜像名称")
    @Excel(name = "镜像名称")
    private String imageName;

    /** 所属租户 */
    @ApiModelProperty("所属租户")
    @Excel(name = "所属租户")
    private String projectId;

    /** 磁盘格式 */
    @ApiModelProperty("磁盘格式")
    @Excel(name = "磁盘格式")
    private String diskFormat;

    /** 容器格式 */
    @ApiModelProperty("容器格式")
    @Excel(name = "容器格式")
    private String containerFormat;

    /** 最小内存 */
    @ApiModelProperty("最小内存")
    @Excel(name = "最小内存")
    private Long minRam = 0L;

    /** 最小硬盘 */
    @ApiModelProperty("最小硬盘")
    @Excel(name = "最小硬盘")
    private Long minDisk = 0L;

    /** 是否私有 */
    @ApiModelProperty("是否私有")
    @Excel(name = "是否私有")
    private Long isPrivate = 0L;

    /** 是否保护 */
    @ApiModelProperty("是否保护")
    @Excel(name = "是否保护")
    private Long isProtected = 0L;

    public void setImageId(String imageId) 
    {
        this.imageId = imageId;
    }

    public String getImageId() 
    {
        return imageId;
    }
    public void setImageName(String imageName) 
    {
        this.imageName = imageName;
    }

    public String getImageName() 
    {
        return imageName;
    }
    public void setProjectId(String projectId) 
    {
        this.projectId = projectId;
    }

    public String getProjectId() 
    {
        return projectId;
    }
    public void setDiskFormat(String diskFormat) 
    {
        this.diskFormat = diskFormat;
    }

    public String getDiskFormat() 
    {
        return diskFormat;
    }
    public void setContainerFormat(String containerFormat) 
    {
        this.containerFormat = containerFormat;
    }

    public String getContainerFormat() 
    {
        return containerFormat;
    }
    public void setMinRam(Long minRam) 
    {
        this.minRam = minRam;
    }

    public Long getMinRam() 
    {
        return minRam;
    }
    public void setMinDisk(Long minDisk) 
    {
        this.minDisk = minDisk;
    }

    public Long getMinDisk() 
    {
        return minDisk;
    }
    public void setIsPrivate(Long isPrivate) 
    {
        this.isPrivate = isPrivate;
    }

    public Long getIsPrivate() 
    {
        return isPrivate;
    }
    public void setIsProtected(Long isProtected) 
    {
        this.isProtected = isProtected;
    }

    public Long getIsProtected() 
    {
        return isProtected;
    }

    /** 镜像路径 */
    private String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("imageId", getImageId())
            .append("imageName", getImageName())
            .append("projectId", getProjectId())
            .append("diskFormat", getDiskFormat())
            .append("containerFormat", getContainerFormat())
            .append("minRam", getMinRam())
            .append("minDisk", getMinDisk())
            .append("isPrivate", getIsPrivate())
            .append("isProtected", getIsProtected())
            .toString();
    }
}
