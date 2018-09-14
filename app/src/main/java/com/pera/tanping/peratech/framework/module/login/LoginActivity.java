package com.pera.tanping.peratech.framework.module.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.pera.tanping.peratech.R;
import com.pera.tanping.peratech.framework.base.BaseActivity;

/**
 * 登陆注册界面，用于用户登陆注册
 *

 */
public class LoginActivity extends BaseActivity {
    /**
     * 登录页面选择的选项卡
     */

    @Override
    public boolean isDefaultContentView() {
        return  false;
    }

    @Override
    public Fragment getContentFragment() {
        return new LoginFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Fragment fragment =  getContentFragment();
        //替换添加
        if (fragment !=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment_content,fragment).commit();
        }
    }
}