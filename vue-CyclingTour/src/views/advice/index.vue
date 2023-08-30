<template>
  <div>
    <el-row :gutter="20" style="margin-top:10px;">
      <el-col :span="16">
        <div class="grid-content bg-purple">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>意见反馈</span>
            </div>
            <div>
              <el-form label-width="80px" v-model="dataForm" size="small" label-position="right">
                <el-form-item label="我的邮箱">
                  <el-input  auto-complete="off" v-model="dataForm.email" readonly :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="具体意见" >
                  <el-input auto-complete="off"   type="textarea"  v-model="dataForm.advice" ></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button size="mini" type="primary" @click="saveAdvice">提交</el-button>
              </div>
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import store from "@/store";
import userApi from "@/api/userManage"

export default {
  data() {
    return {
      dataForm: {
        id:null,
        email: "",
        advice:""
      }
    }
  },
  methods: {
    saveAdvice(){
      userApi.updateUser(this.dataForm).then(res=>{
        console.log(res)
        this.$message({
          message: '感谢您的反馈，我在后台已收到您的意见',
          type: 'success'
        });
        this.dataForm.advice=null
      })
    }
  },
  created() {
    store.dispatch('user/getInfo').then(res => {
      // console.log(res)
      this.dataForm.email = res.email
      this.dataForm.id = res.id
    })
  }

}
</script>

<style lang="scss" scoped>
//卡片样式
.text {
  font-size: 14px;
}

.card-content {
  padding: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.box-card {
  width: 100%;
}

//文本样式区
.name-role {
  font-size: 16px;
  padding: 5px;
  text-align: center;
}

.sender {
  text-align: center;
}

.registe-info {
  text-align: center;
  padding-top: 10px;
}

.personal-relation {
  font-size: 16px;
  padding: 10px 10px 15px;
  margin-right: 1px;
  width: 100%
}

.relation-item {
  padding: 12px;

}

.dialog-footer {
  padding-top: 10px;
  padding-left: 10%;
}

//布局样式区
.el-row {
  margin-bottom: 20px;

  &:last-child {
    margin-bottom: 0;
  }
}

.el-col {
  border-radius: 4px;
}

.bg-purple-dark {
  background: #99a9bf;
}

.bg-purple {
  background: #d3dce6;
}

.bg-purple-light {
  background: #e5e9f2;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
