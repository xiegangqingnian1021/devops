<template>
  <div class="app-container">
    <!-- 新增的遮罩层 -->
    <div class="mask" v-if="showMask"
         v-loading="showMask"
         element-loading-text="现在暂停所有操作，且不要刷新页面，耐心等待，预计需要1分钟"
         element-loading-spinner="el-icon-loading"></div>
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户编号" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户名称" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户邮箱" prop="userEmail">
        <el-input
          v-model="queryParams.userEmail"
          placeholder="请输入用户邮箱"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否启用" prop="userEnable">
        <el-input
          v-model="queryParams.userEnable"
          placeholder="请输入是否启用"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:openstack_user_info:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:openstack_user_info:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:openstack_user_info:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:openstack_user_info:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="openstack_user_infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="用户编号" align="center" prop="userId" />
      <el-table-column label="用户名称" align="center" prop="userName" />
      <el-table-column label="用户邮箱" align="center" prop="userEmail" />
      <el-table-column label="是否启用" align="center" prop="userEnable" :formatter="userEnableFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:openstack_user_info:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:openstack_user_info:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改用户信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户名称" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入用户名称" />
        </el-form-item>
        <el-form-item label="用户邮箱" prop="userEmail">
          <el-input v-model="form.userEmail" placeholder="请输入用户邮箱" />
        </el-form-item>
        <el-form-item label="登录密码" prop="userPwd">
          <el-input v-model="form.userPwd" placeholder="请输入登录密码" show-password />
        </el-form-item>
        <el-form-item label="是否启用" prop="userEnable">
          <!-- <el-input v-model="form.userEnable" placeholder="请输入是否启用" /> -->
          <!--
          弹出式下拉框
          label 选项名称 是 否
          value 选项值  1  0
          -->
          <el-select v-model="form.userEnable" placeholder="请选择">
            <el-option
              v-for="item in commonYesNoList"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listOpenstack_user_info, getOpenstack_user_info, delOpenstack_user_info, addOpenstack_user_info, updateOpenstack_user_info, exportOpenstack_user_info } from "@/api/system/openstack_user_info";
import { delProject_info } from '@/api/system/openstack_project_info'

export default {
  name: "Openstack_user_info",
  components: {
  },
  data() {
    return {
      // 遮罩层的控制开关
      showMask: false, //默认不开启遮罩层， 设置为true就开启遮罩层
      // 通用的YesOrNo选项列表对应的数据集
      commonYesNoList: [
        //第一个选项
        {
          label: "是",
          value: 1
        },
        //第二个选项
        {
          label: "否",
          value: 0
        }
      ],
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
      // 用户信息表格数据
      openstack_user_infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否启用字典
      userEnableOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        userName: null,
        userEmail: null,
        userEnable: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        // 当发起新增或修改操作，这两项内容会成为必填项，如果验证不通过，将无法发起新增或修改
        userName: [
          { required: true, message: "用户名称不能为空", trigger: "blur" }
        ],
        userEmail: [
          { required: true, message: "用户邮箱不能为空", trigger: "blur" }
        ],
        userPwd: [
          { required: true, message: "登录密码不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_yes_no").then(response => {
      this.userEnableOptions = response.data;
    });
  },
  methods: {
    /** 查询用户信息列表 */
    getList() {
      this.loading = true;
      listOpenstack_user_info(this.queryParams).then(response => {
        this.openstack_user_infoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 是否启用字典翻译
    userEnableFormat(row, column) {
      //return this.selectDictLabel(this.userEnableOptions, row.userEnable);
      for (let i=0; i<this.commonYesNoList.length; i++){
        if (row.userEnable === this.commonYesNoList[i].value){
          return this.commonYesNoList[i].label
        }
      }
      return "否"
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        userId: null,
        userName: null,
        userEmail: null,
        userPwd: null,
        userEnable: null,
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
      this.ids = selection.map(item => item.userId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加用户信息";
      //在对话框中默认是否启用为 是
      this.form.userEnable = 1
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const userId = row.userId || this.ids
      getOpenstack_user_info(userId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) { // 输入验证合法时
          if (this.form.userId != null) {
            updateOpenstack_user_info(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            //开启遮罩层
            this.showMask = true;
            addOpenstack_user_info(this.form).then(response => {
              //关闭遮罩层
              this.showMask = false;
              this.msgSuccess("新增成功");
              //将新增用户的对话框关闭
              this.open = false;
              //重新获取新增用户后的用户列表
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const userIds = row.userId || this.ids;
      this.$confirm('是否确认删除用户信息编号为"' + userIds + '"的数据项?', "提示", {
        confirmButtonText: "确认",//确认按钮文字更换
        cancelButtonText: "取消",//取消按钮文字更换
        type: "warning",//提示类型  success/info/warning/error
      }).then(() => {
        //开始删除用户，启动遮罩层
        this.showMask = true;
        delOpenstack_user_info(userIds).then(res => {
          //网络应答结束，关闭遮罩层
          this.showMask = false;
          this.getList();
          this.msgSuccess("删除成功");
        });
      }).then((data) => {
        //取消操作
      }).catch((err) => {
        //捕获异常
        console.log(err);
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有用户信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportOpenstack_user_info(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
<style scoped>
.mask {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 9999; /* 确保遮罩层在其他内容之上 */
}
</style>
