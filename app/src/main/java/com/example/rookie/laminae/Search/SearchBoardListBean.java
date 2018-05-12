package com.example.rookie.laminae.Search;

import com.example.rookie.laminae.imageDetial.PinsDetialBean;
import com.example.rookie.laminae.user.UserBoard.UserBoardBean;
import com.example.rookie.laminae.user.UserPins.UserPinsBean;

import java.util.List;

/**
 * Created by rookie on 2018/5/4.
 */
public class SearchBoardListBean {
    private SearchImageBean.QueryBean query;
    private int pin_count;
    private int board_count;
    private int people_count;
    private int gift_count;
    private List<BoardPinsBean> boards;

    public SearchImageBean.QueryBean getQuery() {
        return query;
    }

    public void setQuery(SearchImageBean.QueryBean query) {
        this.query = query;
    }

    public int getPin_count() {
        return pin_count;
    }

    public void setPin_count(int pin_count) {
        this.pin_count = pin_count;
    }

    public int getBoard_count() {
        return board_count;
    }

    public void setBoard_count(int board_count) {
        this.board_count = board_count;
    }

    public int getPeople_count() {
        return people_count;
    }

    public void setPeople_count(int people_count) {
        this.people_count = people_count;
    }

    public int getGift_count() {
        return gift_count;
    }

    public void setGift_count(int gift_count) {
        this.gift_count = gift_count;
    }

    public List<BoardPinsBean> getBoards() {
        return boards;
    }

    public void setBoards(List<BoardPinsBean> boards) {
        this.boards = boards;
    }

    //   画板的详细信息
    public class BoardPinsBean{

        /**
         * board_id : 3210286
         * user_id : 470656
         * title : Special、
         * description :
         * category_id : beauty
         * seq : 5
         * pin_count : 460
         * follow_count : 1387
         * like_count : 18
         * created_at : 1354788674
         * updated_at : 1459764657
         * deleting : 0
         * is_private : 0
         * extra : {"cover":{"pin_id":"396708240"}}
         * cover : {"pin_id":396708240,"user_id":470656,"board_id":3210286,"file_id":69076727,"file":{"farm":"farm1","bucket":"hbimg","key":"cf06b9cab8166dff391a222536c2b43e674e7dfea9204-iTtsBl","type":"image/jpeg","width":1416,"height":1416,"frames":1,"theme":"9A969D"},"media_type":0,"source":"photo.weibo.com","link":"http://photo.weibo.com/1750156860/wbphotos/large/photo_id/3750772890161141/album_id/3561561172683350","raw_text":" ","text_meta":{},"via":394762585,"via_user_id":17020317,"original":333187864,"created_at":1433695869,"like_count":19,"comment_count":0,"repin_count":43,"is_private":0,"orig_source":null}
         * user : {"user_id":470656,"username":"有时黑有时白、","urlname":"mre7v3oyfx","created_at":1341270673,"avatar":"图片地址"}
         * following : true
         */

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

    public CoverBean getCover() {
        return cover;
    }

    public void setCover(CoverBean cover) {
        this.cover = cover;
    }

    public PinsDetialBean.Pin.User getUser() {
        return user;
    }

    public void setUser(PinsDetialBean.Pin.User user) {
        this.user = user;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public List<UserPinsBean.UserPinsItem> getPins() {
        return pins;
    }

    public void setPins(List<UserPinsBean.UserPinsItem> pins) {
        this.pins = pins;
    }

    /**
     * pin_id : 396708240
     * user_id : 470656
     * board_id : 3210286
     * file_id : 69076727
     * file : {"farm":"farm1","bucket":"hbimg","key":"cf06b9cab8166dff391a222536c2b43e674e7dfea9204-iTtsBl","type":"image/jpeg","width":1416,"height":1416,"frames":1,"theme":"9A969D"}
     * media_type : 0
     * source : photo.weibo.com
     * link : http://photo.weibo.com/1750156860/wbphotos/large/photo_id/3750772890161141/album_id/3561561172683350
     * raw_text :
     * text_meta : {}
     * via : 394762585
     * via_user_id : 17020317
     * original : 333187864
     * created_at : 1433695869
     * like_count : 19
     * comment_count : 0
     * repin_count : 43
     * is_private : 0
     * orig_source : null
     */

    private CoverBean cover;
    /**
     * user_id : 470656
     * username : 有时黑有时白、
     * urlname : mre7v3oyfx
     * created_at : 1341270673
     * avatar : 图片地址
     */

    private PinsDetialBean.Pin.User user;
    private boolean following;

    private List<UserPinsBean.UserPinsItem> pins;
    public class CoverBean{
        private int pin_id;
        private int user_id;
        private int board_id;
        private int file_id;
        /**
         * farm : farm1
         * bucket : hbimg
         * key : cf06b9cab8166dff391a222536c2b43e674e7dfea9204-iTtsBl
         * type : image/jpeg
         * width : 1416
         * height : 1416
         * frames : 1
         * theme : 9A969D
         */

        private UserBoardBean.BoardItemInfo.PinsSimpleBean.FileBean file;
        private int media_type;
        private String source;
        private String link;
        private String raw_text;
        private int via;
        private int via_user_id;
        private int original;
        private int created_at;
        private int like_count;
        private int comment_count;
        private int repin_count;
        private int is_private;
        private Object orig_source;

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

        public UserBoardBean.BoardItemInfo.PinsSimpleBean.FileBean getFile() {
            return file;
        }

        public void setFile(UserBoardBean.BoardItemInfo.PinsSimpleBean.FileBean file) {
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

        public Object getOrig_source() {
            return orig_source;
        }

        public void setOrig_source(Object orig_source) {
            this.orig_source = orig_source;
        }
    }
    }
}
