package com.example.mvp_20181226.activity.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvp_20181226.R;
import com.example.mvp_20181226.presenter.user.RegPresenter;
import com.example.mvp_20181226.view.user.IRegView;

import java.util.HashMap;

public class RegActivity extends AppCompatActivity implements IRegView {
    private RegPresenter regPresenter;
    private EditText edname;
    private EditText edpwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        edname = findViewById(R.id.name);
        edpwd = findViewById(R.id.pwd);
        initData();
    }

    private void initData() {
         regPresenter = new RegPresenter(this);
    }

    public void reg(View view){
        String name = edname.getText().toString();
        String pwd = edpwd.getText().toString();
       HashMap<String,String> params = new HashMap<>();
       params.put("mobile",name);
       params.put("password",pwd);
       if (regPresenter!=null) {
           regPresenter.reg(params);
       }
    }


    @Override
    public void mobileError(String error) {
        Toast.makeText(this,error+"",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String result) {
        Toast.makeText(this,result+"注册成功",Toast.LENGTH_SHORT).show();
    }
}
