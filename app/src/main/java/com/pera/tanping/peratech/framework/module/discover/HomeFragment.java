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
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pera.tanping.peratech.GlideApp;
import com.pera.tanping.peratech.R;
import com.pera.tanping.peratech.framework.base.BaseListFragment;
import com.pera.tanping.peratech.framework.base.NetResult;
import com.pera.tanping.peratech.framework.bean.IndexCategoryBean;
import com.pera.tanping.peratech.framework.bean.news.NewsBean;
import com.pera.tanping.peratech.framework.module.goods.GoodsListActivity;
import com.pera.tanping.peratech.framework.remote.ApiManager;
import com.pera.tanping.peratech.framework.remote.config.RequestParam;
import com.pera.tanping.peratech.framework.remote.config.XGsonSubscriber;
import com.pera.tanping.peratech.framework.utils.ScreenUtil;
import com.pera.tanping.peratech.framework.widget.MultiStatusView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.pera.tanping.peratech.framework.module.discover.HomeBeanEnitiy.TYPE_CATEGORY;
import static com.pera.tanping.peratech.framework.module.discover.HomeBeanEnitiy.TYPE_HEADER;

/**
 */
public class HomeFragment extends BaseListFragment implements BaseQuickAdapter.OnItemClickListener{

    private static final int DEFAULT_PAGE_SIZE = 20;
    private HomeAdapter adapter;
    private List<HomeBeanEnitiy> data;



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
        adapter = new HomeAdapter(this, data);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        adapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                if (adapter.getData().get(position).type == TYPE_CATEGORY){
                    return 1;
                }
                return 4;
            }
        });
        mRecyclerView.setBackgroundColor(getContext().getResources().getColor(R.color.color_f7f7f7));
        mRecyclerView.setAdapter(adapter);
//        createBanner();

        adapter.setOnItemClickListener(this);
        mSwipeRefresh.setEnabled(false);
        reqBannerData(false);
        requestCategroy(true);
    }


    public void createBanner(List<NewsBean> newsBeans){

        Banner banner = new Banner(getContext());

        //设置图片加载器
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                NewsBean newsBean = (NewsBean) path;
                GlideApp.with(getContext().getApplicationContext()).asBitmap().load(newsBean.img_url).into(imageView);
            }
        });

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                try {
                    NewsBean newsBean = newsBeans.get(position);
                    String url = newsBean.link_url;
                    Intent intent = new Intent(getActivity(),WebViewActivity.class);
                    intent.putExtra(WebViewActivity.URL ,url);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        List<String> titles = new ArrayList<>();
        for (int i=0;i<newsBeans.size();i++){
            titles.add(newsBeans.get(i).title);
        }

        //设置图片集合
        banner.setImages(newsBeans);
        banner.setBannerTitles(titles);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        adapter.addHeaderView(banner);

        banner.getLayoutParams().height = ScreenUtil.getScreenWidth(getContext())*3/4;
    }

    private void reqBannerData(boolean showDialog){
        RequestParam param = new RequestParam();
        param.put("pageindex", page);
        param.put("pagesize",5);
        ApiManager.Api().getDaList(param.createRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XGsonSubscriber<NetResult<List<NewsBean>>>(getActivity(),showDialog) {
                    @Override
                    public void onSuccess(NetResult<List<NewsBean>> listNetResult) {
                        if (listNetResult.isSuccess()){
                            createBanner(listNetResult.Data);
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

    private void requestCategroy(boolean showDialog){
        RequestParam param = new RequestParam();
        ApiManager.Api().getCategoryList(param.createRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XGsonSubscriber<NetResult<List<IndexCategoryBean>>>(getActivity(),showDialog) {
                    @Override
                    public void onSuccess(NetResult<List<IndexCategoryBean>> listNetResult) {
                        if (listNetResult.isSuccess()){
                            showIndexCategory(listNetResult.Data);
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





    private void showIndexCategory(List<IndexCategoryBean> indexCategoryBeans){
        HomeBeanEnitiy bean = new HomeBeanEnitiy();
        bean.type = TYPE_HEADER;
        data.add(bean);
        for (int i =0 ;i<indexCategoryBeans.size();i++){
            bean = new HomeBeanEnitiy();
            bean.type = TYPE_CATEGORY;
            bean.value = indexCategoryBeans.get(i);
            data.add(bean);

        }

        adapter.notifyDataSetChanged();

    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            if (data.get(position).type == TYPE_CATEGORY){
                IndexCategoryBean indexCategoryBean = (IndexCategoryBean) data.get(position).value;
                Intent intent = new Intent(getActivity(), GoodsListActivity.class);
                intent.putExtra(GoodsListActivity.CATEGORY_ID,indexCategoryBean.id+"");
                intent.putExtra(GoodsListActivity.CATEGORY_NAME,indexCategoryBean.title+"");
                startActivity(intent);
            }
    }
}
