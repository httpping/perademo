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
import com.pera.tanping.peratech.framework.bean.goods.CategoryBean;
import com.pera.tanping.peratech.framework.bean.goods.GoodsBean;
import com.pera.tanping.peratech.framework.module.goods.GoodsListFragment;
import com.pera.tanping.peratech.framework.widget.RatioImageView;

import java.util.List;

/**
 */
public class GoodsListAdapter extends GBBaseBindAdapter<CategoryBean,BaseViewHolder> implements View.OnClickListener {

    private GoodsListFragment fragment;
    private Context mContext;
    private String curSelAddressId;

    public GoodsListAdapter(GoodsListFragment fragment, List<CategoryBean> data) {
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
    @BindItem(type = 0,layout = R.layout.item_goods_view)
    public void one(BaseViewHolder helper, CategoryBean bean) throws Exception {

        RatioImageView ratioImageView = helper.getView(R.id.imageView2);
        ratioImageView.setUrl(bean.ico_url);
        helper.setText(R.id.tv_title,bean.title);
        helper.setText(R.id.tv_price,bean.remark);
        helper.getView(R.id.textView5).setVisibility(View.GONE);
        /*ratioImageView.setUrl(bean.focusImgUrl);

        helper.setText(R.id.tv_title,bean.productName);
        helper.setText(R.id.tv_price,"￥"+bean.salePrice);*/
    }

    public GoodsBean curDefaultAddress = null;

    @Override
    public boolean isOpenAutoBindView() {
        return true;
    }

    @Override
    public void onClick(View v) {
        GoodsBean GoodsBean = (GoodsBean) v.getTag(R.id.recycler_view_item_id);

    }

    /**
     * 地址按钮选择处理
     * @param GoodsBean
     */
    private void dealButtonSelect(GoodsBean GoodsBean) {
    }
}
