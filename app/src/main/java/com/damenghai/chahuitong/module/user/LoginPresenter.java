package com.damenghai.chahuitong.module.user;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.config.Config;
import com.damenghai.chahuitong.model.AccountModel;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.utils.LUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.view.UMFriendListener;

import java.util.Map;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class LoginPresenter extends Presenter<LoginActivity> implements UMAuthListener {

    private UMShareAPI mShareApi;

    @Override
    protected void onCreate(LoginActivity view, Bundle saveState) {
        super.onCreate(view, saveState);

        mShareApi = UMShareAPI.get(getView());
    }

    public void login(String username, String password) {
        AccountModel.getInstance().login(username, password)
                .subscribe(new ServiceResponse<User>(){
                    @Override
                    public void onNext(User user) {
                        SharedPreferences.Editor editor = LUtils.getPreferences().edit();
                        editor.putString("mobile", username);
                        editor.putString("key", user.getKey());
                        editor.putString("avatar", user.getMember_avatar());
                        editor.apply();
                        getView().setResult(Activity.RESULT_OK);
                        getView().finish();
                    }
                });
    }

    public void doOauthVerify(SHARE_MEDIA media) {
        mShareApi.doOauthVerify(getView(), media, this);
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        // 使用SSO授权必须添加如下代码
        mShareApi.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
        final String type;
        final String openID;

        if (share_media == SHARE_MEDIA.WEIXIN) {
            openID = map.containsKey("openid") ? map.get("openid") : "";
            type = "wechat";
        } else if (share_media == SHARE_MEDIA.SINA) {
            openID = map.containsKey("uid") ? map.get("uid") : "";
            type = "sina";
        } else {
            openID = map.containsKey("openid") ? map.get("openid") : "";
            type = "qq";
        }
        mShareApi.getPlatformInfo(getView(), share_media, new UMAuthListener() {
            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                if (map.containsKey("screen_name")){
                    String screenName = map.get("screen_name");

                    AccountModel.getInstance().thirdLogin(type, openID, screenName)
                            .compose(new DefaultTransform<>())
                            .subscribe(new ServiceResponse<User>() {
                                @Override
                                public void onNext(User user) {
                                    SharedPreferences.Editor editor = LUtils.getPreferences().edit();
                                    editor.putString("username", user.getMember_name());
                                    editor.putString("key", user.getKey());
                                    editor.putString("avatar", user.getMember_avatar());
                                    editor.apply();
                                    getView().setResult(Activity.RESULT_OK);
                                    getView().finish();
                                }
                            });
                }
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });

    }

    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
//        getView().onError("授权出错");
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {
//        getView().onError("授权取消");
    }

}
