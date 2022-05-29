<template>
  <div>
    <h1>自定校验规则表单</h1>

    <el-form :model="ruleForm" :rules="rules" status-icon label-width="80px">
      <el-form-item label="密码" prop="pass">
        <el-input
          v-model.trim="ruleForm.pass"
          class="check-color"
          type="password"
          autocomplete="off"
        />
      </el-form-item>

      <el-form-item label="确认密码" prop="checkPass">
        <el-input
          v-model.trim="ruleForm.checkPass"
          class="check-color"
          type="password"
          autocomplete="off"
        />
      </el-form-item>

      <el-form-item label="年龄" prop="age">
        <el-input v-model.number="ruleForm.age" class="check-color" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary">提交</el-button>
        <el-button>重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'CustomValidateForm',
  data() {
    // eslint-disable-next-line
    const validatePass = (rule, value, callback) => {
      if (!value) return callback(new Error('请输入密码'))
      return callback()
    }
    // eslint-disable-next-line
    const validatePass2 = (rule, value, callback) => {
      if (!value) return callback(new Error('请再次输入密码'))
      if (value !== this.ruleForm.pass) return callback(new Error('两次输入密码不一致'))
      return callback()
    }
    // eslint-disable-next-line
    const checkAge = (rule, value, callback) => {
      console.log(value)
      if (!value) return callback(new Error('请填写年龄'))
      if (!Number.isInteger(value)) return callback(new Error('请输入数字'))
      return callback()
    }
    return {
      ruleForm: {
        pass: '',
        checkPass: '',
        age: ''
      },
      rules: {
        pass: [
          { required: true, validator: validatePass, trigger: 'blur' },
          { min: 6, max: 10, message: '长度应该6-10个字符', trigger: 'blur' }
        ],
        checkPass: [{ required: true, validator: validatePass2, trigger: 'blur' }],
        age: [{ required: true, validator: checkAge, trigger: 'blur' }]
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.el-form {
  width: 50%;
  .check-color {
    ::v-deep {
      .el-input__suffix {
        color: green;
      }
    }
  }
}
</style>
