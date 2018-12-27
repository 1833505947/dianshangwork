package com.example.day2_20181227.util;

import android.os.AsyncTask;

import com.example.day2_20181227.entity.UserBean;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Utils {
    public void getRequest3(String apiurl, final Class clazz, final UtilCallback callback){
        new AsyncTask<String, Void, Object>() {
            @Override
            protected Object doInBackground(String... strings) {
                return getRequest2(strings[0],clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                if (o==null){
                    callback.onFail("请求错误！");
                }else {
                    callback.onSuccess(o);
                }
            }
        }.execute(apiurl);
    }
    public interface UtilCallback<T>{
        void onFail(String msg);
        void onSuccess(T o);
    }
    public <T> T getRequest2(String apiurl,Class clazz){
        Object o;
        String request = getRequest(apiurl);
        o = new Gson().fromJson(request, clazz);
        return (T)o;
    }
    public String getRequest(String apiurl){
        String steam = "";
        try {
            URL url = new URL(apiurl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            int responseCode = urlConnection.getResponseCode();
            if (responseCode==HttpURLConnection.HTTP_OK){
                steam = getSteam(urlConnection.getInputStream());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return steam;
    }

    private String getSteam(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        for (String tmp = bufferedReader.readLine();tmp != null;tmp = bufferedReader.readLine()){
            stringBuilder.append(tmp);
        }
        return stringBuilder.toString();

    }

}
