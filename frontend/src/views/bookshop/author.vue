<template>
  <div class="search">

    <!--<div style="padding-bottom: 50px;max-height: 400px;">-->
    <!--<el-input v-model="book_id" style="width: 300px;"></el-input>-->
    <!--<el-button @click="loadBookDetail">loadDesc</el-button>-->
    <!--<el-button @click="loadShortComments">loadComments</el-button>-->
    <!--<el-button @click="handleTest">Test</el-button>-->
    <!--<el-button type="info" icon="el-icon-star-off"></el-button>-->
    <!--<el-button type="info" icon="el-icon-star-on"></el-button>-->
    <!--</div>-->

    <el-card class="box-card-component" style="margin-top: 20px;margin-bottom: 50px">
      <div slot="header" class="box-card-header">
        <span style="font-size: 36px;padding-top: 5px;padding-bottom: 5px">{{ author.title }}</span>
        <div style="float: right;margin-right: 20px">
          <el-button :type="collectedBtnType" :icon="collectedBtnIcon" @click="handleCollected"/>
        </div>
      </div>

      <div>
        <div style="float: left;padding-right: 40px;min-height: 400px;height: auto !important;height: 400px">
          <img :src="author.pic_url" height="300px">
        </div>

        <div style="margin-right: 20px">
          <div class="book-desc">姓名：{{ author.name}}</div>
          <div class="book-desc" v-if="author.original != null">语言：{{ author.original }}</div>
          <div class="book-desc">国家：{{ author.country }}</div>

          <div class="book-desc" v-if="author.gender== 1">性别：男</div>
          <div class="book-desc" v-if="author.gender== 2">性别：女</div>
          <div class="book-desc" v-if="author.gender== 0">性别：无</div>

          <div class="book-desc">生日：{{ author.born}}</div>

          <div class="book-desc" v-if="author.died != null">卒日：{{ author.died }}</div>
          <div class="book-desc" v-else>卒日：无</div>
        </div>

      </div>

    </el-card>


    <el-card class="box-card-component" style="margin-top: 50px;margin-bottom: 50px;background-color: #263445;">
      <div slot="header">
        <span style="font-size: 32px;color: #FFFFFF;padding-top: 5px;padding-bottom: 5px">简介</span>
      </div>

      <el-card
        class="box-card-component"
        style="margin-top: 20px;margin-bottom: 20px;">
        <div style="font-size: 16px;line-height:20pt;">
          {{ author.intro }}
        </div>
      </el-card>


    </el-card>

    <el-card class="box-card-component" style="margin-top: 50px;margin-bottom: 50px;">
      <div slot="header" style="background-color: #263445">
        <span style="font-size: 32px;color: #FFFFFF;padding-top: 5px;padding-bottom: 5px">该作者热门图书</span>
      </div>

      <el-col :span="4" :offset="1" class="item"  v-for="book in hotBooks">
        <router-link :to="{name: 'desc', params: { book_id: book.id }}">
          <img :src="book.pic_url" height="200" width="150"><br/>
        </router-link>
        【{{book.title}}】<br/>
        {{book.author}}<br/>
        {{parseFloat(book.price).toFixed(2)}}<br/>
      </el-col>
    </el-card>


  </div>
</template>

<style>
  .book-desc {
    padding-bottom: 5px;
    padding-top: 5px;
  }
</style>

<script>
  import * as RestAPI from '@/api/RestAPI';

  export default {
    name: 'author',
    props: {
      author_id: {
        type: Number,
        default: 997810
      }
    },
    data() {
      return {
        author:"",
        title: this.movie_title,
        hotBooks: [],
        collected: false,
        collectedBtnType: 'info',
        collectedBtnIcon: 'el-icon-star-off'
      }
    },
    created() {
      this.loadAuthorDetail();
      this.loadComment();
      this.checkBookCollected();
    },
    methods: {
      loadAuthorDetail() {
        var _this = this;
        RestAPI.getAuthorDetail(this.author_id).then(function (response) {
          if (response.data.status === 200){
            _this.author = response.data.data;
          }else{
            console.log(response.data);
            // TODO: 提示找不到该图书
          }
        }).catch(function (error) {
          console.log(error)
        });
      },
      loadComment() {
        var _this = this
        RestAPI.getAuthorHotBooks(this.book_id).then(function (response) {
          if (response.data.status === 200) {
            _this.hotBooks = response.data.data;
          } else {
            console.log('error')
            console.log(response)
          }
        }).catch(function (error) {
          console.log(error)
        })
      },








      // TODO: 待修改
      handleCollected() {
        if (this.collectedBtnType === 'info') {
          this.addFavoriteMovie()
        } else {
          this.removeFavoriteMovie()
        }
      },
      addFavoriteMovie() {
        this.collectedBtnType = 'danger'
        this.collectedBtnIcon = 'el-icon-star-on'

        var data = {
          username: this.$store.getters.username,
          title: this.title
        }
        console.log(data)
        RestAPI.addCollectedMovie(data).then(function (response) {
          if (response.data.msg == 'ok') {
            console.log('collected!')
          }
        })
      },
      removeFavoriteMovie() {
        this.collectedBtnType = 'info'
        this.collectedBtnIcon = 'el-icon-star-off'

        var data = {
          username: this.$store.getters.username,
          title: this.title
        }
        RestAPI.removeCollectedMovie(data).then(function (response) {
          if (response.data.msg == 'ok') {
            console.log('remove collected!')
          }
        })
      },
      checkBookCollected() {
        if (this.$store.getters.username === '') {
          console.log('null username')
          this.$store.commit('SET_USERNAME', 'admin')
          console.log('set username: ' + this.$store.getters.username)
        }
        var data = {
          username: this.$store.getters.username,
          title: this.title
        }
        var _this = this
        RestAPI.checkBookCollected(data).then(function (response) {
          if (response.data.msg == 'ok') {
            console.log('collected!')
            _this.collectedBtnType = 'danger'
            _this.collectedBtnIcon = 'el-icon-star-on'
          } else {
            _this.collectedBtnType = 'info'
            __this.collectedBtnIcon = 'el-icon-star-off'
          }
        })
      },
      handleTest() {
        var name = this.$store.getters.username
        var roles = this.$store.getters.roles
        console.log('name: ' + name)
        console.log('role: ' + roles)
      }
    }
  }
</script>

<style scoped>

</style>
