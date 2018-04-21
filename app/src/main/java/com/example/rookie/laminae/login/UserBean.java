package com.example.rookie.laminae.login;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rookie on 2018/4/14.
 */

public class UserBean implements Parcelable{
/*
    {
        　　"user_id":22282493,
        　　"username":"鱼鱼鱼三条鱼i",
        　　"urlname":"qi0vhxtpbq",
        　　"avatar":{
        　　　　"bucket":"hbimg",
        　　　　"farm":"farm1",
        　　　　"frames":1,
        　　　　"height":300,
        　　　　"id":102890808,
        　　　　"key":"654953460733026a7ef6e101404055627ad51784a95c-B6OFs4",
        　　　　"type":"image/jpeg",
        　　　　"width":300
        　　},
        　　"email":"1171349468@qq.com",
        　　"created_at":1507536736,
        　　"board_count":1,
        　　"like_count":1,
        　　"boards_like_count":0,
        　　"follower_count":0,
        　　"muse_board_count":0,
        　　"following_count":17,
        　　"explore_following_count":1,
        　　"pin_count":3,
        　　"profile":{
        　　　　"sex":0
        　　},
        　　"bindings":{

        　　},
        　　"tel":"13755040072",
        　　"tag_count":null,
        　　"user":{
        　　　　"user_id":22282493,
        　　　　"username":"鱼鱼鱼三条鱼i",
        　　　　"urlname":"qi0vhxtpbq",
        　　　　"avatar":{
            　　　　　　"bucket":"hbimg",
            　　　　　　"farm":"farm1",
            　　　　　　"frames":1,
            　　　　　　"height":300,
            　　　　　　"id":102890808,
            　　　　　　"key":"654953460733026a7ef6e101404055627ad51784a95c-B6OFs4",
            　　　　　　"type":"image/jpeg",
            　　　　　　"width":300
            　　　　},
        　　　　"email":"1171349468@qq.com",
        　　　　"created_at":1507536736,
        　　　　"board_count":1,
        　　　　"like_count":1,
        　　　　"boards_like_count":0,
        　　　　"follower_count":0,
        　　　　"muse_board_count":0,
        　　　　"following_count":17,
        　　　　"explore_following_count":1,
        　　　　"pin_count":3,
        　　　　"profile":{
            　　　　　　"sex":0
            　　　　},
        　　　　"bindings":{

            　　　　},
        　　　　"tel":"13755040072",
        　　　　"tag_count":null
        　　}
    }
*/


    /**
     *
     "user_id":22282493,
     "username":"鱼鱼鱼三条鱼i",
     "urlname":"qi0vhxtpbq",
     */
    private int user_id;
    private String username;
    private String urlname;
    /**
     * 用户头像信息
     * "avatar":{
     　　　　"bucket":"hbimg",
     　　　　"farm":"farm1",
     　　　　"frames":1,
     　　　　"height":300,
     　　　　"id":102890808,
     　　　　"key":"654953460733026a7ef6e101404055627ad51784a95c-B6OFs4",
     　　　　"type":"image/jpeg",
     　　　　"width":300
     　　},
     */
    @SerializedName("avatar")
    private Avatar icon_info;
    /**
     * "email":"1171349468@qq.com",
     　　"created_at":1507536736,
     　　"board_count":1,
     　　"like_count":1,
     　　"boards_like_count":0,
     　　"follower_count":0,
     　　"muse_board_count":0,
     　　"following_count":17,
     　　"explore_following_count":1,
     　　"pin_count":3,
     */
    private String email;
    /**
     * 创建时间
     */
    private int created_at;
    /**
     * 用户关注的画板
     */
    private int board_count;
    /**
     * 用户标记为喜欢的图片数量
     */
    private int like_count;
    private int boards_like_count;

    /**
     * 粉丝数量
     */
    private int follower_count;

