package com.pera.tanping.peratech;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.pera.tanping.peratech.framework.base.BaseFragment;
import com.pera.tanping.peratech.framework.module.discover.DiscoverFragment;
import com.pera.tanping.peratech.framework.module.discover.HomeFragment;
import com.pera.tanping.peratech.framework.remote.model.AddressManager;
import com.tp.cache.CacheManager;

import java.util.ArrayList;
import java.util.List;

import library.BottomBarItem;
import library.BottomBarLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVpContent;
    private BottomBarLayout mBottomBarLayout;

    private List<BaseFragment> mFragmentList = new ArrayList<>();
    private RotateAnimation mRotateAnimation;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        AddressManager.getInstance().readAddress(this,false);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        mVpContent = (ViewPager) findViewById(R.id.vp_content);
        mBottomBarLayout = (BottomBarLayout) findViewById(R.id.bbl);
    }

    private void initData() {

        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString(TabFragment.CONTENT,"首页");
        homeFragment.setArguments(bundle1);
        mFragmentList.add(homeFragment);


        DiscoverFragment microFragment = new DiscoverFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putString(TabFragment.CONTENT,"发现");
        microFragment.setArguments(bundle3);
        mFragmentList.add(microFragment);

        MyAccountFragment meFragment = new MyAccountFragment();
        Bundle bundle4 = new Bundle();
        bundle4.putString(TabFragment.CONTENT,"我的");
        meFragment.setArguments(bundle4);
        mFragmentList.add(meFragment);
    }

    private void initListener() {
        mVpContent.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mBottomBarLayout.setViewPager(mVpContent);
        mBottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {

            @Override
            public void onItemSelected(final BottomBarItem bottomBarItem, int previousPosition, final int currentPosition) {
                Log.i("MainActivity","position: " + currentPosition);
                if (currentPosition == 0){
                    //如果是第一个，即首页
                    if (previousPosition == currentPosition){
                        //如果是在原来位置上点击,更换首页图标并播放旋转动画
                        //更换成加载图标
                        bottomBarItem.setIconSelectedResourceId(R.mipmap.tab_loading);
                        bottomBarItem.setStatus(true);

                        //播放旋转动画
                        if (mRotateAnimation == null) {
                            mRotateAnimation = new RotateAnimation(0, 360,
                                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                                    0.5f);
                            mRotateAnimation.setDuration(800);
                            mRotateAnimation.setRepeatCount(-1);
                        }
                        ImageView bottomImageView = bottomBarItem.getImageView();
                        bottomImageView.setAnimation(mRotateAnimation);
                        //播放旋转动画
                        bottomImageView.startAnimation(mRotateAnimation);

                        //模拟数据刷新完毕
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //是否还停留在当前页签
                                boolean tabNotChanged = mBottomBarLayout.getCurrentItem() == currentPosition;
                                //更换成首页原来选中图标
                                bottomBarItem.setIconSelectedResourceId(R.mipmap.tab_home_selected1);
                                //刷新图标
                                bottomBarItem.setStatus(tabNotChanged);
                                cancelTabLoading(bottomBarItem);
                            }
                        },3000);
                        return;
                    }
                }

                //如果点击了其他条目
                BottomBarItem bottomItem = mBottomBarLayout.getBottomItem(0);
                //更换为原来的图标
                bottomItem.setIconSelectedResourceId(R.mipmap.tab_home_selected1);
                //停止旋转动画
                cancelTabLoading(bottomItem);
            }
        });
        //设置第一个页签的未读数为20
//        mBottomBarLayout.setUnread(0,20);
        //设置第二个页签的未读数
//        mBottomBarLayout.setUnread(1,1001);
        //设置第三个页签显示提示的小红点
//        mBottomBarLayout.showNotify(2);
        //设置第四个页签显示NEW提示文字
//        mBottomBarLayout.setMsg(3,"NEW");
    }

    /**停止首页页签的旋转动画*/
    private void cancelTabLoading(BottomBarItem bottomItem) {
        Animation animation = bottomItem.getImageView().getAnimation();
        if (animation != null){
            animation.cancel();
        }
    }

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }
}
