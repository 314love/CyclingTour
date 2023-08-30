<template>
  <div>
    <el-card id="input">
      <el-input v-model="searchModel.routeName" placeholder="行程名称"/>
      <el-input v-model="searchModel.routeRating" placeholder="行程评分"/>
      <el-button type="primary" @click="searchList" round icon="el-icon-search">查找</el-button>
      <el-button type="primary" @click="addTrip(null)" round icon="el-icon-circle-plus-outline">新增</el-button>
      <el-button @click="challenge" type="success" round plain>骑行挑战</el-button>
      <el-button @click="chongzhi" type="success" round>重置</el-button>
    </el-card>
    <el-card>
      <el-table
        :data="tableData"
        stripe
        style="width: 100%"
        @row-click="handleRowClick"
      >
        <el-table-column
          prop="id"
          label="ID"
          width="50"
        />
        <el-table-column
          prop="routeName"
          label="行程名称"
          width="auto"
        />
        <el-table-column
          prop="routeImageUrl"
          label="行程图片"
          width="auto"
        >
          <template slot-scope="scope">
            <el-image :src="scope.row.routeImageUrl"
                      style="width: 100px; height: 100px"
                      :preview-src-list="[scope.row.routeImageUrl]"></el-image>
          </template>
        </el-table-column>
        <el-table-column
          prop="routeDetails"
          label="具体路线"
          width="auto"
        />
        <el-table-column
          prop="riderCount"
          label="骑行人数"
          width="auto"
        />
        <el-table-column
          prop="routeRating"
          label="路线评分"
          width="auto"
        />
        <el-table-column
          prop="routeComments"
          label="路线评价"
          width="auto"
        />
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="addTrip(scope.row.id)">编辑
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
        <el-form-item label="行程名称" :label-width="formLabelWidth" prop="routeName">
          <el-input v-model="form.routeName" autocomplete="off" style="width: 500px"></el-input>
        </el-form-item>
        <el-form-item label="行程图片" :label-width="formLabelWidth" prop="routeImageUrl">
          <el-input v-model="form.routeImageUrl" autocomplete="off" style="width: 500px"></el-input>
        </el-form-item>
        <el-form-item label="具体路线" :label-width="formLabelWidth" prop="routeDetails">
          <el-input v-model="form.routeDetails" autocomplete="off" style="width: 500px"></el-input>
        </el-form-item>
        <el-form-item label="骑行人数" :label-width="formLabelWidth" prop="riderCount">
          <el-input v-model="form.riderCount" autocomplete="off" style="width: 500px"></el-input>
        </el-form-item>
        <el-form-item label="路线评分" :label-width="formLabelWidth" prop="routeRating">
          <el-input v-model="form.routeRating" autocomplete="off" style="width: 500px"></el-input>
        </el-form-item>
        <el-form-item label="路线评价" :label-width="formLabelWidth" prop="routeComments">
          <el-input v-model="form.routeComments" autocomplete="off" style="width: 500px"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveTrip">确 定</el-button>
      </div>
    </el-dialog>

    <!--    骑行挑战dialog-->
    <el-dialog title="骑行挑战" :visible.sync="byBikeDialogFormVisible">
      <el-form :model="formB" ref="ruleFormB" :rules="rulesB">
        <el-form-item label="行程ID" :label-width="formLabelWidth">
          <el-input v-model="formB.tripId" autocomplete="off" style="width: 500px"></el-input>
        </el-form-item>
        <el-form-item label="用户ID(默认是当前用户ID)" :label-width="formLabelWidth">
          <el-input v-model='formB.userId' readonly autocomplete="off" style="width: 500px" :disabled="true"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="byBikeDialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveChallenge()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import tripApi from '@/api/tripManage.js'
import userB from '@/api/user'
import store from "@/store";

export default {
  name: 'Trip',
  data() {

    return {
      selectedId: null,
      byBikeDialogFormVisible: false,
      formB: {
        tripId: null,
        userId: null
      },
      title: "",
      dialogFormVisible: false,
      form: {},
      formLabelWidth: '120px',

      tableData: [],
      searchModel: {
        currentPage: 1,
        sizePage: 10,
      },
      total: 0,
      rulesB:{
        tripId: [
          {required: true, message: '请输入行程ID', trigger: 'blur'},
        ],
      },
      rules: {
        routeName: [
          {required: true, message: '请输入行程名称', trigger: 'blur'},
          { min: 1, max: 5, message: '长度在 1 到 5 个字符', trigger: 'blur' }
        ],
        routeImageUrl: [
          {required: true, message: '请输入行程图片的链接地址', trigger: 'blur'},
        ],
        routeDetails: [
          {required: true, message: '请输入行程的具体路线', trigger: 'blur'},
        ],
        riderCount: [
          {required: true, message: '请输入骑行人数', trigger: 'blur'},
        ],
        routeRating: [
          {required: true, message: '请输入路线评分', trigger: 'blur'},
        ],
        routeComments: [
          {required: true, message: '请输入路线评价', trigger: 'blur'},
        ],
      }
    }
  },
  methods: {
    // 选择一行得到该行id
    handleRowClick(row) {
      this.formB.tripId = row.id; // Store the selected ID
      // console.log(this.selectedId)
    },

    saveChallenge() {

      this.$refs.ruleFormB.validate((valid) => {
        if (valid) {
          // alert('submit!'); 数据校验成功
          tripApi.addChallenge(this.formB).then(res => {
            this.$message({
              message: "骑行挑战已生成，请在挑战记录查看",
              type: 'success'
            });
            this.byBikeDialogFormVisible = false;
          })
        }
      });
    },
    // 骑行挑战
    challenge() {
      if (this.formB.tripId == null) {
        this.$notify.info({
          title: '提示',
          message: '请先选择要挑战的行程'
        });
      }else {
        this.byBikeDialogFormVisible = true
      }
    },

    deleteById(id) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        tripApi.deleteById(id).then(res => {
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
    saveTrip() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          // alert('submit!'); 数据校验成功
          // 判断是否为新增还是修改
          // 新增
          if (this.form.id == null && this.form.id == undefined) {
            tripApi.addTrip(this.form).then(res => {
              this.$message({
                message: "新增" + res.data,
                type: 'success'
              });
              this.dialogFormVisible = false;
              this.searchList()
            })
          } else {
            //   修改
            tripApi.updateTrip(this.form).then(res => {
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
    addTrip(id) {
      if (id == null) {
        this.title = "新增行程"
        this.dialogFormVisible = true;
      } else {
        // console.log(id)
        this.title = "修改行程"
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
        this.total = <res className="data total"></res>
        this.tableData = res.data.records
      })
    },
    chongzhi() {
      this.searchModel.routeName = '',
        this.searchModel.routeRating = ''
    }
  },
  // 钩子函数与method平齐
  created() {
    this.searchList();


  },
  mounted() {
    store.dispatch('user/getInfo').then(res => {
      // console.log(res)
      this.formB.userId = res.id
    })
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
