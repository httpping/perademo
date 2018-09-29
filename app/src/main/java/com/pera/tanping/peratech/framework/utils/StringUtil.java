package com.pera.tanping.peratech.framework.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tanping on 2018/3/19.
 */

public class StringUtil {

    public  static boolean  isGif(String url){
        if (url == null)
            return  false;

        int index = url.toLowerCase().indexOf(".gif");
        return index > 0;

    }

    public static boolean isEmpty(String str){
        return str == null || str.trim().equals("");
    }
    public static boolean isNotEmpty(String str){
        return str != null && !str.trim().equals("") && !str.trim().equals("null");
    }

    /**
     * 匹配返回的数据中没有包含0.00值
     * @author linjinyan
     * @param str
     * @return  true表示不是 false表示返回的是0.00值
     */
    public static boolean judgeNoContainZero(String str){
        Pattern pattern = Pattern.compile(".*[1-9].*");
        return pattern.matcher(str).matches();
    }



    public static boolean isEmpty(List list){
        return list == null || list.size() == 0;
    }
    public static boolean isNotEmpty(List list){
        return list != null && list.size() != 0;
    }



    public static String getNumberForImg(String imgUrl){

        String str = null;
        try {
            String regEx = "[^0-9]";//匹配指定范围内的数字
            //Pattern是一个正则表达式经编译后的表现模式
            Pattern p = Pattern.compile(regEx);

            // 一个Matcher对象是一个状态机器，它依据Pattern对象做为匹配模式对字符串展开匹配检查。
            Matcher m = p.matcher(imgUrl);

            //将输入的字符串中非数字部分用空格取代并存入一个字符串
            String string = m.replaceAll(" ").trim();

            //以空格为分割符在讲数字存入一个字符串数组中
            String[] strArr = string.split(" ");

            //遍历数组转换数据类型输出
            str = "";
            for(String s:strArr){
                str += s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  str;
    }

    public  static final String PATH = "http://testadmin.fnwcm.com";
    public static String getImagePath(String url){
        if (url !=null && !url.startsWith("http")){
            return PATH + url;
        }

        return url ;
    }
}
