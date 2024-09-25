package com.neu.smty.api.common;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.common.config.NeuConfig;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.domain.entity.SysDictData;
import com.neu.common.utils.file.FileUploadUtils;
import com.neu.framework.config.ServerConfig;
import com.neu.smty.api.base.ApiBaseController;
import com.neu.smty.api.vo.DicVo;
import com.neu.system.service.ISysDictTypeService;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "通用工具相关接口")
@RestController
@RequestMapping("/api")
public class ToolkitRestController extends ApiBaseController {
    @Autowired
    private ISysDictTypeService dictTypeService;
    @Autowired
    private ServerConfig serverConfig;
    // APP 上传支持文件格式
    public static final String[] IMAGE_ALLOWED_EXTENSION = {
            // 图片
            "bmp", "gif", "jpg", "jpeg", "png"
    };

    @ApiOperation("根据字典类型获取字典")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictType", value = "字典类型", dataTypeClass = String.class, required = true, paramType = "body")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功返回JSON{code:'返回码',msg:'错误信息',data:[DicVO类型字典数组]}，返回码：0成功；其他失败", response = DicVo.class)})
    @GetMapping("/dict/data/type/{dictType}")
    public AjaxResult getDicItems(@PathVariable String dictType) {
        List<SysDictData> sysDicList = dictTypeService.selectDictDataByType(dictType);
        List<DicVo> list = new ArrayList<>();
        for (SysDictData dictData : sysDicList) {
            DicVo vo = new DicVo();
            vo.setDictLabel(dictData.getDictLabel());
            vo.setDictValue(dictData.getDictValue());
            list.add(vo);
        }
        return AjaxResult.success(list);
    }

    /**
     * 通用上传请求
     */
    @ApiOperation("通用上传接口")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码"),
            @DynamicParameter(name = "msg", value = "消息内容"),
            @DynamicParameter(name = "fileName", value = "文件名"),
            @DynamicParameter(name = "url", value = "文件访问地址"),
    })
    @PostMapping("/common/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception {
        try {
            // 上传文件路径
            String filePath = NeuConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file, IMAGE_ALLOWED_EXTENSION);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }
}
