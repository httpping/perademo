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
import com.pera.tanping.peratech.framework.widget.RatioImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        OrderBean orderBean = (OrderBean) bean.value;

        RatioImageView ratioImageView = helper.getView(R.id.imageView2);
//        ratioImageView.setUrl(orderBean.focusImgUrl);

        try {
            JSONArray jsonArray = new JSONArray(orderBean.message);
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            jsonArray = (JSONArray) jsonObject.get("AttrList");
            String title = "";
            for (int i=0;i<jsonArray.length();i++){
                jsonObject = (JSONObject) jsonArray.get(i);
                title += jsonObject.getString("AttrName");
                title += "(" + jsonObject.getString("AttrValue") +")";
            }
            helper.setText(R.id.tv_title,title);

        } catch (Exception e) {
            e.printStackTrace();
        }

        helper.setText(R.id.textView5,"");
        ratioImageView.setVisibility(View.GONE);
        helper.setText(R.id.tv_price,"");

    }

    @BindItem(type = TYPE_HEADER,layout = R.layout.item_order_header)
    public void header(BaseViewHolder helper, OrderBeanEnitity bean) throws Exception {
        OrderBean orderBean = (OrderBean) bean.value;
        helper.setText(R.id.tv_goods_number,"下单时间："+orderBean.add_time);
        helper.setText(R.id.tv_order_price,"订单号："+orderBean.order_no);

    }

    @BindItem(type = TYPE_FOOTER,layout = R.layout.item_order_footer)
    public void footer(BaseViewHolder helper, OrderBeanEnitity bean) throws Exception {
        OrderBean orderBean = (OrderBean) bean.value;
//        helper.setText(R.id.tv_goods_number,);
        helper.setText(R.id.tv_order_price,"￥"+orderBean.order_amount);

    }

    @Override
    public void onClick(View v) {
        OrderBean OrderBean = (OrderBean) v.getTag(R.id.recycler_view_item_id);

    }

}
