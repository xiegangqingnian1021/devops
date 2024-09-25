<template>
  <el-dialog :title="title" :visible.sync="open" width="1000px" :close-on-click-modal="false" :show-close="false" append-to-body @open="handleOpen">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="退货单号" prop="returnNo">
        <el-input v-model="queryParams.returnNo" placeholder="请输入退货单号" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="发货单号" prop="deliveryNo">
        <el-input v-model="queryParams.deliveryNo" placeholder="请输入发货单号" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="合同编号" prop="contractNo">
        <el-input v-model="queryParams.contractNo" placeholder="请输入合同编号" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="客户名称" prop="customerName">
        <el-input v-model="queryParams.customerName" placeholder="请输入客户名称" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="退货日期">
        <el-date-picker v-model="daterangeReturnDate" size="small" style="width: 240px" value-format="yyyy-MM-dd" type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button v-no-more-click type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button v-no-more-click icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col v-if="!isSingle" :span="1.5">
        <el-button v-no-more-click type="primary" plain icon="el-icon-check" :disabled="multiple" size="mini" @click="handleSelect">选择
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button v-no-more-click type="warning" plain icon="el-icon-close" size="mini" @click="cancel">取消</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="returnList" @selection-change="handleSelectionChange">
      <el-table-column v-if="!isSingle" type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="退货单号" align="center" prop="returnNo" />
      <el-table-column label="发货单号" align="center" prop="deliveryNo" />
      <el-table-column label="合同编号" align="center" prop="contractNo" />
      <el-table-column label="客户名称" align="center" prop="customerName" />
      <el-table-column label="退货日期" align="center" prop="returnDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.returnDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="退款金额" align="center" prop="returnAmount" />
      <el-table-column label="入库状态" align="center" prop="status" :formatter="statusFormat" />
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
import {
  listReturn,
} from "@/api/sale/return";
export default {
  name: "ReturnSelect",
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
      default: "选择退货单",
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
      // 销售退货表格数据
      returnList: [],
      selectedList: [],
      daterangeReturnDate: [],
      // 入库状态字典
      statusOptions: [],
      // 创建人字典
      createByOptions: [],
      // 创建时间时间范围
      daterangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        returnNo: null,
        deliveryNo: null,
        contractNo: null,
        customerName: null,
        returnDate: null,
        status: '0', // 未入库状态
      },
    };
  },
  created() {
    this.getDicts("scm_in_warehouse_status").then(response => {
      this.statusOptions = response.data;
    });
    this.getQueryData("query_user").then(res => {
      this.createByOptions = res.data;
    });
  },
  methods: {
    handleOpen() {
      this.getList();
    },
    handleSelect(row) {
      if (row) {
        this.selectedList = [];
        this.selectedList.push(row);
      }
      this.$emit('onReturnSelected', this.selectedList);
    },

    // 状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },

    // 送货方式字典翻译
    deliveryTypeFormat(row, column) {
      return this.selectDictLabel(this.deliveryTypeOptions, row.deliveryType);
    },

    /** 查询销售退货列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeReturnDate && '' != this.daterangeReturnDate) {
        this.queryParams.params["beginReturnDate"] = this.daterangeReturnDate[0];
        this.queryParams.params["endReturnDate"] = this.daterangeReturnDate[1];
      }
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      listReturn(this.queryParams).then(response => {
        this.returnList = response.rows;
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
      this.daterangeReturnDate = [];
      this.daterangeCreateTime = [];
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
      this.$emit('onReturnCancel')
    }
  }
};

</script>
