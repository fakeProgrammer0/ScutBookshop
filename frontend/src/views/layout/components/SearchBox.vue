<template>
  <div class="search">
    <el-container>
      <el-header style="margin-top: 20px">
        <!--<el-row style="min-height: 45px">-->
          <!--<el-col :offset="5">-->
            <!--<div align="left">-->
              <!--<el-tag v-for="entityTag in entityTags"-->
                      <!--:key="entityTag.name"-->
                      <!--closable-->
                      <!--@close="handleCloseTag(entityTag)" style="margin:5px">-->
                <!--{{entityTag.name}}-->
              <!--</el-tag>-->
            <!--</div>-->
          <!--</el-col>-->
        <!--</el-row>-->
        <el-row :gutter="0" style="margin-top: 10px;">

          <el-col :span="2" :offset="4" style="margin-right: 20px" >
            <el-select v-model="selectedSearchMode" placeholder="搜索模式"
                       @change="changeSearchMode">
              <el-option v-for="item in searchModes"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value">
              </el-option>
            </el-select>
          </el-col>

          <el-col :span="7" >
            <el-autocomplete
              v-model="keyword"
              :fetch-suggestions="searchEntityAsync"
              :placeholder="searchPlaceholder"
              @select="jumpToSearchResult"
              style="width:400px;margin-right: 10px"
              clearable
            >
              <template slot-scope="{ item }">
<!--                <img :src="item.item.pic_url" style="width: 5px"/>-->
                <div v-if="selectedSearchMode === 'hybrid'" style="float: left;width: 50px">
                  <span v-if="item.item.type === 'author'">[作者] </span>
                  <span v-if="item.item.type === 'book'">[图书] </span>
                </div>
                <div style="float: left">
                  <span>{{item.value}}</span>
                </div>
              </template>

            </el-autocomplete>
          </el-col>
          <!--<el-col :span="1" style="margin-left: 40px;margin-right: 5px">-->
            <!--<el-button type="primary" @click="getEntityInfo" icon="el-icon-info"></el-button>-->
          <!--</el-col>-->

          <el-col :span="1" style="margin-left: 100px;margin-right: 5px">
            <el-button type="primary" @click="jumpToSearchResult" icon="el-icon-search">搜索</el-button>
          </el-col>

          <!--<el-col :span="2" style="margin-left: 10px;margin-right: 5px">-->
          <!--<el-select v-model="selectedSearchMode" placeholder="搜索模式"-->
                     <!--@change="changeSearchMode">-->
            <!--<el-option v-for="item in searchModes"-->
                       <!--:key="item.value"-->
                       <!--:label="item.label"-->
                       <!--:value="item.value">-->
            <!--</el-option>-->
          <!--</el-select>-->
        <!--</el-col>-->
          <!--<el-col :span="1" style="margin-left:10px">-->
            <!--<el-checkbox v-model="cn" border :disabled="cnCheckBoxDisabled">中文分词</el-checkbox>-->
          <!--</el-col>-->
        </el-row>
      </el-header>

    </el-container>
  </div>
</template>

