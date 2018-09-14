package com.pera.tanping.peratech.framework.remote.config;
/*

                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG

*/

/**
 * 项目名称: z
 * 类描述：
 * 创建时间:2018/8/16 19:55
 *
 * @author tanping
 */
public interface Constants {

    String USERNAME = "username";
    String PASSWORD = "password";



    /**
     * 地址id
     */
     String ADDRESS_ID = "address_id";

    /**
     * 从哪里进入地址页
     */
      String FROM_WHERE_TO_ADDRESS = "from_where_to_address";
    public  interface EnterAddress{
        /**
         * 1，结算页选择地址进入
         */
        int checkout = 1;
        /**
         * 2,购物车点击checkout
         */
        int car = 2;



        /**
         * 国家
         */
         String COUNTRY_NAME = "address_country";

        /**
         * 省市
         */
        String STATE_NAME = "address_state";

        /**
         * 城市
         */
        public static final String CITY_NAME = "address_city";
    }
}
