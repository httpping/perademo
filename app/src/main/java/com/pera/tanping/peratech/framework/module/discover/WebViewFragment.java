package com.pera.tanping.peratech.framework.module.discover;
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

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.pera.tanping.peratech.R;
import com.pera.tanping.peratech.framework.base.BaseFragment;
import com.tp.bindbean.utils.BindStringUtil;

/**
 * 项目名称: z
 * 类描述：
 * 创建时间:2018/9/25 15:53
 *
 * @author tanping
 */
public class WebViewFragment extends BaseFragment {

    WebView webView;
    public String url ;

    @Override
    public int getContentResId() {
        return R.layout.webview_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        webView = findViewById(R.id.webview);
//        url ="http://www.baidu.com";
        initWebView(webView);

        multiStatusView.showContent();
    }


    protected void initWebView(WebView webView) {
        //除线上环境外，开启chrome,android 调试
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

                WebViewActivity activity = (WebViewActivity) getActivity();
                activity.toolbar.setTitle(title);
            }
        });
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings webSetting = webView.getSettings();
        //启用javascript支持 用于访问页面中的javascript
        webSetting.setJavaScriptEnabled(true);
        dealJavascriptLeak(webView);
        //设置脚本是否允许自动打开弹窗
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        //设置在WebView内部是否允许访问文件
        webSetting.setAllowFileAccess(true);
        //设定true时会将viewport的meta tag启动。如果我们没有强制设定宽度，那么就会使用可视范围的最大视野，其意思就是荧幕的大小
        webSetting.setUseWideViewPort(true);
        //默认flase，为true表示当内容大于viewport时，系统将会自动缩小内容以适应屏幕宽度
        webSetting.setLoadWithOverviewMode(true);
        //使所有列的宽度不超过屏幕宽度。默认NARROW_COLUMNS
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //让WebView支持DOM storage API。默认false
        webSetting.setDomStorageEnabled(true);
        //让WebView支持缩放。false表示不支持
        webSetting.setSupportZoom(true);
        //启用WebView内置缩放功能。false表示关闭
        webSetting.setBuiltInZoomControls(true);
        //不显示webview缩放按钮
        webSetting.setDisplayZoomControls(false);
        webSetting.setDatabaseEnabled(true);
        //设置h5的缓存打开
        webSetting.setAppCacheEnabled(true);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //http/https混合加载 防止有些网站出错
            webSetting.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        if (BindStringUtil.isNotEmpty(url)) {
            webView.loadUrl(url);
        }
    }




    /**
     * 处理webview漏洞，删除危险API
     *
     * @param webView
     */
    public static void dealJavascriptLeak(WebView webView) {
        if (webView == null) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                webView.removeJavascriptInterface("searchBoxJavaBridge_");
                webView.removeJavascriptInterface("accessibility");
                webView.removeJavascriptInterface("accessibilityTraversal");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
