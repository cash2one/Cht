package com.damenghai.chahuitong.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.BeamBaseActivity;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.model.bean.Account;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.module.main.MainActivity;
import com.damenghai.chahuitong.module.personal.FeedbackActivity;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(LoginPresenter.class)
public class LoginActivity extends BeamBaseActivity<LoginPresenter> {

    @Bind(R.id.et_login_username)
    EditText mEtUsername;

    @Bind(R.id.et_login_password)
    EditText mEtPassword;

    @Bind(R.id.btn_login)
    Button mBtnLogin;

    @Bind(R.id.btn_login_register)
    Button mBtnRegister;

    @Bind(R.id.btn_login_forgot)
    Button mBtnForgot;

    @Bind(R.id.btn_login_feedback)
    Button mBtnFeedback;

    @Bind(R.id.tv_login_qq)
    Button mBtnQQ;

    @Bind(R.id.tv_login_weibo)
    Button mBtnWeibo;

    @Bind(R.id.tv_login_weixin)
    Button mBtnWeixin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_login);
        setToolbarTitle(R.string.title_activity_login);
        ButterKnife.bind(this);

        mEtUsername.setText(LUtils.getPreferences().getString("username", ""));
        mBtnLogin.setOnClickListener(v -> getPresenter().login());
        mBtnRegister.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
        mBtnForgot.setOnClickListener(v -> startActivity(new Intent(this, ForgotActivity.class)));
        mBtnFeedback.setOnClickListener(v -> startActivity(new Intent(this, FeedbackActivity.class)));
        mBtnQQ.setOnClickListener(v -> getPresenter().doOauthVerify(SHARE_MEDIA.QQ));
        mBtnWeibo.setOnClickListener(v -> getPresenter().doOauthVerify(SHARE_MEDIA.SINA));
        mBtnWeixin.setOnClickListener(v -> getPresenter().doOauthVerify(SHARE_MEDIA.WEIXIN));
    }

    public String getUsername() {
        return mEtUsername.getText().toString();
    }

    public String getPassword() {
        return mEtPassword.getText().toString();
    }
}
