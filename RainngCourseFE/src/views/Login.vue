<template>
  <div class="login-wrap">
    <div class="login-form">
      <div class="form-title">dating app</div>
      <div class="form-subtitle">Login to your account</div>
      <el-form
        :model="formData"
        :rules="rules"
        class="form-content"
        label-width="0px"
        ref="form"
      >
        <div class="input_form">
          <el-form-item prop="username">
            <el-input
              class="input_form"
              placeholder="username"
              v-model="formData.username"
            >
            </el-input>
          </el-form-item>
        </div>

        <el-form-item prop="password">
          <el-input
            @keyup.enter.native="submit()"
            placeholder="password"
            type="password"
            v-model="formData.password"
            class="input_form"
          >
          </el-input>
        </el-form-item>

        <div class="login-btn">
          <el-button @click="submit_login()" type="primary">confirm</el-button>
          <el-button @click="signup_visible_state = true" type="primary"
            >signup</el-button
          >
        </div>
      </el-form>
    </div>

    <el-dialog
      title="verification"
      :visible.sync="verification_visible_state"
      width="400px"
      center
    >
      <el-form :model="teleform" ref="loginForm" label-width="80px">
        <div class="input_form">
          mobile_number:
          <el-form-item prop="mobile_number">
            <el-input
              class="input_form"
              readonly="true"
              placeholder="mobile_number"
              v-model="teleform.mobile_number"
            >
            </el-input>
          </el-form-item>
        </div>

        <el-form-item prop="code">
          <el-input
            @keyup.enter.native="verification()"
            placeholder="code"
            type="password"
            v-model="teleform.code"
            class="input_form"
          >
          </el-input>
        </el-form-item>
      </el-form>
      <!-- 取消，确定按钮点击事件 -->
      <span slot="footer">
        <el-button size="mini" @click="verification_visible_state = false"
          >cancel</el-button
        >
        <el-button size="mini" @click="verification()">login</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="signup"
      :visible.sync="signup_visible_state"
      width="6y00px"
      center
    >
      <el-form :model="signup_form" ref="registerForm" label-width="80px">
        <el-form-item prop="username">
          <el-input
            class="input_form"
            placeholder="username"
            v-model="signup_form.username"
          >
          </el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input
            class="input_form"
            placeholder="password"
            v-model="signup_form.password"
          >
          </el-input>
        </el-form-item>

        <el-form-item prop="mobile_number">
          <el-input
            class="input_form"
            placeholder="mobile_number"
            v-model="signup_form.mobile_number"
          >
          </el-input>
        </el-form-item>

        <el-form-item prop="email">
          <el-input
            class="input_form"
            placeholder="email"
            v-model="signup_form.email"
          >
          </el-input>
        </el-form-item>
      </el-form>
      <!-- 取消，确定按钮点击事件 -->
      <span slot="footer">
        <el-button size="mini" @click="signup_visible_state = false"
          >cancel</el-button
        >
        <el-button size="mini" @click="submit_signup()">confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { login, match, signup } from "@/api/user";
import Config from "@/common/config";

export default {
  data: function() {
    return {
      verification_visible_state: false,
      signup_visible_state: false,
      formData: {
        username: "",
        password: ""
      },
      teleform: {
        mobile_number: "",
        code: ""
      },
      signup_form: {
        username: "",
        password: "",
        mobile_number: "",
        email: ""
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" }
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }]
      }
    };
  },
  methods: {
    submit_login() {
      this.$refs.form.validate(valid => {
        if (valid) {
          match(this.formData).then(
            res => {
              if (Config.verify === true) {
                this.verification_visible_state = true;
                this.teleform.mobile_number = res.data["data"].mobile_number;
              } else {
                this.$router.push({ name: "container" });
              }
            },
            err => {
              this.$message.error(err.response.msg);
            }
          );
        }
      });
    },
    verification() {
      login(this.teleform.code).then(
        res => {
          this.$message.success("welcome:   " + res.data["data"].username);
          this.$router.push({ name: "container" });
        },
        err => {
          this.$message.error(err.response.msg);
        }
      );
    },
    submit_signup() {
      signup(this.signup_form).then(() => {
        this.signup_visible_state = false;
      });
    }
  }
};
</script>

<style scoped>
.login-wrap {
  position: relative;
  width: 100%;
  height: 100%;
  background-image: url("../assets/login-background.jpg");
  background-size: 100% 100%;
}

.form-title {
  position: relative;
  top: 60px;
  left: 10%;
  height: 90px;
  width: 80%;
  line-height: 80px;
  text-align: center;
  font-size: 40px;
  color: #fff;
  border-bottom: 1px solid #ddd;
}

.form-subtitle {
  position: relative;
  top: 50px;
  left: 10%;
  height: 60px;
  width: 80%;
  line-height: 100px;
  text-align: center;
  font-size: 30px;
  color: #fff;
}

.login-form {
  position: absolute;
  left: 45%;
  top: 240px;
  height: 550px;
  width: 500px;
  margin: -190px 0 0 -175px;
  border-radius: 5px;
  background: rgba(0, 0, 0, 0.6);
  overflow: hidden;
}

.form-content {
  padding: 45px 45px;
}

::v-deep .input_form .el-input__inner {
  position: relative;
  top: 35px;
  margin-top: 5px;
  height: 40px;
  border-radius: 30px;
}

.login-btn {
  position: relative;
  top: 50px;
  text-align: center;
}

.login-btn button {
  border-radius: 30px;
  background: deeppink;
  width: 100%;
  height: 50px;
}

.el-radio {
  color: #fff;
}
</style>
