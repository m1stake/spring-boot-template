package com.songfeifan.samples.sbt.security;

import com.alibaba.fastjson.JSON;
import com.songfeifan.samples.sbt.model.vo.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class AuthFailedHandler implements AuthenticationFailureHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        LOGGER.error("登录失败", exception);

        response.setHeader("Content-Type", "application/json; charset=utf8;");
        Writer w = response.getWriter();
        R<Void> r = new R<>(403, "登录失败");

        w.write(JSON.toJSONString(r));

    }
}
