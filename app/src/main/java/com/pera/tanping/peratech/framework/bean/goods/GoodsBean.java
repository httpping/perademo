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
 * 类描述：
 * 创建时间:2018/8/16 21:01
 *
 * @author tanping
 */
public class GoodsBean implements MultiItemEntity{


    public int id;
    public int categoryId;
    public int brandid;
    public String sku;
    public String productName;
    public String shortDesc;
    public String description;
    public String focusImgUrl;
    public String thumbnailsUrll;
    public String specialOffer;
    public String costPrice;
    public String marketPrice;
    public double salePrice;
    public String addDate;
    public String productattrlist;
    public int PageCount;

    @Override
    public int getItemType() {
        return 0;
    }
}
