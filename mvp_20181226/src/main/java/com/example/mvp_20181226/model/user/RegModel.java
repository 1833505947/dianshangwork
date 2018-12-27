package com.example.mvp_20181226.model.user;

import android.os.Handler;

import com.example.mvp_20181226.api.UserApi;

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

public class RegModel implements IRegModel{
    private RegCallback regCallback;
    Handler handler = new Handler();
    public void reg(HashMap<String,String> params){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, String> p : params.entrySet()) {
            formBody.add(p.getKey(),p.getValue());
        }
        final Request request = new Request.Builder()
                .url(UserApi.USER_REG)
                .post(formBody.build())
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (regCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            regCallback.onFailure("网络异常，请稍后再试");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (regCallback!=null){
                    final String result = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            regCallback.onResponse(result);
                        }
                    });
                }
            }
        });
    }

    public void setRegCallback(RegCallback regCallback) {
        this.regCallback = regCallback;
    }

    public interface RegCallback{
        void onFailure(String msg);
        void onResponse(String result);
    }
}
