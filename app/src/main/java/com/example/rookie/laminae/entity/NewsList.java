package com.example.rookie.laminae.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rookie on 2018/5/14.
 */

public class NewsList {
    public List<NewsData> data;
    public class NewsData{
        @SerializedName("content")
        public String content;
        public String code;
    }
}
