package com.example.rookie.laminae.user.UserPins;

import com.example.rookie.laminae.login.UserBean;

import java.util.List;

/**
 * Created by rookie on 2018/4/22.
 * 用户的采集和喜欢两个功能返回的数据类型一致，所以共用这两个类进行解析
 * userPinsItem 为所有图片列表的返回基类
 *
 * {
 "pins": [{
 "pin_id": 1605480709,
 "user_id": 22282493,
 "board_id": 40485806,
 "file_id": 77240162,
 "file": {
 "id": 77240162,
 "farm": "farm1",
 "bucket": "hbimg",
 "key": "6d9ae4a3cb57fa1e4f72379931857a27ff17eaeb2294d-h4NNm0",
 "type": "image/jpeg",
 "width": 640,
 "height": 640,
 "frames": 1,
 "colors": [{
 "color": 16768460,
 "ratio": 0.09
 }],
 "theme": "ffddcc"
 },
 "media_type": 0,
 "source": null,
 "link": null,
 "raw_text": "《怎么能够》-【)6200张完整收录(】灵感之源 #图案# #花纹# #手绘# #线描# #密集# #马克笔# #针管笔# #临摹# #淡彩# #素材# (4509)",
 "text_meta": {
 "tags": []
 },
 "via": 1605479946,
 "via_user_id": 22282493,
 "original": 424130571,
 "created_at": 1523975464,
 "like_count": 0,
 "comment_count": 0,
 "repin_count": 0,
 "is_private": 0,
 "extra": null,
 "orig_source": null,
 "board": {
 "board_id": 40485806,
 "user_id": 22282493,
 "title": "哈哈",
 "description": "",
 "category_id": null,
 "seq": 1,
 "pin_count": 8,
 "follow_count": 0,
 "like_count": 0,
 "created_at": 1510418655,
 "updated_at": 1523975464,
 "deleting": 0,
 "is_private": 0,
 "extra": null
 },
 "user": {
 "user_id": 22282493,
 "username": "鱼鱼鱼三条鱼i",
 "urlname": "qi0vhxtpbq",
 "created_at": 1507536736,
 "avatar": {
 "id": 189389834,
 "farm": "farm1",
 "bucket": "hbimg",
 "key": "b451737f2f143688c7183d5bdda77fb28a2aa8c9f5c3-gq5R53",
 "type": "image/jpeg",
 "width": 720,
 "height": 716,
 "frames": 1
 },
 "extra": null
 },
 "via_user": {
 "user_id": 22282493,
 "username": "鱼鱼鱼三条鱼i",
 "urlname": "qi0vhxtpbq",
 "created_at": 1507536736,
 "avatar": {
 "id": 189389834,
 "farm": "farm1",
 "bucket": "hbimg",
 "key": "b451737f2f143688c7183d5bdda77fb28a2aa8c9f5c3-gq5R53",
 "type": "image/jpeg",
 "width": 720,
 "height": 716,
 "frames": 1
 },
 "extra": null
 },
 "tags": []
 }
 */

public class UserPinsBean {
    private List<UserPinsItem> pins;

    public List<UserPinsItem> getPins() {
        return pins;
    }

    public void setPins(List<UserPinsItem> pins) {
        this.pins = pins;
    }

    public class UserPinsItem{
        private int pin_id;
        private int user_id;

        public int getPin_id() {
            return pin_id;
        }

