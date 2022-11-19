<template>
  <div class="dashboard-container">
    <div class="dashboard-text">
      屁博的博客管理系统
    </div>
    <div>
      欢迎浏览我的博客
    </div>
    <div>
      菜就多练，必能翻身
    </div>
  </div>
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
</style>
