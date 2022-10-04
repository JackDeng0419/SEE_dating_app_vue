<template>
  <div class="home">
    <head-bar />
    <side-bar />

    <div class="content-box">
      <router-view />
      <el-backtop target=".content"></el-backtop>
    </div>
  </div>
</template>

<script>
import  {getLoginStatus } from "@/api/user";
import SideBar from "@/components/SideBar";
import HeadBar from "@/components/HeadBar";

export default {
  name: "Container",
  components: {
    HeadBar,
    SideBar
  },
  created() {
    getLoginStatus().then((  ) => {
      if (this.$route.path === "/") {
        this.$router.push({name: "home"});
      }}, (  ) => {
      this.$router.push({name: "login"});
    });
  }
};
</script>
<style scoped>
.content-box {
  position: absolute;
  left: 30%;
  right: 10%;
  width: 60%;
  top: 85px;
  bottom: 0;
  background: transparent;
  color: white;
}
</style>
