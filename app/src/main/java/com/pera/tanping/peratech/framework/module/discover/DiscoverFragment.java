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

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.pera.tanping.peratech.R;
import com.pera.tanping.peratech.framework.base.BaseListFragment;
import com.pera.tanping.peratech.framework.remote.config.RequestParam;
import com.pera.tanping.peratech.framework.widget.MultiStatusView;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class DiscoverFragment extends BaseListFragment {

    private static final int DEFAULT_PAGE_SIZE = 20;
    public int isFromWhere ;
    private String addressId;
    static final int REQUEST_CODE_TO_LOGIN = 212;
    private DiscoverAdapter adapter;
    private List<DiscoverBean> data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentResId() {
        return R.layout.fragment_discover;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        multiStatusView.showContent();
        View emptyView = View.inflate(getContext(),R.layout.empty_address_layout,null);
        multiStatusView.add(MultiStatusView.VIEW_STATE_EMPTY,emptyView);

        data = new ArrayList<>();
        adapter = new DiscoverAdapter(this, data);
        adapter.setCurAddressId(addressId);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setBackgroundColor(getContext().getResources().getColor(R.color.color_f7f7f7));
        mRecyclerView.setAdapter(adapter);

        reqData(false);
    }

    private void reqData(boolean showDialog){
        RequestParam param = new RequestParam();
        param.put("page",1);
        param.put("page_size",DEFAULT_PAGE_SIZE);

        for (int i =0 ;i<10;i++){
            DiscoverBean bean = new DiscoverBean();
            data.add(bean);
        }

        adapter.notifyDataSetChanged();


      /*  ApiManager.addressApi().getAddressLits(param.createRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XGsonSubscriber<NetResult<AddressListBean>>(getContext(),showDialog) {

                    @Override
                    public void showDialog() {
                        super.showDialog();
                        if(!showDialog){
                            multiStatusView.showLoading();
                        }
                    }

                    @Override
                    public void onSuccess(NetResult<AddressListBean> bean) {
                        data.clear();
                        if(bean.isSuccess() && bean.result != null && bean.result.data != null && bean.result.data.size() > 0){
                            multiStatusView.showContent();
                            data.addAll(bean.result.data);
                            // 地址数量超过5条不显示添加按钮
                            rlAddress.setVisibility(data.size() >= 5 ? View.GONE : View.VISIBLE);
                        }else{
                            multiStatusView.showEmpty();
                        }
                        address.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        multiStatusView.showNoNetWork();
                    }
                });*/
    }


}
