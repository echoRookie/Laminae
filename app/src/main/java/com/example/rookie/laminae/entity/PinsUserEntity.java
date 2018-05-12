package com.example.rookie.laminae.entity;

import com.example.rookie.laminae.login.UserBean;

/**
 * Created by rookie on 2018/5/10.
 */
public class PinsUserEntity {
    private int user_id;
    private String username;
    private String urlname;
    private int created_at;
    private UserBean.Avatar avatar;

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUrlname(String urlname) {
        this.urlname = urlname;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public void setAvatar(UserBean.Avatar avatar) {
        this.avatar = avatar;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getUrlname() {
        return urlname;
    }

    public int getCreated_at() {
        return created_at;
    }

    public UserBean.Avatar getAvatar() {
        return avatar;
    }

}
