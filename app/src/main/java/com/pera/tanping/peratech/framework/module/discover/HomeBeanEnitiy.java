package com.pera.tanping.peratech.framework.module.discover;
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
 * @author tanping
 */
public class HomeBeanEnitiy<T> implements GbMultiItemEntity{


    public static final int TYPE_HEADER = 1;
    public static final int TYPE_CATEGORY = 3;

    public int type ;
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
