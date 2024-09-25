package com.neu.carbon.wms.controller;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.neu.carbon.wms.domain.*;
import com.neu.carbon.wms.service.*;
import com.neu.common.annotation.Log;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.core.page.TableDataInfo;
import com.neu.common.enums.BusinessType;
import com.neu.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 仓库物料信息Controller
 *
 * @author neusoft
 * @date 2022-06-24
 */
@Api(tags = {"智能仓储WMS-物料库存"})
@RestController
@RequestMapping("/material/materialInventory")
public class WmsWarehouseMaterialController extends BaseController {
    @Autowired
    private IWmsWarehouseMaterialService wmsWarehouseMaterialService;
    @Autowired
    private IWmsMaterialInfoService wmsMaterialInfoService;
    @Autowired
    private IWmsWarehouseService warehouseService;
    @Autowired
    private IWmsWarehouseRegionService whRegionService;
    @Autowired
    private IWmsWarehouseLocationService whLocationService;

    /**
     * 查询仓库物料信息列表
     */
    @GetMapping("/list")
    @ApiOperation("查询仓库物料信息列表")
    @DynamicResponseParameters(name = "materialMaterialInventoryList",
            properties = {
                    @DynamicParameter(name = "total", value = "总记录数"),
                    @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = WmsWarehouseMaterial.class),
                    @DynamicParameter(name = "msg", value = "返回消息内容")
            })
    public TableDataInfo list(WmsWarehouseMaterial wmsWarehouseMaterial) {
        startPage();
        List<WmsWarehouseMaterial> list = wmsWarehouseMaterialService.selectWmsWarehouseMaterialList(wmsWarehouseMaterial);

        list.stream().forEach(whMaterial -> {
            //获取物料档案信息
            WmsMaterialInfo material = wmsMaterialInfoService.selectWmsMaterialInfoById(whMaterial.getMaterialId());
            if (material != null) {
                whMaterial.setMaterialCode(material.getCode());
                whMaterial.setMaterialModel(material.getModel());
                whMaterial.setMaterialName(material.getName());
                whMaterial.setMaterialPrice(material.getPrice());
                whMaterial.setMaterialSpecification(material.getSpecification());
                whMaterial.setMaterialUnit(material.getUnit());
            }
            //设置仓库信息
            WmsWarehouse wh = warehouseService.selectWmsWarehouseById(whMaterial.getWarehouseId());
            if (wh != null) {
                whMaterial.setWarehouseName(wh.getName());
            }
        });
        return getDataTable(list);
    }

    /**
     * 导出仓库物料信息列表
     */
    @ApiOperation("导出仓库物料信息列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('material:materialInventory:export')")
    @Log(title = "仓库物料信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WmsWarehouseMaterial wmsWarehouseMaterial) {
        List<WmsWarehouseMaterial> list = wmsWarehouseMaterialService.selectWmsWarehouseMaterialList(wmsWarehouseMaterial);
        list.stream().forEach(whMaterial -> {
            //获取物料档案信息
            WmsMaterialInfo material = wmsMaterialInfoService.selectWmsMaterialInfoById(whMaterial.getMaterialId());
            if (material != null) {
                whMaterial.setMaterialCode(material.getCode());
                whMaterial.setMaterialModel(material.getModel());
                whMaterial.setMaterialName(material.getName());
                whMaterial.setMaterialPrice(material.getPrice());
                whMaterial.setMaterialSpecification(material.getSpecification());
                whMaterial.setMaterialUnit(material.getUnit());
            }
            //设置仓库信息
            WmsWarehouse wh = warehouseService.selectWmsWarehouseById(whMaterial.getWarehouseId());
            if (wh != null) {
                whMaterial.setWarehouseName(wh.getName());
            }
        });
        ExcelUtil<WmsWarehouseMaterial> util = new ExcelUtil<WmsWarehouseMaterial>(WmsWarehouseMaterial.class);
        return util.exportExcel(list, "materialInventory");
    }

    /**
     * 获取仓库物料信息详细信息
     */
    @ApiOperation("获取仓库物料信息详细信息")
    @DynamicResponseParameters(name = "materialMaterialInventoryGet",
            properties = {
                    @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
                    @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = WmsWarehouseMaterial.class),
                    @DynamicParameter(name = "msg", value = "返回消息内容")
            })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        WmsWarehouseMaterial wmsWarehouseMaterial = wmsWarehouseMaterialService.selectWmsWarehouseMaterialById(id);
        WmsMaterialInfo material = wmsMaterialInfoService.selectWmsMaterialInfoById(wmsWarehouseMaterial.getMaterialId());
        //设置物料信息
        if (material != null) {
            wmsWarehouseMaterial.setMaterialCode(material.getCode());
            wmsWarehouseMaterial.setMaterialModel(material.getModel());
            wmsWarehouseMaterial.setMaterialName(material.getName());
            wmsWarehouseMaterial.setMaterialPrice(material.getPrice());
            wmsWarehouseMaterial.setMaterialSpecification(material.getSpecification());
            wmsWarehouseMaterial.setMaterialUnit(material.getUnit());
        }

        List<WmsWarehouseMaterialDetail> detailList = wmsWarehouseMaterial.getWmsWarehouseMaterialDetailList();
        if (detailList != null && !detailList.isEmpty()) {
            detailList.stream().forEach(detail -> {
                //设置物料信息
                if (material != null) {
                    detail.setMaterialCategoryId(material.getCategoryId());
                    detail.setMaterialId(material.getId());
                    detail.setMaterialCode(material.getCode());
                    detail.setMaterialModel(material.getModel());
                    detail.setMaterialName(material.getName());
                    detail.setMaterialSpecification(material.getSpecification());
                    detail.setMaterialUnit(material.getUnit());
                }
                //设置仓库信息
                WmsWarehouse wh = warehouseService.selectWmsWarehouseById(detail.getWhId());
                if (wh != null) {
                    detail.setWarehouseName(wh.getName());
                }
                WmsWarehouseRegion region = whRegionService.selectWmsWarehouseRegionById(detail.getWhRegionId());
                if (region != null) {
                    detail.setWhRegionName(region.getName());
                }
                WmsWarehouseLocation location = whLocationService.selectWmsWarehouseLocationById(detail.getWhLocationId());
                if (location != null) {
                    detail.setWhLocationName(location.getName());
                }
            });
        }
        return AjaxResult.success(wmsWarehouseMaterial);
    }

    /**
     * 新增仓库物料信息
     */
    @ApiOperation("新增仓库物料信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('material:materialInventory:add')")
    @Log(title = "仓库物料信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmsWarehouseMaterial wmsWarehouseMaterial) {
        WmsWarehouseMaterial condition = new WmsWarehouseMaterial();
        condition.setMaterialId(wmsWarehouseMaterial.getMaterialId());
        condition.setWarehouseId(wmsWarehouseMaterial.getMaterialId());
        List<WmsWarehouseMaterial> list = wmsWarehouseMaterialService.selectWmsWarehouseMaterialList(condition);
        if (list != null && !list.isEmpty()) {
            return AjaxResult.error("当前仓库中已有此物料库存，不能再添加");
        }
        return toAjax(wmsWarehouseMaterialService.insertWmsWarehouseMaterial(wmsWarehouseMaterial));
    }

    /**
     * 修改仓库物料信息
     */
    @ApiOperation("修改仓库物料信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('material:materialInventory:edit')")
    @Log(title = "仓库物料信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmsWarehouseMaterial wmsWarehouseMaterial) {
        return toAjax(wmsWarehouseMaterialService.updateWmsWarehouseMaterial(wmsWarehouseMaterial));
    }

    /**
     * 删除仓库物料信息
     */
    @ApiOperation("删除仓库物料信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('material:materialInventory:remove')")
    @Log(title = "仓库物料信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(wmsWarehouseMaterialService.deleteWmsWarehouseMaterialByIds(ids));
    }
}
