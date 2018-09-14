package com.pera.tanping.peratech.framework.remote.model;

import android.support.annotation.NonNull;



import com.pera.tanping.peratech.framework.bean.ExchangeBean;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by tanping on 2018/3/23.
 *
 * 货币转换管理
 */

public class CurrencyManager {

    private  static CurrencyManager instance;
    private CurrencyManager(){}

    /**
     * 单例弱引用。
     * @return
     */
    public static CurrencyManager getInstance(){
        if (instance ==null) {
            instance = new CurrencyManager();
        }
        return instance;
    }

    public String getExchangePrice(double price, boolean b) {
        return price+"";
    }

    public String getExchangePrice(ExchangeBean bean, RoundingMode up, double price, boolean b) {
        return price+"";
    }

    public ExchangeBean readExchange() {

        return  new ExchangeBean();
    }
}
