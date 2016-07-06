package com.damenghai.chahuitong.module.main;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;

import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.model.bean.Home;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.module.goods.GoodsListActivity;
import com.damenghai.chahuitong.module.mall.CartActivity;
import com.damenghai.chahuitong.module.user.LoginActivity;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class MainHomePresenter extends Presenter<MainHomeFragment> implements SwipeRefreshLayout.OnRefreshListener {

    @Override
    protected void onCreateView(MainHomeFragment view) {
        super.onCreateView(view);
        getView().startRefresh();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        ServiceClient.getServices().home(LUtils.getPreferences().getString("key", ""))
                .compose(new DefaultTransform<>())
                .subscribe(new ServiceResponse<Home>() {
                    @Override
                    public void onCompleted() {
                        getView().stopRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        getView().stopRefresh();
                    }

                    @Override
                    public void onNext(Home home) {
                        getView().setData(home);
                    }
                });
    }

    public void showCart() {
        if (checkLogin()) {
            Intent i = new Intent(getView().getActivity(), CartActivity.class);
            getView().startActivity(i);
        }
    }

    public void showGoodsList(String op) {
        Intent intent = new Intent(getView().getActivity(), GoodsListActivity.class);
        intent.putExtra("op", op);
        getView().startActivity(intent);
    }

    private boolean checkLogin() {
        if (TextUtils.isEmpty(LUtils.getPreferences().getString("key", ""))) {
            Intent i = new Intent(getView().getActivity(), LoginActivity.class);
            getView().startActivity(i);
            return false;
        } else {
            return true;
        }
    }

}