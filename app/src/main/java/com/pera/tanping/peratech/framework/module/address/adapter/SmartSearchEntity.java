package com.pera.tanping.peratech.framework.module.address.adapter;
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

import com.gb.bind.adapter.GbMultiItemEntity;

/**
 * 创建时间:2018/8/14 15:20
 */
public class SmartSearchEntity<T>  implements GbMultiItemEntity{

    /**
     * 地址信息
     */
    public static final int TYPE_NORMAL = 1;
    /**
     * 没有找到的错误提示
     */
    public static final int TYPE_ERROR = 2;
    /**
     * google logo标识
     */
    public static final int TYPE_GOOGLE_LOGO = 3;

    public int type;
    public T value;

    @Override
    public int getItemType() {
        return type;
    }


    @Override
    public Object getItemValue() {
        return value;
    }
}
