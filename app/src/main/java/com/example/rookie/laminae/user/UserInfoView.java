package com.example.rookie.laminae.user;

import com.example.rookie.laminae.base.BaseView;
import com.example.rookie.laminae.login.UserBean;

/**
 * Created by rookie on 2018/4/21.
 */

public interface UserInfoView extends BaseView {
    /**
     * 根据回调回来的数据设置用户信息
     * @param userBean
     */
   void setUserInfo(UserBean userBean);
}
