package com.songfeifan.samples.sbt.security;

import com.alibaba.fastjson.JSON;
import com.songfeifan.samples.sbt.model.vo.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthEntryPoint.class);

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse resp,
                         AuthenticationException authException) throws IOException, ServletException {

        LOGGER.error("", authException);

        R<Void> r = new R<>(403, "没有访问权限");

        resp.setContentType("application/json; charset=utf-8;");
        resp.getWriter().write(JSON.toJSONString(r));

    }

}
