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

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.pera.tanping.peratech.framework.base.BaseActivity;

/**
 * 项目名称: z
 * 类描述：
 * 创建时间:2018/9/25 15:53
 *
 * @author tanping
 */
public class WebViewActivity extends BaseActivity {
    public static  final String URL ="url";
    WebViewFragment fragment;
    String url ;
    @Override
    public Fragment getContentFragment() {
        url = getIntent().getStringExtra(URL);
         fragment = new WebViewFragment();
        fragment.url = url ;
        return fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onBackPressed() {

        try {
            if (fragment.webView.canGoBack()){
                fragment.webView.goBack();
            }else {
                super.onBackPressed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
