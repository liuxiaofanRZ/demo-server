package com.example.demo.qo;

public class UserListQo extends ListQo{

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserListQo{" +
                "username='" + username + '\'' +
                "pageNo=" + getPageNo() +
                ", pageSize=" + getPageSize() +
                '}';
    }
}
