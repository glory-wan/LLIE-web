<template>
    <div class="upload-container">
        <label for="upload-input" class="upload-button">
            选择并上传文件
            <input type="file" id="upload-input" @change="uploadImage" style="display: none;">
        </label>
    </div>
</template>

<script setup lang="ts">
import api from '@/api/request.js';
import { ElMessage } from 'element-plus';

const emits = defineEmits(['file-change'])

const uploadImage = async (e) => {
    try {
        const file = e.target.files[0];

        if (!file.type.startsWith('image/')) {
            ElMessage.error('请选择图片文件')
            return
        }
        else if (file.size > 1024 * 1024 * 8) {
            ElMessage.error('文件大小超过限制(8MB)，请重新选择')
            return
        }

        const formData = new FormData();
        formData.append('file', file);

        const response = await api.post('/common/upload', formData, {
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        });
        console.log('Image upload successfully:', response.data);
        // 假设服务器返回的响应中包含图片的 URL
        emits('file-change', response.data.data);
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