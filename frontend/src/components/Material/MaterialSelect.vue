<template>
  <el-dialog :title="title" :visible.sync="open" width="1000px" :close-on-click-modal="false" :show-close="false" @open="handleOpen">
    <el-row :gutter="20">
      <!--分类数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-tree :data="categoryOptions" :props="defaultProps" :expand-on-click-node="false" ref="tree" default-expand-all @node-click="handleNodeClick" />
        </div>
      </el-col>
      <!--物料数据-->
      <el-col :span="20" :xs="24">
        <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="物料编码" prop="code">
            <el-input v-model="queryParams.code" placeholder="请输入物料编码" clearable size="small" @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="物料名称" prop="name">
            <el-input v-model="queryParams.name" placeholder="请输入物料名称" clearable size="small" @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="型号" prop="model">
            <el-input v-model="queryParams.model" placeholder="请输入物料型号" clearable size="small" @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="规格" prop="specification">
            <el-input v-model="queryParams.specification" placeholder="请输入物料规格" clearable size="small" @keyup.enter.native="handleQuery" />
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
          <el-table-column label="物料编码" align="center" prop="code" />
          <el-table-column label="物料名称" align="center" prop="name" />
          <el-table-column label="型号" align="center" prop="model" />
          <el-table-column label="规格" align="center" prop="specification" />
          <el-table-column label="单位" align="center" prop="unit" />
          <el-table-column label="单价" align="center" prop="price" />
          <el-table-column label="类型" align="center" prop="type" :formatter="typeFormat" />
          <el-table-column label="备注" align="center" prop="remark" />
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button v-no-more-click size="mini" type="text" icon="el-icon-check" @click="handleSelect(scope.row)">选择</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { listMaterialInfo } from "@/api/material/materialInfo";
import { listMaterialCategory } from "@/api/material/materialCategory";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
export default {
  name: "MaterialInfo",
  components: {
    Treeselect
  },
  props: {
    open: {
      type: Boolean,
      default: false,
    },
    // 弹出层标题
    title: {
      type: String,
      default: "选择物料档案",
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
      defaultProps: {
        children: "children",
        label: "name"
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        categoryId: null,
        code: null,
        name: null,
        model: null,
        specification: null,
        unit: null,
        type: null,
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
  },
  methods: {
    handleOpen() {
      this.getList();
      this.getTreeselect();
    },
    handleSelect(row) {
      if (row) {
        this.selectedList = [];
        this.selectedList.push(row);
      }
      this.$emit('onSelected', this.selectedList);
    },
    /** 查询下拉树结构 */
    getTreeselect() {
      listMaterialCategory().then(response => {
        this.categoryOptions = [];
        const data = { id: 0, name: '分类', children: [] };
        data.children = this.handleTree(response.data, "id", "parentId");
        this.categoryOptions.push(data);
      });
    },
    normalizer(node) {
      return {
        id: node.id,
        label: node.name,
        children: node.children,
      }
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.categoryId = data.id;
      this.getList();
    },
    /** 查询物料档案管理列表 */
    getList() {
      this.loading = true;
      listMaterialInfo(this.queryParams).then(response => {
        this.materialInfoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 类型字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        categoryId: null,
        code: null,
        name: null,
        model: null,
        specification: null,
        unit: null,
        price: null,
        type: null,
        remark: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.queryParams.categoryId = null;
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
