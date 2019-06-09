<template>
  <div class="fav" >
    <el-col :span="22" :offset="1" >
  <el-table
    ref="multipleTable"
    :data="tableData"
    tooltip-effect="dark"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
      label="图书信息">
                       <template scope="scope">
                           <img src="https://f11.baidu.com/it/u=3709696843,3226059805&fm=76" height="100" width="150"/>
                       </template>
    </el-table-column>
    <el-table-column
      prop="title"
      label="书名">
    </el-table-column>
    <el-table-column
      prop="douban_score"
      label="评分"
      width="120">
    </el-table-column>
    <el-table-column
      label="作者"
      prop="author"
      width="120">
    </el-table-column>
    <el-table-column
      prop="price"
      label="价格"
      width="120">
    </el-table-column>

  </el-table>
  <div style="margin-top: 20px">
<!--    按钮事件未完善-->
    <el-button @click="toggleSelection([tableData[1], tableData[2]])">购买</el-button>
    <el-button @click="toggleSelection()">取消选择</el-button>
    <el-button @click="toggleSelection()">取消收藏</el-button>
  </div>

    </el-col>
  </div>
</template>

<script>
  import store from '@/store'
  export default {
    name:'fav',
    data() {
      return {
        id:'',
        tableData: [{
          title: "追风筝的人",
          author:"[美]卡勒德·胡赛尼 Khaled Hosseini",
          price: 29,
          douban_score:8.9
        }, {
          title: "追风筝的人",
          author:"[美]卡勒德·胡赛尼 Khaled Hosseini",
          price: 29,
          douban_score:8.9
        }, {
          title: "追风筝的人",
          author:"[美]卡勒德·胡赛尼 Khaled Hosseini",
          price: 29,
          douban_score:8.9
        }],
        multipleSelection: []
      }
    },
    created(){
      if (store.getters.id != -1){
        this.id = store.getters.id
      }
    },
    methods: {
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      }
    }
  }
</script>

<style scoped>

</style>
