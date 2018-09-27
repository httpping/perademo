package com.pera.tanping.peratech.framework.module.goods;
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
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pera.tanping.peratech.R;
import com.pera.tanping.peratech.framework.base.BaseListFragment;
import com.pera.tanping.peratech.framework.base.NetResult;
import com.pera.tanping.peratech.framework.bean.news.NewsBean;
import com.pera.tanping.peratech.framework.bean.order.OrderBean;
import com.pera.tanping.peratech.framework.module.discover.DiscoverBean;
import com.pera.tanping.peratech.framework.module.goods.adapter.OrderBeanEnitity;
import com.pera.tanping.peratech.framework.module.goods.adapter.OrderListAdapter;
import com.pera.tanping.peratech.framework.remote.ApiManager;
import com.pera.tanping.peratech.framework.remote.config.RequestParam;
import com.pera.tanping.peratech.framework.remote.config.XGsonSubscriber;
import com.pera.tanping.peratech.framework.remote.model.LoginManager;
import com.pera.tanping.peratech.framework.widget.MultiStatusView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.pera.tanping.peratech.framework.module.goods.adapter.OrderBeanEnitity.TYPE_FOOTER;
import static com.pera.tanping.peratech.framework.module.goods.adapter.OrderBeanEnitity.TYPE_GOODS;
import static com.pera.tanping.peratech.framework.module.goods.adapter.OrderBeanEnitity.TYPE_HEADER;

/**
 */
public class OrderListFragment extends BaseListFragment  implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener ,BaseQuickAdapter.OnItemClickListener {

    private static final int DEFAULT_PAGE_SIZE = 20;
    public int isFromWhere ;
    private String addressId;
    static final int REQUEST_CODE_TO_LOGIN = 212;
    private OrderListAdapter adapter;
    private List<OrderBeanEnitity> data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        multiStatusView.showContent();
        View emptyView = View.inflate(getContext(),R.layout.empty_address_layout,null);
        multiStatusView.add(MultiStatusView.VIEW_STATE_EMPTY,emptyView);

        data = new ArrayList<>();
        adapter = new OrderListAdapter(this, data);
        adapter.setCurAddressId(addressId);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setBackgroundColor(getContext().getResources().getColor(R.color.color_f7f7f7));
        mRecyclerView.setAdapter(adapter);

        adapter.setOnLoadMoreListener(this,mRecyclerView);
        mSwipeRefresh.setOnRefreshListener(this);
        reqData(false);
    }

    private void reqData(boolean showDialog){
        RequestParam param = new RequestParam();
        param.put("pageindex", page);
        param.put("pagesize",DEFAULT_PAGE_SIZE);
        try {
            String userId = LoginManager.getInstance().getUser().id;
            param.put("userid",userId);
            param.put("userid",38);
            param.put("orderno","201809161459245924112");
        } catch (Exception e) {
            LoginManager.getInstance().toLogin(getActivity());
            e.printStackTrace();
        }
        ApiManager.Api().getOrderList(param.createRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XGsonSubscriber<NetResult<List<OrderBean>>>(getActivity(),showDialog) {
                    @Override
                    public void onSuccess(NetResult<List<OrderBean>> listNetResult) {
                        if (listNetResult.isSuccess()){
                            if (listNetResult.Data.size()==0){
                                adapter.loadMoreEnd();
                                return;
                            }
                            createdData(listNetResult.Data);
                            adapter.loadMoreComplete();
                        }else{
                            adapter.loadMoreEnd();
                        }
                    }


                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        adapter.loadMoreFail();
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();

                        try {
                            mSwipeRefresh.setRefreshing(false);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });



    }
    private void createdData(List<OrderBean> orderBeans) {


        if (page == 1){
            data.clear();
        }


        for (int i =0 ;i<orderBeans.size();i++){
            OrderBeanEnitity bean = new OrderBeanEnitity();
            bean.type = TYPE_HEADER;
            bean.value = orderBeans.get(i);
            data.add(bean);

            bean = new OrderBeanEnitity();
            bean.type = TYPE_GOODS;
            bean.value = orderBeans.get(i);
            data.add(bean);


            bean = new OrderBeanEnitity();
            bean.type = TYPE_FOOTER;
            bean.value = orderBeans.get(i);
            data.add(bean);
        }




        if (page ==1){
            adapter.setNewData(data);
        }else {
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onRefresh() {
        page = 1 ;
        reqData(false);
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        reqData(false);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
