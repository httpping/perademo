package com.pera.tanping.peratech.framework.remote.model;


import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.pera.tanping.peratech.framework.bean.user.UserBean;
import com.pera.tanping.peratech.framework.module.login.LoginActivity;
import com.pera.tanping.peratech.framework.utils.SharedPreferencesUtil;
import com.tp.cache.CacheManager;

import java.util.HashMap;

/**
 * Created by tanping on 2018/3/23.
 *
 * 登录管理
 */

public class LoginManager {

    private  static  LoginManager  instance;
    private LoginManager(){}

    /**
     * 单例弱引用
     * @return
     */
    public static LoginManager getInstance(){
        if (instance ==null){
            instance =  new LoginManager();
        }
        return instance;
    }



    public void saveUser(UserBean userBean){
        CacheManager.put("login_user",userBean);
    }


    public UserBean getUser(){
        UserBean userBean= CacheManager.get("login_user");
        if (userBean !=null){
            return userBean;
        }

        return null;
    }

    public boolean isLogin() {

        if (getUser() !=null) {
            return true;
        }

        return false;
    }


    public void toLogin(FragmentActivity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }
}
