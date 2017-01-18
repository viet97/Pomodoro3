package com.example.quocvietdang.pomodoro1.settings;

/**
 * Created by Quoc Viet Dang on 1/14/2017.
 */

public class LoginCredentials {
    private String username;
    private String password;

    public LoginCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginCredendials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
