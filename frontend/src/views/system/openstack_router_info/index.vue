<template>
  <div class="app-container">
    <!-- 页面遮罩层 -->
    <div class="mask" v-if="showMask"
         v-loading="showMask"
         element-loading-text="现在暂停所有操作，且不要刷新页面，耐心等待，预计需要1分钟"
         element-loading-spinner="el-icon-loading"></div>
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="路由编号" prop="routerId">
        <el-input
          v-model="queryParams.routerId"
          placeholder="请输入路由编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="路由名称" prop="routerName">
        <el-input
          v-model="queryParams.routerName"
          placeholder="请输入路由名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="租户编号" prop="projectId">
        <el-input
          v-model="queryParams.projectId"
          placeholder="请输入租户编号"
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
          v-hasPermi="['system:openstack_router_info:add']"
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
          v-hasPermi="['system:openstack_router_info:edit']"
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
          v-hasPermi="['system:openstack_router_info:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:openstack_router_info:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="openstack_router_infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="路由编号" align="center" prop="routerId" />
      <el-table-column label="路由名称" align="center" prop="routerName" />
      <el-table-column label="路由介绍" align="center" prop="routerDescription" />
      <el-table-column label="所属租户" align="center" prop="projectId" :formatter="showProjectName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:openstack_router_info:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:openstack_router_info:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-set-up"
            @click="handleGateway(scope.row)"
            v-hasPermi="['system:openstack_router_interface:add']"
          >设置网关</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-operation"
            @click="handleSubnet(scope.row)"
            v-hasPermi="['system:openstack_router_interface:add']"
          >设置内网</el-button>
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

    <!-- 添加或修改路由信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="路由名称" prop="routerName">
          <el-input v-model="form.routerName" placeholder="请输入路由名称" />
        </el-form-item>
        <el-form-item label="路由介绍" prop="routerDescription">
          <el-input v-model="form.routerDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="租户编号" prop="projectId">
          <!-- <el-input v-model="form.projectId" placeholder="请输入租户编号" /> -->
          <el-select v-model="form.projectId" placeholder="请选择所属项目">
            <el-option
              v-for="item in project_infoList"
              :key="item.projectId"
              :label="item.projectName"
              :value="item.projectId">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改路由接口对话框 -->
    <el-dialog :title="gatewayDialog.title" :visible.sync="gatewayDialog.open" width="500px" append-to-body>
      <el-form ref="gatewayDialog_form" :model="gatewayDialog.form" :rules="gatewayDialog.rules" label-width="80px">
        <el-form-item label="所属租户" prop="projectId">
          <el-input v-model="gatewayDialog.form.projectId" readonly />
        </el-form-item>
        <el-form-item label="所属路由" prop="routerId">
          <el-input v-model="gatewayDialog.form.routerId" readonly />
        </el-form-item>
        <el-form-item label="网络编号" prop="netId">
          <el-input v-model="gatewayDialog.form.netId" placeholder="请输入网络编号" />
        </el-form-item>
        <el-form-item label="所属用户" prop="userName">
          <el-input v-model="gatewayDialog.form.userName" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="身份密码" prop="userPwd">
          <el-input v-model="gatewayDialog.form.userPwd" placeholder="请输入密码" show-password />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="gatewayDialog_submitForm">确 定</el-button>
        <el-button @click="gatewayDialog_cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 设置路内网的对话框 -->
    <el-dialog :title="subnetDialog.title" :visible.sync="subnetDialog.open" width="500px" append-to-body>
      <el-form ref="subnetDialog_form" :model="subnetDialog.form" :rules="subnetDialog.rules" label-width="80px">
        <el-form-item label="所属租户" prop="projectId">
          <el-input v-model="subnetDialog.form.projectId" readonly />
        </el-form-item>
        <el-form-item label="所属路由" prop="routerId">
          <el-input v-model="subnetDialog.form.routerId" readonly />
        </el-form-item>
        <el-form-item label="子网编号" prop="netId">
          <el-input v-model="subnetDialog.form.netId" placeholder="请输入网络编号" />
        </el-form-item>
        <el-form-item label="所属用户" prop="userName">
          <el-input v-model="subnetDialog.form.userName" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="身份密码" prop="userPwd">
          <el-input v-model="subnetDialog.form.userPwd" placeholder="请输入密码" show-password />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="subnetDialog_submitForm">确 定</el-button>
        <el-button @click="subnetDialog_cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listOpenstack_router_info, getOpenstack_router_info, delOpenstack_router_info, addOpenstack_router_info, updateOpenstack_router_info, exportOpenstack_router_info } from "@/api/system/openstack_router_info";
import { listProject_info } from '@/api/system/openstack_project_info'
import { addOpenstack_router_interface } from '@/api/system/openstack_router_interface'