        public void setPin_id(int pin_id) {
            this.pin_id = pin_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getBoard_id() {
            return board_id;
        }

        public void setBoard_id(int board_id) {
            this.board_id = board_id;
        }

        public int getFile_id() {
            return file_id;
        }

        public void setFile_id(int file_id) {
            this.file_id = file_id;
        }

        public PinsFile getFile() {
            return file;
        }

        public void setFile(PinsFile file) {
            this.file = file;
        }

        public int getMedia_type() {
            return media_type;
        }

        public void setMedia_type(int media_type) {
            this.media_type = media_type;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getRaw_text() {
            return raw_text;
        }

        public void setRaw_text(String raw_text) {
            this.raw_text = raw_text;
        }

        public int getVia() {
            return via;
        }

        public void setVia(int via) {
            this.via = via;
        }

        public int getVia_user_id() {
            return via_user_id;
        }

        public void setVia_user_id(int via_user_id) {
            this.via_user_id = via_user_id;
        }

        public int getOriginal() {
            return original;
        }

        public void setOriginal(int original) {
            this.original = original;
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

        public int getSeq() {
            return seq;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public int getRepin_count() {
            return repin_count;
        }

        public void setRepin_count(int repin_count) {
            this.repin_count = repin_count;
        }

        public int getIs_private() {
            return is_private;
        }

        public void setIs_private(int is_private) {
            this.is_private = is_private;
        }

        public String getOrig_source() {
            return orig_source;
        }

        public void setOrig_source(String orig_source) {
            this.orig_source = orig_source;
        }

        public boolean isLiked() {
            return liked;
        }

        public void setLiked(boolean liked) {
            this.liked = liked;
        }

        public PinsUser getUser() {
            return user;
        }

        public void setUser(PinsUser user) {
            this.user = user;
        }

        public PinsBoard getBoard() {
            return board;
        }

        public void setBoard(PinsBoard board) {
            this.board = board;
        }

        private int board_id;
        private int file_id;
        private PinsFile file;
        private int media_type;
        private String source;
        private String link;
        private String raw_text;
        private int via;
        private int via_user_id;
        private int original;
        private int created_at;
        private int like_count;
        private int seq;
        private int comment_count;
        private int repin_count;
        private int is_private;
        private String orig_source;
        private boolean liked;
        private PinsUser user;
        private PinsBoard board;

        /**
         * 采集所属画板信息
         */
        public class PinsBoard{
            public int getBoard_id() {
                return board_id;
            }

            public void setBoard_id(int board_id) {
                this.board_id = board_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public int getSeq() {
                return seq;
            }

            public void setSeq(int seq) {
                this.seq = seq;
            }

            public int getPin_count() {
                return pin_count;
            }

            public void setPin_count(int pin_count) {
                this.pin_count = pin_count;
            }

            public int getFollow_count() {
                return follow_count;
            }

            public void setFollow_count(int follow_count) {
                this.follow_count = follow_count;
            }

            public int getLike_count() {
                return like_count;
            }

            public void setLike_count(int like_count) {
                this.like_count = like_count;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public int getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }

            public int getDeleting() {
                return deleting;
            }

            public void setDeleting(int deleting) {
                this.deleting = deleting;
            }

            public int getIs_private() {
                return is_private;
            }

            public void setIs_private(int is_private) {
                this.is_private = is_private;
            }

            private int board_id;
            private int user_id;
            private String title;
            private String description;
            private String category_id;
            private int seq;
            private int pin_count;
            private int follow_count;
            private int like_count;
            private int created_at;
            private int updated_at;
            private int deleting;
            private int is_private;
        }

        /**
         * 采集所属用户信息
         */
        public class PinsUser{
            private int user_id;
            private String username;

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

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public UserBean.Avatar getAvatar() {
                return avatar;
            }

            public void setAvatar(UserBean.Avatar avatar) {
                this.avatar = avatar;
            }

            private String urlname;
            private int created_at;
            private UserBean.Avatar avatar;
        }

        /**
         * 采集的图片信息
         */
        public class PinsFile{
            private String farm;
            private String bucket;
            private String key;
            private String type;
            private int width;

            public String getFarm() {
                return farm;
            }

            public void setFarm(String farm) {
                this.farm = farm;
            }

            public String getBucket() {
                return bucket;
            }

            public void setBucket(String bucket) {
                this.bucket = bucket;
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

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getFrames() {
                return frames;
            }

            public void setFrames(int frames) {
                this.frames = frames;
            }

            private int height;
            private int frames;
        }
    }
}
