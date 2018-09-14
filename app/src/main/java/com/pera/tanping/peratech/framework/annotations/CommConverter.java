package com.pera.tanping.peratech.framework.annotations;
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

import android.widget.TextView;

/**
 * 类描述：
 * 创建人：Created by tanping
 * 创建时间:2018/7/30 8:12
 */
public class CommConverter {

    @DemoAnnBind
    public static  void demoTest(TextView view , float price){
        view.setText("市场价: $" +price);
    }


    @PirceAnnBind
    public static  void xxx(TextView view , float price){
        view.setText("市场价: $" +price);
    }

    @PirceAnnBind
    public static  void xxx(TextView view , float price, IUpdateImageView ss){

        view.setText("市场价: $" +price);
    }
}