<script>
  import * as RestAPI from "@/api/RestAPI";

  export default {
    name: "SearchBox",
    data() {
      return {
        keyword: "",
        searchPlaceholder: "",
        selectedSearchMode: "hybrid",
        searchModes: [
          {
            label: "全部",
            value: "hybrid",
            placeholder: "请输入图书名称、作者或ISBN",
          },
          {
            label: "图书",
            value: "books",
            placeholder: "请输入图书名称或图书作者",
          },
          {
            label: "作者",
            value: "authors",
            placeholder: "请输入作者名称",
          },
          {
            label: "ISBN",
            value: "isbn",
            placeholder: "请输入图书ISBN",
          }
        ],
        searchResult: [

        ],
        searchSuggestions: [

        ],
        searchSuggestionSize: 10,

      }
    },
    created() {

    },
    methods: {
      searchEntityAsync(keyword, cb){

        // var mount_Result = [{"country":"中国","score":30.638096,"name":"鲁迅","id":4502506,"pic_url":"http://107.191.52.220:4869/3e0642dbf88826ef8777dbbd0b98104b","type":"author"},{"score":22.489704,"ISBN":"9787807240648","author":"鲁迅","price":39.8,"douban_score":8.7,"id":1398395,"title":"狂人日记","type":"book","pic_url":"http://107.191.52.220:4869/d22d4a51e7fbd058fde314ca87818928"},{"score":22.489704,"ISBN":null,"author":"鲁迅","price":0.25,"douban_score":8.7,"id":1449352,"title":"朝花夕拾","type":"book","pic_url":"http://107.191.52.220:4869/0988983502f39899465c986b896331ae"},{"score":21.90284,"ISBN":"9787500655718","author":"鲁迅","price":19.5,"douban_score":8.8,"id":1157014,"title":"伤逝","type":"book","pic_url":"http://107.191.52.220:4869/fdb9501227f0734e20179085ef8d4277"},{"score":21.90284,"ISBN":"9787201089560","author":"鲁迅","price":25.0,"douban_score":9.2,"id":26285194,"title":"故事新编","type":"book","pic_url":"http://107.191.52.220:4869/b2ac2b1046b0360c7039153f86d5d7fc"},{"score":21.83226,"ISBN":"9787532523337","author":"鲁迅","price":12.9,"douban_score":9.1,"id":1203363,"title":"中国小说史略","type":"book","pic_url":"http://107.191.52.220:4869/3d18ab296304771395b79639ae241a76"},{"score":21.83226,"ISBN":null,"author":"鲁迅","price":0.37,"douban_score":8.9,"id":1449348,"title":"彷徨","type":"book","pic_url":"http://107.191.52.220:4869/ac3962e19e2220817fc396f64a226d5c"},{"score":4.784784,"ISBN":"9787536340497","author":"[英]丹尼尔·笛福","price":9.2,"douban_score":8.0,"id":1016003,"title":"鲁滨逊漂流记","type":"book","pic_url":"http://107.191.52.220:4869/19104e0290051949d89d81cd5806d85d"},{"score":3.2610443,"ISBN":"9787111184829","author":"[美]史蒂夫·克鲁克","price":39.0,"douban_score":8.5,"id":1827702,"title":"点石成金","type":"book","pic_url":"http://107.191.52.220:4869/3ed4b4d4338ac695649aca24b2289373"},{"country":"美国","score":3.083653,"name":"史蒂夫·克鲁克","id":1269736,"pic_url":null,"type":"author"},{"score":3.0683625,"ISBN":"9787111165170","author":"[美]彼得·德鲁克","price":28.0,"douban_score":8.7,"id":1322025,"title":"卓有成效的管理者","type":"book","pic_url":"http://107.191.52.220:4869/b0d9f4f9e321f3456d20fed5ca576bd1"},{"country":"智利","score":2.9014776,"name":"巴勃罗·聂鲁达 Pablo Neruda","id":1383647,"pic_url":"http://107.191.52.220:4869/99f86e61333df9f1bf7292d98cc3e6bf","type":"author"},{"country":null,"score":2.9014776,"name":"彼得·德鲁克 Peter F. Drucker","id":4563653,"pic_url":"http://107.191.52.220:4869/83eef538c99e9288a732914af3b8bb4e","type":"author"},{"score":2.8565688,"ISBN":"9787500436966","author":"[智利]巴勃罗·聂鲁达","price":20.0,"douban_score":8.8,"id":1045352,"title":"二十首情诗与绝望的歌","type":"book","pic_url":"http://107.191.52.220:4869/61a40a4785da36189babd02a3d1da377"},{"score":2.8565688,"ISBN":"9787544270779","author":"[智利]巴勃罗·聂鲁达","price":39.5,"douban_score":8.3,"id":25858680,"title":"二十首情诗和一首绝望的歌","type":"book","pic_url":"http://107.191.52.220:4869/3d641c7b7ef0cf5619c2f8c151e09ceb"}]
        // this.searchSuggestions = mount_Result;
        // this.searchResult = mount_Result;
        // cb(mount_Result);

        // 以下为真实数据
        var _this = this;
        var search_type = this.getCurrentSearchMode().value;
        RestAPI.search(search_type, this.keyword, 40).then(res => {
          console.log(res);
          _this.searchSuggestions = res.data.data;
          if(res.data.status === 200)
          {
            var results = [];
            for(var i = 0; i < _this.searchSuggestions.length; i++)
            {
              var item = _this.searchSuggestions[i];
              var suggestion = {
                "item": item,
                "value": null
              }
              switch (search_type)
              {
                case "hybrid":
                  if(item.type === "book")
                    suggestion.value = item.title;
                  else suggestion.value = item.name;
                  break;
                case "books":
                  suggestion.value = "[" + item.author + "] " + item.title;
                  break;
                case "authors":
                  suggestion.value = "[" + item.country + "] " + item.name;
                  break;
                case "isbn":
                  // suggestion.value = "isbn: " + item.ISBN + " | " + item.title;
                  suggestion.value = item.ISBN + " | " + item.title;
                  break;
                default:
                  break;
              }
              results.push(suggestion);
            }
            results.splice(_this.searchSuggestionSize, results.length - _this.searchSuggestionSize);
            cb(results);
          }
        }).catch(err => {

        });
      },
      jumpToSearchResult() {
        var search_type = this.getCurrentSearchMode().value;
        this.$router.push({
          name: 'SearchResult',
          params:
            {
              keyword: this.keyword,
              search_type: search_type
            }
        });
      },
      getCurrentSearchMode() {
        for(var i = 0; i < this.searchModes.length; i++)
          if(this.searchModes[i].value === this.selectedSearchMode){
            return this.searchModes[i];
          }
      },
      changeSearchMode() {
        this.searchPlaceholder = getCurrentSearchMode().placeholder;
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .Result{
  }
  .box-card-component {
    float: left;
    width: 200px;
    margin: 20px 10px 20px 10px;
    max-height: 500px;
  }
</style>
<style rel="stylesheet/scss" lang="scss">
  .box-card-component {
    .el-card__header {
      padding: 0px !important;
    }
  }
</style>
<style rel="stylesheet/scss" lang="scss" scoped>
  .box-card-component {
    .box-card-header {
      position: relative;
      height: 250px;
      img {
        width: 100%;
        height: 100%;
        transition: all 0.2s linear;
        &:hover {
          transform: scale(1.1, 1.1);
          filter: contrast(130%);
        }
      }
    }
  }
</style>

