package com.soft.audiomaster;

import java.io.Serializable;

public class Users implements Serializable {
    private String username;
    private String email;
    private String address;
    private String phone;
    private String password;

    public Users() {
    }

    public Users(String username, String email, String address, String phone, String password) {
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
