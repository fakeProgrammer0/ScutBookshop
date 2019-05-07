<template>
  <div class="app-container">
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
            >
              <template slot-scope="{ item }">
                <img :src="item.item.pic_url" style="width: 5px"/>
                <span>{{item.value}}</span>
              </template>

            </el-autocomplete>
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

        <!--<div align="center">-->
          <!--<el-card class="result-card" v-for="result in searchResults">-->
            <!--<div slot="header" class="result-header">-->
              <!--<span></span>-->
            <!--</div>-->
            <!--<div>-->
              <!--<span></span>-->
            <!--</div>-->
          <!--</el-card>-->
        <!--</div>-->

        <el-card v-for="item in searchResult" class="box-card-component">
          <div slot="header" class="box-card-header">
            <router-link v-if="item.type === 'book' " :to="'/bookshop/desc/'+item.id">
            <img :src="item.pic_url"></router-link>
          </div>
          <div style="position: relative;line-height: 16pt">
            <b>{{ item.title }}</b>
          </div>
        </el-card>

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

        ],
        searchSuggestionSize: 10
      }
    },
    created() {

    },
    methods: {
      searchEntityAsync(keyword, cb){

        var mount_Result = [{"score":"NaN","ISBN":"9787510809941","author":"钱穆","price":138.0,"douban_score":9.6,"id":7000811,"title":"国史大纲（上下新校本）","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s8486394.jpg"},{"score":"NaN","ISBN":"9787807463726","author":"[英]贡布里希","price":280.0,"douban_score":9.6,"id":3162991,"title":"艺术的故事","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s3219163.jpg"},{"score":"NaN","ISBN":"9787212003760","author":"老子","price":4.3,"douban_score":9.5,"id":1009789,"title":"道德经","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s2371875.jpg"},{"score":"NaN","ISBN":"9787208113039","author":"[英]J·R·R·托尔金","price":169.0,"douban_score":9.5,"id":21979017,"title":"魔戒","type":"book","pic_url":"https://img1.doubanio.com/view/subject/l/public/s27063429.jpg"},{"score":"NaN","ISBN":"9787020103355","author":"[英]J·K·罗琳","price":66.0,"douban_score":9.5,"id":26126545,"title":"哈利·波特与死亡圣器（纪念版）","type":"book","pic_url":"https://img1.doubanio.com/view/subject/l/public/s27614427.jpg"},{"score":"NaN","ISBN":"9787108027467","author":"钱钟书","price":172.0,"douban_score":9.5,"id":2988964,"title":"管錐編（全四冊）","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s2894815.jpg"},{"score":"NaN","ISBN":"9787544278591","author":"[俄]安德烈·塔可夫斯基","price":45.0,"douban_score":9.5,"id":26435510,"title":"雕刻时光","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s28622011.jpg"},{"score":"NaN","ISBN":"9787200065039","author":"孔丘","price":19.9,"douban_score":9.4,"id":1883245,"title":"诗经","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s1979223.jpg"},{"score":"NaN","ISBN":"9787301150894","author":"[美]N·格里高利·曼昆","price":54.0,"douban_score":9.4,"id":3719533,"title":"经济学原理","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s3802186.jpg"},{"score":"NaN","ISBN":"9787100013239","author":"[美]侯世达","price":88.0,"douban_score":9.4,"id":1291204,"title":"哥德尔、艾舍尔、巴赫","type":"book","pic_url":"https://img1.doubanio.com/view/subject/l/public/s1789059.jpg"},{"score":"NaN","ISBN":"9787108027498","author":"钱钟书","price":52.0,"douban_score":9.4,"id":2988970,"title":"谈艺录","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s3083924.jpg"},{"score":"NaN","ISBN":"9787805200132","author":"罗贯中","price":13.0,"douban_score":9.4,"id":1483894,"title":"三国演义","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s1661101.jpg"},{"score":"NaN","ISBN":"9787508637242","author":"[美]张纯如","price":49.0,"douban_score":9.4,"id":20440053,"title":"南京大屠杀","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s24511860.jpg"},{"score":"NaN","ISBN":"9787312018381","author":"陈希孺","price":38.0,"douban_score":9.4,"id":2201479,"title":"概率论与数理统计","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s8899433.jpg"},{"score":"NaN","ISBN":"9789622019874","author":"[美]徐中約","price":148.0,"douban_score":9.4,"id":1476213,"title":"中國近代史（上冊）","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s1493864.jpg"},{"score":"NaN","ISBN":"9787532766413","author":"[捷克]米兰·昆德拉","price":33.0,"douban_score":9.4,"id":25900366,"title":"小说的艺术","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s27471974.jpg"},{"score":"NaN","ISBN":"9787563390939","author":"李乾朗","price":118.0,"douban_score":9.3,"id":4082434,"title":"穿墙透壁","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s4030091.jpg"},{"score":"NaN","ISBN":"9787530455234","author":"[美]阿诺德·施瓦辛格","price":98.0,"douban_score":9.3,"id":7067916,"title":"施瓦辛格健身全书","type":"book","pic_url":"https://img1.doubanio.com/view/subject/l/public/s7022558.jpg"},{"score":"NaN","ISBN":"9787100017664","author":"錢穆","price":76.0,"douban_score":9.3,"id":1046492,"title":"國史大綱（上下）","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s23922774.jpg"},{"score":"NaN","ISBN":"9787536693968","author":"刘慈欣","price":32.0,"douban_score":9.3,"id":3066477,"title":"三体Ⅱ","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s3078482.jpg"},{"score":"NaN","ISBN":"9787201094601","author":"[美]罗伯特·麦基","price":49.0,"douban_score":9.3,"id":26717137,"title":"故事","type":"book","pic_url":"https://img1.doubanio.com/view/subject/l/public/s28446099.jpg"},{"score":"NaN","ISBN":"9787510812675","author":"钱穆","price":16.0,"douban_score":9.3,"id":10434849,"title":"中国历代政治得失","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s11164840.jpg"},{"score":"NaN","ISBN":"9787806570920","author":"[美]玛格丽特·米切尔","price":40.0,"douban_score":9.3,"id":1068920,"title":"飘","type":"book","pic_url":"https://img1.doubanio.com/view/subject/l/public/s1078958.jpg"},{"score":"NaN","ISBN":"9787101007473","author":"钱钟书","price":88.0,"douban_score":9.3,"id":1041366,"title":"管锥编（全五册）","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s1057173.jpg"},{"score":"NaN","ISBN":"9787020103324","author":"[英]J·K·罗琳","price":56.0,"douban_score":9.3,"id":26126139,"title":"哈利·波特与火焰杯（纪念版）","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s27614035.jpg"},{"score":"NaN","ISBN":"9787802254817","author":"兰晓龙","price":35.0,"douban_score":9.3,"id":3297466,"title":"我的团长我的团（上部）","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s3510333.jpg"},{"score":"NaN","ISBN":"9787506415408","author":"克里斯·杰弗莉","price":198.0,"douban_score":9.3,"id":1018426,"title":"服装缝制图解大全","type":"book","pic_url":"https://img1.doubanio.com/view/subject/l/public/s4585347.jpg"},{"score":"NaN","ISBN":"9787511725813","author":"[奥地利]斯蒂芬·茨威格","price":188.0,"douban_score":9.3,"id":26352569,"title":"茨威格文集","type":"book","pic_url":"https://img1.doubanio.com/view/subject/l/public/s29315757.jpg"},{"score":"NaN","ISBN":"9787121022982","author":"[美]史蒂夫·迈克康奈尔","price":128.0,"douban_score":9.3,"id":1477390,"title":"代码大全（第2版）","type":"book","pic_url":"https://img1.doubanio.com/view/subject/l/public/s1495029.jpg"},{"score":"NaN","ISBN":"9787530210291","author":"[英]乔治·奥威尔","price":28.0,"douban_score":9.3,"id":4820710,"title":"1984","type":"book","pic_url":"https://img1.doubanio.com/view/subject/l/public/s4371408.jpg"},{"score":"NaN","ISBN":"9787536671256","author":"[美]乔治·R·R·马丁","price":68.0,"douban_score":9.3,"id":1336330,"title":"冰与火之歌（卷一）","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s1358984.jpg"},{"score":"NaN","ISBN":"9787530211113","author":"三毛","price":24.0,"douban_score":9.3,"id":6710437,"title":"撒哈拉的故事","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s6780941.jpg"},{"score":"NaN","ISBN":"9787538276374","author":"曹天元","price":32.0,"douban_score":9.2,"id":1467022,"title":"上帝掷骰子吗","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s1486674.jpg"},{"score":"NaN","ISBN":"9787208062153","author":"费孝通","price":38.0,"douban_score":9.2,"id":1795079,"title":"乡土中国","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s2161063.jpg"},{"score":"NaN","ISBN":"9787532528684","author":"张岱","price":25.0,"douban_score":9.2,"id":1053088,"title":"陶庵梦忆 西湖梦寻","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s4039793.jpg"},{"score":"NaN","ISBN":"9789859438851","author":"钱钟书","price":19.0,"douban_score":9.2,"id":1855364,"title":"围城","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s6941453.jpg"},{"score":"NaN","ISBN":"9787508353937","author":"[美]弗里曼","price":98.0,"douban_score":9.2,"id":2243615,"title":"Head First 设计模式（中文版）","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s2686916.jpg"},{"score":"NaN","ISBN":"9787544253994","author":"[哥伦比亚]加西亚·马尔克斯","price":39.5,"douban_score":9.2,"id":6082808,"title":"百年孤独","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s6384944.jpg"},{"score":"NaN","ISBN":"9787201089560","author":"鲁迅","price":25.0,"douban_score":9.2,"id":26285194,"title":"故事新编","type":"book","pic_url":"https://img1.doubanio.com/view/subject/l/public/s27967429.jpg"},{"score":"NaN","ISBN":"9787806398791","author":"三毛","price":15.8,"douban_score":9.2,"id":1060068,"title":"撒哈拉的故事","type":"book","pic_url":"https://img3.doubanio.com/view/subject/l/public/s1066570.jpg"}];
        this.searchSuggestions = mount_Result;
        this.searchResult = mount_Result;
        cb(mount_Result);

        // 以下为真实数据
        var _this = this;
        RestAPI.search(this.keyword, 40).then(res => {
          console.log(res);
          _this.searchSuggestions = res.data.data;
          _this.searchResult = _this.searchSuggestions;
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
            // _this.searchResult = results;
            results.splice(_this.searchSuggestionSize, results.length - _this.searchSuggestionSize);
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

<style rel="stylesheet/scss" lang="scss" scoped>
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

