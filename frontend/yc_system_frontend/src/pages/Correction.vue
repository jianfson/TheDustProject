<template>
  <div class="correction-container">
      <correction-map></correction-map>
  </div>
</template>

<script>
// 此组件没有用，待删除
// import correctionDate from '../components/charts/correctionDate'
import correctionMap from '../components/correctionMap'
export default {
  components: {
    // correctionDate,
    correctionMap
  },
  data() {
    return {
    }
  }
}
</script>

<style>
.correction-container {
  width: 100%;
  height: 100%;
  /* display: flex; */
  background-color: rgba(243, 243, 243, 0.712);
}
.correction-left-container {
  padding: 10px;
  background-color: #fff;
  height: calc(100% - 10px);
  flex-grow: 1;
  margin-right: 20px;
}
.correction-right-container {
  flex-grow: 1;
  padding: 10px;
  height: calc(100% - 10px);
  min-width: 500px;
  background-color: #fff;
}

/* 带横线标题 */
.title {
  width: 100%;
  position: relative;
  height: 30px;
  margin-bottom: 10px;
}
.subtitle {
  text-align: middle;
  height: 30px;
  line-height: 30px;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  /* background-color: #545c64; */
}
.subtitle::before {
  position: absolute;
  top: 50%;
  content: "";
  right: 100%;
  width: 100px;
  height: 1px;
  background-color: #ddd;
  margin-right: 10px;
}
.subtitle::after {
  position: absolute;
  top: 50%;
  content: "";
  left: 100%;
  width: 100px;
  height: 1px;
  background-color: #ddd;
  margin-left: 10px;
}

</style>
