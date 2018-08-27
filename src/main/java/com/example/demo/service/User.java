package com.example.demo.service;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String userName;
    private String passWord;

    public User() {
    }

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public User(String userName) {
        this.userName = userName;
    }
}

