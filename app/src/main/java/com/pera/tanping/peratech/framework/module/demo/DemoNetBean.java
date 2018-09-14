package com.pera.tanping.peratech.framework.module.demo;
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

import com.google.gson.annotations.SerializedName;

/**
 * 创建时间:2018/8/1 14:47
 */
public class DemoNetBean {


    public ArgsBean args;
    public String data;
    public FilesBean files;
    public FormBean form;
    public HeadersBean headers;
    public Object json;
    public String origin;
    public String url;

    public static class ArgsBean {
    }

    public static class FilesBean {
    }

    public static class FormBean {
        public String key1;
    }

    public static class HeadersBean {
        @SerializedName("Accept-Encoding")
        public String AcceptEncoding;
        public String Connection;
        @SerializedName("Content-Length")
        public String ContentLength;
        @SerializedName("Content-Type")
        public String ContentType;
        public String Host;
        @SerializedName("User-Agent")
        public String UserAgent;
    }
}
