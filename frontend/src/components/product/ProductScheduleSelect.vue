<template>
  <el-dialog :title="title" :visible.sync="open" width="1000px" :close-on-click-modal="false" :show-close="false" @open="handleOpen">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="计划号" prop="planNo">
        <el-input v-model="queryParams.planNo" placeholder="请输入计划号" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="物料" prop="materialName">
        <el-input v-model="queryParams.materialName" placeholder="请输入物料" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="BOM" prop="bomId">
        <el-select v-model="queryParams.bomId" placeholder="请选择BOM" clearable filterable size="small">
          <el-option v-for="dict in bomIdOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="生产线" prop="productLineId">
        <el-select v-model="queryParams.productLineId" placeholder="请选择生产线" clearable filterable size="small">
          <el-option v-for="dict in productLineIdOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="工艺" prop="processId">
        <el-select v-model="queryParams.processId" placeholder="请选择工艺" clearable filterable size="small">
          <el-option v-for="dict in processIdOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="项目" prop="projectId">
        <el-select v-model="queryParams.projectId" placeholder="请选择项目" clearable filterable size="small">
          <el-option v-for="dict in projectIdOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="生产日期">
        <el-date-picker v-model="daterangeProductDate" size="small" style="width: 240px" value-format="yyyy-MM-dd" type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
      </el-form-item>
      <el-form-item label="交货日期">
        <el-date-picker v-model="daterangeDeliveryDate" size="small" style="width: 240px" value-format="yyyy-MM-dd" type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable filterable size="small">
          <el-option v-for="dict in statusOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
        </el-select>
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

    <el-table v-loading="loading" :data="productScheduleList">
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="计划号" align="center" prop="planNo" />
      <el-table-column label="物料" align="center" prop="materialName" />
      <el-table-column label="型号" prop="materialModel">
      </el-table-column>
      <el-table-column label="规格" prop="materialSpecification">
      </el-table-column>
      <el-table-column label="单位" prop="materialUnit">
      </el-table-column>
      <el-table-column label="BOM" align="center" prop="bomId" :formatter="bomIdFormat" />
      <el-table-column label="生产线" align="center" prop="productLineId" :formatter="productLineIdFormat" />
      <el-table-column label="工艺" align="center" prop="processId" :formatter="processIdFormat" />
      <el-table-column label="项目" align="center" prop="projectId" :formatter="projectIdFormat" />
      <el-table-column label="待产数量" align="center" prop="requireQuantity" />
      <el-table-column label="已产数量" align="center" prop="productQuantity" />
      <el-table-column label="生产日期" align="center" prop="productDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.productDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="交货日期" align="center" prop="deliveryDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.deliveryDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发布状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="领料状态" align="center" prop="receiveStatus" :formatter="receiveStatusFormat" />
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
import { listProductSchedule } from "@/api/mesPlan/productSchedule";
export default {
  name: "ProductScheduleSelect",
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
      default: "选择生产计划排产",
    },
    receiveStatus: {
      type: String,
      default: null,
    },
    status: {
      type: String,
      default: null,
    }
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
      // 计划排产表格数据
      productScheduleList: [],
      // BOM字典
      bomIdOptions: [],
      bomIdOptionsByMaterial: [],
      // 生产线字典
      productLineIdOptions: [],
      productLineIdOptionsByMaterial: [],
      // 工艺字典
      processIdOptions: [],
      processIdOptionsByMaterial: [],
      // 项目字典
      projectIdOptions: [],
      // 生产日期时间范围
      daterangeProductDate: [],
      // 交货日期时间范围
      daterangeDeliveryDate: [],
      // 状态字典
      statusOptions: [],
      // 领料状态字典
      receiveStatusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        planId: null,
        planNo: null,
        orderId: null,
        materialId: null,
        materialName: null,
        bomId: null,
        productLineId: null,
        processId: null,
        projectId: null,
        productDate: null,
        deliveryDate: null,
        status: this.status,
        receiveStatus: this.receiveStatus,
      },
    };
  },
  created() {
    this.getQueryData("query_bom").then(res => {
      this.bomIdOptions = res.data;
    });
    this.getQueryData("query_product_line").then(res => {
      this.productLineIdOptions = res.data;
    });
    this.getQueryData("process_query").then(res => {
      this.processIdOptions = res.data;
    });
    this.getQueryData("query_project").then(res => {
      this.projectIdOptions = res.data;
    });
    this.getDicts("product_schedule_status").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("schedule_requisition_status").then(response => {
      this.receiveStatusOptions = response.data;
    });
  },
  methods: {
    handleOpen() {
      this.getList();
    },
    handleSelect(row) {
      this.$emit('onSelected', row);
    },
    /** 查询计划排产列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeProductDate && '' != this.daterangeProductDate) {
        this.queryParams.params["beginProductDate"] = this.daterangeProductDate[0];
        this.queryParams.params["endProductDate"] = this.daterangeProductDate[1];
      }
      if (null != this.daterangeDeliveryDate && '' != this.daterangeDeliveryDate) {
        this.queryParams.params["beginDeliveryDate"] = this.daterangeDeliveryDate[0];
        this.queryParams.params["endDeliveryDate"] = this.daterangeDeliveryDate[1];
      }
      listProductSchedule(this.queryParams).then(response => {
        this.productScheduleList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // BOM字典翻译
    bomIdFormat(row, column) {
      return this.selectDictLabel(this.bomIdOptions, row.bomId);
    },
    // 生产线字典翻译
    productLineIdFormat(row, column) {
      return this.selectDictLabel(this.productLineIdOptions, row.productLineId);
    },
    // 工艺字典翻译
    processIdFormat(row, column) {
      return this.selectDictLabel(this.processIdOptions, row.processId);
    },
    // 项目字典翻译
    projectIdFormat(row, column) {
      return this.selectDictLabel(this.projectIdOptions, row.projectId);
    },
    // 状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 领料状态字典翻译
    receiveStatusFormat(row, column) {
      return this.selectDictLabel(this.receiveStatusOptions, row.receiveStatus);
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
      this.daterangeProductDate = [];
      this.daterangeDeliveryDate = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
  }
};
</script>
