<template>
  <el-dialog :title="title" :visible.sync="open" width="1000px" :close-on-click-modal="false" :show-close="false" @open="handleOpen">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="物料编码" prop="materialCode">
        <el-input v-model="queryParams.materialCode" placeholder="请输入物料编码" clearable size="small" />
      </el-form-item>
      <el-form-item label="物料名称" prop="materialName">
        <el-input v-model="queryParams.materialName" placeholder="请输入物料名称" clearable size="small" />
      </el-form-item>
      <el-form-item label="型号" prop="materialModel">
        <el-input v-model="queryParams.materialModel" placeholder="请输入物料型号" clearable size="small" />
      </el-form-item>
      <el-form-item label="规格" prop="materialSpecification">
        <el-input v-model="queryParams.materialSpecification" placeholder="请输入物料规格" clearable size="small" />
      </el-form-item>
      <el-form-item label="生产批号" prop="batchNo">
        <el-input v-model="queryParams.batchNo" placeholder="请输入物料名称" clearable size="small" />
      </el-form-item>
      <el-form-item label="仓库" prop="warehouseId">
        <el-select v-model="queryParams.warehouseId" placeholder="请选择库位" @change="handleWarehouseChange">
          <el-option v-for="dict in warehouseIdOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="库区" prop="whRegionId">
        <el-select v-model="queryParams.whRegionId" placeholder="请选择库区" @change="handleWhRegionChange">
          <el-option v-for="dict in whRegionOptions" :key="dict.id" :label="dict.name" :value="dict.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="库位" prop="whLocationId">
        <el-select v-model="queryParams.whLocationId" placeholder="请选择库位">
          <el-option v-for="dict in whLocationOptions" :key="dict.id" :label="dict.name" :value="dict.id" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button v-no-more-click type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button v-no-more-click icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col v-if="!isSingle" :span="1.5">
        <el-button v-no-more-click type="primary" plain icon="el-icon-check" :disabled="multiple" size="mini" @click="handleSelect">选择</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button v-no-more-click type="warning" plain icon="el-icon-close" size="mini" @click="cancel">取消</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="materialInfoList" @selection-change="handleSelectionChange">
      <el-table-column v-if="!isSingle" type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="物料编码" align="center" prop="materialCode" />
      <el-table-column label="物料名称" align="center" prop="materialName" />
      <el-table-column label="型号" align="center" prop="materialModel" />
      <el-table-column label="规格" align="center" prop="materialSpecification" />
      <el-table-column label="单位" align="center" prop="materialUnit" />
      <el-table-column label="仓库" align="center" prop="warehouseName" />
      <el-table-column label="库区" align="center" prop="whRegionName" />
      <el-table-column label="库位" align="center" prop="whLocationName" />
      <el-table-column label="生产批号" align="center" prop="batchNo" />
      <el-table-column label="现有库存" align="center" prop="inventory" />
      <el-table-column label="锁定库存" align="center" prop="lockInventory" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right">
        <template slot-scope="scope">
          <el-button v-no-more-click size="mini" type="text" icon="el-icon-check" @click="handleSelect(scope.row)">选择</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />
  </el-dialog>
</template>

<script>
import { listMaterialInventoryDetail } from "@/api/material/materialInventoryDetail";
import { listWarehouseRegion } from "@/api/warehouse/warehouseRegion";
import { listWarehouseLocation } from "@/api/warehouse/warehouseLocation";
export default {
  name: "MaterialInventoryInfo",
  components: {

  },
  props: {
    open: {
      type: Boolean,
      default: false,
    },
    // 弹出层标题
    title: {
      type: String,
      default: "选择物料库存",
    },
    //是否单选
    isSingle: {
      type: Boolean,
      default: true,
    },
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 物料档案管理表格数据
      materialInfoList: [],
      selectedList: [],
      // 类型字典
      typeOptions: [],
      categoryOptions: [],
      warehouseIdOptions: [],
      whRegionOptions: [],
      whLocationOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        materialCode: null,
        materialName: null,
        batchNo: null,
        warehouseId: null,
        whRegionId: null,
        whLocationId: null,
        materialModel: null,
        materialSpecification: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getDicts("material_type").then(response => {
      this.typeOptions = response.data;
    });
    this.getQueryData("query_warehouse").then(res => {
      this.warehouseIdOptions = res.data.map(item => ({
        dictValue: item.dictValue * 1,
        dictLabel: item.dictLabel,
      }));
    });
  },
  methods: {
    handleOpen() {
      this.getList();
    },
    //选择仓库处理函数
    handleWarehouseChange(val) {
      this.queryParams.whRegionId = null;
      this.queryParams.whLocationId = null;
      if (val) {
        this.getWhRegionOpts(val);
      }
    },
    //选择库区处理函数
    handleWhRegionChange(val) {
      this.queryParams.whLocationId = null;
      if (val) {
        this.getWhLocationOpts(val);
      }
    },
    //获取库区选项
    getWhRegionOpts(val) {
      let queryParams = {
        warehouseId: val,
      }
      listWarehouseRegion(queryParams).then(res => {
        this.whRegionOptions = res.rows;
      });
    },
    //获取库位选项
    getWhLocationOpts(val) {
      let queryParams = {
        regionId: val,
      }
      listWarehouseLocation(queryParams).then(res => {
        this.whLocationOptions = res.rows;
      })
    },
    handleSelect(row) {
      if (row) {
        this.selectedList = [];
        this.selectedList.push(row);
      }
      this.$emit('onSelected', this.selectedList);
    },
    /** 查询物料档案管理列表 */
    getList() {
      this.loading = true;
      listMaterialInventoryDetail(this.queryParams).then(response => {
        this.materialInfoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.selectedList = [];
      this.selectedList.push(selection);
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    cancel() {
      this.selectedList = [];
      this.$emit('onCancel')
    }
  }
};
</script>
