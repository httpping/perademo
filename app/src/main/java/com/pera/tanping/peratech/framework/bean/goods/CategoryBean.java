package com.pera.tanping.peratech.framework.bean.goods;
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

/**
 * 项目名称: z
 * 类描述：
 * 创建时间:2018/11/5 13:37
 *
 * @author tanping
 */
public class CategoryBean  implements MultiItemEntity {


    public int id;
    public String title;
    public String code;
    public int parent_id;
    public String class_list;
    public int class_layer;
    public int sort_id;
    public String link_url;
    public String img_url;
    public String class_content;
    public String remark;
    public String seo_title;
    public String seo_keywords;
    public String seo_description;
    public int wid;
    public String ico_url;

    @Override
    public int getItemType() {
        return 0;
    }
}
