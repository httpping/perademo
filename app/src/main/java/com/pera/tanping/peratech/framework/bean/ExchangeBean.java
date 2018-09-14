package com.pera.tanping.peratech.framework.bean;

import java.io.Serializable;

/**
 * 汇率的实体类
 */
public class ExchangeBean implements Serializable {
    private Long id;
    /**
     * 货币符号，如$
     */
    private String symbol;
    /**
     * 相对美元的汇率值，如0.90249939
     */
    private double rate;
    /**
     * 货币名称，如USD
     */
    private String code;

    //精度
    public int exponent = 2;

}
