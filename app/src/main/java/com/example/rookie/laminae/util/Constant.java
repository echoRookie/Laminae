package com.example.rookie.laminae.util;

/**
 * Created by rookie on 2018/3/20.
 * 用来保存系统所用到的常量
 */

public class Constant {
    //  登录需要的报文
    public static final String PASSWORD = "password";
    public static final String Authorization="Authorization";

    //token information
    public static final String TOKENACCESS = "TokenAccess";
    public static final String TOKENREFRESH = "TokenRefresh";
    public static final String TOKENTYPE = "TokenType";
    public static final String TOKENEXPIRESIN = "TokeExpiresIn";
    public static final String USERAUthorization = "userAuthorization";
    //用户信息
    public static final String USERTOKEN = "userToken";
    public static final String USERBEAN = "userBean";
    public static final String ISLOGIN = "isLogin";
    public static final String LOGINTIME = "loginTime";
    public static final String USERNAME = "username";
    public static final String USERPASSWORD = "userPassword";
    public static final String USERID = "userID";
    public static final String USERICONKEY = "userIconKey";
    public static final String USEREMAIL = "userEmail";
    //http limit number
    public static final int LIMIT = 20;
    //图片ID
    public static final String PINSID = "pinsId";
    //图片类别标识 + 标题
    public static final String TYPEKEY = "typeKey";
    public static final String TYPETITLE = "typeTitle";
    //搜索内容
    public static final String SEARCHKEY = "searchKey";
    //画板
    public static final String BOARDID = "boardId";
    public static final String BOARDTITLE = "boardTilte";
    public static final String BOARDDESCRIPTION = "boardDescription";
    //用户喜欢操作的操作字段
    public static final String OPERATELIKE = "like";
    public static final String OPERATEUNLIKE = "unlike";

    //用户对画板的关注操作字段
    public static final String OPERATEFOLLOW = "follow";
    public static final String OPERATEUNFOLLOW = "unfollow";

    //获得用户画板列表详情的操作符
    public static final String OPERATEBOARDEXTRA="recommend_tags";
    public static final boolean OPERATECHECK=true;

    //删除画板的操作符
    public static final String OPERATEDELETEBOARD="DELETE";


}
