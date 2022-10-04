package com.course.dating.entity.Session;

import java.security.PrivateKey;
import java.security.PublicKey;

public class SessionUserEntity {
    private Boolean loggedIn = false;

    private Integer userId;

    private String username;

    private String mobileNumber;

    private String email;

    private PublicKey publickey;

    private PrivateKey privatekey;


    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PublicKey getPublickey() {
        return publickey;
    }

    public void setPublickey(PublicKey publickey) {
        this.publickey = publickey;
    }

    public PrivateKey getPrivatekey() {
        return privatekey;
    }

    public void setPrivatekey(PrivateKey privatekey) {
        this.privatekey = privatekey;
    }

}
