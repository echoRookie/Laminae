package com.example.rookie.laminae.entity;

/**
 * Created by rookie on 2018/5/10.
 */
public class PinsFileEntity {
    private String farm;
    private String bucket;
    private String key;
    private String type;
    private int width;
    private int height;
    private int frames;

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setFrames(int frames) {
        this.frames = frames;
    }

    public String getFarm() {
        return farm;
    }

    public String getBucket() {
        return bucket;
    }

    public String getKey() {
        return key;
    }

    public String getType() {
        return type;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getFrames() {
        return frames;
    }
}
