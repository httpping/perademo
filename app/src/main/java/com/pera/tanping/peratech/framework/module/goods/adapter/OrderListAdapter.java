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
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.gb.bind.adapter.GBBaseBindAdapter;
import com.gb.bind.annotations.BindItem;
import com.pera.tanping.peratech.R;
import com.pera.tanping.peratech.framework.bean.order.OrderBean;
import com.pera.tanping.peratech.framework.module.goods.OrderListFragment;

import java.util.List;

import static com.pera.tanping.peratech.framework.module.goods.adapter.OrderBeanEnitity.TYPE_FOOTER;
import static com.pera.tanping.peratech.framework.module.goods.adapter.OrderBeanEnitity.TYPE_GOODS;
import static com.pera.tanping.peratech.framework.module.goods.adapter.OrderBeanEnitity.TYPE_HEADER;

/**
 */
public class OrderListAdapter extends GBBaseBindAdapter<OrderBeanEnitity,BaseViewHolder> implements View.OnClickListener {

    private OrderListFragment fragment;
    private Context mContext;
    private String curSelAddressId;

    public OrderListAdapter(OrderListFragment fragment, List<OrderBeanEnitity> data) {
        super(data);
        this.fragment = fragment;
        this.mContext = this.fragment.getContext();
    }

    public void setCurAddressId(String curAddressId) {
        this.curSelAddressId = curAddressId;
    }

    /**
     * @param helper
     * @param bean
     * @throws Exception
     */
    @BindItem(type = TYPE_GOODS,layout = R.layout.item_order_view)
    public void one(BaseViewHolder helper, OrderBeanEnitity bean) throws Exception {
    }

    @BindItem(type = TYPE_HEADER,layout = R.layout.item_order_header)
    public void header(BaseViewHolder helper, OrderBeanEnitity bean) throws Exception {
    }

    @BindItem(type = TYPE_FOOTER,layout = R.layout.item_order_footer)
    public void footer(BaseViewHolder helper, OrderBeanEnitity bean) throws Exception {
    }

    @Override
    public void onClick(View v) {
        OrderBean OrderBean = (OrderBean) v.getTag(R.id.recycler_view_item_id);

    }

}
