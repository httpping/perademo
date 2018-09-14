package com.pera.tanping.peratech.framework.module.address;
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
 * 创建时间:2018/8/9 15:29
 */
public interface AddressConstants {
    /**
     * 国家、州、城市标识
     */
    String FROM_COUNTRY_REGION = "from_country_region";

    /**
     * 0代表是国家，1代表是州，2代表是城市
     */
    interface CountryRegion{
        int zero = 0;
        int one = 1;
        int two = 2;
    }

    /**
     * 国家id
     */
    String COUNTRY_ID = "countryId";
    /**
     * 州id
     */
    String STATE_ID = "stateId";

    /**
     * 从哪里进入地址页
     */
    String FROM_WHERE_TO_ADDRESS = "from_where_to_address";
    interface EnterAddress{
        /**
         * 1，结算页选择地址进入
         */
        int checkout = 1;
        /**
         * 2,购物车点击checkout
         */
        int car = 2;
    }

    /**
     * 智能地址搜索获取到的id
     */
    String PLACE_ID = "place_id";
    String ADDRESS_CONTENT = "address_content";
}
