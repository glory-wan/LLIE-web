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
        <div class="upload-container">
          <label for="upload-input" class="upload-button">
            选择文件
            <input type="file" id="upload-input" @change="uploadImage" style="display: none;">
          </label>
        </div>

        <div class="radios">
          <el-radio-group v-model="cs">
            <el-radio-button label="rgb" value="rgb" />
            <el-radio-button label="hls" value="hls" />
            <el-radio-button label="hsv" value="hsv" />
            <el-radio-button label="lab" value="lab" />
            <el-radio-button label="yuv" value="yuv" />
          </el-radio-group>
        </div>

        <div class="radios">
          <el-radio-group v-model="method">
            <el-radio-button label="he" value="he" />
            <el-radio-button label="clahe" value="clahe" />
            <el-radio-button label="rclahe" value="rclahe" />
          </el-radio-group>
        </div>

        <el-button type="primary" size="large" @click="processImage" :loading="processing">开始处理</el-button>
      </div>
    </div>

    <div class=" flex-item">
      <h3>处理后图片</h3>
      <div v-if="processedImage" style="display: flex;flex-direction: column;">
        <img class="image" :src="processedImage" alt="Processed Image" />
        <el-button type="primary" size="large" @click="downloadImage" style="width: 90px;margin-top: 20px;">
          下载图片
        </el-button>
        <el-button type="primary" size="large" @click="refreshPage"
          style="width: 90px;margin-top: 8px;margin-left: 0px;">
          清除图片
        </el-button>
      </div>
      <div v-else class="image-placeholder">处理后图片</div>
    </div>
  </el-container>
</template>

<script setup lang="ts">
import api from '@/api/request.js';
import { ElMessage } from 'element-plus';
import { ref } from 'vue';

const originalImage = ref(null);
const processedImage = ref(null);
const method = ref(null);
const file = ref(null);
const cs = ref(null);
const processing = ref(false);

const emits = defineEmits(['processed-image', 'file-change'])

const uploadImage = async (e) => {
  try {
    file.value = e.target.files[0];

    if (file.value.type !== 'image/jpeg' && file.value.type !== 'image/png' && file.value.type !== 'image/jpg') {
      ElMessage.error('请选择jpeg、png、jpg格式的图片文件')
      return
    }
    else if (file.value.size > 1024 * 1024 * 8) {
      ElMessage.error('文件大小超过限制(8MB)，请重新选择')
      return
    }

    if (file) {
      const reader = new FileReader();
      reader.onload = function (e) {
        originalImage.value = e.target.result;
      };
      reader.readAsDataURL(file.value);
    } else {
      ElMessage.error('上传文件不可为空')
      return
    }
  } catch (error) {
    console.error('Error uploading image:', error);
  }
};

const processImage = async () => {
  if (originalImage.value == null || method.value == null || cs.value == null) {
    console.log(originalImage.value, method.value, cs.value)
    ElMessage.error('请选择图片文件和算法');
    return;
  }
  try {
    processing.value = true;
    const formData = new FormData();
    formData.append('file', file.value);
    formData.append('method', method.value);
    formData.append('cs', cs.value);

    const response = await api.post('/img', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    // console.log(response.data.data)
    processedImage.value = response.data.data;
    processing.value = false;
  } catch (error) {
    console.error('Error uploading image:', error);
  }
};

// 创建一个临时的 <a> 标签下载处理后的图片
const downloadImage = () => {
  if (!processedImage.value) return;

  const link = document.createElement('a');
  link.href = processedImage.value;

  const parts = processedImage.value.split(',');
  const mime = parts[0].match(/:(.*?);/)[1];
  link.download = 'image.' + mime.split('/')[1];

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
  justify-content: space-between;
}

.flex-item {
  flex-basis: calc(40% - 10px);
  margin: 5px;
}

.proc-img-container {
  display: flex;
  flex-direction: column;

}


/* 图片样式 */
.image {
  max-width: 90%;
  height: auto;
  border-radius: 8%;
}

/* 按钮和单选框样式 */
.button,
.radios {
  margin: 8px 0;
}

.upload-container {
  position: relative;
  display: inline-block;
  margin-top: 8px;
}

.upload-button {
  display: inline-block;
  padding: 10px 19px;
  background-color: #409EFF;
  color: #fff;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  font-size: 14px;
}

.upload-button:hover {
  background-color: #66B1FF;
}

/* 媒体查询：手机 */
@media (max-width: 768px) {
  .flex-item {
    flex-basis: 100%;
  }
}
</style>