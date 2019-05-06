<template>
  <el-container>
    <el-header>
  </el-header>
    <el-main>

      <el-row type="flex" v-for="i in categories">
        <el-col class="left-title" :span="4">
          <h4><i>{{i.id}}F</i></h4>
          <h5>{{i.title}}</h5>
        </el-col>
        <el-col :span="4" v-for="x in all_categories_books[i.id-1]">
          <router-link :to="{name: 'desc', params: { color: x.id }}">
          <img   :src="x.pic_url" height="200" width="150" ><br>
          书名：{{x.title}}<br>
          作者：{{x.author}}<br>
          定价：{{x.price}}<br>
          </router-link>
        </el-col>


      </el-row>
    </el-main>
  </el-container>
</template>

<script>
  import * as RestAPI from '@/api/RestAPI';
    export default {
        name: "index",
        data(){
          return{
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
            all_categories_books:[
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
      created () {
        this.get_categories()
      },
      methods:{
        get_categories(id) {
          RestAPI.getAllCategories() .then(function(res){
            this.categories = res.data.data
            this.get_categories_book()
          },function(){
            console.log('请求失败')
          })
        },
        get_categories_book(category_id, page, per_page){
          RestAPI.getBooksInCategory(category_id, page, per_page).then(
            function(res){
              return  res.data.data
            },function(){
              console.log('请求失败');
            }
          )
        },
        get_all_categories_book() {
          for (let i in this.categories){
            this.all_categories_books[i.id-1]=this.get_categories_book(i.id, 1, 4)
          }
        }

      }
    }
</script>

<style scoped>
  .left-title {float: left; height: 300px; width: 198px; text-align: center;}

</style>
