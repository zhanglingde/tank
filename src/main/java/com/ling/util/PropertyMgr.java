package com.ling.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取配置文件
 */
public class PropertyMgr {

    static Properties props = new Properties();

    static {
        try {
            // 获取一个文件的输入流
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据key获取配置文件的值
     * @param key
     * @return
     */
    public static Object get(String key){
        if(props == null){
            return null;
        }
        return props.get(key);
    }

    public static void main(String[] args) {
        System.out.println(PropertyMgr.get("initTankCount"));
    }
}