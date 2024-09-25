<template>
  <el-dialog :title="title" :visible.sync="open" width="1000px" :close-on-click-modal="false" :show-close="false"
    @open="handleOpen">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="作业名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入作业名称" clearable size="small"
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="开始时间">
        <el-date-picker v-model="daterangeStartTime" size="small" style="width: 240px" value-format="yyyy-MM-dd"
          type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间">
        <el-date-picker v-model="daterangeEndTime" size="small" style="width: 240px" value-format="yyyy-MM-dd"
          type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
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

    <el-table v-loading="loading" :data="productJobList">
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="作业名称" align="center" prop="name" />
      <el-table-column label="生产计划" align="center" prop="planNo" />
      <el-table-column label="产品" align="center" prop="productName" />
      <el-table-column label="型号" prop="materialModel">
      </el-table-column>
      <el-table-column label="规格" prop="materialSpecification">
      </el-table-column>
      <el-table-column label="单位" prop="materialUnit">
      </el-table-column>
      <el-table-column label="开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="生产数量" align="center" prop="productQuantity" />
      <el-table-column label="作业状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-no-more-click size="mini" type="text" icon="el-icon-check" @click="handleSelect(scope.row)">选择</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />
  </el-dialog>
</template>

<script>
  import {
    listProductJob
  } from "@/api/mesProduct/productJob";
  export default {
    name: "ProductJobSelect",
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
        default: "选择生产作业",
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
        // 生产作业表格数据
        productJobList: [],
        // 开始时间时间范围
        daterangeStartTime: [],
        // 结束时间时间范围
        daterangeEndTime: [],
        // 作业状态：0未执行1执行中2已完成3异常字典
        statusOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          name: null,
          planId: null,
          scheduleId: null,
          productId: null,
          startTime: null,
          endTime: null,
          status: this.status,
        },
      };
    },
    created() {
      this.getDicts("product_job_status").then(response => {
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
      /** 查询生产作业列表 */
      getList() {
        this.loading = true;
        this.queryParams.params = {};
        if (null != this.daterangeStartTime && '' != this.daterangeStartTime) {
          this.queryParams.params["beginStartTime"] = this.daterangeStartTime[0];
          this.queryParams.params["endStartTime"] = this.daterangeStartTime[1];
        }
        if (null != this.daterangeEndTime && '' != this.daterangeEndTime) {
          this.queryParams.params["beginEndTime"] = this.daterangeEndTime[0];
          this.queryParams.params["endEndTime"] = this.daterangeEndTime[1];
        }
        listProductJob(this.queryParams).then(response => {
          this.productJobList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 作业状态：0未执行1执行中2已完成3异常字典翻译
      statusFormat(row, column) {
        return this.selectDictLabel(this.statusOptions, row.status);
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.daterangeStartTime = [];
        this.daterangeEndTime = [];
        this.resetForm("queryForm");
        this.handleQuery();
      },
    }
  };

</script>