export default {
  name: "Openstack_router_info",
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
      // 路由信息表格数据
      openstack_router_infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        routerId: null,
        routerName: null,
        projectId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      // 租户列表
      project_infoList: null,
      // 页面遮罩
      showMask: false,
      // 网关对话框数据模型
      gatewayDialog: {
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 表单参数
        form: {},
        // 表单校验
        rules: {
        }
      },
      // 内网对话框数据模型
      subnetDialog: {
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 表单参数
        form: {},
        // 表单校验
        rules: {
        }
      },
    };
  },
  created() {
    this.getList();
    this.getProjectList(); //打开页面时获取租户列表
  },
  methods: {
    /** 查询路由信息列表 */
    getList() {
      this.loading = true;
      listOpenstack_router_info(this.queryParams).then(response => {
        this.openstack_router_infoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        routerId: null,
        routerName: null,
        routerDescription: null,
        projectId: null,
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
      this.ids = selection.map(item => item.routerId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加路由信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const routerId = row.routerId || this.ids
      getOpenstack_router_info(routerId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改路由信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.routerId != null) {
            updateOpenstack_router_info(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.showMask = true;
            addOpenstack_router_info(this.form).then(response => {
              this.showMask = false;
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
      const routerIds = row.routerId || this.ids;
      this.$confirm('是否确认删除路由信息编号为"' + routerIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delOpenstack_router_info(routerIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有路由信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportOpenstack_router_info(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },

    /** 获取项目列表 */
    getProjectList() {
      listProject_info({}).then(response => {
        this.project_infoList = response.rows;
      });
    },

    /** 显示租户名称 */
    showProjectName(row){
      for (let i=0; i<this.project_infoList.length; i++){
        if (row.projectId === this.project_infoList[i].projectId){
          return this.project_infoList[i].projectName
        }
      }
      return row.projectId
    },

    /** 打开设置网关 */
    handleGateway(row) {
      this.gatewayDialog_reset()
      this.gatewayDialog.form.projectName = this.showProjectName(row);
      this.gatewayDialog.form.projectId = row.projectId + ":" + this.gatewayDialog.form.projectName;
      this.gatewayDialog.form.routerId = row.routerId + ":" + row.routerName;
      this.gatewayDialog.form.isGateway = 1;
      this.gatewayDialog.open = true;
      this.gatewayDialog.title = "添加网关";
    },

    // 设置网关的表单重置
    gatewayDialog_reset() {
      this.gatewayDialog.form = {
        id: null,
        routerId: null,
        isGateway: null,
        netId: null,
        userName: null,
        userPwd: null,
        projectId: null,
        projectName: null
      };
      this.resetForm("gatewayDialog_form");
    },

    /** 提交网关设置 */
    gatewayDialog_submitForm() {
      this.$refs["gatewayDialog_form"].validate(valid => {
        if (valid) {
          if (this.gatewayDialog.form.id != null) {
            updateOpenstack_router_interface(this.form).then(response => {
              this.msgSuccess("修改网关成功");
              this.gatewayDialog.open = false;
              this.getList();
            });
          } else {
            addOpenstack_router_interface(this.gatewayDialog.form).then(response => {
              this.msgSuccess("设置网关成功");
              this.gatewayDialog.open = false;
              this.getList();
            });
          }
        }
      });
    },

    /** 取消设置网关 */
    gatewayDialog_cancel() {
      this.gatewayDialog.open = false;
      this.gatewayDialog_reset();
    },

    /** 打开设置网关 */
    handleSubnet(row) {
      this.subnetDialog_reset()
      this.subnetDialog.form.projectName = this.showProjectName(row);
      this.subnetDialog.form.projectId = row.projectId + ":" + this.subnetDialog.form.projectName;
      this.subnetDialog.form.routerId = row.routerId + ":" + row.routerName;
      this.subnetDialog.form.isGateway = 0;
      this.subnetDialog.open = true;
      this.subnetDialog.title = "添加内网";
    },

    // 设置内网的表单重置
    subnetDialog_reset() {
      this.subnetDialog.form = {
        id: null,
        routerId: null,
        isGateway: null,
        netId: null,
        userName: null,
        userPwd: null,
        projectId: null,
        projectName: null
      };
      this.resetForm("subnetDialog_form");
    },

    /** 提交内网设置 */
    subnetDialog_submitForm() {
      this.$refs["subnetDialog_form"].validate(valid => {
        if (valid) {
          if (this.subnetDialog.form.id != null) {
            updateOpenstack_router_interface(this.subnetDialog.form).then(response => {
              this.msgSuccess("修改内网成功");
              this.subnetDialog.open = false;
              this.getList();
            });
          } else {
            addOpenstack_router_interface(this.subnetDialog.form).then(response => {
              this.msgSuccess("设置内网成功");
              this.subnetDialog.open = false;
              this.getList();
            });
          }
        }
      });
    },

    /** 取消内网设置 */
    subnetDialog_cancel() {
      this.subnetDialog.open = false;
      this.subnetDialog_reset();
    },
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
