<template>
  <el-card>
    <el-table
      :data="tableData"
      stripe
      style="width: 100%"
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
        prop="avatar"
        label="挑战者头像"
        width="auto"
      >
        <template slot-scope="scope">
          <el-image :src="scope.row.avatar"
                    style="width: 100px; height: 100px"
                    :preview-src-list="[scope.row.avatar]"></el-image>
        </template>
      </el-table-column>

      <el-table-column
        prop="username"
        label="挑战者姓名"
        width="auto"
      />
      <el-table-column
        prop="phone"
        label="电话"
        width="auto"
      />
    </el-table>
  </el-card>
</template>

<script>
import tripApi from '@/api/tripManage'
export default {
  name: "index",
  data(){
    return{
      tableData:[],
    }
  },
  created() {
    tripApi.recordList().then(res=>{
      this.tableData=res.data
    })
  }
}
</script>

<style >
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
