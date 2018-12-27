package com.example.mvp_20181226.presenter;

import com.example.mvp_20181226.entity.UserEntity;
import com.example.mvp_20181226.model.LoginModel;
import com.example.mvp_20181226.net.RequestCallback;
import com.example.mvp_20181226.utils.ValidatorUtil;
import com.example.mvp_20181226.view.IloginView;

import java.util.HashMap;

public class LoginPresenter {
    private LoginModel loginModel;
    private IloginView iloginView;
    public LoginPresenter(IloginView iloginView) {
        loginModel = new LoginModel();
        this.iloginView = iloginView;
    }

    public void login(HashMap<String,String> params){
        String mobile ="18612991023";
        String password = "11111111";
        if (!ValidatorUtil.isMobile(mobile)){

            return;
        }
        if (loginModel!=null){
            loginModel.login(params, new RequestCallback() {
                @Override
                public void failure(String msg) {
                    if (iloginView!=null){
                        iloginView.failure(msg);
                    }
                }

                @Override
                public void successMsg(String msg) {
                    if (iloginView!=null){
                        iloginView.successMsg(msg);
                    }
                }

                @Override
                public void success(UserEntity userEntity) {
                    if (iloginView!=null){
                        iloginView.success(userEntity);
                    }
                }
            });
        }
    }

}
