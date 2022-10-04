<template>
  <div>
    <h2 >eeeeeeeeeeeeeeeeeeeeeeeee</h2>
  </div>

</template>

<script>
// import * as api from "../api/news";

export default {
  name: "Test",
  data() {
    return {
      tableData: []
    };
  },
  // methods: {
  //   query() {
  //     api.get().then(res => {
  //       this.tableData = res;
  //     });
  //   },
  //   openNews(url) {
  //     window.open(url, "_blank");
  //   }
  // },
  created() {
    this.query();
  }
};
</script>
