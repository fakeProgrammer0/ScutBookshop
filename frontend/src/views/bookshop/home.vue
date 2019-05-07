<template>
  <el-container>
    <el-main>
      <el-card class="box-card-component title" shadow="always" v-for="category in categories">
        <div slot="header" class="box-card-header">

          <h2>{{category.title}}</h2>

        </div>
      <el-row type="flex">
<!--        <el-col class="left-title" :span="4">-->
<!--          <h4><i>{{category.id}}F</i></h4>-->
<!--          <h5>{{category.title}}</h5>-->
<!--        </el-col>-->
        <!--<span>{{category.books.length}}</span>-->
        <el-col :span="4" :offset="1" class="item" v-if="category.books != null" v-for="book in category.books">
          <router-link :to="{name: 'desc', params: { book_id: book.id }}">
          <img :src="book.pic_url" height="200" width="150"><br/>
          </router-link>
          【{{book.title}}】<br/>
          {{book.author}}<br/>
          {{book.price.toFixed(2)}}<br/>
        </el-col>
      </el-row>
      </el-card>

    </el-main>
  </el-container>
</template>

<script>
  import * as RestAPI from '@/api/RestAPI';

  export default {
    name: "home",
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
        ]
      }
    },
    created() {
      this.get_categories();
    },
    methods: {
      get_categories() {
        var _this = this;
        RestAPI.getAllCategoriesBooks().then(function (res) {
          if (res.data.status === 200) {
            _this.categories = res.data.data;
          } else
            console.log(res);
        }, function () {
          console.log('请求失败')
        })


        // RestAPI.getAllCategories().then(function (res) {
        //   if (res.data.status === 200) {
        //     _this.categories = res.data.data;
        //     _this.get_all_categories_book();
        //   } else
        //     console.log(res);
        // }, function () {
        //   console.log('请求失败')
        // })
      },
      // get_all_categories_book() {
      //   var _this = this;
      //   for (let i = 0; i < this.categories.length; i++) {
      //     var category = this.categories[i];
      //     RestAPI.getBooksInCategory(category.id, 1, 5).then(
      //       function (res) {
      //         if (res.data.status === 200) {
      //           _this.categories[i].books = res.data.data;
      //           // _this.categories[i].books = res.data.data;
      //
      //           // Vue.set(_this.categories, i, category);
      //           // _this.categories[i].splice(i, 1, category);
      //
      //           console.log(category);
      //         } else console.log(res);
      //       }).catch(function (err) {
      //       console.log('请求失败');
      //     });
      //   }
      //   // this.categories = categories;
      //   // console.log("categories: " );
      //   // console.log(categories);
      //   console.log("this.categories: ");
      //   console.log(this.categories);
      //   this.categories.push({});
      //   this.categories.splice(this.categories.length - 1, 1);
      // }

    }
  }
</script>

<style scoped>
  .title{
    margin-top: 20px;
    margin-bottom: 50px;
  }
  .box-card-header{
    height: 20px;
    margin-left: 30px;
    line-height: 10px;
  }
  .item{
    text-align: center;
  }

</style>
