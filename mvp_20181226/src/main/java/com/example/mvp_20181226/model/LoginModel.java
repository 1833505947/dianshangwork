package com.example.mvp_20181226.model;

import android.os.Handler;
import android.text.TextUtils;

import com.example.mvp_20181226.api.UserApi;
import com.example.mvp_20181226.entity.UserEntity;
import com.example.mvp_20181226.net.RequestCallback;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginModel implements IloginModel{

    Handler handler = new Handler();

    public void login(HashMap<String, String> params, final RequestCallback callback){
        //okhttp网络框架的管理类
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();

        //对请求体，构建数据的过程，建造者模式
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, String> p : params.entrySet()) {
            formBody.add(p.getKey(),p.getValue());
        }
        //创建请求信息对象
        final Request request = new Request.Builder()
                .url(UserApi.USER_LOGIN)
                .post(formBody.build())
                .build();
        //创建请求执行对象
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.failure("网络可能不稳定，请稍后再试");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();//返回的数据，json串
                if (!TextUtils.isEmpty(string)){
                    paserResult(string,callback);
                }
            }
        });
    }

    private void paserResult(String string, final RequestCallback callback) {
        final UserEntity userEntity = new Gson().fromJson(string, UserEntity.class);
        if (callback!=null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                   // if (code==200) {
                        callback.success(userEntity);
                   // }else {
//                        callback.successMsg(userEntity.msg);
//                    }
                }
            });
        }
    }


}
