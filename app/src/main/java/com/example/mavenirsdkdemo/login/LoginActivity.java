package com.example.mavenirsdkdemo.login;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.mavenirsdkdemo.BaseActivity;
import com.example.mavenirsdkdemo.R;
import com.example.mavenirsdkdemo.databinding.ActivityLoginBinding;
import com.mavenir.sdk.api.interfaces.ISDKManager;

public class LoginActivity extends BaseActivity {
    ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        // TODO: Just to verify RCS SDK dependency integration.
        ISDKManager.ACCOUNT_TYPE accountType = ISDKManager.ACCOUNT_TYPE.MULTIPLE;
    }
}
