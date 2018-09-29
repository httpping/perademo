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
import com.pera.tanping.peratech.framework.bean.goods.GoodsBean;
import com.pera.tanping.peratech.framework.module.goods.adapter.BaseFragmentStatePagerAdapter;
import com.pera.tanping.peratech.framework.module.goods.adapter.GoodsListAdapter;
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
public class GoodsListFragment extends BaseListFragment implements BaseFragmentStatePagerAdapter.IViewPageTitleListener,BaseQuickAdapter.RequestLoadMoreListener ,SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.OnItemClickListener {

    private static final int DEFAULT_PAGE_SIZE = 20;
    public int isFromWhere ;
    private String addressId;
    static final int REQUEST_CODE_TO_LOGIN = 212;
    private GoodsListAdapter adapter;
    private List<GoodsBean> data;
    public String title;
    //非必填，产品类目ID
    public String categoryid;
    //非必填，产品品牌id
    public String catalogid;

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
        adapter = new GoodsListAdapter(this, data);
        adapter.setCurAddressId(addressId);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setBackgroundColor(getContext().getResources().getColor(R.color.color_f7f7f7));
        mRecyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this ,mRecyclerView);
        adapter.setOnItemClickListener(this);
        mSwipeRefresh.setOnRefreshListener(this);
        getGoodsList(true);
    }


    private void getGoodsList(boolean showDialog){
        RequestParam param = new RequestParam();
        param.put("categoryid",categoryid);
        param.put("catalogid",catalogid);
        param.put("pageindex", page);
        param.put("pagesize",pageSize);
        ApiManager.Api().getProductList(param.createRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XGsonSubscriber<NetResult<List<GoodsBean>>>(getActivity(),showDialog) {
                    @Override
                    public void onSuccess(NetResult<List<GoodsBean>> listNetResult) {
                        if (page == 1){
                            data.clear();
                        }
                        if (listNetResult.isSuccess()){
                            filterData(listNetResult.Data);
                            adapter.loadMoreComplete();
                        }else{
                            adapter.notifyDataSetChanged();
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


    private void filterData(List<GoodsBean> goodsBeans){

        if (page == 1){
            data.clear();
        }
        data.addAll(goodsBeans);

        adapter.notifyDataSetChanged();

    }




    @Override
    public String getFragmentTitleText() {
        return title;
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        getGoodsList(false);
    }

    @Override
    public void onRefresh() {
        page = 1;
        getGoodsList(false);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
           try {
                GoodsBean  goodsBean = this.adapter.getData().get(position);
               Intent intent = new Intent(getContext(),GoodsDetaillActivity.class);
               intent.putExtra(GoodsDetaillActivity.PRODUCT_ID,goodsBean.id+"");
               startActivity(intent);
           }catch (Exception e){
               e.printStackTrace();
           }
    }
}
