<template>
  <el-dialog :title="title" :visible.sync="open" width="1000px" :close-on-click-modal="false" :show-close="false" @open="handleOpen">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="生产计划" prop="planNo">
        <el-input v-model="queryParams.planNo" placeholder="请输入生产计划" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="生产作业" prop="jobName">
        <el-input v-model="queryParams.jobName" placeholder="请输入生产作业" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="产品" prop="materialName">
        <el-input v-model="queryParams.materialName" placeholder="请输入产品" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="完工日期">
        <el-date-picker v-model="daterangeFinishDate" size="small" style="width: 240px" value-format="yyyy-MM-dd" type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
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

    <el-table v-loading="loading" :data="productFinishList">
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="生产计划" align="center" prop="planNo" />
      <el-table-column label="生产作业" align="center" prop="jobName" />
      <el-table-column label="产品" align="center" prop="materialName" />
      <el-table-column label="型号" prop="materialModel">
      </el-table-column>
      <el-table-column label="规格" prop="materialSpecification">
      </el-table-column>
      <el-table-column label="单位" prop="materialUnit" />
      <el-table-column label="完工日期" align="center" prop="finishDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.finishDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="生产数量" align="center" prop="finishQuantity" />
      <el-table-column label="生产批号" align="center" prop="batchNo" />
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-no-more-click size="mini" type="text" icon="el-icon-check" @click="handleSelect(scope.row)">选择</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />
  </el-dialog>
</template>

<script>
import { listProductFinish, getProductFinish, delProductFinish, addProductFinish, updateProductFinish, exportProductFinish } from "@/api/mesProduct/productFinish";

export default {
  name: "ProductFinishSelect",
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
      default: "选择生产完工单",
    },
    // 单据状态
    status: {
      type: String,
      default: '',
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
      // 生产完工单表格数据
      productFinishList: [],
      // 完工日期时间范围
      daterangeFinishDate: [],
      // 生产完工单表格数据
      productFinishList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        planId: null,
        planNo: null,
        scheduleId: null,
        jobId: null,
        jobName: null,
        materialId: null,
        materialName: null,
        finishDate: null,
        status: this.status,
      },
    };
  },
  created() {
    this.getDicts("product_finish_status").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    handleOpen() {
      this.getList();
    },
    handleSelect(row) {
      this.$emit('onSelected', row);
    },
    // 取消按钮
    cancel() {
      this.$emit('onCancel')
    },
    // 状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    /** 查询生产完工单列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeFinishDate && '' != this.daterangeFinishDate) {
        this.queryParams.params["beginFinishDate"] = this.daterangeFinishDate[0];
        this.queryParams.params["endFinishDate"] = this.daterangeFinishDate[1];
      }
      listProductFinish(this.queryParams).then(response => {
        this.productFinishList = response.rows;
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
      this.daterangeFinishDate = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
  }
};
</script>
