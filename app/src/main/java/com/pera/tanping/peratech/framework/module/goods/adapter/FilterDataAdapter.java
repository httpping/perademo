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

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.gb.bind.adapter.GBBaseBindAdapter;
import com.gb.bind.annotations.BindItem;
import com.pera.tanping.peratech.R;

import java.util.List;

import static com.pera.tanping.peratech.framework.module.goods.adapter.FilterDataEnitity.FILTER_DATA;

/**
 */
public class FilterDataAdapter extends GBBaseBindAdapter<FilterDataEnitity,BaseViewHolder> {

    private Context mContext;
    private String curSelAddressId;

    public FilterDataAdapter(List<FilterDataEnitity> data) {
        super(data);
    }


    /**
     * @param helper
     * @param bean
     * @throws Exception
     */
    @BindItem(type = FILTER_DATA,layout = R.layout.item_filter_data_view)
    public void one(BaseViewHolder helper, OrderBeanEnitity bean) throws Exception {
    }


}
