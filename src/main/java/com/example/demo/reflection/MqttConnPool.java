package com.example.demo.reflection;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

public class MqttConnPool {

    private static final Logger logger = LoggerFactory.getLogger(MqttConnPool.class);

    private final static ConcurrentHashMap<String, MethodAccess> urlMap =
            new ConcurrentHashMap<>(1000000, 0.9f, 256);

    public static void registerUrl(String url, MethodAccess chn) {
        if (chn == null) {
            return;
        }
        if (url == null) {
            return;
        }
        logger.info("registered Url is :{}", url);
        urlMap.put(url, chn);
    }

    public static MethodAccess getByUrl(String url) {
        return Collections.unmodifiableMap(urlMap).get(url);
    }

    public static void init(String basePackage) {
        try {
            for (Class<?> clazz : MqttClassLoader.getClassSet(basePackage)) {
                Annotation ca = clazz.getAnnotation(MqttMapper.class);
                if (null != ca) {
                    String pName = ((MqttMapper) ca).value();
                    Method[] methods = clazz.getDeclaredMethods();
                    for (Method method : methods) {
                        Annotation ma = method.getAnnotation(MqttMapper.class);
                        if (null != ma) {
                            String subName = ((MqttMapper) ma).value();
                            if (clazz.getName().startsWith(basePackage)) {
                                MqttConnPool.registerUrl(pName + subName, new MethodAccess(clazz.newInstance(), method));
                            }
                        }
                    }
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
