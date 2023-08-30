<template>
  <div>
    <el-card id="input">
      <el-input v-model="searchModel.username" placeholder="用户名"/>
      <el-input v-model="searchModel.phone" placeholder="手机号"/>
      <el-button type="primary" @click="searchList" round icon="el-icon-search">查找</el-button>
      <el-button type="primary" @click="addUser(null)" round icon="el-icon-circle-plus-outline">新增</el-button>
      <el-button @click="chongzhi" type="success" round>重置</el-button>
    </el-card>
    <el-card>
      <el-table
        :data="tableData"
        stripe
        style="width: 100%"
      >
        <el-table-column
          prop="id"
          label="ID"
          width="120"
        />
        <el-table-column
          prop="username"
          label="姓名"
          width="100px"
        />
        <el-table-column
          prop="avatar"
          label="头像"
          width="110px"
        >
          <template slot-scope="scope">
            <el-image :src="scope.row.avatar"
                      style="width: 50px; height: 50px"
                      :preview-src-list="[scope.row.avatar]"></el-image>
          </template>
        </el-table-column>
        <el-table-column
          prop="email"
          label="邮箱"
          width="auto"
        />
        <el-table-column
          prop="phone"
          label="电话"
          width="auto"
        />
        <el-table-column
          prop="roles"
          label="角色"
          width="auto"
        >
          <template slot-scope="scope">
            <el-tag v-if="scope.row.roles==1" type="danger">管理员</el-tag>
            <el-tag v-else-if="scope.row.roles==0">普通用户</el-tag>
          </template>
        </el-table-column>

        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="addUser(scope.row.id)">编辑
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="deleteById(scope.row.id)">删除
            </el-button>

          </template>
        </el-table-column>

      </el-table>
      <!--      分页-->
      <el-pagination
        :current-page="searchModel.currentPage"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="searchModel.sizePage"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </el-card>
    <!--    对话框-->
    <el-dialog :title="title" :visible.sync="dialogFormVisible" @close="clearDialog">
      <el-form :model="form" :rules="rules" ref="ruleForm">
        <el-form-item label="用户名" :label-width="formLabelWidth" prop="username">
          <el-input v-model="form.username" autocomplete="off" style="width: 500px"></el-input>
        </el-form-item>
        <el-form-item v-if="form.id==null || form.id==undefined" label="用户密码" :label-width="formLabelWidth"
                      prop="password">
          <el-input type="password" v-model="form.password" autocomplete="off" style="width: 500px"></el-input>
        </el-form-item>
        <el-form-item label="电子邮箱" :label-width="formLabelWidth" prop="email">
          <el-input v-model="form.email" autocomplete="off" style="width: 500px"></el-input>
        </el-form-item>
        <el-form-item label="电话" :label-width="formLabelWidth" prop="phone">
          <el-input v-model="form.phone" autocomplete="off" style="width: 500px"></el-input>
        </el-form-item>
        <el-form-item label="头像地址" :label-width="formLabelWidth">
          <el-input v-model="form.avatar" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveUser">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import tripApi from '@/api/userManage.js'

export default {
  name: 'User',
  data() {
    var checkEmail = (rule, value, callback) => {
      const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
      if (!value) {
        return callback(new Error('邮箱不能为空'))
      }
      setTimeout(() => {
        if (mailReg.test(value)) {
          callback()
        } else {
          callback(new Error('请输入正确的邮箱格式'))
        }
      }, 100)
    }

    return {
      title: "",
      dialogFormVisible: false,
      form: {},
      formLabelWidth: '120px',

      tableData: [],
      searchModel: {
        currentPage: 1,
        sizePage: 10
      },
      total: 0,
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, max: 10, message: '长度在 6 到 10 个字符', trigger: 'blur'}
        ],
        email: [
          {required: true, message: '请输入邮箱', trigger: 'blur'},
          {validator: checkEmail, message: "请输⼊正确的邮箱格式", trigger: 'blur'}
        ],
        phone: [
          {required: true, message: "⼿机号不能为空", trigger: "blur"},
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: "请输⼊正确的⼿机号码", trigger:
              "blur"
          }
        ],
      }
    }
  },
  methods: {
    deleteById(id){
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        tripApi.deleteById(id).then(res=>{
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          this.searchList()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    saveUser() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          // alert('submit!'); 数据校验成功
          // 判断是否为新增还是修改
          // 新增
          if (this.form.id == null && this.form.id == undefined) {
            tripApi.addUser(this.form).then(res => {
              this.$message({
                message: "新增" + res.data,
                type: 'success'
              });
              this.dialogFormVisible = false;
              this.searchList()
            })
          } else {
            //   修改
            tripApi.updateUser(this.form).then(res => {
              this.$message({
                message: "修改" + res.data,
                type: 'success'
              });
              this.dialogFormVisible = false;
              this.searchList()
            })
          }
        }
      });
    },
    clearDialog() {
      this.form = {}
      this.$refs.ruleForm.clearValidate()
    },
    addUser(id) {
      if (id == null) {
        this.title = "新增用户"
        this.dialogFormVisible = true;
      } else {
        // console.log(id)
        this.title = "修改用户"
        this.dialogFormVisible = true;
        tripApi.queryById(id).then(res => {
          this.form = res.data
        })
      }
    },
    handleSizeChange(sizePage) {
      this.searchModel.sizePage = sizePage
      this.searchList()
    },
    handleCurrentChange(currentPage) {
      this.searchModel.currentPage = currentPage
      this.searchList()
    },
    searchList() {
      tripApi.queryList(this.searchModel).then(res => {
        // console.log(res)
        this.total = res.data.total
        this.tableData = res.data.records
      })
    },
    chongzhi() {
      this.searchModel.username = '',
        this.searchModel.phone = ''
    }
  },
  // 钩子函数与method平齐
  created() {
    this.searchList();
  }
}
</script>

<style>
#input .el-input {
  width: 200px;
  padding-right: 10px;
}

.app-main {
  padding: 10px;
}

.el-card {
  margin-bottom: 10px;
}
</style>
