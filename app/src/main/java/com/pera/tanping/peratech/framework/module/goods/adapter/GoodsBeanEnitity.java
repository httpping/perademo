package com.pera.tanping.peratech.framework.module.goods.adapter;
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

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.gb.bind.adapter.GbMultiItemEntity;

/**
 * 类描述：
 * 创建时间:2018/8/16 21:01
 *
 * @author tanping
 */
public class GoodsBeanEnitity<T> implements GbMultiItemEntity{


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
