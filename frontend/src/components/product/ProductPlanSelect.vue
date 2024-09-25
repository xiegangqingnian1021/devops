<template>
  <el-dialog :title="title" :visible.sync="open" width="1000px" :close-on-click-modal="false" :show-close="false" @open="handleOpen">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="物料" prop="materialName">
        <el-input v-model="queryParams.materialName" placeholder="请输入物料" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="生产日期" prop="productDate">
        <el-date-picker clearable size="small" v-model="queryParams.productDate" type="date" value-format="yyyy-MM-dd" placeholder="选择生产日期">
        </el-date-picker>
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

    <el-table v-loading="loading" :data="productPlanDetailList">
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="计划号" align="center" prop="planNo" />
      <el-table-column label="产品" align="center" prop="materialName" />
      <el-table-column label="型号" prop="materialModel">
      </el-table-column>
      <el-table-column label="规格" prop="materialSpecification">
      </el-table-column>
      <el-table-column label="单位" prop="materialUnit">
      </el-table-column>
      <el-table-column label="生产数量" align="center" prop="quantity" />
      <el-table-column label="生产日期" align="center" prop="productDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.productDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
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
import { listProductPlanDetail } from "@/api/mesPlan/productPlanDetail";

export default {
  name: "ProductPlanSelect",
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
      default: "选择生产计划",
    },
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      productPlanDetailList: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        planId: null,
        materialId: null,
        materialName: null,
        auditStatus: '1',
      },
    };
  },
  created() {

  },
  methods: {
    handleOpen() {
      this.getList();
    },
    handleSelect(row) {
      this.$emit('onSelected', row);
    },
    /** 查询生产计划明细列表 */
    getList() {
      this.loading = true;
      listProductPlanDetail(this.queryParams).then(response => {
        this.productPlanDetailList = response.rows;
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
