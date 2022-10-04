package com.course.dating.entity.profile;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@TableName("user_info")
@Data
public class BIEntity {

    public BIEntity() {
        this.firstName = null;
        this.lastName = null;
        this.age = 0;
        this.gender = null;
        this.preferredLanguage = null;
        this.nationality = null;
        this.birthday = Date.valueOf("1900-01-01");
        this.relationshipStatus = null;
        this.education = null;
        this.profession = null;
    }

    @TableId("id")
    private Integer id;

    @TableField("firstName")
    private String firstName;

    @TableField("lastName")
    private String lastName;

    @TableField("age")
    private Integer age;

    @TableField("gender")
    private String gender;

    @TableField("preferredLanguage")
    private String preferredLanguage;

    @TableField("nationality")
    private String nationality;

    @TableField("birthday")
    private Date birthday;

    @TableField("relationshipStatus")
    private String relationshipStatus;

    @TableField("education")
    private String education;

    @TableField("profession")
    private String profession;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

}
