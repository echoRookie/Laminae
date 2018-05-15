package com.example.rookie.laminae.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rookie on 2018/5/14.
 */

public class NewsDataDetial {
    public String article_url;
    public String comment_count;
    public long publish_time;
    public String media_name;
    @SerializedName("user_info")
    public user_info user_info;
    public String title;
    public class user_info{
        public String  avatar_url;
        public boolean follow;
        public int follower_count;
        public String name;
        public long user_id;
    }
}
