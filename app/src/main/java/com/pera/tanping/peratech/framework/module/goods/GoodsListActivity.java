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
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pera.tanping.peratech.R;
import com.pera.tanping.peratech.framework.base.BaseActivity;
import com.pera.tanping.peratech.framework.base.NetResult;
import com.pera.tanping.peratech.framework.bean.IndexCategoryBean;
import com.pera.tanping.peratech.framework.bean.goods.BrandBean;
import com.pera.tanping.peratech.framework.module.address.AddressConstants;
import com.pera.tanping.peratech.framework.module.goods.adapter.FilterDataAdapter;
import com.pera.tanping.peratech.framework.module.goods.adapter.FilterDataEnitity;
import com.pera.tanping.peratech.framework.remote.ApiManager;
import com.pera.tanping.peratech.framework.remote.config.Constants;
import com.pera.tanping.peratech.framework.remote.config.RequestParam;
import com.pera.tanping.peratech.framework.remote.config.XGsonSubscriber;
import com.pera.tanping.peratech.framework.utils.StringUtil;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.pera.tanping.peratech.framework.module.address.AddressConstants.EnterAddress.checkout;


/**
 */
public class GoodsListActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;
    @BindView(R.id.rv_right)
    RecyclerView rvRight;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private int isSelectAddress;
    private String addressId;

    public static String CATEGORY_ID ="category_id";
    public static String CATEGORY_NAME ="category_name";


    //品牌过滤
    public boolean brandFiflter ;

    GoodsListFragment goodsListFragment;
    private FilterDataAdapter filterDataAdapter;

    @Override
    public Fragment getContentFragment() {
        getExtraData();
        goodsListFragment = new GoodsListFragment();
        Bundle bundle = new Bundle();
        goodsListFragment.setArguments(bundle);
        return goodsListFragment;
    }

    @Override
    public boolean isDefaultContentView() {
        return false;
    }

    private void getExtraData() {
        if (getIntent() != null) {
            isSelectAddress = getIntent().getIntExtra(AddressConstants.FROM_WHERE_TO_ADDRESS, 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_goods_list);
        ButterKnife.bind(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onToolbarNavigationClick();
            }
        });

        String category =  getIntent().getStringExtra(CATEGORY_ID);
        getContentFragment();
        goodsListFragment.categoryid = category;

        String categoryName =  getIntent().getStringExtra(CATEGORY_NAME);
        if (StringUtil.isEmpty(categoryName)) {
            toolbar.setTitle("商品列表");
        }else {
            toolbar.setTitle(categoryName);
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,goodsListFragment).commit();


        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);


        getBrandList(false);
    }

    /**
     * 过滤数据
     */
    public void filterData(List<BrandBean> brandBeans){

        List<FilterDataEnitity> data = new LinkedList<>();
        for (int i=0;i<brandBeans.size();i++){
            FilterDataEnitity enitity = new FilterDataEnitity();
            enitity.type = FilterDataEnitity.FILTER_DATA;
            enitity.value = brandBeans.get(i);
            data.add(enitity);

        }

        filterDataAdapter = new FilterDataAdapter(data);
        filterDataAdapter.setOnItemClickListener(this);
        rvRight.setLayoutManager(new LinearLayoutManager(this));
        rvRight.setAdapter(filterDataAdapter);
        brandFiflter =true;

    }

    @Override
    public void onToolbarNavigationClick() {
        super.onToolbarNavigationClick();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final int menuId = R.menu.goods_list;
        if (menuId > 0) {
            getMenuInflater().inflate(menuId, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            default:
                break;
        }
        return true;
    }





    private void getBrandList(boolean showDialog){
        RequestParam param = new RequestParam();
//        param.put("pid",categoryid);
        ApiManager.Api().getBrandList(param.createRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XGsonSubscriber<NetResult<List<BrandBean>>>(this,showDialog) {
                    @Override
                    public void onSuccess(NetResult<List<BrandBean>> listNetResult) {
                        if (listNetResult.isSuccess()){
                            filterData(listNetResult.Data);
                        }else{
                        }
                    }


                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();

                    }
                });


    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        FilterDataEnitity filterDataEnitity = filterDataAdapter.getData().get(position);
        BrandBean brandBean = (BrandBean) filterDataEnitity.value;

        goodsListFragment.catalogid = brandBean.id+"";

        goodsListFragment.onRefresh();

        drawerLayout.closeDrawers();
    }
}
