package com.damenghai.chahuitong.module.special;

import android.os.Bundle;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.BargainGoodsViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.ButterKnife;

@RequiresPresenter(BargainPresenter.class)
public class BargainActivity extends BaseListActivity<BargainPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle(R.string.title_activity_bargain);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.special_activity_bargain;
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new BargainGoodsViewHolder(parent);
    }

    @Override
    public int getNoMoreRes() {
        return R.layout.footer_no_more;
    }

    @Override
    public int getLoadMoreRes() {
        return R.layout.footer_load_more;
    }

    @Override
    public int getEmptyRes() {
        return R.layout.empty_favorites_list;
    }

}
