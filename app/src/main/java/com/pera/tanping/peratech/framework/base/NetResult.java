package com.pera.tanping.peratech.framework.base;

import android.content.Context;

import com.utils.ui.ToastUtil;

/**
 * 网络返回结果封装
 * @param <T>
 */
public class NetResult<T> extends NetBaseBean {

    public int StatusCode;
    public T Data;
    public String Info ="请求失败";
    public int flag ;

    public boolean isSuccess() {
        if (StatusCode == 200){
            return  true;
        }

//        throw new NullPointerException(message);
        return false;
    }

    public boolean isResultNull() {
        if (StatusCode == 202){
            return  true;
        }
        return false;
    }

    public void showError(Context context){
        ToastUtil.showToast(context,Info);
    }

}
