package com.songfeifan.samples.sbt.util;

import java.io.InputStream;

public abstract class ClassPathUtil {

    private ClassPathUtil() { }

    /**
     * 获取文件输入流
     * @param relativePath 相对classPath的路径
     */
    public static InputStream getInputStream(String relativePath) {
        ClassLoader classLoader = ClassPathUtil.class.getClassLoader();
        return classLoader.getResourceAsStream(relativePath);
    }
}
