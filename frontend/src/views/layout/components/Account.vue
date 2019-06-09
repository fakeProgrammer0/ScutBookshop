<template>
    <div v-if="Login == true">
      <Login @toRegister="toOther" @LoginSuccess="LoginSuccess"></Login>
    </div>
    <div v-else>
      <Register @toLogin="toOther"></Register>
    </div>
</template>

<script>
  import Login from './Login'
  import Register from './Register'
  export default {
    name: 'Account',
    components: {
      Register,
      Login
    },
    data(){
      return {
        Login:true
      }
    },
    methods:{
      toOther(val){
        this.Login = val
      },
      LoginSuccess(val){
        this.$emit("success",val)
      }
    }
  }
</script>

<style scoped>

</style>
