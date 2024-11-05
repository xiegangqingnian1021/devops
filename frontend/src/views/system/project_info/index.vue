<template>
  <div class="app-container">
    <!-- 新增的遮罩层 -->
    <div class="mask" v-if="showMask"
         v-loading="showMask"
         element-loading-text="现在暂停所有操作，且不要刷新页面，耐心等待，预计需要1分钟"
         element-loading-spinner="el-icon-loading"></div>
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目编号" prop="projectId">
        <el-input
          v-model="queryParams.projectId"
          placeholder="请输入项目编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
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
          v-hasPermi="['system:project_info:add']"
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
          v-hasPermi="['system:project_info:edit']"
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
          v-hasPermi="['system:project_info:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:project_info:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="project_infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="项目编号" align="center" prop="projectId" />
      <el-table-column label="项目名称" align="center" prop="projectName" />
      <el-table-column label="项目描述" align="center" prop="projectDescription" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:project_info:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:project_info:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-connection"
            @click="handleAssociate(scope.row)"
            v-hasPermi="['system:openstack_project_user:add']"
          >绑定成员</el-button>
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

    <!-- 添加或修改租户信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="项目描述" prop="projectDescription">
          <el-input v-model="form.projectDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或删除租户用户关联关系的对话框 -->
    <el-dialog :title="associateDialog.title" :visible.sync="associateDialog.open" width="650px" append-to-body>
      <el-transfer v-model="associateDialog.value" :data="associateDialog.data" :titles="associateDialog.tittles"></el-transfer>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="assiocateDialogSubmit">确 定</el-button>
        <el-button @click="assiocateDialogCancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listProject_info, getProject_info, delProject_info, addProject_info, updateProject_info, exportProject_info } from "@/api/system/openstack_project_info";
import { listOpenstack_user_info } from '@/api/system/openstack_user_info'
import { addOpenstack_project_user } from '@/api/system/openstack_project_user'

export default {
  name: "Project_info",
  components: {
  },
  data() {
    const generateData = _ => {
      const data = [];
      for (let i = 1; i <= 15; i++) {
        data.push({
          key: i,
          label: `用户 ${ i }`,
          disabled: i % 4 === 0
        });
      }
      return data;
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
      // 租户信息表格数据
      project_infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectId: null,
        projectName: null,
        projectDescription: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        // 当发起新增或修改操作，这两项内容会成为必填项，如果验证不通过，将无法发起新增或修改
        projectName: [
          { required: true, message: "项目名称不能为空", trigger: "blur" }
        ],
        projectDescription: [
          { required: true, message: "项目描述不能为空", trigger: "blur" }
        ]
      },
      //页面遮罩层
      showMask: false,
      // 租户用户管理的对话框模型
      associateDialog: {
        title: "绑定用户",
        open: false,
        data: [],
        value: [],
        tittles: ["待选用户","已选用户"],
        projectId: null
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询租户信息列表 */
    getList() {
      this.loading = true;
      listProject_info(this.queryParams).then(response => {
        this.project_infoList = response.rows;
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
        projectId: null,
        projectName: null,
        projectDescription: null,
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
      this.ids = selection.map(item => item.projectId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true // 打开对话的弹窗
      this.title = "添加租户信息"; //给弹窗加上标题信息
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const projectId = row.projectId || this.ids
      getProject_info(projectId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改租户信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          //表单合法执行操作
          if (this.form.projectId != null) {
            //租户存在，就执行修改操作
            this.showMask = true
            updateProject_info(this.form).then(response => {
              this.showMask = false;
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            //启用遮罩，阻止页面的一切操作
            this.showMask = true;
            //租户ID不存在，一定执行的是新增操作
            addProject_info(this.form).then(response => {
              //停止遮罩，恢复正常
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
      const projectIds = row.projectId || this.ids;
      this.$confirm('是否确认删除租户信息编号为"' + projectIds + '"的数据项?', "提示", {
        confirmButtonText: "确认",//确认按钮文字更换
        cancelButtonText: "取消",//取消按钮文字更换
        type: "warning",//提示类型  success/info/warning/error
      }).then(() => {
        //开始删除租户，启动遮罩层
        this.showMask = true;
        delProject_info(projectIds).then(res => {
          this.showMask = false; //网路应答结束，关闭遮罩层
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
      this.$confirm('是否确认导出所有租户信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportProject_info(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },
    /** 打开租户用户关联的对话框 */
    handleAssociate(row) {
      //将对话框打开
      this.associateDialog.open = true
      //获取未与指定租户绑定的用户列表
      this.getUserList(row.projectId)
      //设定当前租户
      this.associateDialog.projectId = row.projectId
    },
    /** 获取用户列表，未与当前选定的租户绑定的用户列表 */
    getUserList(project_id) {
      listOpenstack_user_info({
        projectId: project_id
      }).then(response1 => {
        // 将未绑定的用户放入穿梭框的左侧框
        for (let i=0; i<response1.total; i++){
          this.associateDialog.data.push(
            {
              key: response1.rows[i].userId,
              label: response1.rows[i].userName,
            }
          )
        }
        // 将租户已经关联的用户数据添加进来
        listOpenstack_user_info({
          pId: project_id
        }).then(response2 => {
          for (let i=0; i<response2.total; i++){
            this.associateDialog.value.push(response2.rows[i].userId)
            this.associateDialog.data.push(
              {
                key: response2.rows[i].userId,
                label: response2.rows[i].userName,
              }
            )
          }
        })
      });
    },

    /** 提交租户用户的关联处理 */
    assiocateDialogSubmit(){
      // 开启页面遮罩
      this.showMask = true;
      //准备选定的用户列表
      let userIds = this.associateDialog.value;
      //准备要关联的租户Id
      let projectId = this.associateDialog.projectId;
      // 访问关联接口
      addOpenstack_project_user(
        {
          roleName: "_member_",
          projectId: projectId,
          userIds: userIds
        }
      ).then(response => {
        //关闭遮罩层
        this.showMask = false
        //绑定租户用户的对话框关闭
        this.associateDialog.open = false;
        //清空数据
        this.associateDialog.data = [];
        this.associateDialog.value = [];
        this.associateDialog.projectId = null;
        //弹出消息
        this.msgSuccess("绑定成功");
      }).catch(err => {
        //关闭遮罩层
        this.showMask = false
        //绑定租户用户的对话框关闭
        this.associateDialog.open = false;
        //清空数据
        this.associateDialog.data = [];
        this.associateDialog.value = [];
        this.associateDialog.projectId = null;
        //弹出消息
        this.msgError("绑定失败")
      })
    },

    /** 关闭租户用户的关联对话框 */
    assiocateDialogCancel(){
      //关闭对话框
      this.associateDialog.open = false;
      // 清空数据
      this.associateDialog.data = [];
      this.associateDialog.value = [];
      this.associateDialog.projectId = null;
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
