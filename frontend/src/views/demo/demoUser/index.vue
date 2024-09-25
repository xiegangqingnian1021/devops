<template>
<!--  <div class="app-container">-->
<!--    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">-->
<!--      <el-form-item label="姓名" prop="name">-->
<!--        <el-input-->
<!--          v-model="queryParams.name"-->
<!--          placeholder="请输入姓名"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="部门" prop="deptId">-->
<!--        <el-select v-model="queryParams.deptId" placeholder="请选择部门" clearable filterable size="small">-->
<!--          <el-option-->
<!--            v-for="dict in deptIdOptions"-->
<!--            :key="dict.dictValue"-->
<!--            :label="dict.dictLabel"-->
<!--            :value="dict.dictValue"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="状态" prop="status">-->
<!--        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable filterable size="small">-->
<!--          <el-option-->
<!--            v-for="dict in statusOptions"-->
<!--            :key="dict.dictValue"-->
<!--            :label="dict.dictLabel"-->
<!--            :value="dict.dictValue"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="系统用户" prop="userId">-->
<!--        <el-select v-model="queryParams.userId" placeholder="请选择系统用户" clearable filterable size="small">-->
<!--          <el-option-->
<!--            v-for="dict in userIdOptions"-->
<!--            :key="dict.dictValue"-->
<!--            :label="dict.dictLabel"-->
<!--            :value="dict.dictValue"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button v-no-more-click type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>-->
<!--        <el-button v-no-more-click icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->

<!--    <el-row :gutter="10" class="mb8">-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['demo:demoUser:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['demo:demoUser:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['demo:demoUser:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['demo:demoUser:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
<!--      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>-->
<!--    </el-row>-->

<!--    <el-table v-loading="loading" :data="demoUserList" @selection-change="handleSelectionChange">-->
<!--      <el-table-column type="selection" width="55" align="center" />-->
<!--      <el-table-column label="编号" align="center" prop="id" />-->
<!--      <el-table-column label="姓名" align="center" prop="name" />-->
<!--      <el-table-column label="部门" align="center" prop="deptId" :formatter="deptIdFormat" />-->
<!--      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" />-->
<!--      <el-table-column label="系统用户" align="center" prop="userId" :formatter="userIdFormat" />-->
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['demo:demoUser:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['demo:demoUser:remove']"-->
<!--          >删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--    </el-table>-->
<!--    -->
<!--    <pagination-->
<!--      v-show="total>0"-->
<!--      :total="total"-->
<!--      :page.sync="queryParams.pageNum"-->
<!--      :limit.sync="queryParams.pageSize"-->
<!--      @pagination="getList"-->
<!--    />-->

<!--    &lt;!&ndash; 添加或修改演示对话框 &ndash;&gt;-->
<!--    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>-->
<!--      <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
<!--        <el-form-item label="姓名" prop="name">-->
<!--          <el-input v-model="form.name" placeholder="请输入姓名" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="部门" prop="deptId">-->
<!--          <el-select v-model="form.deptId" placeholder="请选择部门" clearable filterable>-->
<!--            <el-option-->
<!--              v-for="dict in deptIdOptions"-->
<!--              :key="dict.dictValue"-->
<!--              :label="dict.dictLabel"-->
<!--              :value="parseInt(dict.dictValue)"-->
<!--            ></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="状态" prop="status">-->
<!--          <el-select v-model="form.status" placeholder="请选择状态" clearable filterable>-->
<!--            <el-option-->
<!--              v-for="dict in statusOptions"-->
<!--              :key="dict.dictValue"-->
<!--              :label="dict.dictLabel"-->
<!--              :value="dict.dictValue"-->
<!--            ></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="系统用户" prop="userId">-->
<!--          <el-select v-model="form.userId" placeholder="请选择系统用户" clearable filterable>-->
<!--            <el-option-->
<!--              v-for="dict in userIdOptions"-->
<!--              :key="dict.dictValue"-->
<!--              :label="dict.dictLabel"-->
<!--              :value="parseInt(dict.dictValue)"-->
<!--            ></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button v-no-more-click type="primary" @click="submitForm">确 定</el-button>-->
<!--        <el-button v-no-more-click @click="cancel">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->
<!--  </div>-->
</template>

<script>
import { listDemoUser, getDemoUser, delDemoUser, addDemoUser, updateDemoUser, exportDemoUser } from "@/api/demo/demoUser";

export default {
  name: "DemoUser",
  components: {
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
      // 演示表格数据
      demoUserList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 部门字典
      deptIdOptions: [],
      // 状态字典
      statusOptions: [],
      // 系统用户字典
      userIdOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        deptId: null,
        status: null,
        userId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getQueryData("dept_query").then(res => {
      this.deptIdOptions = res.data;
    });
    this.getDicts("valid_status").then(response => {
      this.statusOptions = response.data;
    });
    this.getSysUserList().then(res => {
      this.userIdOptions = [];
      let list = res.rows;
      list.forEach(element => {
        this.userIdOptions.push({
          dictValue: element.userId,
          dictLabel: element.userName,
        });
      });
    });
  },
  methods: {
    /** 查询演示列表 */
    getList() {
      this.loading = true;
      listDemoUser(this.queryParams).then(response => {
        this.demoUserList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 部门字典翻译
    deptIdFormat(row, column) {
      return this.selectDictLabel(this.deptIdOptions, row.deptId);
    },
    // 状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 系统用户字典翻译
    userIdFormat(row, column) {
      return this.selectDictLabel(this.userIdOptions, row.userId);
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
        deptId: null,
        status: null,
        userId: null
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加演示";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDemoUser(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改演示";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDemoUser(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDemoUser(this.form).then(response => {
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
      this.$confirm('是否确认删除演示编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDemoUser(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有演示数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportDemoUser(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
