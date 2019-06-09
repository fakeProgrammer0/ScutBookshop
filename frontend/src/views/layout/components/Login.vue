<!--基本html代码区域-->
<template>
    <div v-if="Account==true">
        <el-form :model="form" status-icon :rules="rules" ref="form" label-width="100px" class="demo-ruleForm">
          <el-row type="flex" justify="center">
            <h2>登录</h2>
          </el-row>
          <el-row type="flex" justify="center">
            <el-form-item label="账户：" prop="username">
              <el-input v-model="form.username" size="small"></el-input>
            </el-form-item>
          </el-row>
          <el-row type="flex" justify="center">
            <el-form-item label="密码：" prop="password">
              <el-input v-model="form.password" size="small" type="password"></el-input>
            </el-form-item>
          </el-row>
          <el-row type="flex" justify="center">
            <el-button type="primary" @click="submitForm('form')">登录</el-button>
            <el-button type="primary" @click="register">注册</el-button>
          </el-row>
        </el-form>
    </div>
</template>

<script>
  import * as RestAPI from '@/api/RestAPI';
  import store from '@/store'
  export default {
    name: 'login',
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
      return {
        Account:true,
        form: {
          username: '',
          password: ''
        },
        rules: {
          username: [
            { validator: validateName, trigger: 'blur' }
          ],
          password: [
            { validator: validatePass, trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      /* 提交进行判断的函数 */
      submitData: function () {
        let _this = this;
        RestAPI.login(_this.form.username, _this.form.password).then(function(res) {
          if (res.data.status === 200) {
            let user = res.data.data.user
            store.dispatch("setUser",user)
            _this.$emit("LoginSuccess",user)
          } else {
            alert(res.data.msg)
          }
        }).catch(function (err)  {
          alert("用户名或密码错误")
          console.log(err)
        })
      },
      register: function () {
        this.$emit('toRegister', false)
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
<!-- 写样式的地方 -->
<style scoped>

</style>
