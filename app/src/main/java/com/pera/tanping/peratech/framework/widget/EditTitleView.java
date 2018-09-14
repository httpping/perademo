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
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pera.tanping.peratech.R;
import com.pera.tanping.peratech.framework.utils.StringUtil;

/**
 * 项目名称: z
 * 类描述：
 * 创建时间:2018/9/2 11:04
 *
 * @author tanping
 */
public class EditTitleView extends LinearLayout {

    public TextView tvTitle;
    public EditText etContent;
    private String title;
    private String hintContent;

    public EditTitleView(Context context) {
        super(context);
    }

    public EditTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditTitleView);
        title = typedArray.getString(R.styleable.EditTitleView_tv_title);
        hintContent = typedArray.getString(R.styleable.EditTitleView_et_content);
        typedArray.recycle();
        init();

    }



    private void init(){
        inflate(getContext(), R.layout.item_edit_view,this);
        tvTitle = findViewById(R.id.tv_title);
        etContent = findViewById(R.id.et_edit);

        tvTitle.setText(title);
        etContent.setHint(hintContent);
    }

    public String getDefaultError(){
        if (StringUtil.isEmpty(etContent.getText().toString())){
            return title + getContext().getString(R.string.et_error_tip0);
        }

        return null;
    }
}
