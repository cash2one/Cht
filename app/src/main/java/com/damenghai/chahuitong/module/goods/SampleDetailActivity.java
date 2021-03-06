package com.damenghai.chahuitong.module.goods;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.data.BaseDataActivity;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.widget.CirclePageIndicator;
import com.damenghai.chahuitong.widget.HeadViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(SampleDetailPresenter.class)
public class SampleDetailActivity extends BaseDataActivity<SampleDetailPresenter, Goods> {

    @Bind(R.id.pager_sample_image)
    HeadViewPager mImagePager;

    @Bind(R.id.sample_indicator)
    CirclePageIndicator mIndicator;

    @Bind(R.id.tv_sample_name)
    TextView mTvName;

    @Bind(R.id.tv_sample_storage)
    TextView mTvStorage;

    @Bind(R.id.tv_sample_obtain)
    TextView mTvObtain;

    @Bind(R.id.tv_sample_origin)
    TextView mTvOrigin;

    @Bind(R.id.btn_sample_apply)
    Button mBtnApply;

    @Bind(R.id.group_sample_tab)
    RadioGroup mGroupTab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.special_activity_sample);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new SampleNoticeFragment());
        fragments.add(new SampleListFragment());

        mGroupTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction ft  = getSupportFragmentManager().beginTransaction();
                switch (checkedId) {
                    case R.id.rbt_sample_notice :
                        ft.replace(R.id.layout_sample_container, fragments.get(0));
                        ft.commit();
                        break;
                    case R.id.rbt_sample_history :
                        ft.replace(R.id.layout_sample_container, fragments.get(1));
                        ft.commit();
                        break;
                }
            }
        });
        mGroupTab.check(R.id.rbt_sample_notice);
    }

    @Override
    public void setData(final Goods goods) {
        mIndicator.setViewPager(mImagePager);
        mTvName.setText(goods.getGoods_name());
        mTvOrigin.append(goods.getOrigin());
        mTvStorage.append(goods.getGoods_storage());
        mTvObtain.append(goods.getGoods_salenum());
    }

}
