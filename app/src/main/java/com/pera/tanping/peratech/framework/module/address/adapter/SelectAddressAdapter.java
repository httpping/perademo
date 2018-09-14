package com.pera.tanping.peratech.framework.module.address.adapter;
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
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseViewHolder;
import com.gb.bind.adapter.GBBaseBindAdapter;
import com.gb.bind.annotations.BindItem;
import com.pera.tanping.peratech.R;
import com.pera.tanping.peratech.framework.module.address.fragment.AddressFragment;
import com.pera.tanping.peratech.framework.utils.StringUtil;
import com.pera.tanping.peratech.framework.utils.klog.KLog;

import java.util.List;

import static com.pera.tanping.peratech.framework.bean.address.AddressListBean.AddressBean;
import static com.pera.tanping.peratech.framework.remote.config.Constants.EnterAddress.checkout;

/**
 * 创建时间:2018/8/10 11:15
 */
public class SelectAddressAdapter extends GBBaseBindAdapter<AddressBean,BaseViewHolder> implements View.OnClickListener {

    private AddressFragment fragment;
    private Context mContext;
    private String curSelAddressId;

    public SelectAddressAdapter(AddressFragment fragment,List<AddressBean> data) {
        super(data);
        this.fragment = fragment;
        this.mContext = this.fragment.getContext();
    }

    public void setCurAddressId(String curAddressId) {
        this.curSelAddressId = curAddressId;
    }

    /**
     * @param helper
     * @param bean
     * @throws Exception
     */
    @BindItem(type = 0,layout = R.layout.item_select_address)
    public void one(BaseViewHolder helper, AddressBean bean) throws Exception {
        if(bean != null){
            helper.setText(R.id.tv_name,(bean.firstname + " " + bean.lastname));
            String supplierNumber = bean.supplier_number;
            String tel = bean.tel;
            String quHao = bean.code;
            StringBuilder builder = new StringBuilder();
            if (StringUtil.isNotEmpty(quHao)) {
                builder.append("+");
                builder.append(quHao);
                builder.append(" ");
            }
            if (StringUtil.isNotEmpty(supplierNumber)) {
                builder.append(supplierNumber);
            }
            builder.append(tel);
            helper.setText(R.id.tv_phone,builder.toString().trim());

            String shortAddress = bean.addressline1;
            if (StringUtil.isNotEmpty(bean.addressline2)) {
                shortAddress = shortAddress + "," + bean.addressline2;
            }
//            String detailAddress = shortAddress + "\r\n" + bean.city + "," + bean.province + "/" + bean.country_name + "," + bean.zipcode + "\r\n" + bean.email;
            String detailAddress = shortAddress + "\r\n" + bean.city + "," + bean.province + "/" + bean.country_name + "," + bean.zipcode ;
            KLog.d("adress item-->" + detailAddress);
            helper.setText(R.id.tv_address,detailAddress);

            //记录当前选择的默认地址
            if ("1".equals(bean.is_default_address)){
                curDefaultAddress = bean;
            }

            AppCompatTextView tvShippingAddress =  helper.getView(R.id.tv_shipping_address);
            if(fragment.isFromWhere != checkout && "1".equals(bean.is_default_address)){
                tvShippingAddress.setText(mContext.getString(R.string.default_address));
            }else{
                tvShippingAddress.setText(mContext.getString(R.string.shipping_address));
            }

            ImageView ivSelect = helper.getView(R.id.iv_select);
            if(fragment.isFromWhere == checkout){
                //结算页进入选择作为选中使用
                if(curSelAddressId.equals(bean.address_id)){
                    ivSelect.setImageResource(R.mipmap.icon_selected);
                }else{
                    ivSelect.setImageResource(R.mipmap.icon_normal);
                }
            }else{
                //作为默认地址选中使用
                if("1".equals(bean.is_default_address)){
                    ivSelect.setImageResource(R.mipmap.icon_selected);
                }else{
                    ivSelect.setImageResource(R.mipmap.icon_normal);
                }
            }

            ImageView ivDelete = helper.getView(R.id.iv_delete_address);
            if(fragment.isFromWhere == checkout){
                ivDelete.setVisibility(View.GONE);
            }else{
                ivDelete.setVisibility(View.VISIBLE);
            }
            ivSelect.setTag(R.id.recycler_view_item_id,bean);
            ivSelect.setOnClickListener(this);
            tvShippingAddress.setTag(R.id.recycler_view_item_id,bean);
            tvShippingAddress.setOnClickListener(this);

            LinearLayout llAddress = helper.getView(R.id.ll_address);
            llAddress.setTag(R.id.recycler_view_item_id,bean);
            llAddress.setOnClickListener(this);

            ivDelete.setTag(R.id.recycler_view_item_id,bean);
            ivDelete.setOnClickListener(this);

            ImageView ivEdit = helper.getView(R.id.iv_edit_address);
            ivEdit.setTag(R.id.recycler_view_item_id,bean);
            ivEdit.setOnClickListener(this);
        }
    }

    public AddressBean curDefaultAddress = null;

    @Override
    public boolean isOpenAutoBindView() {
        return true;
    }

    @Override
    public void onClick(View v) {
        AddressBean addressBean = (AddressBean) v.getTag(R.id.recycler_view_item_id);
        switch (v.getId()){
            case R.id.iv_select:
                dealButtonSelect(addressBean);
                break;
            case R.id.tv_shipping_address:
                dealButtonSelect(addressBean);
                break;
            case R.id.ll_address:
                if(fragment.isFromWhere == checkout) {
                    fragment.selectAddressReturn(addressBean);
                }else{
                    fragment.enterEditAddressActivity(addressBean);
                }
                break;
            case R.id.iv_delete_address:
                fragment.deleteAddress(addressBean);
                break;
            case R.id.iv_edit_address:
                fragment.enterEditAddressActivity(addressBean);
                break;
            default:
                break;
        }
    }

    /**
     * 地址按钮选择处理
     * @param addressBean
     */
    private void dealButtonSelect(AddressBean addressBean) {
        if(fragment.isFromWhere == checkout){
            curSelAddressId = addressBean.address_id;
            notifyDataSetChanged();
            fragment.selectAddressReturn(addressBean);
        }else{
            if("1".equals(addressBean.is_default_address)){
                return;
            }
            fragment.reqSetDefaultAddress(addressBean);
        }
    }
}
