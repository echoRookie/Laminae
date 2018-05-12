package com.example.rookie.laminae.entity;

/**
 * Created by rookie on 2018/5/10.
 */
public class PinsMainEntity {
    private int pin_id;
    private int user_id;
    private int board_id;
    private int file_id;
    /**
     * farm : farm1
     * bucket : hbimg
     * key : a2945bdf440f492c5144d24eebe45f23b82f3ff84d81f-4vhC2L
     * type : image/jpeg /type=image/gif
     * width : 900
     * height : 1350
     * frames : 1
     */

    private PinsFileEntity file;
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

    /**
     * user_id : 17344184
     * username : 蓝枫芊柔
     * urlname : y0ogn4uezm
     * created_at : 1432372599
     * avatar : https
     */

    private PinsUserEntity user;
    /**
     * board_id : 24048815
     * user_id : 17344184
     * title : 爱好
     * description :
     * category_id : food_drink
     * seq : 5
     * pin_count : 1128
     * follow_count : 76
     * like_count : 3
     * created_at : 1432544067
     * updated_at : 1450779767
     * deleting : 0
     * is_private : 0
     * extra : {"cover":{"pin_id":"401845965"}}
     */

    private PinsBoardEntity board;

    /**
     * user_id : 605533
     * username : 宁馨郁金香
     * urlname : xxf837568038
     * created_at : 1344088961
     * avatar : https
     */


    public void setPin_id(int pin_id) {
        this.pin_id = pin_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    public void setFile(PinsFileEntity file) {
        this.file = file;
    }

    public void setMedia_type(int media_type) {
        this.media_type = media_type;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setRaw_text(String raw_text) {
        this.raw_text = raw_text;
    }

    public void setVia(int via) {
        this.via = via;
    }

    public void setVia_user_id(int via_user_id) {
        this.via_user_id = via_user_id;
    }

    public void setOriginal(int original) {
        this.original = original;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public void setRepin_count(int repin_count) {
        this.repin_count = repin_count;
    }

    public void setIs_private(int is_private) {
        this.is_private = is_private;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public void setOrig_source(String orig_source) {
        this.orig_source = orig_source;
    }

    public void setUser(PinsUserEntity user) {
        this.user = user;
    }

    public void setBoard(PinsBoardEntity board) {
        this.board = board;
    }


    public int getPin_id() {
        return pin_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getBoard_id() {
        return board_id;
    }

    public int getFile_id() {
        return file_id;
    }

    public PinsFileEntity getFile() {
        return file;
    }

    public int getMedia_type() {
        return media_type;
    }

    public String getSource() {
        return source;
    }

    public String getLink() {
        return link;
    }

    public String getRaw_text() {
        return raw_text;
    }

    public int getVia() {
        return via;
    }

    public int getVia_user_id() {
        return via_user_id;
    }

    public int getOriginal() {
        return original;
    }

    public int getCreated_at() {
        return created_at;
    }

    public int getLike_count() {
        return like_count;
    }

    public int getSeq() {
        return seq;
    }

    public int getComment_count() {
        return comment_count;
    }

    public int getRepin_count() {
        return repin_count;
    }

    public int getIs_private() {
        return is_private;
    }

    public boolean isLiked() {
        return liked;
    }

    public String getOrig_source() {
        return orig_source;
    }

    public PinsUserEntity getUser() {
        return user;
    }

    public PinsBoardEntity getBoard() {
        return board;
    }

}
