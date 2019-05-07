<template>
  <el-container>
    <el-header>
    </el-header>
    <el-main>

      <el-row type="flex" v-for="(category, index) in categories">
        <el-col class="left-title" :span="4">
          <h4><i>{{category.id}}F</i></h4>
          <h5>{{category.title}}</h5>
        </el-col>
        <!--<span>{{category.books.length}}</span>-->
        <!--<el-col :span="4" v-for="book in category.books">-->
        <el-col :span="4" v-for="book in all_categories_books[index]">
          <img :src="book.pic_url" height="200" width="150"><br/>
          书名：{{book.title}}<br/>
          作者：{{book.author}}<br/>
          定价：{{book.price}}<br/>

          <!--<router-link :to="{name: 'desc', params: { color: book.id }}">-->
          <!--<img :src="book.pic_url" height="200" width="150"><br>-->
          <!--书名：{{book.title}}<br>-->
          <!--作者：{{book.author}}<br>-->
          <!--定价：{{book.price}}<br>-->
          <!--</router-link>-->
        </el-col>
      </el-row>

      <!--<el-row type="flex" v-for="i in categories">-->
      <!--<el-col class="left-title" :span="4">-->
      <!--<h4><i>{{i.id}}F</i></h4>-->
      <!--<h5>{{i.title}}</h5>-->
      <!--</el-col>-->
      <!--<el-col :span="4" v-for="x in all_categories_books[i.id-1]">-->
      <!--<router-link :to="{name: 'desc', params: { color: x.id }}">-->
      <!--<img :src="x.pic_url" height="200" width="150"><br>-->
      <!--书名：{{x.title}}<br>-->
      <!--作者：{{x.author}}<br>-->
      <!--定价：{{x.price}}<br>-->
      <!--</router-link>-->
      <!--</el-col>-->


      <!--</el-row>-->

    </el-main>
  </el-container>
</template>

<script>
  import * as RestAPI from '@/api/RestAPI';

  export default {
    name: "index",
    data() {
      return {
        categories: [
          {
            "id": 1,
            "title": "文学"
          },
          {
            "id": 2,
            "title": "流行"
          }
        ],
        all_categories_books: [
          [
            {
              "id": 1770782,
              "title": "追风筝的人",
              "author": "[美]卡勒德·胡赛尼",
              "price": 29.00,
              "douban_score": 8.9,
              "pic_url": "https://img3.doubanio.com/view/subject/l/public/s1727290.jpg"
            },
            {
              "id": 1006197,
              "title": "哈姆莱特",
              "author": "[英]威廉·莎士比亚",
              "price": 7,
              "douban_score": 8.5,
              "pic_url": "https://img1.doubanio.com/view/subject/l/public/s27009357.jpg"
            }
          ],
          [
            {
              "id": 1770782,
              "title": "追风筝的人",
              "author": "[美]卡勒德·胡赛尼",
              "price": 29.00,
              "douban_score": 8.9,
              "pic_url": "https://img3.doubanio.com/view/subject/l/public/s1727290.jpg"
            },
            {
              "id": 1006197,
              "title": "哈姆莱特",
              "author": "[英]威廉·莎士比亚",
              "price": 7,
              "douban_score": 8.5,
              "pic_url": "https://img1.doubanio.com/view/subject/l/public/s27009357.jpg"
            }
          ]
        ]
      }
    },
    created() {
      this.get_categories();
    },
    methods: {
      get_categories() {
        var _this = this;
        RestAPI.getAllCategories().then(function (res) {
          if (res.data.status === 200) {
            _this.categories = res.data.data;
            _this.get_all_categories_book();
          } else
            console.log(res);
        }, function () {
          console.log('请求失败')
        })
      },
      get_categories_book(i) {
        var id = this.categories[i].id;
        var _this = this;
        RestAPI.getBooksInCategory(id, 1, 5).then(
          function (res) {
            if (res.data.status === 200) {
              _this.all_categories_books[i] = res.data.data;
            } else console.log(res);
          }).catch(function (err) {
          console.log('请求失败');
        });

        // var _this = this;
        // var categories = this.categories;
        // for (let i = 0; i < categories.length; i++) {
        //   var category = categories[i];
        //   RestAPI.getBooksInCategory(category.id, 1, 5).then(
        //     function (res) {
        //       if (res.data.status === 200) {
        //         categories[i].books = res.data.data;
        //         // _this.categories[i].books = res.data.data;
        //
        //         // Vue.set(_this.categories, i, category);
        //         // _this.categories[i].splice(i, 1, category);
        //
        //         console.log(category);
        //       } else console.log(res);
        //     }).catch(function (err) {
        //     console.log('请求失败');
        //   });
        // }
        // this.categories = categories;
        // console.log("categories: " );
        // console.log(categories);
        // console.log("this.categories: ");
        // console.log(this.categories);
      },
      get_all_categories_book() {
        for (let i = 0; i < this.categories.length; i++) {
          // categories_books[i] = this.get_categories_book(this.categories[i].id, 1, 5);
          this.get_categories_book(i);
        }
        console.log("all_categories_books");
        console.log(this.all_categories_books);
      }

    }
  }
</script>

<style scoped>
  .left-title {
    float: left;
    height: 300px;
    width: 198px;
    text-align: center;
  }

</style>
