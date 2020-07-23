package com.songfeifan.samples.sbt.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public abstract class AppContextUtil {

    private static ApplicationContext appContext;

    private AppContextUtil() {  }

    public static ApplicationContext getAppContext() {
        return appContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return appContext.getBean(clazz);
    }

    public static class AppContextStartedListener implements ApplicationListener<ContextRefreshedEvent> {

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            AppContextUtil.appContext = event.getApplicationContext();
        }
    }
}
