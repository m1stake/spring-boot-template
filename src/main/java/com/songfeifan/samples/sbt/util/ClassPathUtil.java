/*
 * @author songfeifan-南昌泽诺信息科技有限公司
 * @date 2019/5/31 11:13
 */
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
