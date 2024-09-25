package com.neu.smty.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("字典信息")
public class DicVo {
    @ApiModelProperty(value = "字典键值")
    private String dictValue;
    @ApiModelProperty(value = "字典标签")
    private String dictLabel;
}
