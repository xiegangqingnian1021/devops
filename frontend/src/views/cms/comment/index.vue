<template>
<!--  <div class="app-container">-->
<!--    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">-->
<!--      <el-form-item label="文章标题" prop="title">-->
<!--        <el-input v-model="queryParams.title" placeholder="请输入文章标题" clearable size="small" @keyup.enter.native="handleQuery" />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="评论人" prop="createBy">-->
<!--        <el-select v-model="queryParams.createBy" placeholder="请选择创建人" clearable filterable size="small">-->
<!--          <el-option v-for="dict in createByOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="评论时间">-->
<!--        <el-date-picker v-model="daterangeCreateTime" size="small" style="width: 240px" value-format="yyyy-MM-dd" type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button v-no-more-click type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>-->
<!--        <el-button v-no-more-click icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->

<!--    <el-row :gutter="10" class="mb8">-->
<!--      <el-col :span="1.5">-->
<!--        <el-button v-no-more-click type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['cms:comment:add']">新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button v-no-more-click type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['cms:comment:edit']">修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button v-no-more-click type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['cms:comment:remove']">删除</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button v-no-more-click type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['cms:comment:export']">导出</el-button>-->
<!--      </el-col>-->
<!--      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>-->
<!--    </el-row>-->

<!--    <el-table v-loading="loading" :data="commentList" @selection-change="handleSelectionChange">-->
<!--      <el-table-column type="selection" width="55" align="center" />-->
<!--      <el-table-column label="编号" align="center" prop="id" />-->
<!--      <el-table-column label="文章标题" align="center" prop="title" />-->
<!--      <el-table-column label="点赞数" align="center" prop="goodNum" />-->
<!--      <el-table-column label="评论人" align="center" prop="createBy" :formatter="createByFormat" />-->
<!--      <el-table-column label="评论时间" align="center" prop="createTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button v-no-more-click size="mini" type="text" icon="el-icon-chat-dot-round" @click="handleReplay(scope.row)" v-hasPermi="['cms:comment:edit']">回复</el-button>-->
<!--          <el-button v-no-more-click size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['cms:comment:edit']">修改</el-button>-->
<!--          <el-button v-no-more-click size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['cms:comment:remove']">删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--    </el-table>-->

<!--    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />-->

<!--    &lt;!&ndash; 添加或修改文章评论对话框 &ndash;&gt;-->
<!--    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>-->
<!--      <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
<!--        <el-form-item label="文章" prop="articleId">-->
<!--          <el-select v-model="form.articleId" placeholder="请选择文章" clearable filterable size="small">-->
<!--            <el-option v-for="dict in articleOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="内容">-->
<!--          <editor v-model="form.content" :min-height="192" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="点赞数" prop="goodNum">-->
<!--          <el-input v-model="form.goodNum" placeholder="请输入点赞数" />-->
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
import { listComment, getComment, delComment, addComment, updateComment, exportComment } from "@/api/cms/comment";
import Editor from '@/components/MyEditor';
import { listArticle } from "@/api/cms/article";

export default {
  name: "Comment",
  components: {
    Editor,
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
      // 文章评论表格数据
      commentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 创建人字典
      createByOptions: [],
      articleOptions: [],
      // 创建时间时间范围
      daterangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        articleId: null,
        createBy: null,
        createTime: null,
        parentId: 0
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
    this.getSysUserList().then(res => {
      this.createByOptions = [];
      let list = res.rows;
      list.forEach(element => {
        this.createByOptions.push({
          dictValue: element.userId,
          dictLabel: element.userName,
        });
      });
    });
    this.getArticleOpts();
  },
  methods: {
    handleReplay(row) {
      this.$router.push({
        path: "/cms/commentReply",
        query: { parentId: row.id }
      })
    },
    getArticleOpts() {
      this.articleOptions = [];
      listArticle().then(res => {
        let list = res.rows;
        list.forEach(item => {
          this.articleOptions.push({
            dictValue: item.id,
            dictLabel: item.title,
          })
        })
      })
    },
    /** 查询文章评论列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      listComment(this.queryParams).then(response => {
        this.commentList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 创建人字典翻译
    createByFormat(row, column) {
      return this.selectDictLabel(this.createByOptions, row.createBy);
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
        articleId: null,
        content: null,
        goodNum: null,
        createBy: null,
        createTime: null,
        parentId: null
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
      this.daterangeCreateTime = [];
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
      this.title = "添加文章评论";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getComment(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改文章评论";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateComment(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addComment(this.form).then(response => {
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
      this.$confirm('是否确认删除文章评论编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delComment(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有文章评论数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportComment(queryParams);
      }).then(response => {
        this.download(response.msg);
      })
    }
  }
};
</script>
