package com.pera.tanping.peratech.framework.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;

import com.pera.tanping.peratech.MainApplication;
import com.pera.tanping.peratech.framework.utils.klog.KLog;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

/**
 * SharedPreferences相关工具类
 *
 */
public final class SharedPreferencesUtil {
    /**
     * 配置文件名
     */
    private static final String FILE_NAME = "local_config";

    /**
     * SharedPreferences实例对象
     */
    private SharedPreferences sharedPreferences;

    /**
     * 静态内部类实现单例模式
     */
    public static class Holder {
        public static final SharedPreferencesUtil INSTANCE = new SharedPreferencesUtil();
    }

    /**
     * 构造方法私有化
     */
    private SharedPreferencesUtil() {
        sharedPreferences = MainApplication.getContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 获取本类实例对象
     *
     * @return SharedPreferencesUtil实例对象
     */
    public static SharedPreferencesUtil getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 保存boolean类型数据到配置文件中
     *
     * @param key   键名称
     * @param value boolean类型数据
     * @return 成功返回true，失败返回false
     */
    public synchronized static boolean save(@NonNull String key, @NonNull boolean value) {
        final Editor editor = getInstance().sharedPreferences.edit();
        return !(null == editor || StringUtil.isEmpty(key)) && editor.putBoolean(key, value).commit();
    }

    /**
     * 保存boolean类型数据到配置文件中
     *
     * @param key   键名称
     * @param value boolean类型数据
     * @return 成功返回true，失败返回false
     */
    public synchronized static <T> boolean save(@NonNull String key, @NonNull T value) {
        saveSpecialKey(key, value);
        return true;
    }

    /**
     * 保存char类型数据到配置文件中
     *
     * @param key   键名称
     * @param value char类型数据
     * @return 成功返回true，失败返回false
     */
    public synchronized static boolean save(@NonNull String key, @NonNull char value) {
        final Editor editor = getInstance().sharedPreferences.edit();
        return !(null == editor || StringUtil.isEmpty(key)) && editor.putString(key, String.valueOf(value)).commit();
    }

    /**
     * 读取SharedPreferences Integer类型值,如果不存在则返回默认值
     *
     * @param specialKey   读取SharedPreferences 的key
     * @param defaultValue 如果不存在则返回该默认值
     * @author dingpeihua
     * @date 2016/3/21 9:19
     * @version 1.0
     */
    public static int getIntBySpecialKey(String specialKey, int defaultValue) {
        return getInt(specialKey, defaultValue);
    }

    /**
     * 存储SharedPreferences key 和value
     *
     * @param specialKey 要存储的SharedPreferences 的key
     * @param value      要存储的SharedPreferences 的value
     * @author dingpeihua
     * @date 2016/3/21 9:26
     * @version 1.0
     */
    private static void saveSpecialKey(String specialKey, Object value) {
        final Editor editor = getInstance().sharedPreferences.edit();
        if (value instanceof Boolean) {
            editor.putBoolean(specialKey, (Boolean) value);
        } else if (value instanceof String) {
            editor.putString(specialKey, (String) value);
        } else if (value instanceof Float) {
            editor.putFloat(specialKey, (Float) value);
        } else if (value instanceof Integer) {
            editor.putInt(specialKey, (Integer) value);
        } else if (value instanceof Long) {
            editor.putLong(specialKey, (Long) value);
        }
        editor.apply();
    }

    private static <T> T readValue(String specialKey, T defaultValue) {
        Object returnValue = defaultValue;
        if (defaultValue instanceof Boolean) {
            returnValue = getInstance().sharedPreferences.getBoolean(specialKey, (Boolean) defaultValue);
        } else if (defaultValue instanceof String) {
            returnValue = getInstance().sharedPreferences.getString(specialKey, (String) defaultValue);
        } else if (defaultValue instanceof Float) {
            returnValue = getInstance().sharedPreferences.getFloat(specialKey, (Float) defaultValue);
        } else if (defaultValue instanceof Integer) {
            returnValue = getInstance().sharedPreferences.getInt(specialKey, (Integer) defaultValue);
        } else if (defaultValue instanceof Long) {
            returnValue = getInstance().sharedPreferences.getLong(specialKey, (Long) defaultValue);
        }
        return (T) returnValue;
    }

    public static <T> T read(String specialKey, T defaultValue) {
        return readValue(specialKey, defaultValue);
    }

    /**
     * 保存byte类型数据到配置文件中
     *
     * @param key   键名称
     * @param value byte类型数据
     * @return 成功返回true，失败返回false
     */
    public synchronized static boolean save(@NonNull String key, @NonNull byte value) {
        final Editor editor = getInstance().sharedPreferences.edit();
        return null != editor && editor.putInt(key, value).commit();
    }

    /**
     * 保存int类型数据到配置文件中
     *
     * @param key   键名称
     * @param value int类型数据
     * @return 成功返回true，失败返回false
     */
    public synchronized static boolean save(@NonNull String key, @NonNull int value) {
        final Editor editor = getInstance().sharedPreferences.edit();
        KLog.d("key:" + key + ",value:" + value);
        return null != editor && editor.putInt(key, value).commit();
    }

    /**
     * 保存short类型数据到配置文件中
     *
     * @param key   键名称
     * @param value short类型数据
     * @return 成功返回true，失败返回false
     */
    public synchronized static boolean save(@NonNull String key, @NonNull short value) {
        final Editor editor = getInstance().sharedPreferences.edit();
        return null != editor && editor.putInt(key, value).commit();
    }

    /**
     * 保存long类型数据到配置文件中
     *
     * @param key   键名称
     * @param value long类型数据
     * @return 成功返回true，失败返回false
     */
    public synchronized static boolean save(@NonNull String key, @NonNull long value) {
        final Editor editor = getInstance().sharedPreferences.edit();
        return null != editor && editor.putLong(key, value).commit();
    }

    /**
     * 保存float类型数据到配置文件中
     *
     * @param key   键名称
     * @param value float类型数据
     * @return 成功返回true，失败返回false
     */
    public synchronized static boolean save(@NonNull String key, @NonNull float value) {
        final Editor editor = getInstance().sharedPreferences.edit();
        return null != editor && editor.putFloat(key, value).commit();
    }

    /**
     * 读取SharedPreferences String类型值,如果不存在则返回""
     *
     * @param prefsKey 读取SharedPreferences 的key
     * @author dingpeihua
     * @date 2016/3/21 9:19
     * @version 1.0
     */
    public static String getStringByPrefsKey(String prefsKey) {
        return getString(prefsKey);
    }

    /**
     * 读取SharedPreferences String类型值,如果不存在则返回默认值
     *
     * @param prefsKey     读取SharedPreferences 的key
     * @param defaultValue 如果不存在则返回该默认值
     * @author dingpeihua
     * @date 2016/3/21 9:19
     * @version 1.0
     */
    public static String getStringByPrefsKey(String prefsKey, String defaultValue) {
        return getString(prefsKey, defaultValue);
    }

    /**
     * 保存double类型数据到配置文件中
     *
     * @param key   键名称
     * @param value double类型数据
     * @return 成功返回true，失败返回false
     */
    public synchronized static boolean save(@NonNull String key, @NonNull double value) {
        final Editor editor = getInstance().sharedPreferences.edit();
        return !(null == editor || StringUtil.isEmpty(key)) && editor.putFloat(key, (float) value).commit();
    }

    /**
     * 保存String类型数据到配置文件中
     *
     * @param key   键名称
     * @param value String类型数据
     * @return 成功返回true，失败返回false
     */
    public synchronized static boolean save(@NonNull String key, @NonNull String value) {
        final Editor editor = getInstance().sharedPreferences.edit();
        return !(null == editor || StringUtil.isEmpty(key)) && editor.putString(key, value).commit();
    }

    /**
     * 保存JSONObject类型数据到配置文件中，该方法会遍历JSONObject中的数据
     *
     * @param jsonObject JSONObject类型数据
     * @return 成功返回true，失败返回false
     */
    public synchronized static boolean save(@NonNull JSONObject jsonObject) {
        if (jsonObject != null) {
            Iterator<String> iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                String key = iterator.next();
                KLog.d("key name = " + key);
            }
        }
        return false;
    }

