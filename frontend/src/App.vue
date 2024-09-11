<template>
  <el-tabs v-model="activeName" class="demo-tabs">
    <el-tab-pane label="LLIE-Lib" name="first">
      <LLIELib />
    </el-tab-pane>
    <el-tab-pane label="About" name="second">
      <AboutPage />
    </el-tab-pane>
  </el-tabs>
  <div class="bottom">
    <a href="https://beian.miit.gov.cn/" target="_blank">蜀ICP备2024057466号-3</a>
    <div style="height: 20px;"></div>
  </div>
</template>
<script lang="ts" setup>
import AboutPage from './components/AboutPage.vue'
import LLIELib from './components/LLIELib.vue'
import { ref } from 'vue'
const activeName = ref('first')

</script>

<style>
/* 全局样式 */
body {
  margin: 0;
  padding: 0 10px;
}

.demo-tabs>.el-tabs__content {
  padding: 32px;
  color: #6b778c;
}

a {
  text-decoration: none;
  color: #808080;
  font-size: 12px;
}

a:hover {
  color: #5a9cf8;
}

.bottom {
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
}
</style>