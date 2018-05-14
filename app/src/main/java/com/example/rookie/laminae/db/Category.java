package com.example.rookie.laminae.db;

import com.example.rookie.laminae.util.SPUtils;

import org.litepal.crud.DataSupport;

/**
 * Created by rookie on 2018/5/13.
 */

public class Category extends DataSupport {
    private int id;
    private String categoryName;
    private String getCategoryType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getGetCategoryType() {
        return getCategoryType;
    }

    public void setGetCategoryType(String getCategoryType) {
        this.getCategoryType = getCategoryType;
    }
}