    /**
     * 从配置文件中读取boolean类型数据
     *
     * @param key 键名称
     * @return boolean类型数据，默认如果没有该key返回false
     */
    public static boolean getBoolean(@NonNull String key) {
        return !(null == getInstance().sharedPreferences || StringUtil.isEmpty(key)) && getInstance().sharedPreferences.getBoolean(key, false);
    }

    /**
     * 从配置文件中读取boolean类型数据
     *
     * @param key          键名称
     * @param defaultValue 默认值
     * @return boolean类型数据
     */
    public static boolean getBoolean(@NonNull String key, @NonNull boolean defaultValue) {
        if (null == getInstance().sharedPreferences || StringUtil.isEmpty(key)) {
            return defaultValue;
        }
        return getInstance().sharedPreferences.getBoolean(key, defaultValue);
    }

    public static int getInt(@NonNull String key) {
        if (null == getInstance().sharedPreferences || StringUtil.isEmpty(key)) {
            return 0;
        }
        return getInstance().sharedPreferences.getInt(key, 0);
    }

    public static int getInt(@NonNull String key, @NonNull int defaultValue) {
        KLog.d(">>>>key:" + key + ">>>>>>>>>>>>>>>>>>>>>>>>>>defaultValue:" + defaultValue);
        if (null == getInstance().sharedPreferences || StringUtil.isEmpty(key)) {
            return defaultValue;
        }
        KLog.d(">>>>key:" + key + ">>>>>>>>>>>>>>>>>>>>>>>>>>defaultValue:" + defaultValue);
        return getInstance().sharedPreferences.getInt(key, defaultValue);
    }

