package com.pera.tanping.peratech.framework.module.address;
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
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ScrollView;

import com.pera.tanping.peratech.R;
import com.pera.tanping.peratech.framework.base.BaseFragment;
import com.pera.tanping.peratech.framework.widget.EditTitleView;
import com.utils.ui.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 */
public class EditAddressFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.ll_account_name)
    EditTitleView llAccountName;
    @BindView(R.id.ll_account_phone)
    EditTitleView llAccountPhone;
    @BindView(R.id.ll_account_area)
    EditTitleView llAccountArea;
    @BindView(R.id.ll_account_detail)
    EditTitleView llAccountDetail;
    @BindView(R.id.sv_content)
    ScrollView svContent;
    @BindView(R.id.bt_save)
    Button btSave;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        multiStatusView.showContent();
        unbinder = ButterKnife.bind(this, multiStatusView);

        llAccountPhone.etContent.setInputType(EditorInfo.TYPE_CLASS_PHONE);

        btSave.setOnClickListener(this);

        reqData(false);
    }

    @Override
    public int getContentResId() {
        return R.layout.fragment_edit_address;
    }

    private void reqData(boolean showDialog) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        if (btSave.getId() == v.getId()){
            toSave();
        }
    }

    /**
     * 保存
     */
    private void toSave() {

        if (verity(llAccountName,llAccountPhone,llAccountArea,llAccountDetail)){
            //请求接口
            getActivity().finish();
        }


    }

    public boolean verity(EditTitleView ... editTitleViews){

        for (EditTitleView editTitleView:editTitleViews) {
            if (editTitleView.getDefaultError() != null) {
                ToastUtil.showToast(getContext(), editTitleView.getDefaultError());
                return false;
            }

        }
        return true;
    }
}
