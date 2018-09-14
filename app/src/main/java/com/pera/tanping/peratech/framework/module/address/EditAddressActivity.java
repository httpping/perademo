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
import android.support.v4.app.Fragment;

import com.pera.tanping.peratech.R;
import com.pera.tanping.peratech.framework.base.BaseActivity;


/**
 * 创建时间:2018/8/10 10:15
 */
public class EditAddressActivity extends BaseActivity {


    @Override
    public Fragment getContentFragment() {
        EditAddressFragment addressFragment = new EditAddressFragment();
        return addressFragment;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar.setTitle(R.string.txt_recevie_people);
    }

    @Override
    public void onToolbarNavigationClick() {
        sendCheckoutAgaginMessage();
        super.onToolbarNavigationClick();
    }

    /**
     * 地址列表页点击关闭按钮也通知订单详情页重新checkout
     * （以前的功能，目前暂时保留，等soa支付接入再做处理）
     */
    private void sendCheckoutAgaginMessage() {
//        if (isSelectAddress == checkout) {
//            AddressListBean.AddressBean addressBean = new AddressListBean.AddressBean();
//            addressBean.setAddress_id(addressId);
//            EventBus.getDefault().post(new MessageEvent(Constants.EVENT_TYPE.SELECTED_ADDRESS, addressBean));
//            finish();
//        }
    }

//    @Override
//    public void finish() {
//        super.finish();
        //关闭窗体动画显示
//        if(isSelectAddress == checkout){
//            this.overridePendingTransition(0,R.anim.anim_close);
//        }
//    }

}
