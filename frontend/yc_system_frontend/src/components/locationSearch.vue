<template>
  <div class="search-container">
    <div class="title">
      <i id="location" class="iconfront-yc icon-yc-location"></i>
      <span>所属区域</span>
    </div>
    <div class="search-input">
      <el-input type="text" placeholder="输入关键字" size="small">
        <el-button slot="append" prefix-icon="el-icon-search">搜索</el-button>
      </el-input>
    </div>
    <div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'locationSearch',
  data() {
    return {
    }
  }
}
</script>

<style>
.search-container {
  width: 300px;
  height: 100%;
  position: relative;
}
#location {
  font-size: 20px;
}
.title span {
  margin-left: 10px;
  font-size: 1.2em;
}
.search-input {
  margin-top: 20px;
  width: 230px;
}

</style>
