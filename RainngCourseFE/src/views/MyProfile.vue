<template>
  <div class="profile_form">
    <div class="form-title">&nbsp;&nbsp;Basic Information</div>
    <el-form
        :model="basic_information"
        class="profile_form_content"
        label-width="0px"
        ref="form"
    >
      <div class="form_left">
        <el-form-item class="input_form" prop="first_name">
          <div class="input_title">First name</div>
          <el-input :readonly="profile_readonly" class="input_item" placeholder="null" v-model="basic_information.first_name">
          </el-input>
        </el-form-item>
        <el-form-item class="input_form" prop="gender">
          <div class="input_title">gender</div>
          <el-select :disabled="profile_readonly" class="input_item" v-model="basic_information.gender" placeholder="male">
            <el-option
                v-for="item in profile_gender"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item class="input_form" prop="age">
          <div class="input_title">age</div>
          <el-input :readonly="profile_readonly" class="input_item" placeholder="null" v-model="basic_information.age">
          </el-input>
        </el-form-item>
        <el-form-item class="input_form" prop="preferred language">
          <div class="input_title">preferred language</div>
          <el-input :readonly="profile_readonly" class="input_item" placeholder="null" v-model="basic_information.preferred_language">
          </el-input>
        </el-form-item>
        <el-form-item class="input_form" prop="education">
          <div class="input_title">education</div>
          <el-input :readonly="profile_readonly" class="input_item" placeholder="null" v-model="basic_information.education">
          </el-input>
        </el-form-item>
      </div>
      <div class="form_right">
        <el-form-item class="input_form" prop="last_name">
          <div class="input_title">Last name</div>
          <el-input :readonly="profile_readonly" class="input_item" placeholder="null" v-model="basic_information.last_name">
          </el-input>
        </el-form-item>
        <el-form-item class="input_form" prop="nationality">
          <div class="input_title">nationality</div>
          <el-input :readonly="profile_readonly" class="input_item" placeholder="null" v-model="basic_information.nationality">
          </el-input>
        </el-form-item>
        <el-form-item class="input_form" prop="birthday">
          <div class="input_title">birthday</div>
          <el-date-picker :readonly="profile_readonly" v-model="basic_information.birthday" value-format="yyyy-MM-dd" type="date" placeholder="1900-01-01">
          </el-date-picker>
        </el-form-item>
        <el-form-item class="input_form" prop="relationship status">
          <div class="input_title">relationship status</div>
          <el-input :readonly="profile_readonly" class="input_item" placeholder="null" v-model="basic_information.relationship_status">
          </el-input>
        </el-form-item>
        <el-form-item class="input_form" prop="profession">
          <div class="input_title">profession</div>
          <el-input :readonly="profile_readonly" class="input_item" placeholder="null" v-model="basic_information.profession">
          </el-input>
        </el-form-item>
      </div>
      <div class="edit-btn">
        <el-button @click="edit_profile()" type="primary">confirm</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
import {get_basic_information, update_basic_information} from "@/api/profile";

export default {
  name: "Home",
  data: function() {
    return {
      profile_readonly:true,
      profile_disabled:true,
      basic_information: {
        first_name: "",
        last_name: "",
        age:"",
        gender:"",
        preferred_language:"",
        nationality:"",
        birthday:"",
        relationship_status:"",
        profession:"",
        education:""
      },
      profile_gender:[{
        value: 'male',
        label: 'male'
      }, {
        value: 'female',
        label: 'female'
      }]
    };
  },
  created() {
    get_basic_information().then((res) => {
      this.basic_information.first_name = res.data["data"].first_name;
      this.basic_information.last_name = res.data["data"].last_name;
      this.basic_information.age = res.data["data"].age;
      this.basic_information.gender = res.data["data"].gender;
      this.basic_information.preferred_language = res.data["data"].preferred_language;
      this.basic_information.nationality = res.data["data"].nationality;
      this.basic_information.birthday = res.data["data"].birthday;
      this.basic_information.relationship_status = res.data["data"].relationship_status;
      this.basic_information.profession = res.data["data"].profession;
      this.basic_information.education = res.data["data"].education;
      this.profile_readonly = true;
      this.profile_disabled = true;
      },
        (err)=>{
          this.$message.error(err.response.msg);
        });

  },
  methods:{
    edit_profile() {
      if(this.profile_readonly === true){
        this.profile_readonly = false;
        this.profile_disabled = false;
      }
      else{
        update_basic_information(this.basic_information);
        this.profile_readonly = true;
        this.profile_disabled = true;
      }
    }
  }
};
</script>

<style scoped>
.profile_form{
  position:absolute;
  width:100%;
  background-color:black;
  border-radius:15px 15px 15px 15px;
  height:600px;
}
.form-title {
  position:absolute;
  top:0;
  left:0;
  height: 60px;
  width: 100%;
  line-height: 60px;
  font-size: 30px;
  color: #ffffff;
  border-bottom: 1px solid gray;
}
.form_left{
  position:absolute;
  width:40%;
  left:5%;
  top:80px;
}
.form_right{
  position:absolute;
  width:40%;
  left:55%;
  top:80px;
}

.edit-btn{
  position:absolute;
  top:10px;
  right:30px;
}

::v-deep .el-input__inner,::v-deep .el-input__inner[disabled]{
  border-top-width: 0;
  border-left-width: 0;
  border-right-width: 0;
  border-bottom-width: 1px;
  background-color:transparent;
}
</style>