    protected UserBean(Parcel in) {
        user_id = in.readInt();
        username = in.readString();
        urlname = in.readString();
        email = in.readString();
        created_at = in.readInt();
        board_count = in.readInt();
        like_count = in.readInt();
        boards_like_count = in.readInt();
        follower_count = in.readInt();
        muse_board_count = in.readInt();
        following_count = in.readInt();
        explore_following_count = in.readInt();
        pin_count = in.readInt();
        commodity_count = in.readInt();
        creations_count = in.readInt();
        following = in.readInt();
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel in) {
            return new UserBean(in);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };

    public Avatar getIcon_info() {
        return icon_info;
    }

    public void setIcon_info(Avatar icon_info) {
        this.icon_info = icon_info;
    }

    public int getMuse_board_count() {
        return muse_board_count;
    }

    public void setMuse_board_count(int muse_board_count) {
        this.muse_board_count = muse_board_count;
    }

    public int getExplore_following_count() {
        return explore_following_count;
    }

    public void setExplore_following_count(int explore_following_count) {
        this.explore_following_count = explore_following_count;
    }

    private int muse_board_count;
    /**
     * 关注的话题
     */
    private int following_count;
    private int explore_following_count;
    private int pin_count;
    private int commodity_count;
    private int creations_count;


    //该用户是否已经关注 关注为1 否则没有对应的网络字段 int默认值为0
    private int following;

    /**
     * 用户个人信息
     * location : 金华
     * sex : 1
     * birthday : 1992-6-10
     * job : 工程师
     * url :
     * about : Android开发工程师
     */

    private ProfileBean profile;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrlname() {
        return urlname;
    }

    public void setUrlname(String urlname) {
        this.urlname = urlname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getBoards_like_count() {
        return boards_like_count;
    }

    public void setBoards_like_count(int boards_like_count) {
        this.boards_like_count = boards_like_count;
    }

    public int getFollowing_count() {
        return following_count;
    }

    public void setFollowing_count(int following_count) {
        this.following_count = following_count;
    }

    public int getCommodity_count() {
        return commodity_count;
    }

    public void setCommodity_count(int commodity_count) {
        this.commodity_count = commodity_count;
    }

    public int getBoard_count() {
        return board_count;
    }

    public void setBoard_count(int board_count) {
        this.board_count = board_count;
    }

    public int getFollower_count() {
        return follower_count;
    }

    public void setFollower_count(int follower_count) {
        this.follower_count = follower_count;
    }

    public int getCreations_count() {
        return creations_count;
    }

    public void setCreations_count(int creations_count) {
        this.creations_count = creations_count;
    }

    public int getPin_count() {
        return pin_count;
    }

    public void setPin_count(int pin_count) {
        this.pin_count = pin_count;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public ProfileBean getProfile() {
        return profile;
    }

    public void setProfile(ProfileBean profile) {
        this.profile = profile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(user_id);
        dest.writeString(username);
        dest.writeString(urlname);
        dest.writeString(email);
        dest.writeInt(created_at);
        dest.writeInt(board_count);
        dest.writeInt(like_count);
        dest.writeInt(boards_like_count);
        dest.writeInt(follower_count);
        dest.writeInt(muse_board_count);
        dest.writeInt(following_count);
        dest.writeInt(explore_following_count);
        dest.writeInt(pin_count);
        dest.writeInt(commodity_count);
        dest.writeInt(creations_count);
        dest.writeInt(following);
    }

    /**
     * 用户头像icon参数
     */
    public static class Avatar{
        private String bucket;
        private String farm;

        private String frames;
        private String height;
        private String id;
        private String key;
        private String type;
        private String width;

        public String getBucket() {
            return bucket;
        }

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }

        public String getFarm() {
            return farm;
        }

        public void setFarm(String farm) {
            this.farm = farm;
        }

        public String getFrames() {
            return frames;
        }

        public void setFrames(String frames) {
            this.frames = frames;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }
    }

    public static class ProfileBean {
        private String location;
        private String sex;
        private String birthday;
        private String job;
        private String url;
        private String about;

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }
    }
}
