package com.neu.smty.api.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("密码信息参数")
public class PwdForm {

    @ApiModelProperty("用户旧密码")
    private String oldPassword;
    @ApiModelProperty("用户新密码")
    private String newPassword;

}
