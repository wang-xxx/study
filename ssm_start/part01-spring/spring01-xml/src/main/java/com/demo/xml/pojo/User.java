package com.demo.xml.pojo;

public class User {

    private String username;
    private String password;

    public User() {
        System.out.println("User无参构造函数");
    }

    public void init() {
        System.out.println("User的init方法");
    }

    public void destroy() {
        System.out.println("User的destroy方法");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        System.out.println("User的setUsername方法");
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
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
