package com.neu.smty.constant;

/**
 * @author: Daniel
 * 2021/4/6 16:06
 */
public class Constant {


    /** 大写字符 */
    public static final String BASE_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 登录验证码 redis key 前缀
     */
    public static final String CODE_KEY = "verify_codes:";

    public static final String IS_DEFAULT_NO = "N";

    public static final String IS_DEFAULT_YES = "Y";

    /** 访客开门状态 0 待开门 1 已开门 */
    public static final String HOUSE_DOOR_CLOSED = "0";
    public static final String HOUSE_DOOR_OPEN = "1";


    /** 验证码 */
    public static final String CODE = "valid_code";

    /** 生效状态(1:正常，0:禁用) */
    public static final String STATUS_ACTIIVE = "1";

    /** 广告轮播类型 (1: 引导页录播  2：首页轮播) */
    public static final String ADV_TYPE_GUIDE = "1";

    public static final String ADV_TYPE_HOME = "2";

    public static final String BAD_REQUEST_MSG = "请求参数错误!";

    public static final String PAYNO_PREFIX = "751875";


    /**
     * 问诊订单状态
     */
    public interface CONSULT_STATUS {
        String NO_PAY = "待支付";
        String WAITE = "待接诊";
        String ON = "问诊中";
        String FINISH = "已完成";
        String CANCEL ="已取消";
    }

    public interface RESERVATION_STATUS {
        String NO_PAY = "待支付";
        String FINISH = "已支付";
        String CANCEL ="已取消";
    }
    /** im 发送人类型1系统用户2医生 */
    public static final String MSG_USER_USER = "1";
    public static final String MSG_USER_DOCTOR = "2";

    /** 预约门诊类型 */
    public static final String CLINIC_TYPE_COMMON ="普通门诊";
    public static final String CLINIC_TYPE_PROF ="特约门诊";

    /**
     * 支付状态
     * 3种：待支付，已支付，已失效
     * 未支付  - 用户下单，有支付金额，还未支付状态
     * 已支付 - 支付完成后
     */
    public static final String PAY_STATUS_NOPAY = "未支付";
    public static final String PAY_STATUS_FINISHED = "已支付";

    public static final String AUTH_STATUS_ON = "认证中";


    /** 用户注册默认电子钱包金额1000元 */
    public static final String USER_BALANCE_INIT = "1000";

    /** 电子钱包收入支出 */
    public static final String BALANCE_OUT = "支出";
    public static final String BALANCE_IN = "收入";




}
