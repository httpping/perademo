package com.pera.tanping.peratech.framework.widget;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;


import com.pera.tanping.peratech.R;

import java.util.ArrayList;
import java.util.List;

/**
 *  可以做各种神奇适配的view
 * author tanping
 */
public class AutoWeightLinearLayoutView extends LinearLayout {


    public List<View> views = new ArrayList<>();
  
    public AutoWeightLinearLayoutView(Context context) {
        super(context);
        init();
    }  
  
    public AutoWeightLinearLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }  

    public void init(){
        setOrientation(HORIZONTAL);
    }


    /**
     * 更新操作
     * @param data
     * @param onUpdateBindView
     */
    public  void updateView(List data, OnUpdateBindView onUpdateBindView){
        if (data == null || onUpdateBindView == null){
            return;
        }

        try {
            if (data.size()> views.size()){
                int count = data.size() - views.size();
                for (int i =0 ;i<count;i++){
                    RatioImageView iv = new RatioImageView(getContext());
                    views.add(iv);
                }
            }

             createView(data,onUpdateBindView);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 创建view
     * @param data
     * @param onUpdateBindView
     */
    private void createView(List data, OnUpdateBindView onUpdateBindView){
        removeAllViews();

        int countW = 0;
        for (int i=0;i<data.size();i++){
            countW += onUpdateBindView.getWidth(data.get(i));
        }
        int height = onUpdateBindView.getHight(data.get(0));

        if (height == 0){
            height = 1;
        }
        float wh = (float) (countW*1.0/height);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int scWidth = dm.widthPixels;
        height = (int)(scWidth/wh);


        //超大高度
        if (height> dm.heightPixels*2){
            height = dm.heightPixels;
        }
//            getLayoutParams().height = height;
        //设置比例
        for (int i =0;i<data.size();i++){
            View iv = views.get(i);
            addView(iv);
            int weight = onUpdateBindView.getWidth(data.get(i));
            LayoutParams params = (LayoutParams) iv.getLayoutParams();
            params.weight = weight;
            params.width = 0;
            params.height = height;

            iv.setTag(R.id.recycler_view_item_id,data.get(i));
            iv.setOnClickListener(onUpdateBindView);
        }

        //刷数据
        for (int i = 0; i< views.size(); i++) {
            View iv = views.get(i);
            onUpdateBindView.update(iv,data.get(i));
        }
    }


    /**
            * 更新操作
     * @param data
     * @param onUpdateBindView
     */
    public  void updateView(List data, @LayoutRes int layout, OnUpdateBindView onUpdateBindView){
        if (data == null || onUpdateBindView == null){
            return;
        }

        try {
            if (data.size()> views.size()){
                int count = data.size() - views.size();
                for (int i =0 ;i<count;i++){
                    View iv = View.inflate(getContext(), layout,null);
                    views.add(iv);
                }
            }

            createView(data,onUpdateBindView);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    /**
     * 与view分离器
     */
    public interface  OnUpdateBindView<T,V> extends OnClickListener {

          void  update(V iv, T data);
          int getWidth(T data);
          int getHight(T data);

    }
}  