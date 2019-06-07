<template>
  <div class="navbar">
    <hamburger :toggle-click="toggleSideBar" :is-active="sidebar.opened" class="hamburger-container"/>

<!--    <breadcrumb class="breadcrumb-container"/>-->
    <span class="left-menu">
       <router-link :to="{name: 'home'}">
      <el-link>首页</el-link>
       </router-link>

      <el-link>推荐</el-link>
    </span>

    <div class="right-menu">
<!--      <template v-if="device!=='mobile'">-->
<!--        <error-log class="errLog-container right-menu-item"/>-->

<!--        <el-tooltip :content="$t('navbar.screenfull')" effect="dark" placement="bottom">-->
<!--          <screenfull class="screenfull right-menu-item"/>-->
<!--        </el-tooltip>-->

<!--        <el-tooltip :content="$t('navbar.size')" effect="dark" placement="bottom">-->
<!--          <size-select class="international right-menu-item"/>-->
<!--        </el-tooltip>-->

<!--        <lang-select class="international right-menu-item"/>-->

<!--        <el-tooltip :content="$t('navbar.theme')" effect="dark" placement="bottom">-->
<!--          <theme-picker class="theme-switch right-menu-item"/>-->
<!--        </el-tooltip>-->
<!--      </template>-->


      <el-dropdown class="avatar-container right-menu-item" trigger="click">
        <div class="avatar-wrapper">
          <img :src="avatar+'?imageView2/1/w/80/h/80'" class="user-avatar">
          <i class="el-icon-caret-bottom"/>
        </div>
        <el-dropdown-menu slot="dropdown">
          <router-link to="/">
            <el-dropdown-item>
              {{ $t('navbar.dashboard') }}
            </el-dropdown-item>
          </router-link>
          <a target="_blank" href="https://github.com/PanJiaChen/vue-element-admin/">
            <el-dropdown-item>
              {{ $t('navbar.github') }}
            </el-dropdown-item>
          </a>
          <el-dropdown-item divided>
            <span style="display:block;" @click="Login">登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>

<!--    todo：定位不太会调，暂时换个顺序解决吧。。-->
    <div class="mid">
      <span><router-link :to="{name: 'fav'}"><svg-icon icon-class="star" />收藏 </router-link></span>
      <span><router-link :to="{name: 'shopping'}"><svg-icon icon-class="shopping" />购物车 </router-link></span>
    </div>

    <el-dialog title="提示" :visible.sync="LoginVisible">
      <Account></Account>
    </el-dialog>

  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import ErrorLog from '@/components/ErrorLog'
import Screenfull from '@/components/Screenfull'
import SizeSelect from '@/components/SizeSelect'
import LangSelect from '@/components/LangSelect'
import ThemePicker from '@/components/ThemePicker'
import Account from './Account'

export default {
  data(){
    return {
      LoginVisible:false
    }
  },
  components: {
    Account,
    Breadcrumb,
    Hamburger,
    ErrorLog,
    Screenfull,
    SizeSelect,
    LangSelect,
    ThemePicker
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'name',
      'avatar',
      'device'
    ])
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('toggleSideBar')
    },
    Login() {
      // this.$store.dispatch('LogOut').then(() => {
      //   location.reload()// In order to re-instantiate the vue-router object to avoid bugs
      // })
      // todo:测试用
      this.LoginVisible = true
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.navbar {
  height: 50px;
  line-height: 50px;
  border-radius: 0px !important;
  .hamburger-container {
    line-height: 58px;
    height: 50px;
    float: left;
    padding: 0 10px;
  }
  .breadcrumb-container{
    float: left;
  }
  .errLog-container {
    display: inline-block;
    vertical-align: top;
  }
  .mid{
    float: right;
    span{
      margin: 10px;
    }
  }
  .right-menu {
    float: right;
    height: 100%;
    &:focus{
     outline: none;
    }
    .right-menu-item {
      display: inline-block;
      margin: 0 8px;
    }
    .screenfull {
      height: 20px;
    }
    .international{
      vertical-align: top;
    }
    .theme-switch {
      vertical-align: 15px;
    }
    .avatar-container {
      height: 50px;
      margin-right: 30px;
      .avatar-wrapper {
        cursor: pointer;
        margin-top: 5px;
        position: relative;
        .user-avatar {
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }
        .el-icon-caret-bottom {
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
