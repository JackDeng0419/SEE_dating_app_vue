<template>
  <div class="sidebar">
    <el-menu
        :default-active="this.$router.path"
        active-text-color="#20a0ff"
        background-color="transparent"
        class="sidebar-el-menu"
        router
        text-color="#ffffff"
    >
      <el-menu-item class="item" index="/home" key="/home">
        <i class="el-icon-fa fa-bar-chart"></i>
        <span slot="title">My Profile</span>
      </el-menu-item>
      <el-menu-item class="item" index="/test" key="/test">
        <i class="el-icon-fa fa-bar-chart"></i>
        <span slot="title">test</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "SideBar"
};
</script>

<style scoped>
.sidebar {
  display: block;
  position: absolute;
  left: 10%;
  top: 350px;
  bottom: 0;
}

.sidebar-el-menu:not(.el-menu--collapse) {
  width: 250px;
}

.sidebar > ul {
  height: 100%;
}

.sidebar-el-menu{
  border: 0 !important;
}

</style>
