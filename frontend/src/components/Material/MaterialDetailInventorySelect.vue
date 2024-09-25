<template>
  <el-dialog :title="title" :visible.sync="open" width="1000px" :close-on-click-modal="false" :show-close="false" @open="handleOpen">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
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
      <el-form-item label="物料名称" prop="materialName">
        <el-input v-model="queryParams.materialName" placeholder="请输入物料名称" clearable size="small" />
      </el-form-item>
      <el-form-item label="型号" prop="materialModel">
        <el-input v-model="queryParams.materialModel" placeholder="请输入物料型号" clearable size="small" />
      </el-form-item>
      <el-form-item label="规格" prop="materialSpecification">
        <el-input v-model="queryParams.materialSpecification" placeholder="请输入物料规格" clearable size="small" />
      </el-form-item>
      <el-form-item label="批号" prop="batchNo">
        <el-input v-model="queryParams.batchNo" placeholder="请输入生产批号" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button v-no-more-click type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button v-no-more-click icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button v-no-more-click type="warning" plain icon="el-icon-close" size="mini" @click="cancel">取消</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="materialInventoryDetailList">
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="仓库" align="center" prop="warehouseName" />
      <el-table-column label="库区" align="center" prop="whRegionName" />
      <el-table-column label="库位" align="center" prop="whLocationName" />
      <el-table-column label="物料" align="center" prop="materialName" />
      <el-table-column label="序列号" align="center" prop="serialNo" />
      <el-table-column label="生产日期" align="center" prop="productDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.productDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="生产厂家" align="center" prop="manufacturer" />
      <el-table-column label="批号" align="center" prop="batchNo" />
      <el-table-column label="库存量" align="center" prop="inventory" />
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
  name: "MaterialInventoryDetailSelect",
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
      default: "选择库位物料",
    },
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 仓库物料明细表格数据
      materialInventoryDetailList: [],
      warehouseIdOptions: [],
      whRegionOptions: [],
      whLocationOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        whId: null,
        whRegionId: null,
        whLocationId: null,
        whMaterialId: null,
        serialNo: null,
        productDate: null,
        manufacturer: null,
        batchNo: null,
        materialName: null,
        materialModel: null,
        materialSpecification: null,
      },
    };
  },
  created() {
    this.getList();
    this.getQueryData("query_warehouse").then(res => {
      this.warehouseIdOptions = res.data.map(item => ({
        dictValue: item.dictValue * 1,
        dictLabel: item.dictLabel,
      }));
    });
  },
  methods: {
    handleOpen(){
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
      this.$emit('onSelected', row);
    },
    /** 查询仓库物料明细列表 */
    getList() {
      this.loading = true;
      listMaterialInventoryDetail(this.queryParams).then(response => {
        this.materialInventoryDetailList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.$emit('onCancel')
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
  }
};
</script>
