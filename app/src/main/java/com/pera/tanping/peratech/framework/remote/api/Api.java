package com.pera.tanping.peratech.framework.remote.api;


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

}
