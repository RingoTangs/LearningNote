<template>
  <div>
    <h1>LoadingButton</h1>
    <el-button type="primary" :disabled="uploadLoading.isUpload" @click="onUploadBtnClick">
      点击上传
      <i class="el-icon--right" :class="uploadLoading.icon" />
    </el-button>
  </div>
</template>

<script>
export default {
  name: 'LoadingButton',
  data() {
    return {
      uploadLoading: {
        isUpload: false,
        icon: 'el-icon-upload'
      }
    }
  },
  methods: {
    onUploadBtnClick() {
      this.uploadLoading.isUpload = true
      this.uploadLoading.icon = 'el-icon-loading'
      // 模拟上传请求
      setTimeout(() => {
        alert('上传成功')
        this.uploadLoading.icon = 'el-icon-upload'
        this.uploadLoading.isUpload = false
      }, 2000)
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
