package com.pera.tanping.peratech.framework.widget;
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
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pera.tanping.peratech.R;
import com.pera.tanping.peratech.framework.bean.goods.GoodsBean;
import com.pera.tanping.peratech.framework.event.UpdateGoodsPriceEvent;
import com.pera.tanping.peratech.framework.utils.DoubleFromat;
import com.pera.tanping.peratech.framework.utils.StringUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

/**
 * 项目名称: z
 * 类描述： 属性选择
 * 创建时间:2018/11/05 11:04
 *
 * @author tanping
 */
public class AttrSelectView extends LinearLayout {

    public TextView tvTitle;
    public EditText etContent;
    private String title;
    private String hintContent;

    TagFlowLayout tagFlowLayout;

    public AttrSelectView(Context context) {
        super(context);
        init();
    }

    public AttrSelectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init(){
        inflate(getContext(), R.layout.ll_attr_goods_view,this);
        tvTitle = findViewById(R.id.tv_title);
        tagFlowLayout =  findViewById(R.id.id_flowlayout);
    }

    GoodsBean.ProductattrlistBean productattrlistBean;
    GoodsBean.ProductattrlistBean.ListBean selectBean;
    public void updateView(GoodsBean.ProductattrlistBean productattrlistBean) {

        this.productattrlistBean = productattrlistBean;

        tvTitle.setText(productattrlistBean.AttrName);
        tagFlowLayout.setAdapter(new TagAdapter<GoodsBean.ProductattrlistBean.ListBean>(productattrlistBean.list) {
            @Override
            public View getView(FlowLayout parent, int position, GoodsBean.ProductattrlistBean.ListBean listBean) {
                TextView tv = (TextView)  LayoutInflater.from(getContext()).inflate(R.layout.flow_tv,null);
                String price = DoubleFromat.fromat(listBean.AttrPrice,2, RoundingMode.FLOOR);
                tv.setText(listBean.AttrValue+"("+price+")");
                return tv;
            }
        });

        tagFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {

                try {
                    if (selectPosSet !=null && selectPosSet.size()  >0){
                        Integer select = selectPosSet.iterator().next();
                        selectBean = productattrlistBean.list.get(select);
                    }else {
                        selectBean = null;
                    }
                    EventBus.getDefault().post(new UpdateGoodsPriceEvent());
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }


    public GoodsBean.ProductattrlistBean.ListBean getSelected(){
        return selectBean;
    }
}
