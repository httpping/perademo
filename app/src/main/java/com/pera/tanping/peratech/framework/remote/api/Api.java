package com.pera.tanping.peratech.framework.remote.api;


import com.pera.tanping.peratech.framework.base.NetResult;
import com.pera.tanping.peratech.framework.bean.user.UserBean;
import com.pera.tanping.peratech.framework.module.demo.DemoNetBean;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * XXX服务器
 */
public interface Api {

    /**
     * 获取产品评论列表
     *
     * @param params 请求体
     */
    @POST("post")
    Flowable<String> post(@Body RequestBody params);

    @POST("post")
    Flowable<DemoNetBean> postBean(@Body RequestBody params);



    @POST("common/initialization")
    Flowable<String> initialization(@Body RequestBody params);


    /**
     * login
     * @param params
     * @return
     */
    @POST("api/user/login")
    Flowable<String> login(@Body RequestBody params);


    /**
     * 获取广告/新闻列表
     * api/ad/getdalist
     */
    @POST("api/ad/getdalist")
    Flowable<String> getDaList(@Body RequestBody params);


    /**
     * 获取用户历史订单信息
     * api/ad/getdalist
     */
    @POST("api/order/getorderlist")
    Flowable<String> getOrderList(@Body RequestBody params);


    /**
     * 修改用户密码
     * api/ad/getdalist
     */
    @POST("api/changepass/do")
    Flowable<String> changePass(@Body RequestBody params);



    /**
     * 获取用户信息
     * api/getuserinfo/do
     */
    @POST("api/getuserinfo/do")
    Flowable<String> getUserInfo(@Body RequestBody params);



    /**
     * 获取用户城市列表
     * api/getuserinfo/do
     */
    @POST("api/address/getaddresslist")
    Flowable<String> getAddressList(@Body RequestBody params);


    /**
     *  获取类目(首页产品一级分类)列表
     * api/getuserinfo/do
     */
    @POST("api/category/getcategorylist")
    Flowable<String> getCategoryList(@Body RequestBody params);



    /**
     *  获取类目(首页产品一级分类)列表
     *  api/brand/getbrandlist
     */
    @POST("api/brand/getbrandlist")
    Flowable<String> getBrandList(@Body RequestBody params);


    /**
     *  获取产品列表
     *  api/brand/getbrandlist
     */
    @POST("api/product/getproductlist")
    Flowable<String> getProductList(@Body RequestBody params);



    /**
     *  获取产品详情
     *  api/brand/getbrandlist
     */
    @POST("api/ProductInfo/getinfo")
    Flowable<String> getProductInfo(@Body RequestBody params);



    /**
     * 会员下订单
     *  api/brand/getbrandlist
     */
    @POST("api/setorder/do")
    Flowable<String> createdOrder(@Body RequestBody params);

}
