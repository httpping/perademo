/*
 * Copyright 2014 - 2015 Henning Dodenhof
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pera.tanping.peratech.framework.widget;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.pera.tanping.peratech.framework.bean.ExchangeBean;
import com.pera.tanping.peratech.framework.remote.model.CurrencyManager;

import java.math.RoundingMode;

/**
 * 货币控件，展示货币逻辑
 */
public class CurrencyTextView extends android.support.v7.widget.AppCompatTextView {

    private String prefix;
    private String suffix;

    private ExchangeBean mExchange;

    public CurrencyTextView(Context context) {
        super(context);
        init();
    }

    public CurrencyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CurrencyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);


        init();
    }

    private void init() {

    }

    public void setCurrency(double price) {
        String text = CurrencyManager.getInstance().getExchangePrice(price, true);
        setCurrencyText(text);
    }

    public void setCurrency(double price, ExchangeBean bean) {
        mExchange = bean;
        String text = CurrencyManager.getInstance().getExchangePrice(bean, RoundingMode.UP, price, true);
        setCurrencyText(text);
    }

    /**
     *
     * price 钱
     *
     * ExchangeBean 汇率
     *
     * RoundingMode.FLOOR; 下
        RoundingMode.UP; 上
        RoundingMode.HALF_UP 四舍五入
     * @param price x
     * @param mode x
     */

    public void setCurrency(double price,RoundingMode mode) {
        mExchange = CurrencyManager.getInstance().readExchange();
        String text = CurrencyManager.getInstance().getExchangePrice(mExchange, mode, price, true);
        setCurrencyText(text);
    }
    public void setCurrency(double price, ExchangeBean bean,RoundingMode mode) {
        mExchange = bean;
        String text = CurrencyManager.getInstance().getExchangePrice(mExchange, mode, price, true);
        setCurrencyText(text);
    }

    public void setCurrencyText(String text) {
        if (!TextUtils.isEmpty(prefix)) {
            text = prefix + text;
        }
        if (!TextUtils.isEmpty(suffix)) {
            text = text + suffix;
        }
        setText(text);
    }

    /**
     * 设置下划线
     * @param b
     */
    public void underLine(boolean b) {
        getPaint().setUnderlineText(b);
        //抗锯齿
        getPaint().setAntiAlias(true);
    }

    /**
     * 设置textview的中划线样式
     *
     * @param
     */
    public void setMiddleLines() {
        //下划线
        getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        //抗锯齿
        getPaint().setAntiAlias(true);
        //中划线
        getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    }

    /**
     * 设置删除效果
     * @param b
     */
    public void setStrikeThruText(boolean b) {
        getPaint().setStrikeThruText(b);
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

//    public ExchangeBean getmExchange() {
//        return mExchange;
//    }


}