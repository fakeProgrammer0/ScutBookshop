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

        <!--<el-row :gutter="0" style="margin-top: 10px">-->
          <!--<el-col :span="7" :offset="5">-->
            <!--<el-autocomplete-->
              <!--v-model="keyword"-->
              <!--:fetch-suggestions="searchEntityAsync"-->
              <!--placeholder="请输入图书名称、作者或ISBN"-->
              <!--@select="handleSelect"-->
              <!--style="width:400px;margin-right: 10px"-->
              <!--clearable-->
            <!--&gt;-->
              <!--<template slot-scope="{ item }">-->
<!--&lt;!&ndash;                <img :src="item.item.pic_url" style="width: 5px"/>&ndash;&gt;-->
                <!--<span v-if="item.item.type === 'author'">[作者] </span>-->
                <!--<span v-if="item.item.type === 'book'">[图书] </span>-->
                <!--<span>{{item.value}}</span>-->
              <!--</template>-->

            <!--</el-autocomplete>-->
          <!--</el-col>-->

          <!--<el-col :span="1" style="margin-left: 40px;margin-right: 5px">-->
            <!--<el-button type="primary" @click="getEntityInfo" icon="el-icon-info"></el-button>-->
          <!--</el-col>-->

          <!--<el-col :span="1" style="margin-left: 10px;margin-right: 5px">-->
            <!--<el-button type="primary" @click="showSearchResults" icon="el-icon-search"></el-button>-->
          <!--</el-col>-->

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
        <!--</el-row>-->
      </el-header>
      <el-main>

      <div class="Result" v-for="item in searchResult" >
        <el-card  class="box-card-component" v-if="item.type === 'book'">
          <div slot="header" class="box-card-header"  >
            <router-link  :to="'/bookshop/desc/'+item.id">
            <img :src="item.pic_url">
            </router-link>
          </div>
          <div style="position: relative;line-height: 16pt;text-align: center">
            【{{ item.title }}】<br>
            {{ item.author }}<br>
           {{ item.price.toFixed(2) }}<br>
            {{ item.ISBN }}
          </div>
        </el-card>

        <el-card  class="box-card-component" v-if="item.type === 'author'">
          <div slot="header" class="box-card-header"  >
            <router-link  :to="'/bookshop/desc/'+item.id">
              <img :src="item.pic_url">
            </router-link>
          </div>
          <div style="position: relative;line-height: 16pt;text-align: center">
            【{{ item.country }}】<br>
            {{ item.name }}<br>
          </div>
        </el-card>
      </div>
      </el-main>
    </el-container>

  </div>
</template>

<script>
  import * as RestAPI from "@/api/RestAPI";

  export default {
    name: "SearchResult",
    props: {
      keyword: {
        type: String,
        default: ""
      },
      search_type: {
        type: String,
        default: "hybrid"
      }
    },
    data() {
      return {
        searchResult: [],
      }
    },
    created() {
      this.showSearchResults();
    },
    methods: {
      showSearchResults() {
        var _this = this;
        RestAPI.search(this.search_type, this.keyword, 40).then(res => {
          console.log(res);
          _this.searchResult = res.data.data;
        }).catch(err => {
          console.log(err);
        });
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

