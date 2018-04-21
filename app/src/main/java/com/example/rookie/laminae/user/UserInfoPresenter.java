package com.example.rookie.laminae.user;

import com.example.rookie.laminae.base.BasePresenter;
import com.example.rookie.laminae.base.CallBack;
import com.example.rookie.laminae.login.UserBean;

/**
 * Created by rookie on 2018/4/21.
 */

public class UserInfoPresenter extends BasePresenter<UserInfoView> implements CallBack<UserBean>{
    private UserModel userModel;
    public UserInfoPresenter(){
        this.userModel = new UserModel();
    }
    public void getUserInfoData(String userId){
        userModel.getUserInfoAPI(userId,this);
    }
    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onCompleted(UserBean info) {
        getView().setUserInfo(info);

    }
}
