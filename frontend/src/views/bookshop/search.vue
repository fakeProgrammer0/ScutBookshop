<template>
  <div>
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
        <el-row :gutter="0" style="margin-top: 10px">
          <el-col :span="7" :offset="5">
            <el-autocomplete
              v-model="keyword"
              :fetch-suggestions="searchEntityAsync"
              placeholder="请输入图书名称、作者或ISBN"
              @select="handleSelect"
              style="width:400px;margin-right: 10px"
              clearable
            ></el-autocomplete>
          </el-col>
          <!--<el-col :span="1" style="margin-left: 40px;margin-right: 5px">-->
            <!--<el-button type="primary" @click="getEntityInfo" icon="el-icon-info"></el-button>-->
          <!--</el-col>-->

          <el-col :span="1" style="margin-left: 10px;margin-right: 5px">
            <el-button type="primary" @click="showSearchResults" icon="el-icon-search"></el-button>
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
      <el-main>
        <div align="center">
          <el-card class="result-card" v-for="result in searchResults">
            <div slot="header" class="result-header">
              <span></span>
            </div>
            <div>
              <span></span>
            </div>
          </el-card>
        </div>
      </el-main>
    </el-container>

    <!--<el-dialog title="实体信息" :visible.sync="selectedEntityDialogVisible" width="35%">-->
      <!--<el-form ref="selectedEntity" :model="selectedEntity" label-width="80px" style="margin-bottom: 5px">-->
        <!--<el-form-item label="uuid">-->
          <!--<div align="left">-->
            <!--<span>{{selectedEntity.uuid}}</span>-->
          <!--</div>-->
        <!--</el-form-item>-->
        <!--<el-form-item label="实体类型">-->
          <!--<div align="left">-->
            <!--<span>{{selectedEntity.type}}</span>-->
          <!--</div>-->
        <!--</el-form-item>-->
        <!--<el-form-item label="实体名称">-->
          <!--<div align="left">-->
            <!--<span>{{selectedEntity.name}}</span>-->
          <!--</div>-->
        <!--</el-form-item>-->
        <!--<el-form-item label="实体全称">-->
          <!--<div align="left">-->
            <!--<span>{{selectedEntity.FQDN}}</span>-->
          <!--</div>-->
        <!--</el-form-item>-->
        <!--<el-form-item label="实体描述">-->
          <!--<div align="left">-->
            <!--<span>{{selectedEntity.desc}}</span>-->
          <!--</div>-->
        <!--</el-form-item>-->
      <!--</el-form>-->
      <!--<span slot="footer">-->
      <!--<el-button type="success" @click="checkEntityInfo">确定</el-button>-->
    <!--</span>-->
    <!--</el-dialog>-->
  </div>
</template>

<script>
  import * as RestAPI from "@/api/RestAPI";

  export default {
    name: "Search",
    data() {
      return {
        keyword: "",
        searchResult: [

        ],
        searchSuggestions: [

        ]
      }
    },
    created() {

    },
    methods: {
      searchEntityAsync(keyword, cb){
        var _this = this;
        RestAPI.search(this.keyword, 10).then(res => {
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
              if(item.type === "book")
                suggestion.value = item.title;
              else suggestion.value = item.name;
              results.push(suggestion);
            }
            cb(results);
          }
        }).catch(err => {

        });
      },
      handleSelect() {

      },
      showSearchResults() {

      }
    }
  }
</script>
