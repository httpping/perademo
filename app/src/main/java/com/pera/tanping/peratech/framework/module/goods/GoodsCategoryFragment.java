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
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.pera.tanping.peratech.R;
import com.pera.tanping.peratech.framework.base.BaseFragment;
import com.pera.tanping.peratech.framework.module.goods.adapter.BaseFragmentStatePagerAdapter;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 类描述：
 * 创建时间:2018/9/8 11:54
 *
 * @author tanping
 */
public class GoodsCategoryFragment extends BaseFragment {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    Unbinder unbinder;
    private BaseFragmentStatePagerAdapter adapter;

    @Override
    public int getContentResId() {
        return R.layout.fragment_goods_cartegory;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        multiStatusView.showContent();
        unbinder = ButterKnife.bind(this, multiStatusView);


        adapter = new BaseFragmentStatePagerAdapter(getChildFragmentManager());
        // 让它每次只加载一个Fragment，ViewPager.setOffscreenPageLimit(int limit)，其中参数可以设为0或者1，参数小于1时，会默认用1来作为参数，未设置之前，ViewPager会默认加载两个Fragment。
        // 关闭预加载，默认一次只加载一个Fragment
        viewpager.setOffscreenPageLimit(1);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);

        createFragments();
    }


    List<Fragment> fragments ;
    public void createFragments(){
        fragments  =new LinkedList<>();
        for (int i= 0; i <10; i++){
            GoodsListFragment goodsListFragment = new GoodsListFragment();
            goodsListFragment.title = "title:" + i;
            fragments.add(goodsListFragment);
        }

        adapter.setFragments(fragments);
        adapter.notifyDataSetChanged();
    }

}
