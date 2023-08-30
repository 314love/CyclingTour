<template>
    <el-image src="https://img.zcool.cn/community/0133e75674ca4332f8759f043f3536.gif" style="width: 100%"></el-image>

</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
.app-main{
  width: 100%;
}
</style>
