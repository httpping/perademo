package com.pera.tanping.peratech.framework.remote.config;

import com.tpnet.params.VpRequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ConcurrentHashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * 公共基本请求参数
 */

public class RequestParam extends VpRequestParams {
    private static final String TAG = "RequestParam";

     final ConcurrentHashMap<String, String> publicParams = new ConcurrentHashMap<String, String>();

    public RequestParam() {

    }

}