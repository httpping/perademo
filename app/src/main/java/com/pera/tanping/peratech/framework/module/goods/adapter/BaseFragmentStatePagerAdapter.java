package com.pera.tanping.peratech.framework.module.goods.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import com.pera.tanping.peratech.framework.utils.StringUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 一个通用的Fragment适配器
 *
 */
public final class BaseFragmentStatePagerAdapter extends android.support.v4.app.FragmentStatePagerAdapter {
    //反射的方法名
    private static final String GET_ITEM_TITLE_TEXT = "getItemTitleText";
    /**
     * The m fragment list.
     */
    private List<Fragment> fragments;
    private List<String> fragmentTitles;

    /**
     * Instantiates a new ab fragment pager adapter.
     *
     * @param fragmentManager the m fragment manager
     * @param fragments       the fragment list
     */
    public BaseFragmentStatePagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull List<Fragment> fragments) {
        super(fragmentManager);
        this.fragments = fragments;
    }

    public BaseFragmentStatePagerAdapter(@NonNull FragmentManager fragmentManager) {
        super(fragmentManager);
        fragments = new ArrayList<>();
    }

    public void setFragments(@NonNull List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public Fragment getFragmentByPosition(int position) {
        return fragments.get(position);
    }

    public void setFragments(@NonNull List<Fragment> fragments, List<String> fragmentTitles) {
        this.fragments = fragments;
        this.fragmentTitles = fragmentTitles;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    /**
     * 描述：获取数量.
     *
     * @return the count
     * @see android.support.v4.view.PagerAdapter#getCount()
     */
    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    /**
     * 描述：获取索引位置的Fragment.
     *
     * @param position the position
     * @return the item
     * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
     */
    @Override
    public Fragment getItem(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                return fragments.get(position);
            } else {
                return fragments.get(0);
            }
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (fragmentTitles != null && !fragmentTitles.isEmpty()) {
            return fragmentTitles.get(position);
        }
        return fragments != null && position < fragments.size() ? getContentText(fragments.get(position)) : "";
    }

    public void addFragment(@NonNull Fragment fragment) {
        if (fragments != null && fragment != null) {
//            if (!fragments.contains(fragment))
            fragments.add(fragment);
        }
    }

    public void addFragment(@NonNull Fragment fragment, @NonNull String title) {
        addFragment(fragment);
        if (fragmentTitles == null) {
            fragmentTitles = new ArrayList<>();
        }
        if (fragmentTitles != null && !StringUtil.isEmpty(title)) {
            // 不用判断是否重复，会造成get的时候造成长度不一致问题
            //  if (!fragmentTitles.contains(title))
            fragmentTitles.add(title);
        }
    }


    /**
     * 清空所有的标题和Fragment
     */
    public void clear() {
        if (fragmentTitles != null) {
            fragmentTitles.clear();
        }
        if (fragments != null) {
            fragments.clear();
        }
    }

    /**
     * 根据传进来的对象反射出getItemViewText()方法，来获取需要显示的值
     *
     * @param item
     * @return
     */
    public String getContentText(Object item) {
        String contentText = item.toString();
        try {
            if (item instanceof IViewPageTitleListener) {
                IViewPageTitleListener listener = (IViewPageTitleListener) item;
                return listener.getFragmentTitleText();
            } else if (item instanceof Fragment) {
                contentText = item.getClass().getSimpleName();
            }
            Class<?> clz = item.getClass();
            Method m = clz.getMethod(GET_ITEM_TITLE_TEXT);
            contentText = m.invoke(item, new Object[0]).toString();
        } catch (Exception ignored) {
        }
        return contentText;
    }


    public interface IViewPageTitleListener {
        String getFragmentTitleText();
    }

}