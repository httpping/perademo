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

import com.pera.tanping.peratech.R;
import com.pera.tanping.peratech.framework.base.BaseActivity;
import com.pera.tanping.peratech.framework.module.address.AddressConstants;
import com.pera.tanping.peratech.framework.module.goods.adapter.FilterDataAdapter;
import com.pera.tanping.peratech.framework.module.goods.adapter.FilterDataEnitity;
import com.pera.tanping.peratech.framework.remote.config.Constants;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.pera.tanping.peratech.framework.module.address.AddressConstants.EnterAddress.checkout;


/**
 */
public class GoodsListActivity extends BaseActivity {

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


    GoodsCategoryFragment goodsCategoryFragment;

    @Override
    public Fragment getContentFragment() {
        getExtraData();
        GoodsCategoryFragment categoryFragment = new GoodsCategoryFragment();
        Bundle bundle = new Bundle();
        categoryFragment.setArguments(bundle);
        goodsCategoryFragment = categoryFragment;
        return categoryFragment;
    }

    @Override
    public boolean isDefaultContentView() {
        return false;
    }

    private void getExtraData() {
        if (getIntent() != null) {
            isSelectAddress = getIntent().getIntExtra(AddressConstants.FROM_WHERE_TO_ADDRESS, 0);
            addressId = getIntent().getStringExtra(Constants.ADDRESS_ID);
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
        getContentFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,goodsCategoryFragment).commit();


        filterData();
    }

    /**
     * 过滤数据
     */
    public void filterData(){

        List<FilterDataEnitity> data = new LinkedList<>();
        for (int i=0;i<10;i++){
            FilterDataEnitity enitity = new FilterDataEnitity();
            enitity.type = FilterDataEnitity.FILTER_DATA;
            data.add(enitity);

        }

        FilterDataAdapter adapter = new FilterDataAdapter(data);
        rvRight.setLayoutManager(new LinearLayoutManager(this));
        rvRight.setAdapter(adapter);
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


}
