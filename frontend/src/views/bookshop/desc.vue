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
        <span style="font-size: 36px;padding-top: 5px;padding-bottom: 5px">{{ book.title }}</span>
        <div style="float: right;margin-right: 20px">
          <el-button :type="favoriteBtn.type" :icon="favoriteBtn.icon" @click="handleClickFavBtn">
          </el-button>
        </div>
      </div>

      <div>
        <div style="float: left;padding-right: 40px;min-height: 400px;height: auto !important;height: 400px">
          <img :src="book.pic_url" height="350px">
        </div>

        <div style="margin-right: 20px">
          <div class="book-desc">名称：{{ book.title }}</div>
          <div class="book-desc" v-if="book.original != null">原作名：{{ book.original }}</div>
          <div class="book-desc">作者：{{ book.author }}</div>
          <!--<div class="book-desc">作者简介：{{ book.author_intro }}</div>-->
          <div class="book-desc" v-if="book.translator != null">译者：{{ book.translator }}</div>
          <div class="book-desc">出版社：{{ book.press }}</div>
          <div class="book-desc">出版日期：{{ book.publish_date }}</div>

          <div class="book-desc">页数：{{ book.pages }}</div>

          <div class="book-desc">定价：{{ book.price }}</div>
          <div class="book-desc">装帧：{{ book.binding }}</div>
          <div class="book-desc">豆瓣评分：{{ book.douban_score }}</div>
          <div class="book-desc">ISBN：{{ book.ISBN }}</div>
        </div>

      </div>

    </el-card>

    <el-card class="box-card-component" style="margin-top: 50px;margin-bottom: 50px;background-color: #263445;">
      <div slot="header">
        <span style="font-size: 32px;color: #FFFFFF;padding-top: 5px;padding-bottom: 5px">图书简介</span>
      </div>

      <el-card
        class="box-card-component"
        style="margin-top: 20px;margin-bottom: 20px;">
        <div style="font-size: 16px;line-height:20pt;">
          {{ book.summary }}
        </div>
      </el-card>

    </el-card>

    <el-card class="box-card-component" style="margin-top: 50px;margin-bottom: 50px;background-color: #263445;">
      <div slot="header">
        <span style="font-size: 32px;color: #FFFFFF;padding-top: 5px;padding-bottom: 5px">作者简介</span>
      </div>

      <el-card
        class="box-card-component"
        style="margin-top: 20px;margin-bottom: 20px;">
        <div style="font-size: 16px;line-height:20pt;">
          {{ book.author_intro }}
        </div>
      </el-card>

    </el-card>
    <!--<el-card class="box-card-component" style="margin-top: 50px;margin-bottom: 50px">-->
    <!--<div slot="header" class="box-card-header">-->
    <!--<span>豆瓣短评</span>-->
    <!--</div>-->
    <!--<li v-for="comment in shortComments">-->
    <!--{{comment}}-->
    <!--</li>-->
    <!--<div>-->

    <!--</div>-->
    <!--</el-card>-->

    <el-card class="box-card-component" style="margin-top: 50px;margin-bottom: 50px;background-color: #263445;">
      <div slot="header">
        <span style="font-size: 32px;color: #FFFFFF;padding-top: 5px;padding-bottom: 5px">豆瓣短评</span>
      </div>

      <el-card
        v-for="comment in shortComments"
        class="box-card-component"
        style="margin-top: 20px;margin-bottom: 20px;">
        <div style="font-size: 16px;line-height:20pt;">
          {{ comment }}
        </div>
      </el-card>

    </el-card>

    <!--<div style="float: left;margin-left: 10px;margin-right: 10px;width: 300px;" v-for="comment in shortComments">-->
    <!--<el-card style="min-height: 300px">-->
    <!--<span>-->
    <!--{{comment}}-->
    <!--</span>-->
    <!--</el-card>-->
    <!--</div>-->

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
    name: 'Desc',
    props: {
      book_id: {
        type: Number,
        default: 1770782
      }
    },
    data() {
      return {
        book: {
          "id": 1770782,
          "title": "追风筝的人",
          "original": null,
          "author_id": 997810,
          "author": "[美]卡勒德·胡赛尼",
          "translator": null,
          "press": "上海人民出版社",
          "publish_date": "2006-5",
          "pages": 362,
          "price": 29,
          "binding": "平装",
          "douban_score": 8.9,
          "book_url": "https://book.douban.com/subject/1770782/",
          "pic_url": "https://img3.doubanio.com/view/subject/l/public/s1727290.jpg",
          "summary": "12岁的阿富汗富家少爷阿米尔与仆人哈桑情同手足。然而，在一场风筝比赛后，发生了一件悲惨不堪的事，阿米尔为自己的懦弱感到自责和痛苦，逼走了哈桑，不久，自己也跟随父亲逃往美国。 成年后的阿米尔始终无法原谅自己当年对哈桑的背叛。为了赎罪，阿米尔再度踏上暌违二十多年的故乡，希望能为不幸的好友尽最后一点心力，却发现一个惊天谎言，儿时的噩梦再度重演，阿米尔该如何抉择？ 故事如此残忍而又美丽，作者以温暖细腻的笔法勾勒人性的本质与救赎，读来令人荡气回肠。",
          "author_intro": "卡勒德·胡赛尼（Khaled Hosseini），1965年生于阿富汗喀布尔市，后随父亲迁往美国。胡赛尼毕业于加州大学圣地亚哥医学系，现居加州。“立志拂去蒙在阿富汗普通民众面孔的尘灰，将背后灵魂的悸动展示给世人。”著有小说《追风筝的人》(The Kite Runner，2003）、《灿烂千阳》(A Thousand Splendid Suns，2007)、《群山回唱》（And the Mountains Echoed,2013）。作品全球销量超过4000万册。2006年，因其作品巨大的国际影响力，胡赛尼获得联合国人道主义奖，并受邀担任联合国难民署亲善大使。",
          "ISBN": "9787208061644"
        },
        shortComments: [
          "前半部比后半部好",
          "过誉",
          "非常好的一本通俗小说．适合拍电影的好脚本．里面有很多可贵的东西．但情节上也有些地方不可理喻．前半部非常的棒．但后面落入俗套子－－但还是感动了我．这是作者的处女作，已经非常的了不起了．",
          "追到的只有自己的过去\n\n救赎的只有自己的现在",
          "改变了我因为19：00对阿富汗20多年的曲解"
        ],
        starOnBtn: {
          type: 'danger',
          icon: 'el-icon-star-on'
        },
        starOffBtn: {
          type: 'info',
          icon: 'el-icon-star-off'
        },
        favoriteBtn: null,
      }
    },
    created() {
      this.favoriteBtn = this.starOffBtn;
      this.loadBookDetail();
      this.loadShortComments();
      this.checkBookCollected();
    },
    methods: {
      loadBookDetail() {
        var _this = this;
        RestAPI.getBookDetail(this.book_id).then(function (response) {
          if (response.data.status === 200){
            _this.book = response.data.data;
          }else{
            console.log(response.data);
            // TODO: 提示找不到该图书
            if(response.status === 404)
              console.log(response.data.msg);
          }
        }).catch(function (error) {
          console.log(error)
        });
      },
      loadShortComments() {
        var _this = this;
        RestAPI.getShortComments(this.book_id).then(function (response) {
          if (response.data.status === 200) {
            _this.shortComments = response.data.data;
            console.log(_this.shortComments)
          } else {
            console.log('error')
            console.log(response)
          }
        }).catch(function (error) {
          console.log(error)
        })
      },
      // TODO: 待修改
      handleClickFavBtn() {
        if (this.favoriteBtn === this.starOffBtn) {
          this.addFavoriteBook()
        } else {
          this.removeFavoriteBook()
        }
      },
      addFavoriteBook() {
        var user_id = 1;

        var _this = this;
        RestAPI.addFavoriteBook(user_id, _this.book_id).then(res => {
          if(res.data.status === 200)
          {
            _this.favoriteBtn = _this.starOnBtn;
            _this.$message({
              message: '收藏图书成功',
              type: 'success'
            });
          }
        }).catch(err => {
          console.log(err);
        });
      },
      removeFavoriteBook() {
        var user_id = 1;

        var _this = this;
        RestAPI.removeFavoriteBook(user_id, _this.book_id).then(res => {
          if(res.data.status === 200)
          {
            _this.favoriteBtn = _this.starOffBtn;
            _this.$message({
              message: '已取消收藏该图书',
              type: 'warning'
            });
          }
        }).catch(err => {
          console.log(err);
        });
      },
      checkBookCollected() {
        // if (this.$store.getters.username === '') {
        //   console.log('null username')
        //   this.$store.commit('SET_USERNAME', 'admin')
        //   console.log('set username: ' + this.$store.getters.username)
        // }

        var user_id = 1;
        var _this = this
        RestAPI.checkFavoriteBook(user_id, this.book_id).then(res => {
          if(res.data.status === 200)
            _this.favoriteBtn = _this.starOnBtn;
        }).catch(err => {
          _this.favoriteBtn = _this.starOffBtn;
          console.log(err);
        });
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
