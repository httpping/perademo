package com.pera.tanping.peratech.framework.bean.address;
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

import com.pera.tanping.peratech.framework.remote.config.base.NetBaseBean;

import java.util.List;

/**
 * 创建时间:2018/8/14 15:16
 */
public class SmartSearchAddressBean extends NetBaseBean {

    public List<SmartSearchAddress> predictions;

    public static class SmartSearchAddress extends NetBaseBean {
        public String description;
        public String place_id;
        public StructuredFormattingBean structured_formatting;
        public List<HighlightBean> highlight;

        public static class StructuredFormattingBean {
            public String main_text;
            public String secondary_text;
        }

        public static class HighlightBean {
            public int length;
            public int offset;
        }
    }

}
