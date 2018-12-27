package com.example.mvp_20181226.view;

import com.example.mvp_20181226.entity.UserEntity;

public interface IloginView {
    void mobileError(String msg);
    void pwdError(String msg);
    void failure(String msg);
    void success(UserEntity userEntity);
    void successMsg(String msg);
}
