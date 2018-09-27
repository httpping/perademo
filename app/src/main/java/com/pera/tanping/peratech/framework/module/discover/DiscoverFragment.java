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

import android.content.Intent;
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
import com.pera.tanping.peratech.framework.remote.ApiManager;
import com.pera.tanping.peratech.framework.remote.config.RequestParam;
import com.pera.tanping.peratech.framework.remote.config.XGsonSubscriber;
import com.pera.tanping.peratech.framework.widget.MultiStatusView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 */
public class DiscoverFragment extends BaseListFragment implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener ,BaseQuickAdapter.OnItemClickListener {

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

        adapter.setOnLoadMoreListener(this,mRecyclerView);
        mSwipeRefresh.setOnRefreshListener(this);

        adapter.setOnItemClickListener(this);

        reqData(false);
    }

    private void reqData(boolean showDialog){
        RequestParam param = new RequestParam();
        param.put("pageindex", page);
        param.put("pagesize",DEFAULT_PAGE_SIZE);

        ApiManager.Api().getDaList(param.createRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XGsonSubscriber<NetResult<List<NewsBean>>>(getActivity(),showDialog) {
                    @Override
                    public void onSuccess(NetResult<List<NewsBean>> listNetResult) {
                            if (listNetResult.isSuccess()){
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


    public void createdData(List<NewsBean> newsBeans){

        if (page == 1){
            data.clear();
        }

        for (int i =0 ;i<newsBeans.size();i++){
            DiscoverBean bean = new DiscoverBean();
            bean.value = newsBeans.get(i);
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
        try {
            String url = data.get(position).value.link_url;
            Intent intent = new Intent(getActivity(),WebViewActivity.class);
            intent.putExtra(WebViewActivity.URL ,url);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