    public static short getShort(@NonNull String key) {
        if (null == getInstance().sharedPreferences || StringUtil.isEmpty(key)) {
            return Short.valueOf("0");
        }
        return Short.valueOf(getInstance().sharedPreferences.getString(key, "0"));
    }

    public static short getShort(@NonNull String key, @NonNull short defaultValue) {
        if (null == getInstance().sharedPreferences || StringUtil.isEmpty(key)) {
            return defaultValue;
        }
        return Short.valueOf(getInstance().sharedPreferences.getString(key, String.valueOf(defaultValue)));
    }

    public static long getLong(@NonNull String key) {
        if (null == getInstance().sharedPreferences || StringUtil.isEmpty(key)) {
            return 0L;
        }
        return getInstance().sharedPreferences.getLong(key, 0L);
    }

    public static long getLong(@NonNull String key, @NonNull long defaultValue) {
        if (null == getInstance().sharedPreferences || StringUtil.isEmpty(key)) {
            return defaultValue;
        }
        return getInstance().sharedPreferences.getLong(key, defaultValue);
    }

    public static float getFloat(@NonNull String key) {
        if (null == getInstance().sharedPreferences || StringUtil.isEmpty(key)) {
            return 0.0f;
        }
        return getInstance().sharedPreferences.getFloat(key, 0.0f);
    }

    public static float getFloat(@NonNull String key, @NonNull float defaultValue) {
        if (null == getInstance().sharedPreferences || StringUtil.isEmpty(key)) {
            return defaultValue;
        }
        return getInstance().sharedPreferences.getFloat(key, defaultValue);
    }

    public static double getDouble(@NonNull String key) {
        if (null == getInstance().sharedPreferences || StringUtil.isEmpty(key)) {
            return 0.0;
        }
        return Double.valueOf(getInstance().sharedPreferences.getString(key, "0.0"));
    }

    public static double getDouble(@NonNull String key, @NonNull double defaultValue) {
        if (null == getInstance().sharedPreferences || StringUtil.isEmpty(key)) {
            return Double.valueOf(String.valueOf(defaultValue));
        }
        return Double.valueOf(getInstance().sharedPreferences.getString(key, String.valueOf(defaultValue)));
    }

    public static String getString(@NonNull String key) {
        if (null == getInstance().sharedPreferences || StringUtil.isEmpty(key)) {
            return Constant.EMPTY_STR;
        }
        return getInstance().sharedPreferences.getString(key, Constant.EMPTY_STR).trim();
    }

    public static String getString(@NonNull String key, @NonNull String defaultValue) {
        if (null == getInstance().sharedPreferences || StringUtil.isEmpty(key)) {
            return defaultValue;
        }
        return getInstance().sharedPreferences.getString(key, defaultValue).trim();
    }

    /**
     * 删除
     *
     * @param key key名称
     * @return 成功返回true, 失败返回false
     */
    public static boolean remove(@NonNull String key) {
        final Editor editor = getInstance().sharedPreferences.edit();
        return !(null == editor || StringUtil.isEmpty(key)) && editor.remove(key).commit();
    }

    /**
     * 清空
     *
     * @return 成功返回true, 失败返回false
     */
    public static boolean clear() {
        final Editor editor = getInstance().sharedPreferences.edit();
        return editor.clear().commit();
    }



    public synchronized static boolean saveMap(@NonNull Map<String, Object> data) {
        if (data == null || data.size() == 0) {
            return false;
        }
        if (null == getInstance().sharedPreferences ) {
            return false;
        }
            Iterator<String> keys = data.keySet().iterator();
        final Editor editor = getInstance().sharedPreferences.edit();
        if (editor == null) {
            throw new NullPointerException("editor == null");
        }
        while (keys.hasNext()) {
            final String key = keys.next();
            putKeyValue(editor,key, data.get(key));
        }
        editor.apply();
        return true;
    }


    private static void putKeyValue(Editor editor, String specialKey, Object value) {
        if (value instanceof Boolean) {
            editor.putBoolean(specialKey, (Boolean) value);
        } else if (value instanceof String) {
            editor.putString(specialKey, (String) value);
        } else if (value instanceof Float) {
            editor.putFloat(specialKey, (Float) value);
        } else if (value instanceof Integer) {
            editor.putInt(specialKey, (Integer) value);
        } else if (value instanceof Long) {
            editor.putLong(specialKey, (Long) value);
        }
    }
}