package com.example.mvp_20181226.view.user;

public interface IRegView {
    void mobileError(String error);
    void onFail(String msg);
    void onSuccess(String result);
}
