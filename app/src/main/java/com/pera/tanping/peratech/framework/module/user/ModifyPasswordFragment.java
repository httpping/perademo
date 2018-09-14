package com.pera.tanping.peratech.framework.module.user;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class ModifyPasswordFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.ll_account_old)
    EditTitleView llAccountOld;
    @BindView(R.id.ll_account_password)
    EditTitleView llAccountPassword;
    @BindView(R.id.modify_password_new_retry)
    EditTitleView modifyPasswordNewRetry;
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

        llAccountOld.etContent.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD | EditorInfo.TYPE_CLASS_TEXT);
        modifyPasswordNewRetry.etContent.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD | EditorInfo.TYPE_CLASS_TEXT);
        llAccountPassword.etContent.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD | EditorInfo.TYPE_CLASS_TEXT);
        btSave.setOnClickListener(this);

        reqData(false);
    }

    @Override
    public int getContentResId() {
        return R.layout.fragment_modify_password;
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
        if (btSave.getId() == v.getId()) {
            toSave();
        }
    }

    /**
     * 保存
     */
    private void toSave() {

        if (verity(llAccountOld, llAccountPassword, modifyPasswordNewRetry)) {
            //请求接口
            getActivity().finish();
        }

        if (verity(llAccountOld,llAccountPassword,modifyPasswordNewRetry)){
            getActivity().finish();
        }

        if (verity(llAccountPassword,modifyPasswordNewRetry)){
            //todo 去修改密码
        }



    }

    public boolean verity(EditTitleView... editTitleViews) {

        for (EditTitleView editTitleView : editTitleViews) {
            if (editTitleView.getDefaultError() != null) {
                ToastUtil.showToast(getContext(), editTitleView.getDefaultError());
                return false;
            }

        }
        return true;
    }

    public boolean equals(EditTitleView... editTitleViews) {
        String last = null;
        for (EditTitleView editTitleView : editTitleViews) {
            String text = editTitleView.etContent.getText().toString();
            if (last != null) {
                if (!last.equals(text)){
                    return false;
                }
            }

            last = text;
        }
        return true;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
