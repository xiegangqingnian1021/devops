<template>
  <div class="app-container">
    <div class="mask" v-if="showMask"
         v-loading="showMask"
         element-loading-text="现在暂停所有操作，且不要刷新页面，耐心等待，预计需要1分钟"
         element-loading-spinner="el-icon-loading"></div>
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="镜像ID" prop="imageId">
        <el-input
          v-model="queryParams.imageId"
          placeholder="请输入镜像ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="镜像名称" prop="imageName">
        <el-input
          v-model="queryParams.imageName"
          placeholder="请输入镜像名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属租户" prop="projectId">
        <!--
        <el-input
          v-model="queryParams.projectId"
          placeholder="请输入所属租户"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
        -->
        <el-select v-model="queryParams.projectId" placeholder="请选择">
          <el-option
            v-for="item in project_infoList"
            :key="item.projectId"
            :label="item.projectName"
            :value="item.projectId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="磁盘格式" prop="diskFormat">
        <!--
        <el-input
          v-model="queryParams.diskFormat"
          placeholder="请输入磁盘格式"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
        -->
        <el-select v-model="queryParams.diskFormat" placeholder="请选择">
          <el-option
            v-for="item in [{key: 'qcow2', label: 'qcow2', value: 'qcow2'},{key: 'iso', label: 'iso', value: 'iso'}]"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="容器格式" prop="containerFormat">
        <!--
        <el-input
          v-model="queryParams.containerFormat"
          placeholder="请输入容器格式"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
        -->
        <el-select v-model="queryParams.containerFormat" placeholder="请选择">
          <el-option
            v-for="item in [{key: 'bare', label: 'bare', value: 'bare'},{key: 'ovf', label: 'ovf', value: 'ovf'}]"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="最小内存" prop="minRam">
        <!--
        <el-input
          v-model="queryParams.minRam"
          placeholder="请输入最小内存"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
        -->
        <el-input-number v-model="queryParams.minRam" @change="handleChange1" :min="0" :max="1024" label="描述文字"/>
      </el-form-item>
      <el-form-item label="最小硬盘" prop="minDisk">
        <!--
        <el-input
          v-model="queryParams.minDisk"
          placeholder="请输入最小硬盘"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
        -->
        <el-input-number v-model="queryParams.minDisk" @change="handleChange2" :min="0" :max="1024" label="描述文字"/>
      </el-form-item>
      <el-form-item label="是否私有" prop="isPrivate">
        <!--
        <el-input
          v-model="queryParams.isPrivate"
          placeholder="请输入是否私有"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
        -->
        <el-select v-model="queryParams.isPrivate" placeholder="请选择">
          <el-option
            v-for="item in [{key: 0, label: '否', value: 0},{key: 1, label: '是', value: 1}]"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否保护" prop="isProtected">
        <!--
        <el-input
          v-model="queryParams.isProtected"
          placeholder="请输入是否保护"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
        -->
        <el-select v-model="queryParams.isProtected" placeholder="请选择">
          <el-option
            v-for="item in [{key: 0, label: '否', value: 0},{key: 1, label: '是', value: 1}]"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
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
          v-hasPermi="['system:openstack_image_info:add']"
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
          v-hasPermi="['system:openstack_image_info:edit']"
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
          v-hasPermi="['system:openstack_image_info:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:openstack_image_info:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="openstack_image_infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="镜像ID" align="center" prop="imageId" />
      <el-table-column label="镜像名称" align="center" prop="imageName" />
      <el-table-column label="所属租户" align="center" prop="projectId" />
      <el-table-column label="磁盘格式" align="center" prop="diskFormat" />
      <el-table-column label="容器格式" align="center" prop="containerFormat" />
      <el-table-column label="最小内存" align="center" prop="minRam" :formatter="showMinRam" />
      <el-table-column label="最小硬盘" align="center" prop="minDisk" :formatter="showMinDisk" />
      <el-table-column label="是否私有" align="center" prop="isPrivate" :formatter="showYesOrNo1" />
      <el-table-column label="是否保护" align="center" prop="isProtected" :formatter="showYesOrNo2" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:openstack_image_info:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:openstack_image_info:remove']"
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

    <!-- 添加或修改镜像信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <!-- 引入上传组件  -->
      <File-Upload
        :limit="1"
        :file-type="['qcow2', 'img']"
        :is-show-tip="false"
        :file-size="1000"
        @input="uploadEnd"
      />
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="镜像名称" prop="imageName">
          <el-input v-model="form.imageName" placeholder="请输入镜像名称" />
        </el-form-item>
        <el-form-item label="所属租户" prop="projectId">
          <el-input v-model="form.projectId" placeholder="请输入所属租户" />
        </el-form-item>
        <el-form-item label="磁盘格式" prop="diskFormat">
          <!-- <el-input v-model="form.diskFormat" placeholder="请输入磁盘格式" /> -->
          <el-select v-model="form.diskFormat" placeholder="请选择">
            <el-option
              v-for="item in [{key: 'qcow2', label: 'qcow2', value: 'qcow2'},{key: 'iso', label: 'iso', value: 'iso'}]"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="容器格式" prop="containerFormat">
          <!-- <el-input v-model="form.containerFormat" placeholder="请输入容器格式" /> -->
          <el-select v-model="form.containerFormat" placeholder="请选择">
            <el-option
              v-for="item in [{key: 'bare', label: 'bare', value: 'bare'},{key: 'ovf', label: 'ovf', value: 'ovf'}]"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="最小内存(MB)" prop="minRam">
          <!-- <el-input v-model="form.minRam" placeholder="请输入最小内存" /> -->
          <el-input-number v-model="form.minRam" @change="handleChange1" :min="0" :max="1024" label="描述文字"/>
        </el-form-item>
        <el-form-item label="最小硬盘(GB)" prop="minDisk">
          <!-- <el-input v-model="form.minDisk" placeholder="请输入最小硬盘" /> -->
          <el-input-number v-model="form.minDisk" @change="handleChange2" :min="0" :max="100" label="描述文字"/>
        </el-form-item>
        <el-form-item label="是否私有" prop="isPrivate">
          <!-- <el-input v-model="form.isPrivate" placeholder="请输入是否私有" /> -->
          <el-select v-model="form.isPrivate" placeholder="请选择">
            <el-option
              v-for="item in [{key: 0, label: '否', value: 0},{key: 1, label: '是', value: 1}]"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否保护" prop="isProtected">
          <!-- <el-input v-model="form.isProtected" placeholder="请输入是否保护" /> -->
          <el-select v-model="form.isProtected" placeholder="请选择">
            <el-option
              v-for="item in [{key: 0, label: '否', value: 0},{key: 1, label: '是', value: 1}]"
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
import { listOpenstack_image_info, getOpenstack_image_info, delOpenstack_image_info, addOpenstack_image_info, updateOpenstack_image_info, exportOpenstack_image_info } from "@/api/system/openstack_image_info";
import FileUpload from '@/components/FileUpload/index.vue'
import row from 'element-ui/packages/row'
import ro from 'element-ui/src/locale/lang/ro'
import { delProject_info, listProject_info } from '@/api/system/openstack_project_info'
import fa from 'element-ui/src/locale/lang/fa'
export default {
  name: "Openstack_image_info",
  computed: {
    row() {
      return row
    }
  },
  components: {
    FileUpload
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
      // 镜像信息表格数据
      openstack_image_infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        imageId: null,
        imageName: null,
        projectId: null,
        diskFormat: null,
        containerFormat: null,
        minRam: null,
        minDisk: null,
        isPrivate: null,
        isProtected: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        imageName: [
          { required: true, message: "镜像名不能为空", trigger: "blur" }
        ],
        projectId: [
          { required: true, message: "租户不能为空", trigger: "blur" }
        ]
      },
      //租户列表
      project_infoList: null,
      //页面遮罩层
      showMask: false
    };
  },
  created() {
    // 当前页面被加载时，调用的函数
    this.getList();
    this.getProjectList();
  },
  methods: {
    /** 获取所有租户 */
    getProjectList() {
      listProject_info({}).then(response => {
        this.project_infoList = response.rows;
      });
    },
    /** 内存要求的改变 */
    handleChange1(value){
      this.form.minRam = value
    },
    /** 磁盘要求的改变 */
    handleChange2(value){
      this.form.minDisk = value
    },
    /** 上传的处理 */
    uploadEnd(path){
      path = path.replace(this.getBaseUrl(),"")
      console.log(path)
      this.form.imagePath = path
    },
    /** 查询镜像信息列表 */
    getList() {
      this.loading = true;
      listOpenstack_image_info(this.queryParams).then(response => {
        this.openstack_image_infoList = response.rows;
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
        imageId: null,
        imageName: null,
        projectId: null,
        diskFormat: null,
        containerFormat: null,
        minRam: null,
        minDisk: null,
        isPrivate: null,
        isProtected: null,
        imagePath: null
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
      this.ids = selection.map(item => item.imageId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加镜像信息";
      this.form.diskFormat = "qcow2";
      this.form.containerFormat = "bare";
      this.form.minDisk = 0;
      this.form.minRam = 0;
      this.form.isPrivate = 0;
      this.form.isProtected = 0;
      this.form.imagePath = null;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const imageId = row.imageId || this.ids
      getOpenstack_image_info(imageId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改镜像信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.imageId != null) {
            updateOpenstack_image_info(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.showMask = true;
            addOpenstack_image_info(this.form).then(response => {
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
      const imageIds = row.imageId || this.ids;
      this.$confirm('是否确认删除镜像信息编号为"' + imageIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        //开始删除镜像，启动遮罩层
        this.showMask = true;
        delOpenstack_image_info(imageIds).then(res => {
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
      this.$confirm('是否确认导出所有镜像信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportOpenstack_image_info(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },

    /** 显示 是或否 */
    showYesOrNo1(row){
      if (row.isPrivate === 0) {
        return '否'
      }else {
        return '是'
      }
    },
    /** 显示 是或否 */
    showYesOrNo2(row){
      if (row.isProtected === 0) {
        return '否'
      }else {
        return '是'
      }
    },

    /** 显示最小内存 */
    showMinRam(row){
      if (row.minRam === 0){
        return "无限制"
      }else{
        return row.minRam + ""
      }
    },

    /** 显示最小硬盘 */
    showMinDisk(row){
      if (row.minDisk === 0){
        return "无限制"
      }else{
        return row.minDisk + ""
      }
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
