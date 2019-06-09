<template>
        <el-form ref="form" :model="form" label-width="100px"  status-icon :rules="rules">
          <h2>注册</h2>
          <el-form-item label="账户：" prop="username">
            <el-input v-model="form.username" size="small"></el-input>
          </el-form-item>
          <el-form-item label="密码：" prop="password">
            <el-input v-model="form.password" size="small" type="password"></el-input>
          </el-form-item>

          <el-form-item label="确认密码：" prop="checkpassword">
            <el-input v-model="form.checkpassword" size="small" type="password"></el-input>
          </el-form-item>

          <el-row type="flex" justify="center">
            <el-button  type="primary" @click="submitForm('form')" style="width: 68%; font-size: 1.2em; word-spacing: 2.5em">注册</el-button>
          </el-row>

          <el-row type="flex" justify="center" style="margin-top: 20px">
            <el-col  style="font-size: 0.8em">
              已有账号？
              <el-button type="text"  @click="login">直接登录 → </el-button>
            </el-col>
          </el-row>
        </el-form>
</template>
<script>
  import * as RestAPI from '@/api/RestAPI';
  import store from '@/store'
  export default {
    name: 'register',
    data () {
      var validateName = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入用户名'))
        }
        callback()
      }
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'))
        } else {
          callback()
        }
      }
      var validateCheckPassword = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'))
        } else if (value !== this.form.password) {
          callback(new Error('两次输入密码不一致!'))
        } else {
          callback()
        }
      }
      return {
        form: {
          username: '',
          password: '',
          checkpassword: '',
        },
        rules: {
          username: [
            {validator: validateName, trigger: 'blur'}
          ],
          password: [
            {validator: validatePass, trigger: 'blur'}
          ],
          checkpassword: [
            {validator: validateCheckPassword, trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      /* 提交进行判断的函数 */
      submitData: function () {
        let _this = this
        let data = ({
          'username': this.form.username,
          'password': this.form.password,
        })
        // todo:接入后端api
        RestAPI.signUp(data).then(function(res) {
          alert("注册成功")
          _this.$emit('toLogin', true)
        }).catch(function(err) {
          alert(err)
        })
      },
      login: function () {
        this.$emit('toLogin', true)
      },
      submitForm (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.submitData()
          } else {
            return false
          }
        })
      }

    }
  }

</script>

<style scoped>
  .register{
    display:flex;
    flex-direction: column;
    justify-content: center;
    flex-grow: initial;
    align-items: center;
    text-align: center;
  }
</style>
