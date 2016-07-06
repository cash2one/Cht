package com.damenghai.chahuitong.module.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.FrameLayout;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.BeamBaseActivity;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.utils.LUtils;
import com.umeng.update.UmengUpdateAgent;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends BeamBaseActivity<MainPresenter> {

    @Bind(R.id.main_container)
    FrameLayout mContainer;

    @Bind(R.id.main_tab_layout)
    TabLayout mTabLayout;

    private long mTime = 0L;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_main);
        ButterKnife.bind(this);

        initTab();
        checkUpdate();
    }

    private void checkUpdate() {
        UmengUpdateAgent.setUpdateOnlyWifi(false);
        UmengUpdateAgent.setUpdateListener(null);
        UmengUpdateAgent.update(this);
    }

    private void initTab() {
        mTabLayout.setOnTabSelectedListener(getPresenter());
        for (int i = 0; i < getPresenter().getAdapter().getCount(); i++) {
            TabLayout.Tab tab = mTabLayout.newTab();
            mTabLayout.addTab(tab, i == 0);
            tab.setText(getPresenter().getAdapter().getPageTitle(i));
            tab.setIcon(getPresenter().getAdapter().getPageIcon(i));
        }
    }

    public FrameLayout getContainer() {
        return mContainer;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        long curTime = System.currentTimeMillis();
        if (curTime - mTime < 2000) {
            System.exit(0);
        } else {
            mTime = curTime;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long curTime = System.currentTimeMillis();
            if (curTime - mTime > 2000) {
                mTime = curTime;
                LUtils.toast(R.string.toast_exit);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
