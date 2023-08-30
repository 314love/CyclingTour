<template>
  <div>
    <el-row :gutter="20" style="margin-top:10px;">
      <el-col :span="8">
        <div class="card-content">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>个人中心</span>
            </div>
            <div class="name-role">
              <el-image :src="this.dataForm.avatar" ></el-image>
              <div></div>
              <span class="sender"> 用户名：{{ dataForm.username }}</span>
            </div>
            <div class="registe-info">
          <span class="registe-info">
              角色：{{ dataForm.roles == 1 ? '管理员' : '普通用户' }}
          </span>
            </div>
            <el-divider></el-divider>
            <div class="personal-relation">
              <div class="relation-item">邮箱:
                <div style="float: right; padding-right:20px;">{{ dataForm.email }}</div>
              </div>
            </div>
            <div class="personal-relation">
              <div class="relation-item">手机号:
                <div style="float: right; padding-right:20px;">{{ dataForm.phone }}</div>
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

export default {
  data() {
    return {
      dataForm: {
        email: null,
        roles: null,
        username: null,
        avatar: null,
        phone: '173567777777',
        password: null
      }
    }
  },
  methods: {

  },
  created() {
    store.dispatch('user/getInfo').then(res => {
      // console.log(res)
      this.dataForm.avatar = res.avatar
      this.dataForm.username = res.username
      this.dataForm.roles = res.roles
      this.dataForm.email = res.email
      this.dataForm.phone = res.phone
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
