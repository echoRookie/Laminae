package com.example.rookie.laminae.db;

import org.litepal.crud.DataSupport;

/**
 * Created by rookie on 2018/5/17.
 */

public class SearchHistory extends DataSupport {
    private int id;
    private String code;//搜索记录

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
