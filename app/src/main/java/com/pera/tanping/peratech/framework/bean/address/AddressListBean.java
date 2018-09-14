package com.pera.tanping.peratech.framework.bean.address;
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

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.pera.tanping.peratech.framework.remote.config.base.NetBaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间:2018/8/10 11:16
 */
public class AddressListBean extends NetBaseBean {

    public int page;
    public int page_size;
    public String total;
    public int total_page;
    public List<AddressBean> data;

    /**
     * 地址对象
     */
    public static class AddressBean  implements MultiItemEntity {
        public String address_id;
        public String user_id;
        public String firstname;
        public String lastname;
        public String email;
        public String country;
        public String province;
        public String city;
        public String addressline1;
        public String addressline2;
        public String zipcode;
        public String supplier_number;
        public String tel;
        public String code;
        public String id_card;
        public String landmark;
        public String whatsapp;
        public String telspare;
        public String supplier_number_spare;
        public String google_longitude;
        public String google_latitude;
        public String country_name;
        /**
         * 地址列表国家简码是region_code
         */
        public String region_code;
        public String is_cod;
        public String is_default_address;
        public String province_id;
        /**
         * country_code是checkout_info接口参数
         */
        public String country_code;
        /**
         * 后台是否配置了电话长度，0表示未配置，1表示已配置
         */
        public int configured_number;
        /**
         * 区号列表
         */
        public List<String> supplier_number_list;
        /**
         * 可支持的剩余号码最大长度
         */
        public List<Integer> scut_number_list;


        @Override
        public String toString() {
            return "AddressBean{" +
                    "address_id='" + address_id + '\'' +
                    ", addressline1='" + addressline1 + '\'' +
                    ", addressline2='" + addressline2 + '\'' +
                    ", landmark='" + landmark + '\'' +
                    ", city='" + city + '\'' +
                    ", country='" + country + '\'' +
                    ", country_name='" + country_name + '\'' +
                    ", email='" + email + '\'' +
                    ", firstname='" + firstname + '\'' +
                    ", lastname='" + lastname + '\'' +
                    ", province='" + province + '\'' +
                    ", tel='" + tel + '\'' +
                    ", telspare='" + telspare + '\'' +
                    ", user_id='" + user_id + '\'' +
                    ", zipcode='" + zipcode + '\'' +
                    ", id_card='" + id_card + '\'' +
                    ", province_id='" + province_id + '\'' +
                    ", is_default_address='" + is_default_address + '\'' +
                    ", code='" + code + '\'' +
                    ", supplier_number='" + supplier_number + '\'' +
                    ", supplier_number_spare='" + supplier_number_spare + '\'' +
                    ", supplier_number_list='" + supplier_number_list + '\'' +
                    ", country_code='" + country_code + '\'' +
                    ", configured_number=" + configured_number +
                    ", whatsapp='" + whatsapp + '\'' +
                    ", is_cod='" + is_cod + '\'' +
                    ", google_longitude='" + google_longitude + '\'' +
                    ", google_latitude='" + google_latitude + '\'' +
                    ", region_code='" + region_code + '\'' +
                    ", scut_number_list=" + scut_number_list +
                    '}';
        }

        public String getRegion_code() {
            return region_code;
        }

        public void setRegion_code(String region_code) {
            this.region_code = region_code;
        }

        public String getGoogle_longitude() {
            return google_longitude;
        }

        public void setGoogle_longitude(String google_longitude) {
            this.google_longitude = google_longitude;
        }

        public String getGoogle_latitude() {
            return google_latitude;
        }

        public void setGoogle_latitude(String google_latitude) {
            this.google_latitude = google_latitude;
        }



        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getSupplier_number() {
            return supplier_number;
        }

        public void setSupplier_number(String supplier_number) {
            this.supplier_number = supplier_number;
        }

        public AddressBean() {
        }

        @Override
        public int getItemType() {
            return 0;
        }
    }
}
