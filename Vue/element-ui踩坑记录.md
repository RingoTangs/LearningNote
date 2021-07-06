# 一、组件：Form表单

## 1. 自定义校验规则

```html
<!-- 表单 -->
<el-form :rules="rules" :model="formFields" ref="LoginForm">
    <!-- 用户名 -->
    <el-form-item prop="username">
        <el-input type="text" v-model="formFields.username">
        </el-input>
    </el-form-item>

    <!-- 登录 -->
    <el-form-item>
        <el-button type="success" @click="loginBtnClick('LoginForm')">
            登录
        </el-button>
    </el-form-item>
</el-form>

<script>
    export default {
        data() {
            // 检查用户名
            let checkUsername = (rule, value, callback) => {
                if (!value || value.trim() == '')
                    return callback(new Error('用户名/手机号不能为空'));
                callback();
            };

            // 检查密码
            let checkPassword = (rule, value, callback) => {
                if (!value || value.trim() == '')
                    return callback(new Error('密码不能为空'));
                callback();
            };
            
            return {
                // 表单属性
                formFields: {
                    username: '',
                    password: '',
                    rememberMe: false,
                },
                // 表单校验规则
                rules: {
                    // 和 formFields 中的 key 要一致
                    username: [{validator: checkUsername, trigger: onblur}],
                    password: [{validator: checkPassword, trigger: onblur}]
                },
            }
        },
        
        methods: {
            loginBtnClick(formName) {
                this.$refs[formName].validate(valid => {
                    if (valid)
                        alert("success");
                    else
                        console.log("error");
                })
            },
        }
    }
</script>
```

1. 在data()中写自定义校验逻辑 `checkUseranme..`。rule 表示校验规则，value 标识表单中要校验的值。
2. 在 return 中填写自定义校验规则和触发条件（onblur失去焦点时触发）。并添加`<el-form :rules="rules">`。**注意：rules中的key和表单中的key必须完全一致**！ 
3. 登录按钮，传入的是字符串`LoginForm`，这个要和表单中的 `ref="LoginForm"` 一致，这样才可以通过 this.$refs[LoginForm]拿到表单组件。
4. `<el-form :model="formFields">` 必须填写，否则点击 登录按钮 时无法校验。
5. `<el-form-item prop="username"`> 必须填写，传入 Form 组件的 `model` 中的字段。

