package com.pera.tanping.peratech.framework.module.login;
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

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.pera.tanping.peratech.MainActivity;
import com.pera.tanping.peratech.R;
import com.pera.tanping.peratech.framework.base.BaseFragment;
import com.pera.tanping.peratech.framework.remote.config.Constants;
import com.pera.tanping.peratech.framework.utils.SharedPreferencesUtil;
import com.pera.tanping.peratech.framework.utils.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 项目名称: pera
 * 类描述 : 登录
 *
 * @author：Created 创建时间:2018/8/10 10:22
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener{


    @BindView(R.id.et_login_email)
    TextInputEditText etLoginEmail;
    @BindView(R.id.til_login_email)
    TextInputLayout tilLoginEmail;
    @BindView(R.id.et_login_password)
    TextInputEditText etLoginPassword;
    @BindView(R.id.til_login_password)
    TextInputLayout tilLoginPassword;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.header_ll)
    LinearLayout headerLl;
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

        etLoginEmail.setText(SharedPreferencesUtil.getString(Constants.USERNAME));
        etLoginPassword.setText(SharedPreferencesUtil.getString(Constants.PASSWORD));
        btLogin.setOnClickListener(this);

        if (SharedPreferencesUtil.getString(Constants.PASSWORD)!=null){
            toLogin();
        }
    }

    @Override
    public int getContentResId() {
        return R.layout.fragment_login;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_login){
            toLogin();
        }
    }

    private void toLogin() {
        if (StringUtil.isEmpty(etLoginEmail.getText().toString())){
            tilLoginEmail.setError("输入用户名");
            return;
        }
        if (StringUtil.isEmpty(etLoginPassword.getText().toString())){
            tilLoginPassword.setError("输入密码");
            return;
        }
        String  sss= etLoginEmail.getText().toString();

        SharedPreferencesUtil.save(Constants.USERNAME,etLoginEmail.getText().toString());
        SharedPreferencesUtil.save(Constants.PASSWORD,etLoginPassword.getText().toString());

        String username =  SharedPreferencesUtil.getString(Constants.USERNAME);
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);

        getActivity().finish();

    }
}
