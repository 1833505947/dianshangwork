package com.example.mvp_20181226.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvp_20181226.R;
import com.example.mvp_20181226.activity.user.RegActivity;
import com.example.mvp_20181226.entity.UserEntity;
import com.example.mvp_20181226.presenter.LoginPresenter;
import com.example.mvp_20181226.view.IloginView;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements IloginView {

    private LoginPresenter presenter;
    private EditText edname;
    private EditText edpwd;
    private Button reg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        reg = findViewById(R.id.reg);
        /*reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);
            }
        });*/
        edname = findViewById(R.id.name);
        edpwd = findViewById(R.id.pwd);
        initData();
    }

    private void initData() {
        presenter = new LoginPresenter(this);
    }
    public void login(View view){


        String name = edname.getText().toString();
        String pwd = edpwd.getText().toString();
        HashMap<String, String> params = new HashMap<>();
        //18612991023
        params.put("mobile",name);
        params.put("password",pwd);
        if (presenter!=null){
            presenter.login(params);
        }
    }

    @Override
    public void mobileError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pwdError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(UserEntity userEntity) {
        Toast.makeText(this,userEntity.msg+"",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
