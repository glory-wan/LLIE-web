<template>
    <div class="radios">
        <el-radio-group v-model="color_space">
            <el-radio-button label="rgb" value="rgb" />
            <el-radio-button label="hls" value="hls" />
            <el-radio-button label="hsv" value="hsv" />
            <el-radio-button label="lab" value="lab" />
            <el-radio-button label="yuv" value="yuv" />
        </el-radio-group>
    </div>

    <div class="radios">
        <el-radio-group v-model="algorithm">
            <el-radio-button label="he" value="he" />
            <el-radio-button label="clahe" value="clahe" />
            <el-radio-button label="rclahe" value="rclahe" />
        </el-radio-group>
    </div>

    <el-button type="primary" size="large" @click="processImage" :loading="processing">开始处理</el-button>
</template>

<script setup>
import { ref } from 'vue';
import api from '@/api/request.js';
import { ElMessage } from 'element-plus';

const props = defineProps({ originalImage: String })
const algorithm = ref(null);
const color_space = ref(null);
const processing = ref(false);
const emits = defineEmits(['processed-image'])

const processImage = async () => {
    if (props.originalImage == null || algorithm.value == null || color_space.value == null) {
        ElMessage.error('请选择图片文件和算法');
        return;
    }
    try {
        processing.value = true;
        const formData = new FormData();
        formData.append('file_path', props.originalImage);
        formData.append('algorithm', algorithm.value);
        formData.append('color_space', color_space.value);

        const response = await api.post('/img', formData, {
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        });
        console.log('Image processed successfully:', response.data);
        // 假设服务器返回的响应中包含图片的 URL
        emits('processed-image', response.data.data);
        processing.value = false;
    } catch (error) {
        console.error('Error uploading image:', error);
    }
};
</script>