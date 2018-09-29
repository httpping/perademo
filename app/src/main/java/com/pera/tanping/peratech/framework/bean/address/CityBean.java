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

/**
 * 项目名称: z
 * 类描述：
 * 创建时间:2018/9/27 17:15
 *
 * @author tanping
 */
public class CityBean {

    public int id;
    public String name;
    public int level;
    public int upid;


    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof CityBean)){
            return false;
        }

        CityBean cityBean = (CityBean) obj;

        if (id == cityBean.id || name!=null && name.equals(cityBean.name)){
            return true;
        }
        return false;
    }
}
