package com.example.mvp_20181226.presenter.user;

import com.example.mvp_20181226.contract.IRegContract;
import com.example.mvp_20181226.model.user.RegModel;
import com.example.mvp_20181226.utils.ValidatorUtil;
import com.example.mvp_20181226.view.user.IRegView;

import java.util.HashMap;

public class RegPresenter  {
private RegModel regModel;
private IRegView iRegView;
public RegPresenter(IRegView iRegView){
     regModel = new RegModel();
    this.iRegView = iRegView;
}
    public void reg(HashMap<String,String> params) {
        String mobile = params.get("mobile");
        if (!ValidatorUtil.isMobile(mobile)){
            if (iRegView!=null){
                iRegView.mobileError("手机号不合法");
            }
            return;
        }
        if (regModel!=null){
            regModel.reg(params);
            regModel.setRegCallback(new RegModel.RegCallback() {
                @Override
                public void onFailure(String msg) {
                    if (iRegView!=null) {
                        iRegView.onFail(msg);
                    }
                }

                @Override
                public void onResponse(String result) {
                    if (iRegView!=null) {
                        iRegView.onSuccess(result);
                    }
                }
            });
        }
    }


}
