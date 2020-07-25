package com.songfeifan.samples.sbt;

import com.songfeifan.samples.sbt.util.AppContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableCaching
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(Application.class);
        app.addListeners(new AppContextUtil.AppContextStartedListener());
        app.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application
                .listeners(new AppContextUtil.AppContextStartedListener())
                .sources(Application.class);
    }
}
