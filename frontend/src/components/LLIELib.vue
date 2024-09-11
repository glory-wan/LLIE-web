<template>
  <el-container style="justify-content: center;">
    <div class="flex-item">
      <div>
        <h3>原图</h3>
        <img class="image" v-if="originalImage" :src="originalImage" alt="Original Image" />
        <div v-else class="image-placeholder" style="text-align: center;">
          请上传图片
          <br>
          文件大小不超过8MB
        </div>
      </div>

      <div class="button">
        <Upload @file-change="handleFileChange"></Upload>
      </div>

      <div>
        <Radio :originalImage="originalImage" @processed-image="handleProcessedImage" />
      </div>
    </div>

    <div class="flex-item">
      <h3>处理后图片</h3>
      <div v-if="processedImage" style="display: flex;flex-direction: column;">
        <img class="image" :src="processedImage" alt="Processed Image" />
        <el-button type="primary" size="large" @click="downloadImage" style="width: 90px;margin-top: 20px;">
          下载图片
        </el-button>
        <el-button type="primary" size="large" @click="refreshPage"
          style="width: 90px;margin-top: 20px;margin-left: 0px;">
          清除图片
        </el-button>
      </div>
      <div v-else class="image-placeholder">处理后图片</div>
    </div>
  </el-container>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import Upload from './Upload.vue';
import Radio from './Radio.vue';

const originalImage = ref(null);
const processedImage = ref(null);

const handleFileChange = (data: string) => {
  console.log('File changed:', data);
  originalImage.value = data;
};

const handleProcessedImage = (data: string) => {
  console.log('Processed image:', data);
  processedImage.value = data;
};
// 下载处理后的图片
const downloadImage = () => {
  if (!processedImage.value) return;

  const link = document.createElement('a');
  link.href = processedImage.value;
  link.download = 'image.jpg';
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
};
// 刷新页面以清除图片
const refreshPage = () => {
  window.location.reload();
};
</script>


<style>
.image {
  width: 450px;
  object-fit: contain;
  border: 1px solid #ccc;
}

.image-placeholder {
  width: 300px;
  height: 300px;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1px dashed #ccc;
  color: #888;
  border-radius: 10px;
  cursor: pointer;
}

.image-placeholder:hover {
  border: 1px dashed #5a9cf8;
}

.el-container {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}

.flex-item {
  flex-basis: calc(40% - 10px);
  margin: 5px;
}

/* 图片样式 */
.image {
  max-width: 90%;
  height: auto;
}

/* 按钮和单选框样式 */
.button,
.radios {
  margin: 8px 0;
}

/* 媒体查询：手机 */
@media (max-width: 768px) {
  .flex-item {
    flex-basis: 100%;
  }
}
</style>