package com.pera.tanping.peratech.framework.module.address.fragment;
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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pera.tanping.peratech.R;
import com.pera.tanping.peratech.framework.base.BaseFragment;
import com.pera.tanping.peratech.framework.module.address.adapter.SelectAddressAdapter;
import com.pera.tanping.peratech.framework.remote.config.Constants;
import com.pera.tanping.peratech.framework.remote.config.RequestParam;
import com.pera.tanping.peratech.framework.remote.model.LoginManager;
import com.pera.tanping.peratech.framework.utils.StringUtil;
import com.pera.tanping.peratech.framework.widget.MultiStatusView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.pera.tanping.peratech.framework.bean.address.AddressListBean.AddressBean;

/**
 */
public class AddressFragment extends BaseFragment {

    private static final int DEFAULT_PAGE_SIZE = 20;
    private RecyclerView rvAddress;
    private RelativeLayout rlAddress;
    private TextView tvAddress,btnRetry,tvEmptyAddress;
    public int isFromWhere ;
    private String addressId;
    static final int REQUEST_CODE_TO_LOGIN = 212;
    private SelectAddressAdapter selectAddressAdapter;
    private List<AddressBean> data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        judgeLogin();
    }

    /**
     * 登录判断处理
     */
    private void judgeLogin(){
        if (!LoginManager.getInstance().isLogin()) {
            startActivityForResult(new Intent(getActivity(), LoginActivity.class), REQUEST_CODE_TO_LOGIN);
            return;
        }
    }

    @Override
    public int getContentResId() {
        return R.layout.fragment_address;
    }

    private void getExtraData(){
        if(getArguments() != null){
            isFromWhere = getArguments().getInt(Constants.FROM_WHERE_TO_ADDRESS);
            addressId = getArguments().getString(Constants.ADDRESS_ID);
            addressId = StringUtil.isNotEmpty(addressId) ? addressId : LoginManager.getInstance().readDefaultAddressId();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        multiStatusView.showContent();
        View emptyView = View.inflate(getContext(),R.layout.empty_address_layout,null);
        multiStatusView.add(MultiStatusView.VIEW_STATE_EMPTY,emptyView);
        rvAddress = findViewById(R.id.rv_address);
        rlAddress = findViewById(R.id.rl_address);
        rlAddress.setVisibility(View.VISIBLE);
        tvAddress = findViewById(R.id.tv_address);
        tvEmptyAddress = emptyView.findViewById(R.id.tv_address);
        tvEmptyAddress.setVisibility(View.VISIBLE);
//        btnRetry = findViewById(R.id.btn_retry);
        data = new ArrayList<>();
        getExtraData();
        selectAddressAdapter = new SelectAddressAdapter(this, data);
        selectAddressAdapter.setCurAddressId(addressId);
        rvAddress.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAddress.setBackgroundColor(getContext().getResources().getColor(R.color.color_f7f7f7));
        rvAddress.setAdapter(selectAddressAdapter);
        reqData(false);
        initListener();
    }

    private void reqData(boolean showDialog){
        RequestParam param = new RequestParam();
        param.put("page",1);
        param.put("page_size",DEFAULT_PAGE_SIZE);

        for (int i =0 ;i<10;i++){
            AddressBean bean = new AddressBean();
            bean.address_id= "i" + i;
            bean.addressline1 = "sss";
            bean.city ="sz";
            if (i == 2){
                bean.is_default_address = "1";
            }
            data.add(bean);
        }

        selectAddressAdapter.notifyDataSetChanged();


      /*  ApiManager.addressApi().getAddressLits(param.createRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XGsonSubscriber<NetResult<AddressListBean>>(getContext(),showDialog) {

                    @Override
                    public void showDialog() {
                        super.showDialog();
                        if(!showDialog){
                            multiStatusView.showLoading();
                        }
                    }

                    @Override
                    public void onSuccess(NetResult<AddressListBean> bean) {
                        data.clear();
                        if(bean.isSuccess() && bean.result != null && bean.result.data != null && bean.result.data.size() > 0){
                            multiStatusView.showContent();
                            data.addAll(bean.result.data);
                            // 地址数量超过5条不显示添加按钮
                            rlAddress.setVisibility(data.size() >= 5 ? View.GONE : View.VISIBLE);
                        }else{
                            multiStatusView.showEmpty();
                        }
                        selectAddressAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        multiStatusView.showNoNetWork();
                    }
                });*/
    }

    private void initListener() {
        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddOrEditAddressActivity(true, data.size() >= 1, null);
            }
        });
        tvEmptyAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddOrEditAddressActivity(true, data.size() >= 1, null);
            }
        });
       /* btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reqData(false);
            }
        });*/
    }

    /**
     * 设置默认地址
     * @param addressBean
     */
    public void reqSetDefaultAddress(AddressBean addressBean){
        RequestParam param = new RequestParam();
        param.put("address_id",addressBean.address_id);
       /* ApiManager.addressApi().setDefaultAddress(param.createRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XGsonSubscriber<NetResult>(getContext(),true) {

                    @Override
                    public void onSuccess(NetResult netResult) {
                        if(netResult.isSuccess()){
                            addressBean.is_default_address = "1";
                            //更改当前默认地址
                            if(selectAddressAdapter.curDefaultAddress != null){
                                selectAddressAdapter.curDefaultAddress.setIs_default_address("0");
                            }
                            selectAddressAdapter.curDefaultAddress = addressBean;
                            SharedModel.instance().save(Constants.SHARED_KEY.ADDRESS_ID, "" + addressBean.address_id);
                            selectAddressAdapter.notifyDataSetChanged();
                        }else{
                            ToastUtil.showShortMessage(netResult.msg);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        ToastUtil.showShortMessage(R.string.tip_layout_failure);
                    }
                });*/
    }

    /**
     * 选中地址回到地址页面
     * @param addressBean
     */
    public void selectAddressReturn(AddressBean addressBean){
        //选择后的地址同时设置为默认地址
        reqSetDefaultAddress(addressBean);
//        EventBus.getDefault().post(new MessageEvent(Constants.EVENT_TYPE.SELECTED_ADDRESS, addressBean));
        getActivity().finish();
    }

    /**
     * 删除地址
     * @param addressBean
     */
    public void deleteAddress(AddressBean addressBean){
        if(data.size() <= 1){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                    .setMessage(R.string.delete_last_address)
                    .setPositiveButton(R.string.dialog_ok,null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }else{
            showAgainAlterDialog(addressBean);
        }
    }

    private void showAgainAlterDialog(AddressBean addressBean){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setMessage(R.string.delete_address)
                .setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reqDeleteAddress(addressBean);
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void reqDeleteAddress(AddressBean addressBean){
        String deleteAddressId = addressBean.address_id;
        RequestParam param = new RequestParam();
        param.put("address_id",deleteAddressId);
     /*   ApiManager.addressApi().reqDeleteAddress(param.createRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XGsonSubscriber<NetResult>(getContext(),true) {

                    @Override
                    public void onSuccess(NetResult netResult) {
                        if(netResult.isSuccess()){
                            ToastUtil.showShortMessage(R.string.delete_success);
                            String defaultAddressId = SharedModel.instance().read(Constants.SHARED_KEY.ADDRESS_ID, "");
                            KLog.d("默认地址id:" + defaultAddressId + ",删除的地址ID：" + deleteAddressId);
                            if (StringUtil.isNotEmpty(deleteAddressId) && deleteAddressId.equals(defaultAddressId)) {
                                KLog.d("默认地址被删除后，应该调用获取个人资料接口，更新默认地址参数为0");
                                EventBus.getDefault().post(new GetUserInfoMessageEvent());
                            }
                            reqData(true);
                        }else{
                            ToastUtil.showShortMessage(netResult.msg);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        ToastUtil.showShortMessage(R.string.tip_layout_failure);
                    }
                });*/
    }

    public void enterEditAddressActivity(AddressBean addressBean){
        goToAddOrEditAddressActivity(false, data.size() > 1, addressBean);
    }

    public void goToAddOrEditAddressActivity(boolean isAdd, boolean isCheckboxClickable, AddressBean addressBean) {
        /*Intent intent = new Intent(getContext(), AddOrEditAddressActivity.class);
        intent.putExtra("isAdd", isAdd);
        intent.putExtra("isCheckboxClickable", isCheckboxClickable);
        if (addressBean != null) {
            intent.putExtra(Constants.EXTRA_NAME.ADDRESS_BEAN, addressBean);
        }
        startActivity(intent);*/
    }

    @Override
    public boolean isRegisterEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMessage(@NonNull AddressBean event) {
        /*switch (event.what) {
            case Constants.EVENT_TYPE.EDIT_ADDRESS_SUCCESS:
                reqData(true);
                break;
            default:
                break;
        }*/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE_TO_LOGIN == requestCode) {
            if (resultCode == RESULT_OK) {
                reqData(false);
                return;
            }
            getActivity().finish();
        }
    }
}
