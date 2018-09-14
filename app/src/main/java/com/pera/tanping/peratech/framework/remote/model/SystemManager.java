package com.pera.tanping.peratech.framework.remote.model;

import java.lang.ref.WeakReference;

/**
 * 获取存放SystemPresenter中的有关推送相关信息
 */
public class SystemManager {

    private  static WeakReference<SystemManager> instance;
    private SystemManager(){}

    /**
     * 单例弱引用
     * @return
     */
    public static SystemManager getInstance(){
        if (instance ==null || instance.get() == null){
            instance = new WeakReference<>(new SystemManager());
        }

        return instance.get();
    }



}
