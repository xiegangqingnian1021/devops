package com.neu.smty.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neu.common.annotation.Excel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel("用户信息")
public class UserVo {

    /** 用户ID */
    @ApiModelProperty("用户ID")
    private Long userId;

    /** 用户账号 */
    @ApiModelProperty("用户名")
    private String userName;

    /** 用户昵称 */
    @ApiModelProperty("用户昵称")
    private String nickName;

    /** 用户邮箱 */
    @ApiModelProperty("用户邮箱")
    private String email;

    /** 手机号码 */
    @ApiModelProperty("手机号")
    private String phonenumber;

    /** 用户性别 */
    @ApiModelProperty("用户性别 0 男 1 女")
    private String sex;

    /** 用户头像 */
    @ApiModelProperty("用户头像")
    private String avatar;

    /** 身份证号 */
    @ApiModelProperty("身份证号")
    private String  idCard;

    /** 账户余额 */
    @ApiModelProperty("账户余额")
    private BigDecimal balance;

    /** 真实姓名 */
    @ApiModelProperty("真实姓名")
    @Excel(name = "真实姓名")
    private String realName;

    /** 居住地址 */
    @ApiModelProperty("居住地址")
    private String address;

    /** 户籍地址 */
    @ApiModelProperty("户籍地址")
    private String registerPlace;

    /** 出生日期 */
    @ApiModelProperty(value="出生日期",example = "2021-09-10")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /** 籍贯 */
    @ApiModelProperty("籍贯")
    private String nativePlace;

    /** 民族 */
    @ApiModelProperty("民族")
    private String nation;

    /** 文化程度 */
    @ApiModelProperty("文化程度")
    private String degree;

    /** 婚姻状况 */
    @ApiModelProperty("婚姻状况")
    private String marriage;

    /** 职业 */
    @ApiModelProperty("职业")
    private String career;

    /** 单位 */
    @ApiModelProperty("单位")
    @Excel(name = "单位")
    private String organization;

    /** 紧急联系人 */
    @ApiModelProperty("紧急联系人")
    private String contacts;

    /** 联系人电话 */
    @ApiModelProperty("联系人电话")
    private String contactsPhone;

}
