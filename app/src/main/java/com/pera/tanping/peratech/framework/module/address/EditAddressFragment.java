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
import com.pera.tanping.peratech.framework.base.NetResult;
import com.pera.tanping.peratech.framework.bean.address.CityBean;
import com.pera.tanping.peratech.framework.bean.order.OrderBean;
import com.pera.tanping.peratech.framework.bean.user.AddressBean;
import com.pera.tanping.peratech.framework.remote.ApiManager;
import com.pera.tanping.peratech.framework.remote.config.Constants;
import com.pera.tanping.peratech.framework.remote.config.RequestParam;
import com.pera.tanping.peratech.framework.remote.config.XGsonSubscriber;
import com.pera.tanping.peratech.framework.remote.model.AddressManager;
import com.pera.tanping.peratech.framework.remote.model.LoginManager;
import com.pera.tanping.peratech.framework.utils.SharedPreferencesUtil;
import com.pera.tanping.peratech.framework.widget.EditTitleView;
import com.tp.cache.CacheManager;
import com.utils.ui.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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


    AddressBean mAddressBean ;

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

        reqData(true);
    }

    @Override
    public int getContentResId() {
        return R.layout.fragment_edit_address;
    }

    private void reqData(boolean showDialog) {
        RequestParam param = new RequestParam();
        try {
            String userId = LoginManager.getInstance().getUser().id;
            param.put("userid",userId);
        } catch (Exception e) {
            LoginManager.getInstance().toLogin(getActivity());
            e.printStackTrace();
        }
        ApiManager.Api().getUserInfo(param.createRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XGsonSubscriber<NetResult<List<AddressBean>>>(getActivity(),showDialog) {
                    @Override
                    public void onSuccess(NetResult<List<AddressBean>> listNetResult) {
                        if (listNetResult.isSuccess()){
                            mAddressBean = listNetResult.Data.get(0);
                            updateView();
                        }else {
                            listNetResult.showError(getActivity());
                        }
                    }


                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);

                        new NetResult<>().showError(getActivity());
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }
                });
    }


    public void updateView(){
        llAccountName.etContent.setText(mAddressBean.real_name);
        llAccountArea.etContent.setText(mAddressBean.city);
        llAccountDetail.etContent.setText(mAddressBean.address);
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

        if (verity(llAccountName,llAccountArea,llAccountDetail)){
            //请求接口

            List<CityBean> cityBeans =  CacheManager.get("city_list");
            if (cityBeans == null){
                AddressManager.getInstance().readAddress(getActivity(),true);
                return;
            }

            CityBean cityBean = new CityBean();
            cityBean.name = llAccountArea.etContent.getText().toString();

            int postition = cityBeans.indexOf(cityBean);
            if (postition < 0){
                ToastUtil.showToast(getContext(),"您的城市不再覆盖区..");
                return;
            }
            cityBean = cityBeans.get(postition);

            editData(cityBean.id,true);
        }


    }


    private void editData(int cityId ,boolean showDialog) {
        RequestParam param = new RequestParam();
        try {
            String userId = LoginManager.getInstance().getUser().id;
            param.put("userid",userId);
            param.put("countryid",cityId);
            param.put("address",llAccountDetail.etContent.getText().toString());
        } catch (Exception e) {
            LoginManager.getInstance().toLogin(getActivity());
            e.printStackTrace();
        }
        ApiManager.Api().getAddressList(param.createRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XGsonSubscriber<NetResult<List<String>>>(getActivity(),showDialog) {
                    @Override
                    public void onSuccess(NetResult<List<String>> listNetResult) {
                        listNetResult.showError(getActivity());
                    }


                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);

                        new NetResult<>().showError(getActivity());
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }
                });
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
