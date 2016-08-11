package com.htdc.weixin.util;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @Author Han.Sun
 * @Date 2016年08月11日 10:21
 * @Discription
 */
public class Constants {
    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader loader = new PropertiesLoader("override.properties");

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = Maps.newHashMap();

    /**
     * 获取配置
     *
     * @see ${fns:getConfig('adminPath')}
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            value = loader.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }
}
