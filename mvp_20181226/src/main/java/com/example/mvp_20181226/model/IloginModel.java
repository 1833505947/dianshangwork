package com.example.mvp_20181226.model;

import com.example.mvp_20181226.net.RequestCallback;

import java.util.HashMap;

public interface IloginModel {
    void login(HashMap<String,String> params, RequestCallback callback);
}
