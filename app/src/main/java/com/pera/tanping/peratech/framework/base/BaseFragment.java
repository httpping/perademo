package com.pera.tanping.peratech.framework.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pera.tanping.peratech.R;
import com.pera.tanping.peratech.framework.utils.klog.KLog;
import com.pera.tanping.peratech.framework.widget.MultiStatusView;

import org.greenrobot.eventbus.EventBus;


/**
 * 针对当前APP的一个基础的Fragment，可以在这个类中配置数据分析、bug统计等全局配置
 *
 * @author tanping
 * @version 1.0
 * @date 2018/04/03
 * @since 1.0
 */
public abstract class BaseFragment extends Fragment {

    public MultiStatusView multiStatusView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //注册eventbus
        if (isRegisterEventBus()){
            try {
                EventBus.getDefault().register(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          changeAppLanguage();
          multiStatusView = new MultiStatusView(getActivity());
          int layoutId =  getContentResId();
          multiStatusView.add(MultiStatusView.VIEW_STATE_CONTENT,layoutId);
          multiStatusView.add(MultiStatusView.VIEW_STATE_EMPTY,getDefaultEmptyResId());

          return  multiStatusView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        changeAppLanguage();
    }

    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
        KLog.d("Lock:" + this.getClass().getSimpleName() + ">>>>>onResume>>>>>>>");
    }

    @CallSuper
    @Override
    public void onPause() {
        super.onPause();
        KLog.d("Lock:" + this.getClass().getSimpleName() + ">>>>>onPause>>>>>>>");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (isRegisterEventBus()){
            try {
                EventBus.getDefault().unregister(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public abstract @LayoutRes
    int getContentResId();

    public  @LayoutRes
    int getDefaultEmptyResId(){
        return R.layout.base_view_empty_default;
    }

    /**
     * 是否注册eventbus 默认不注册
     * @return
     */
    public boolean isRegisterEventBus(){
        return false ;
    }
    /**
     * 更改app语言包
     *
     * @author dingpeihua
     * @date 2017/2/10 15:42
     * @version 1.0
     */
    void changeAppLanguage() {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 通过ID查找控件
     *
     * @param viewId 控件资源ID
     * @param <VIEW> 泛型参数，查找得到的View
     * @return 找到了返回控件对象，否则返回null
     */
    final public <VIEW extends View> VIEW findViewById(@IdRes int viewId) {
        return (VIEW) multiStatusView.findView(viewId);
    }

    /**
     * 发送消息,用于各个组件之间通信
     *
     * @param event 消息事件对象
     */
    public final <EVENT> void sendEventMessage(@NonNull EVENT event) {
        // 发布事件
        EventBus.getDefault().post(event);
    }
}