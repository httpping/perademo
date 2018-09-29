package com.pera.tanping.peratech.framework.remote.model;


import android.app.Activity;

import com.pera.tanping.peratech.framework.base.NetResult;
import com.pera.tanping.peratech.framework.bean.ExchangeBean;
import com.pera.tanping.peratech.framework.bean.address.CityBean;
import com.pera.tanping.peratech.framework.bean.user.AddressBean;
import com.pera.tanping.peratech.framework.remote.ApiManager;
import com.pera.tanping.peratech.framework.remote.config.RequestParam;
import com.pera.tanping.peratech.framework.remote.config.XGsonSubscriber;
import com.tp.cache.CacheManager;

import java.math.RoundingMode;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tanping on 2018/3/23.
 *
 * 货币转换管理
 */

public class AddressManager {

    private  static AddressManager instance;
    private AddressManager(){}

    /**
     * 单例弱引用。
     * @return
     */
    public static AddressManager getInstance(){
        if (instance ==null) {
            instance = new AddressManager();
        }
        return instance;
    }


    /**
     * 读取地址列表 并缓存
     * @param activity
     * @param showDialog
     */
    public void readAddress(Activity activity,boolean showDialog){
        RequestParam param = new RequestParam();
        ApiManager.Api().getAddressList(param.createRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XGsonSubscriber<NetResult<List<CityBean>>>(activity,showDialog) {
                    @Override
                    public void onSuccess(NetResult<List<CityBean>> listNetResult) {
                        if (listNetResult.isSuccess()){
                            CacheManager.put("city_list",listNetResult.Data);
                        }
                    }


                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);

                        new NetResult<>().showError(activity);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }
                });
    }
}
