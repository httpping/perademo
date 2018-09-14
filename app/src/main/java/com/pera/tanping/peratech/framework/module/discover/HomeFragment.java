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
import com.pera.tanping.peratech.framework.remote.config.RequestParam;
import com.pera.tanping.peratech.framework.utils.ScreenUtil;
import com.pera.tanping.peratech.framework.widget.MultiStatusView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import static com.pera.tanping.peratech.framework.module.discover.HomeBeanEnitiy.TYPE_CATEGORY;
import static com.pera.tanping.peratech.framework.module.discover.HomeBeanEnitiy.TYPE_HEADER;

/**
 */
public class HomeFragment extends BaseListFragment {

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
        createBanner();

        reqData(false);
    }


    public void createBanner(){

        Banner banner = new Banner(getContext());
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                BannerBean bannerBean = (BannerBean) path;
                GlideApp.with(getContext().getApplicationContext()).asBitmap().load(bannerBean.url).into(imageView);
            }
        });

        List<BannerBean> bannerBeans = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (int i=0;i<5;i++){
            BannerBean bannerBean = new BannerBean();
            bannerBean.url = "https://f11.baidu.com/it/u=2465775762,1509670197&fm=72";
            bannerBeans.add(bannerBean);

            titles.add("i");
        }

        //设置图片集合
        banner.setImages(bannerBeans);
        banner.setBannerTitles(titles);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        adapter.addHeaderView(banner);

        banner.getLayoutParams().height = ScreenUtil.getScreenWidth(getContext())/2;
    }


    private void reqData(boolean showDialog){
        RequestParam param = new RequestParam();
        param.put("page",1);
        param.put("page_size",DEFAULT_PAGE_SIZE);

        for (int i =0 ;i<10;i++){
            HomeBeanEnitiy bean = new HomeBeanEnitiy();

            bean.type = TYPE_HEADER;
            data.add(bean);


            for (int j=0;j<8;j++){
                bean = new HomeBeanEnitiy();
                bean.type = TYPE_CATEGORY;
                data.add(bean);
            }
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
