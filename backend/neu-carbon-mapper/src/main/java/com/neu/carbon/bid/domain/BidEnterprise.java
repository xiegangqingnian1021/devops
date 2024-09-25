package com.neu.carbon.bid.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 投标企业信息对象 bid_enterprise
 * 
 * @author neuedu
 * @date 2023-04-21
 */
@ApiModel("投标企业信息")
public class BidEnterprise extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /** 用户名称 */
    @ApiModelProperty("用户名称")
    @Excel(name = "用户名称")
    private String userName;

    /** 所属机构 */
    @ApiModelProperty("所属机构")
    @Excel(name = "所属机构")
    private String affiliation;

    /** 密码 */
    @ApiModelProperty("密码")
    @Excel(name = "密码")
    private String password;

    /** 盐 */
    @ApiModelProperty("盐")
    @Excel(name = "盐")
    private String salt;

    /** 手机号码 */
    @ApiModelProperty("手机号码")
    @Excel(name = "手机号码")
    private String mobile;

    /** 名称 */
    @ApiModelProperty("名称")
    @Excel(name = "名称")
    private String name;

    /** 简称 */
    @ApiModelProperty("简称")
    @Excel(name = "简称")
    private String shortName;

    /** 开户行地址 */
    @ApiModelProperty("开户行地址")
    @Excel(name = "开户行地址")
    private String addr;

    /** 开户银行 */
    @ApiModelProperty("开户银行")
    @Excel(name = "开户银行")
    private String bank;

    /** 银行账号 */
    @ApiModelProperty("银行账号")
    @Excel(name = "银行账号")
    private String account;

    /** 税号 */
    @ApiModelProperty("税号")
    @Excel(name = "税号")
    private String taxNo;

    /** 联系人 */
    @ApiModelProperty("联系人")
    @Excel(name = "联系人")
    private String contact;

    /** 联系电话 */
    @ApiModelProperty("联系电话")
    @Excel(name = "联系电话")
    private String contactTel;

    /** 统一社会信用代码 */
    @ApiModelProperty("统一社会信用代码")
    @Excel(name = "统一社会信用代码")
    private String usci;

    /** 公司注册地址 */
    @ApiModelProperty("公司注册地址")
    @Excel(name = "公司注册地址")
    private String address;

    /** 企业性质 */
    @ApiModelProperty("企业性质")
    @Excel(name = "企业性质")
    private String enterpriseNature;

    /** 成立日期 */
    @ApiModelProperty(value="成立日期",example = "2021-09-10")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "成立日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date doe;

    /** 法人/负责人 */
    @ApiModelProperty("法人/负责人")
    @Excel(name = "法人/负责人")
    private String corpn;

    /** 法人/负责人身份证 */
    @ApiModelProperty("法人/负责人身份证")
    @Excel(name = "法人/负责人身份证")
    private String corpnIdcard;

    /** 单位联系地址 */
    @ApiModelProperty("单位联系地址")
    @Excel(name = "单位联系地址")
    private String contactAddress;

    /** 经营范围 */
    @ApiModelProperty("经营范围")
    @Excel(name = "经营范围")
    private String businessScope;

    /** 公司简介 */
    @ApiModelProperty("公司简介")
    @Excel(name = "公司简介")
    private String profile;

    /** 营业执照扫描件 */
    @ApiModelProperty("营业执照扫描件")
    @Excel(name = "营业执照扫描件")
    private String businessLicenseImage;

    /** 营业执照有效期 */
    @ApiModelProperty("营业执照有效期")
    @Excel(name = "营业执照有效期")
    private String businessLicenseGp;

    /** 法人/负责人身份证扫描件 */
    @ApiModelProperty("法人/负责人身份证扫描件")
    @Excel(name = "法人/负责人身份证扫描件")
    private String corpnIdcardImage;

    /** 注册电话 */
    @ApiModelProperty("注册电话")
    @Excel(name = "注册电话")
    private String registrationPhone;

    /** 注册地址 */
    @ApiModelProperty("注册地址")
    @Excel(name = "注册地址")
    private String contactAddr;

    /** 注册资本 */
    @ApiModelProperty("注册资本")
    @Excel(name = "注册资本")
    private String registeredCapital;

    /** 实缴资本 */
    @ApiModelProperty("实缴资本")
    @Excel(name = "实缴资本")
    private String paidinCapital;

    /** 开户许可证/基本户存款信息证 */
    @ApiModelProperty("开户许可证/基本户存款信息证")
    @Excel(name = "开户许可证/基本户存款信息证")
    private String accountLicence;

    @ApiModelProperty("IP所属地")
    private String ipAddress;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setAffiliation(String affiliation) 
    {
        this.affiliation = affiliation;
    }

    public String getAffiliation() 
    {
        return affiliation;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setSalt(String salt) 
    {
        this.salt = salt;
    }

    public String getSalt() 
    {
        return salt;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setShortName(String shortName) 
    {
        this.shortName = shortName;
    }

    public String getShortName() 
    {
        return shortName;
    }
    public void setAddr(String addr) 
    {
        this.addr = addr;
    }

    public String getAddr() 
    {
        return addr;
    }
    public void setBank(String bank) 
    {
        this.bank = bank;
    }

    public String getBank() 
    {
        return bank;
    }
    public void setAccount(String account) 
    {
        this.account = account;
    }

    public String getAccount() 
    {
        return account;
    }
    public void setTaxNo(String taxNo) 
    {
        this.taxNo = taxNo;
    }

    public String getTaxNo() 
    {
        return taxNo;
    }
    public void setContact(String contact) 
    {
        this.contact = contact;
    }

    public String getContact() 
    {
        return contact;
    }
    public void setContactTel(String contactTel) 
    {
        this.contactTel = contactTel;
    }

    public String getContactTel() 
    {
        return contactTel;
    }
    public void setUsci(String usci) 
    {
        this.usci = usci;
    }

    public String getUsci() 
    {
        return usci;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setEnterpriseNature(String enterpriseNature) 
    {
        this.enterpriseNature = enterpriseNature;
    }

    public String getEnterpriseNature() 
    {
        return enterpriseNature;
    }
    public void setDoe(Date doe) 
    {
        this.doe = doe;
    }

    public Date getDoe() 
    {
        return doe;
    }
    public void setCorpn(String corpn) 
    {
        this.corpn = corpn;
    }

    public String getCorpn() 
    {
        return corpn;
    }
    public void setCorpnIdcard(String corpnIdcard) 
    {
        this.corpnIdcard = corpnIdcard;
    }

    public String getCorpnIdcard() 
    {
        return corpnIdcard;
    }
    public void setContactAddress(String contactAddress) 
    {
        this.contactAddress = contactAddress;
    }

    public String getContactAddress() 
    {
        return contactAddress;
    }
    public void setBusinessScope(String businessScope) 
    {
        this.businessScope = businessScope;
    }

    public String getBusinessScope() 
    {
        return businessScope;
    }
    public void setProfile(String profile) 
    {
        this.profile = profile;
    }

    public String getProfile() 
    {
        return profile;
    }
    public void setBusinessLicenseImage(String businessLicenseImage) 
    {
        this.businessLicenseImage = businessLicenseImage;
    }

    public String getBusinessLicenseImage() 
    {
        return businessLicenseImage;
    }
    public void setBusinessLicenseGp(String businessLicenseGp) 
    {
        this.businessLicenseGp = businessLicenseGp;
    }

    public String getBusinessLicenseGp() 
    {
        return businessLicenseGp;
    }
    public void setCorpnIdcardImage(String corpnIdcardImage) 
    {
        this.corpnIdcardImage = corpnIdcardImage;
    }

    public String getCorpnIdcardImage() 
    {
        return corpnIdcardImage;
    }
    public void setRegistrationPhone(String registrationPhone) 
    {
        this.registrationPhone = registrationPhone;
    }

    public String getRegistrationPhone() 
    {
        return registrationPhone;
    }
    public void setContactAddr(String contactAddr) 
    {
        this.contactAddr = contactAddr;
    }

    public String getContactAddr() 
    {
        return contactAddr;
    }
    public void setRegisteredCapital(String registeredCapital) 
    {
        this.registeredCapital = registeredCapital;
    }

    public String getRegisteredCapital() 
    {
        return registeredCapital;
    }
    public void setPaidinCapital(String paidinCapital) 
    {
        this.paidinCapital = paidinCapital;
    }

    public String getPaidinCapital() 
    {
        return paidinCapital;
    }
    public void setAccountLicence(String accountLicence) 
    {
        this.accountLicence = accountLicence;
    }

    public String getAccountLicence() 
    {
        return accountLicence;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userName", getUserName())
            .append("affiliation", getAffiliation())
            .append("password", getPassword())
            .append("salt", getSalt())
            .append("mobile", getMobile())
            .append("name", getName())
            .append("shortName", getShortName())
            .append("addr", getAddr())
            .append("bank", getBank())
            .append("account", getAccount())
            .append("taxNo", getTaxNo())
            .append("contact", getContact())
            .append("contactTel", getContactTel())
            .append("usci", getUsci())
            .append("address", getAddress())
            .append("enterpriseNature", getEnterpriseNature())
            .append("doe", getDoe())
            .append("corpn", getCorpn())
            .append("corpnIdcard", getCorpnIdcard())
            .append("contactAddress", getContactAddress())
            .append("businessScope", getBusinessScope())
            .append("profile", getProfile())
            .append("businessLicenseImage", getBusinessLicenseImage())
            .append("businessLicenseGp", getBusinessLicenseGp())
            .append("corpnIdcardImage", getCorpnIdcardImage())
            .append("registrationPhone", getRegistrationPhone())
            .append("contactAddr", getContactAddr())
            .append("registeredCapital", getRegisteredCapital())
            .append("paidinCapital", getPaidinCapital())
            .append("accountLicence", getAccountLicence())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
