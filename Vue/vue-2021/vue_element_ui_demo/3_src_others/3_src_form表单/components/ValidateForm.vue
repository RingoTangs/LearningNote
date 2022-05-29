<template>
  <div>
    <h1>表单校验</h1>
    <p>
      在 Form 组件中，每一个表单域由一个 Form-Item
      组件构成，表单域中可以放置各种类型的表单控件，包括
      Input、Select、Checkbox、Radio、Switch、DatePicker、TimePicker
    </p>

    <el-form ref="activityForm" :model="form" :rules="rules" label-width="80px">
      <!-- 1. 活动名称 -->
      <el-form-item label="活动名称" prop="name">
        <el-input v-model.trim="form.name" clearable placeholder="请输入活动名称" />
      </el-form-item>

      <!-- 2. 活动区域 -->
      <el-form-item label="活动区域" class="activity-region" prop="region">
        <el-select v-model="form.region" clearable placeholder="请选择活动区域">
          <el-option label="北京" value="beijing" />
          <el-option label="上海" value="shanghai" />
        </el-select>
      </el-form-item>

      <!-- 3. 活动时间 -->
      <!-- 这里要注意 是 el-form-item 下又套了一层 el-form-item -->
      <el-form-item label="活动时间" class="active-time" required>
        <el-col :span="11">
          <el-form-item prop="date">
            <el-date-picker
              v-model="form.date"
              type="date"
              placeholder="选择日期"
              :picker-options="pickerOptions"
              :editable="false"
              value-format="yyyy-MM-dd"
            />
          </el-form-item>
        </el-col>

        <el-col class="line" :span="2">-</el-col>

        <el-col :span="11">
          <el-form-item prop="time">
            <el-time-picker v-model="form.time" placeholder="选择时间" :editable="false" />
          </el-form-item>
        </el-col>
      </el-form-item>

      <!-- 4. 即时配送 -->
      <el-form-item label="即时配送" prop="delivery">
        <el-tooltip placement="top" :content="form.delivery ? '是' : '否'">
          <el-switch v-model="form.delivery" active-color="#13ce66" inactive-color="#ff4949" />
        </el-tooltip>
      </el-form-item>

      <!-- 5. 活动性质 -->
      <el-form-item label="活动性质" class="active-type app-custom-color" prop="activeType">
        <el-checkbox-group v-model="form.activeType">
          <el-checkbox v-for="type in activeTypeList" :key="type.id" :label="type.id">
            {{ type.name }}
          </el-checkbox>
        </el-checkbox-group>
      </el-form-item>

      <!-- 6. 特殊资源 -->
      <el-form-item label="特殊资源" class="resource" prop="resource">
        <el-radio-group v-model="form.resource">
          <el-radio v-for="resource in resourceList" :key="resource.id" :label="resource.id">
            {{ resource.name }}
          </el-radio>
        </el-radio-group>
      </el-form-item>

      <!-- 7. 活动形式 -->
      <el-form-item label="活动形式" prop="desc">
        <el-input v-model="form.desc" type="textarea" />
      </el-form-item>

      <!-- 8. 按钮 -->
      <el-form-item>
        <el-button type="primary" @click="onCreateBtnClick(form)">立即创建</el-button>
        <el-button @click="onResetBtnClick">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'TypicalForm',
  data() {
    return {
      pickerOptions: {
        disabledDate(time) {
          const date = new Date()
          date.setHours(0, 0, 0, 0)
          return time.getTime() < date.getTime()
        }
      },
      form: {
        name: '',
        region: '',
        date: '',
        time: '',
        delivery: false,
        activeType: [],
        resource: '',
        desc: ''
      },
      activeTypeList: [
        { id: this.nanoid(), name: '美食/餐厅线上活动' },
        { id: this.nanoid(), name: '地推活动' },
        { id: this.nanoid(), name: '线下主题活动' },
        { id: this.nanoid(), name: '单纯商品曝光' }
      ],
      resourceList: [
        { id: this.nanoid(), name: '线上品牌赞助商' },
        { id: this.nanoid(), name: '线下场地免费' }
      ],
      rules: {
        name: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        region: [{ required: true, message: '请选择活动区域', trigger: 'change' }],
        date: [{ type: 'string', required: true, message: '请选择日期', trigger: 'change' }],
        time: [{ type: 'string', required: true, message: '请选择时间', trigger: 'change' }],
        activeType: [
          { type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change' }
        ],
        resource: [{ required: true, message: '请选择活动资源', trigger: 'change' }],
        desc: [{ required: true, message: '请填写活动形式', trigger: 'blur' }]
      }
    }
  },
  methods: {
    /**
     * 监听 "立即创建" 按钮点击
     * @param {object}
     */
    onCreateBtnClick(form) {
      this.$refs['activityForm'].validate((valid) => {
        if (!valid) return false
        console.log(form)
        return true
      })
    },
    onResetBtnClick() {
      if (window.confirm('确定要重置表单吗？')) {
        this.$refs['activityForm'].resetFields()
      }
    }
  }
}
</script>

<style lang="scss" scoped>
$color: purple;

.el-form {
  width: 400px;
  height: 400px;
  overflow-y: auto;
  padding: 20px 20px 20px 0;
  border-radius: 5px;
  border: 1px solid rgba(0, 0, 0, 0.3);
  box-shadow: 0 0 5px 5px rgba(0, 0, 0, 0.1);
  .activity-region {
    .el-select {
      width: 100%;
    }
  }
  .active-time {
    .el-date-editor {
      width: 100%;
    }
    .line {
      text-align: center;
    }
  }
  .active-type {
    ::v-deep {
      .el-checkbox__label {
        width: 105px;
      }
      .el-checkbox__input.is-checked + .el-checkbox__label {
        color: $color;
      }
      .el-checkbox__input.is-checked .el-checkbox__inner,
      .el-checkbox__input.is-indeterminate .el-checkbox__inner {
        background-color: $color;
        border-color: $color;
      }
    }
  }
  .resource {
    ::v-deep {
      .el-radio__input.is-checked + .el-radio__label {
        color: $color;
      }
      .el-radio__input.is-checked .el-radio__inner {
        border-color: $color;
        background-color: $color;
      }
    }
  }
}
</style>
