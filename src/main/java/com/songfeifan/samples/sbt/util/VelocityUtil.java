package com.songfeifan.samples.sbt.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class VelocityUtil {

    private static VelocityEngine velocityEngine;


    private static Map<String, Template> templateCache = new ConcurrentHashMap<>();

    static {
        velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        velocityEngine.init();
    }

    private VelocityUtil() {  }

    public static String merge(String templatePath, Object params) {

        Template t = getTemplate(templatePath);
        VelocityContext ctx = toContext(params);
        StringWriter sw = new StringWriter();
        t.merge(ctx, sw);

        return sw.toString();
    }

    private static VelocityContext toContext(Object params) {
        VelocityContext ctx = new VelocityContext();
        JSONObject jo = JSON.parseObject(JSON.toJSONString(params));
        for (Map.Entry<String, Object> entry: jo.entrySet()) {
            ctx.put(entry.getKey(), entry.getValue());
        }
        return ctx;
    }

    /**
     * 获取模板
     */
    private static Template getTemplate(String templatePath) {
        return templateCache.computeIfAbsent(templatePath,
                k -> velocityEngine.getTemplate(templatePath, "utf-8"));
    }

}











