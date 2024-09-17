<template>
  <div class="app-container">
    <el-tabs v-model="activeName" class="demo-tabs">
      <el-tab-pane label="LLIE-Lib" name="first">
        <LLIELib />
      </el-tab-pane>
      <el-tab-pane label="About" name="second">
        <AboutPage />
      </el-tab-pane>
    </el-tabs>
    <footer class="bottom">
      <a href="https://beian.miit.gov.cn/" target="_blank">蜀ICP备2024057466号-3</a>
      <div style="height: 20px;"></div>
    </footer>
  </div>
</template>

<script lang="ts" setup>
import AboutPage from './components/AboutPage.vue'
import LLIELib from './components/LLIELib.vue'
import { ref } from 'vue'
const activeName = ref('first')
</script>

<style>
body, html {
  margin: 0;
  padding: 0;
  height: 100%;
}

.app-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh; 
}

.demo-tabs {
  flex: 1; 
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
  text-align: center;
  padding: 10px 0;
  width: 100%;
}

</style>
