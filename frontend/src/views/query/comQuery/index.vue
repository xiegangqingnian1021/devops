<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入名称" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="关键字" prop="code">
        <el-input v-model="queryParams.code" placeholder="请输入关键字" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择通用查询类型" clearable filterable size="small">
          <el-option v-for="dict in typeOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button v-no-more-click type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button v-no-more-click icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button v-no-more-click type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['query:comQuery:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button v-no-more-click type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['query:comQuery:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button v-no-more-click type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['query:comQuery:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button v-no-more-click type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['query:comQuery:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="comQueryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="关键字" align="center" prop="code" />
      <el-table-column label="通用查询类型" align="center" prop="type" :formatter="typeFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-no-more-click size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['query:comQuery:edit']">修改</el-button>
          <el-button v-no-more-click size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['query:comQuery:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改通用查询对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="关键字" prop="code">
          <el-input v-model="form.code" placeholder="请输入关键字" />
        </el-form-item>
        <el-form-item label="sql语句" prop="customSql">
          <el-input v-model="form.customSql" type="textarea" placeholder="请输入内容" />
          <el-button v-no-more-click type="primary" size="mini" @click="handleTest(form.customSql)">测试</el-button>
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择通用查询类型" clearable filterable>
            <el-option v-for="dict in typeOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="说明" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入说明" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button v-no-more-click type="primary" @click="submitForm">确 定</el-button>
        <el-button v-no-more-click @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listComQuery, getComQuery, delComQuery, addComQuery, updateComQuery, exportComQuery, testBizData, checkCode } from "@/api/query/comQuery";

export default {
  name: "ComQuery",
  components: {
  },
  data() {
    var codeUnique = (rule, value, callback) => {
      if (value !== '') {
        if (this.oldCode == value) {
          callback();
        }
        checkCode(value).then(res => {
          if (res.data == '0') {
            callback(new Error('关键字重复'));
          } else {
            callback();
          }
        })
      }
    };
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
      // 通用查询表格数据
      comQueryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 通用查询类型字典
      typeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        code: null,
        type: null,
      },
      // 表单参数
      form: {},
      oldCode: '',
      // 表单校验
      rules: {
        name: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        code: [
          { required: true, message: "关键字不能为空", trigger: "blur" },
          { validator: codeUnique, trigger: "blur" },
        ],
        customSql: [
          { required: true, message: "sql语句不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "通用查询类型不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_query_type").then(response => {
      this.typeOptions = response.data;
    });
  },
  methods: {
    handleTest(sql) {
      if (!sql) {
        this.msgError("sql不能为空")
      }
      testBizData({ customSql: sql }).then(res => {
        this.msgSuccess("测试成功");
      })
    },
    /** 查询通用查询列表 */
    getList() {
      this.loading = true;
      listComQuery(this.queryParams).then(response => {
        this.comQueryList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 通用查询类型字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        code: null,
        remark: null,
        customSql: null,
        type: null,
        config: null
      };
      this.resetForm("form");
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加通用查询";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getComQuery(id).then(response => {
        this.form = response.data;
        this.oldCode = this.form.code;
        this.open = true;
        this.title = "修改通用查询";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateComQuery(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addComQuery(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除通用查询编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delComQuery(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有通用查询数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportComQuery(queryParams);
      }).then(response => {
        this.download(response.msg);
      })
    }
  }
};
</script>
