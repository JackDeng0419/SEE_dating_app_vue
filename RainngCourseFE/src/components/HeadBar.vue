<template>
  <div class="head-bar">
    <div class="header-ico">
      <i class="el-icon-s-home"></i>
    </div>
    <div class="logo">dating app</div>
    <div class="head-right">
      <div class="head-user-con">
        <div class="btn-fullscreen">
          <el-tooltip
            :content="fullscreen ? `退出全屏` : `全屏`"
            effect="dark"
            placement="bottom"
          >
            <i class="el-icon-rank"></i>
          </el-tooltip>
        </div>

        <div class="user-avatar">
          <img src="../assets/avatars.jpg" />
        </div>

        <el-dropdown @command="handleCommand" class="user-name" trigger="click">
          <span class="el-dropdown-link">
            <i class="el-icon-caret-bottom"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="logout">log out</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "Header",
  data() {
    return {
      fullscreen: false
    };
  }
};
</script>
<style scoped>
.head-bar {
  position: absolute;
  box-sizing: border-box;
  left: 10%;
  width: 80%;
  height: 70px;
  font-size: 22px;
  color: #fff;
  background-color: #000000;
  border-radius: 0 0 10px 10px;
}

.header-ico {
  float: left;
  padding: 0 21px;
  line-height: 70px;
}

.head-bar .logo {
  float: left;
  width: 250px;
  line-height: 70px;
}

.head-right {
  float: right;
  padding-right: 50px;
}

.head-user-con {
  display: flex;
  height: 70px;
  align-items: center;
}

.btn-fullscreen {
  transform: rotate(45deg);
  margin-right: 5px;
  font-size: 24px;
}

.btn-fullscreen {
  position: relative;
  width: 30px;
  height: 30px;
  text-align: center;
  border-radius: 15px;
  cursor: pointer;
}

.btn-bell .el-icon-bell {
  color: #fff;
}

.user-name {
  margin-left: 10px;
}

.user-avatar {
  margin-left: 20px;
}

.user-avatar img {
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}

.el-dropdown-link {
  color: #fff;
  cursor: pointer;
}

.el-dropdown-menu__item {
  text-align: center;
}
</style>
