<template>
  <el-dialog :title="title" :visible.sync="open" width="1000px" :close-on-click-modal="false" :show-close="false" append-to-body>
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="计划编号" prop="purchasePlanNo">
        <el-input v-model="queryParams.purchasePlanNo" placeholder="请输入计划编号" clearable size="small"
          @keyup.enter.native="handleQuery" />
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

    <el-table v-loading="loading" :data="planList" @selection-change="handleSelectionChange">
      <el-table-column v-if="!isSingle" type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="单据编号" align="center" prop="purchasePlanNo" />
      <el-table-column label="申请人" align="center" prop="applyUser" :formatter="applyUserFormat" />
      <el-table-column label="申请日期" align="center" prop="applyTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.applyTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
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
import { listPlan } from "@/api/purchase/plan";
export default {
  name: "PlanSelect",
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
      default: "选择采购计划",
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
      //表格数据
      planList: [],
      selectedList: [],
      // 类型字典
      saleOrderNoOptions: [],
      applyUserOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        purchasePlanNo: null,
        saleOrderNo: null,
        applyUser: null,
        applyTime: null,
        applyStatus: null,
        auditUser: null,
        auditTime: null,
        auditStatus: '1',
        orderByColumn: 'apply_time',
        isAsc: 'desc',
      },
    };
  },
  created() {
    this.getQueryData("query_sale_order").then(res => {
      this.saleOrderNoOptions = res.data;
    });
    this.getQueryData("query_user").then(res => {
      this.applyUserOptions = res.data;
    });
    this.getList();
  },
  methods: {
    // 销售订单号字典翻译
    saleOrderNoFormat(row, column) {
      return this.selectDictLabel(this.saleOrderNoOptions, row.saleOrderNo);
    },
    // 申请人字典翻译
    applyUserFormat(row, column) {
      return this.selectDictLabel(this.applyUserOptions, row.applyUser);
    },
    handleSelect(row) {
      if (row) {
        this.selectedList = [];
        this.selectedList.push(row);
      }
      this.$emit('onPlanSelected', this.selectedList);
    },
    /** 查询物料档案管理列表 */
    getList() {
      this.loading = true;
      listPlan(this.queryParams).then(response => {
        this.planList = response.rows;
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
      this.$emit('onPlanCancel')
    }
  }
};
</script>
