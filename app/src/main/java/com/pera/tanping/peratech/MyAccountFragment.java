package com.pera.tanping.peratech;
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
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pera.tanping.peratech.framework.base.BaseFragment;
import com.pera.tanping.peratech.framework.bean.user.UserBean;
import com.pera.tanping.peratech.framework.module.address.EditAddressActivity;
import com.pera.tanping.peratech.framework.module.goods.OrderActivity;
import com.pera.tanping.peratech.framework.module.login.LoginActivity;
import com.pera.tanping.peratech.framework.module.user.ModifyPassowrdActivity;
import com.pera.tanping.peratech.framework.module.user.MyAcountActivity;
import com.pera.tanping.peratech.framework.remote.config.Constants;
import com.pera.tanping.peratech.framework.remote.model.LoginManager;
import com.pera.tanping.peratech.framework.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 项目名称: z
 * 类描述：
 * 创建时间:2018/8/13 14:27
 *
 * @author tanping
 */
public class MyAccountFragment extends BaseFragment {


    @BindView(R.id.view_icon_background)
    TextView viewIconBackground;
    @BindView(R.id.tv_welcome)
    TextView tvWelcome;
    @BindView(R.id.rl_no_login_layout)
    LinearLayout rlNoLoginLayout;
    @BindView(R.id.fl_account_top)
    FrameLayout flAccountTop;
    @BindView(R.id.ll_banners)
    LinearLayout llBanners;
    @BindView(R.id.tv_my_account)
    TextView tvMyAccount;
    @BindView(R.id.tv_my_order)
    TextView tvMyOrder;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_modify_password)
    TextView tvModifyPassword;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;

    @BindView(R.id.tv_signout)
    TextView tvSignout;
    Unbinder unbinder;
    Unbinder unbinder1;

    @Override
    public int getContentResId() {
        return R.layout.fragment_account;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        multiStatusView.showContent();
        unbinder = ButterKnife.bind(this, multiStatusView);


        UserBean userBean =  LoginManager.getInstance().getUser();
        if (userBean !=null){
            tvWelcome.setText(userBean.real_name);
        }

    }


    @OnClick({R.id.tv_my_account, R.id.tv_my_order, R.id.tv_address, R.id.tv_modify_password,R.id.tv_signout})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tv_my_account:
                intent = new Intent(getContext(), MyAcountActivity.class);
                break;
            case R.id.tv_my_order:
                intent = new Intent(getContext(), OrderActivity.class);
                break;
            case R.id.tv_address:
                intent = new Intent(getContext(), EditAddressActivity.class);
                break;
            case R.id.tv_modify_password:
                intent = new Intent(getContext(), ModifyPassowrdActivity.class);
                break;
            case R.id.tv_signout:
                toLogin();
                break;
            default:
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }

    /**
     * 注销
     */
    private void toLogin() {
        SharedPreferencesUtil.remove(Constants.PASSWORD);
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
