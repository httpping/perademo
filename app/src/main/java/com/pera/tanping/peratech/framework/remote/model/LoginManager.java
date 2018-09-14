package com.pera.tanping.peratech.framework.remote.model;


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


    public boolean isLogin() {
        return true;
    }

    public String readDefaultAddressId() {
        return null;
    }
}
