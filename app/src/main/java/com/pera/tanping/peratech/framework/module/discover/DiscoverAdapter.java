package com.pera.tanping.peratech.framework.module.discover;
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
import com.pera.tanping.peratech.GlideApp;
import com.pera.tanping.peratech.R;
import com.pera.tanping.peratech.framework.bean.news.NewsBean;
import com.pera.tanping.peratech.framework.widget.RatioImageView;

import java.util.List;

/**
 */
public class DiscoverAdapter extends GBBaseBindAdapter<DiscoverBean,BaseViewHolder> implements View.OnClickListener {

    private DiscoverFragment fragment;
    private Context mContext;
    private String curSelAddressId;

    public DiscoverAdapter(DiscoverFragment fragment, List<DiscoverBean> data) {
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
    @BindItem(type = 0,layout = R.layout.item_discover)
    public void one(BaseViewHolder helper, DiscoverBean bean) throws Exception {
        RatioImageView imageView = helper.getView(R.id.imageView);
        NewsBean newsBean = bean.value;
        imageView.setUrl(newsBean.img_url);
        helper.setText(R.id.tv_title,newsBean.title);
//        helper.setText(R.id.tv_desc,newsBean.title);
    }

    public DiscoverBean curDefaultAddress = null;

    @Override
    public boolean isOpenAutoBindView() {
        return true;
    }

    @Override
    public void onClick(View v) {
        DiscoverBean GoodsBean = (DiscoverBean) v.getTag(R.id.recycler_view_item_id);

    }

    /**
     * 地址按钮选择处理
     * @param GoodsBean
     */
    private void dealButtonSelect(DiscoverBean GoodsBean) {

    }
}
