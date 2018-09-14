package com.pera.tanping.peratech.framework.module.user;
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

import java.util.List;

/**
 */
public class GoodsListAdapter extends GBBaseBindAdapter<GoodsBean,BaseViewHolder> implements View.OnClickListener {

    private MyAcountFragment fragment;
    private Context mContext;
    private String curSelAddressId;

    public GoodsListAdapter(MyAcountFragment fragment, List<GoodsBean> data) {
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
    public void one(BaseViewHolder helper, GoodsBean bean) throws Exception {

    }

    public GoodsBean curDefaultAddress = null;

    @Override
    public boolean isOpenAutoBindView() {
        return true;
    }

    @Override
    public void onClick(View v) {
        GoodsBean GoodsBean = (com.pera.tanping.peratech.framework.module.user.GoodsBean) v.getTag(R.id.recycler_view_item_id);

    }

    /**
     * 地址按钮选择处理
     * @param GoodsBean
     */
    private void dealButtonSelect(GoodsBean GoodsBean) {
    }
}
