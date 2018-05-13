package com.example.rookie.laminae.entity;

import java.util.List;

/**
 * Created by rookie on 2018/5/12.
 */
public class FollowPinsBean {
    private int followed_board;

    private List<PinsMainEntity> pins;

    public int getFollowed_board() {
        return followed_board;
    }

    public void setFollowed_board(int followed_board) {
        this.followed_board = followed_board;
    }


    public void setPins(List<PinsMainEntity> pins) {
        this.pins = pins;
    }

    public List<PinsMainEntity> getPins() {
        return pins;
    }
}
