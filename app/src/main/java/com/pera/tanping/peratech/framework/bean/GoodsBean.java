package com.pera.tanping.peratech.framework.bean;
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

import com.pera.tanping.peratech.R;
import com.pera.tanping.peratech.framework.annotations.IUpdateImageView;
import com.pera.tanping.peratech.framework.annotations.PirceAnnBind;
import com.tp.bindbean.annotations.TextViewBind;
/**
 * 创建人：Created by tanping
 * 创建时间:2018/7/30 8:14
 */
public class GoodsBean implements IUpdateImageView {

//    @PirceAnnBind(id = R.id.textView1)
    public float price ;

    @TextViewBind(id = R.id.textView)
    public String goodsName = "商品名";


    public String id ;
    public String name ;
    public String name2 ;
    public String name3 ;
    public String name4 ;

    public int w;
    public int h;

    @Override
    public int getW() {
        return w;
    }

    @Override
    public int getH() {
        return h;
    }
}
