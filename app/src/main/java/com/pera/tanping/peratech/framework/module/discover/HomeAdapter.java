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
import com.pera.tanping.peratech.R;

import java.util.List;

import static com.pera.tanping.peratech.framework.module.discover.HomeBeanEnitiy.TYPE_CATEGORY;
import static com.pera.tanping.peratech.framework.module.discover.HomeBeanEnitiy.TYPE_HEADER;

/**
 */
public class HomeAdapter extends GBBaseBindAdapter<HomeBeanEnitiy,BaseViewHolder> implements View.OnClickListener {

    private HomeFragment fragment;

    public HomeAdapter(HomeFragment fragment, List<HomeBeanEnitiy> data) {
        super(data);
        this.fragment = fragment;
        this.mContext = this.fragment.getContext();
    }

    /**
     * @param helper
     * @param bean
     * @throws Exception
     */
    @BindItem(type = TYPE_HEADER,layout = R.layout.item_home_title)
    public void one(BaseViewHolder helper, HomeBeanEnitiy bean) throws Exception {

    }

    @BindItem(type = TYPE_CATEGORY,layout = R.layout.item_home_category)
    public void category(BaseViewHolder helper, HomeBeanEnitiy bean) throws Exception {

    }

    @Override
    public void onClick(View v) {

    }
}
