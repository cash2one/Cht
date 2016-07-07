package com.damenghai.chahuitong.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.damenghai.chahuitong.config.API;
import com.damenghai.chahuitong.expansion.data.BaseDataFragmentPresenter;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.module.order.OrderListActivity;
import com.damenghai.chahuitong.module.user.LoginActivity;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class MainPersonalPresenter extends BaseDataFragmentPresenter<MainPersonalFragment, User> {

    private final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(MainPersonalFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
    }

    @Override
    protected void onCreateView(MainPersonalFragment view) {
        super.onCreateView(view);
        loadData();
    }

    public void loadData() {
        if (TextUtils.isEmpty(LUtils.getPreferences().getString("key", ""))) return;
        ServiceClient.getServices().getUser(API.VERSION, LUtils.getPreferences().getString("key", ""))
                .compose(new DefaultTransform<>())
                .subscribe(getSubscriber());
    }

    public void showOrder(int position) {
        if (isLogin()) {
            Intent intent = new Intent(getView().getActivity(), OrderListActivity.class);
            intent.putExtra("position", position);
            getView().startActivity(intent);
        }
    }

    public boolean isLogin() {
        if (TextUtils.isEmpty(LUtils.getPreferences().getString("key", ""))) {
            getView().startActivityForResult(new Intent(getView().getActivity(), LoginActivity.class), REQUEST_CODE);
            return false;
        }
        return true;
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) loadData();
    }
}
