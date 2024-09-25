package com.neu.smty.api.common;


import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.common.core.domain.AjaxResult;
import com.neu.smty.api.base.ApiBaseController;
import com.neu.smty.constant.Constant;
import com.neu.smty.domain.CmtAdv;
import com.neu.smty.service.ICmtAdvService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;


@Api(tags = "广告轮播接口")
@RestController
@RequestMapping("/api/adv")
public class AdvApi extends ApiBaseController {

    @Autowired
    private ICmtAdvService comAdvService;

    @ApiOperation("查询广告轮播")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码"),
            @DynamicParameter(name = "data", value = "列表数据（数组类型）", dataTypeClass = CmtAdv.class),
            @DynamicParameter(name = "msg", value = "消息内容")
    })
    @GetMapping("/list")
    public AjaxResult list4ComAdv(CmtAdv comAdv) {
        // 设置类型和有效
        comAdv.setStatus(Constant.STATUS_ACTIIVE);
        List<CmtAdv> list = comAdvService.selectCmtAdvList(comAdv);
        list.sort(Comparator.comparing(CmtAdv::getSort));
        return AjaxResult.success(list);
    }

}
