<template>
  <el-dialog :title="title" :visible.sync="open" width="1000px" :close-on-click-modal="false" :show-close="false"
    append-to-body>
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="申请单号" prop="applyNo">
        <el-input v-model="queryParams.applyNo" placeholder="请输入申请单号" clearable size="small"
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="申请类型" prop="applyType">
        <el-select v-model="queryParams.applyType" placeholder="请选择申请类型" clearable filterable size="small">
          <el-option v-for="dict in applyTypeOptions" :key="dict.dictValue" :label="dict.dictLabel"
            :value="dict.dictValue" />
        </el-select>
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
    <el-table v-loading="loading" :data="applyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="单据编号" align="center" prop="applyNo" />
      <el-table-column label="类型" align="center" prop="applyType" :formatter="applyTypeFormat" />
      <el-table-column label="计划单号" align="center" prop="planNo" />
      <el-table-column label="申请人" align="center" prop="applyUser" :formatter="applyUserFormat" />
      <el-table-column label="申请日期" align="center" prop="applyTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.applyTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
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
    listApply
  } from "@/api/purchase/apply";
  export default {
    name: "ApplySelect",
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
        default: "选择采购申请",
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
        applyList: [],
        selectedList: [],
        // 申请类别字典
        applyTypeOptions: [],
        applyUserOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          applyNo: null,
          planNo: null,
          applyUser: null,
          applyTime: null,
          applyStatus: null,
          applyType: null,
          auditUser: null,
          auditTime: null,
          auditStatus: null,
          orderByColumn: 'apply_time',
          isAsc: 'desc',
        },
      };
    },
    created() {
      this.getDicts("purchase_apply_type").then(response => {
        this.applyTypeOptions = response.data;
      });
      this.getQueryData("query_user").then(res => {
        this.applyUserOptions = res.data;
      });
      this.getList();
    },
    methods: {
      // 申请类别字典翻译
      applyTypeFormat(row, column) {
        return this.selectDictLabel(this.applyTypeOptions, row.applyType);
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
        this.$emit('onSelected', this.selectedList);
      },
      /** 查询采购申请列表 */
      getList() {
        this.loading = true;
        listApply(this.queryParams).then(response => {
          this.applyList = response.rows;
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
