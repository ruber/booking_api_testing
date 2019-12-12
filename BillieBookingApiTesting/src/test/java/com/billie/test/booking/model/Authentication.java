package com.billie.test.booking.model;

public class Authentication {
    private String user;
    private String password;

    public Authentication(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
