<template>
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
</template>

<script setup lang="ts">
import api from '@/api/request.js';
import { ElMessage } from 'element-plus';
import { ref } from 'vue';

const img = ref(null);
const method = ref(null);
const cs = ref(null);
const processing = ref(false);
const emits = defineEmits(['processed-image', 'file-change'])

const uploadImage = async (e) => {
    try {
        img.value = e.target.files[0];

        if (img.value.type !== 'image/jpeg' && img.value.type !== 'image/png' && img.value.type !== 'image/jpg') {
            ElMessage.error('请选择jpeg、png、jpg格式的图片文件')
            return
        }
        else if (img.value.size > 1024 * 1024 * 8) {
            ElMessage.error('文件大小超过限制(8MB)，请重新选择')
            return
        }

        if (img.value) {
            const reader = new FileReader();
            reader.onload = function (e) {
                const imageUrl = e.target.result;
                emits('file-change', imageUrl);
            };
            reader.readAsDataURL(img.value);
        } else {
            ElMessage.error('上传文件不可为空')
            return
        }
    } catch (error) {
        console.error('Error uploading image:', error);
    }
};

const processImage = async () => {
    if (img.value == null || method.value == null || cs.value == null) {
        console.log(img.value, method.value, cs.value)
        ElMessage.error('请选择图片文件和算法');
        return;
    }
    try {
        processing.value = true;
        const formData = new FormData();
        formData.append('file', img.value);
        formData.append('method', method.value);
        formData.append('cs', cs.value);

        const response = await api.post('/img', formData, {
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        });
        // console.log(response.data.data)
        emits('processed-image', response.data.data);
        processing.value = false;
    } catch (error) {
        console.error('Error uploading image:', error);
    }
};


</script>

<style>
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
</style>
<!-- #upload-input {
    position: absolute;
    top: 0;
    left: 0;
    opacity: 0;
    width: 100%;
    height: 100%;
    cursor: pointer;
} -->