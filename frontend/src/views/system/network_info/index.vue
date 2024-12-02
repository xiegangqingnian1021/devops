<template>
  <div class="app-container">
    <div class="mask" v-if="showMask"
         v-loading="showMask"
         element-loading-text="现在暂停所有操作，且不要刷新页面，耐心等待，预计需要1分钟"
         element-loading-spinner="el-icon-loading"></div>
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="网络ID" prop="networkId">
        <el-input
          v-model="queryParams.networkId"
          placeholder="请输入网络ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="网络名称" prop="networkName">
        <el-input
          v-model="queryParams.networkName"
          placeholder="请输入网络名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属项目" prop="projectId">
        <el-input
          v-model="queryParams.projectId"
          placeholder="请输入所属项目"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否内网" prop="isInternal">
        <el-input
          v-model="queryParams.isInternal"
          placeholder="请输入是否内网"
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
          v-hasPermi="['system:network_info:add']"
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
          v-hasPermi="['system:network_info:edit']"
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
          v-hasPermi="['system:network_info:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:network_info:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="network_infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="网络ID" align="center" prop="networkId" />
      <el-table-column label="网络名称" align="center" prop="networkName" />
      <el-table-column label="所属项目" align="center" prop="projectId" :formatter="showProjectName" />
      <el-table-column label="是否内网" align="center" prop="isInternal" :formatter="showIsInternal" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:network_info:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:network_info:remove']"
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

    <!-- 添加或修改网络管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="网络名称" prop="networkName">
          <el-input v-model="form.networkName" placeholder="请输入网络名称" />
        </el-form-item>
        <el-form-item label="所属项目" prop="projectId">
          <!-- <el-input v-model="form.projectId" placeholder="请输入所属项目" /> -->
          <el-select v-model="form.projectId" placeholder="请选择所属项目">
            <el-option
              v-for="item in project_infoList"
              :key="item.projectId"
              :label="item.projectName"
              :value="item.projectId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否内网" prop="isInternal">
          <!-- <el-input v-model="form.isInternal" placeholder="请输入是否内网" /> -->
          <!--
          <el-switch
            v-model="form.isInternalOpen"
            active-color="#13ce66"
            inactive-color="#ff4949">
          </el-switch>
          -->
          <el-select v-model="form.isInternal" placeholder="请选择是否内网">
            <el-option
              v-for="item in [{id: 0, label: '否'}, {id: 1, label: '是'}]"
              :key="item.id"
              :label="item.label"
              :value="item.id">
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
import { listNetwork_info, getNetwork_info, delNetwork_info, addNetwork_info, updateNetwork_info, exportNetwork_info } from "@/api/system/network_info";
import { listProject_info } from '@/api/system/openstack_project_info'

export default {
  name: "Network_info",
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
      // 网络管理表格数据
      network_infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        networkId: null,
        networkName: null,
        projectId: null,
        isInternal: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      //页面遮罩层
      showMask: false,
      //项目列表
      project_infoList: null,
      //是否内网开关
      isInternalOpen: false
    };
  },
  created() {
    this.getList();
    this.getProjectList(); //页面加载时获取项目列表
  },
  methods: {
    /** 查询网络管理列表 */
    getList() {
      this.loading = true;
      listNetwork_info(this.queryParams).then(response => {
        this.network_infoList = response.rows;
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
        networkId: null,
        networkName: null,
        projectId: null,
        isInternal: null
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
      this.ids = selection.map(item => item.networkId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加网络管理";
      this.isInternalOpen = false; //是否内网开关设置为关闭
      this.form.isInternal = 0; //默认创建网络为外网
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const networkId = row.networkId || this.ids
      getNetwork_info(networkId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改网络管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.networkId != null) {
            updateNetwork_info(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.showMask = true;
            //console.log("isInternalOpen", this.isInternalOpen)
            addNetwork_info(this.form).then(response => {
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
      const networkIds = row.networkId || this.ids;
      this.$confirm('是否确认删除网络管理编号为"' + networkIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delNetwork_info(networkIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有网络管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportNetwork_info(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },
    /** 显示是否内网  */
    showIsInternal(row){
      if (row.isInternal === 1){
        return "是";
      }
      return "否";
    },

    /** 获取项目列表 */
    getProjectList() {
      listProject_info({}).then(response => {
        this.project_infoList = response.rows;
      });
    },

    /** 显示项目名称 */
    showProjectName(row){
      for (let i=0; i<this.project_infoList.length; i++){
        if (row.projectId === this.project_infoList[i].projectId){
          return this.project_infoList[i].projectName
        }
      }
      return row.projectId
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
