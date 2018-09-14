package com.pera.tanping.peratech.framework.remote.config.base;

import android.content.Context;
import android.widget.Toast;

import com.utils.ui.ToastUtil;


/**
 * 网络返回结果封装
 * @param <T>
 */
public class NetResult<T> extends NetBaseBean {

    public int statusCode;
    public T result;
    public String message;
    public int flag ;

    public boolean isSuccess() {
        if (statusCode == 200){
            return  true;
        }

//        throw new NullPointerException(message);
        return false;
    }

    public boolean isResultNull() {
        if (statusCode == 202){
            return  true;
        }
        return false;
    }


    public void showError(Context context){
        ToastUtil.showToast(context,message, Toast.LENGTH_LONG);
    }


}
