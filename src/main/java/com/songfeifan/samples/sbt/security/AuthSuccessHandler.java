package com.songfeifan.samples.sbt.security;

import com.alibaba.fastjson.JSON;
import com.songfeifan.samples.sbt.model.vo.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                 HttpServletResponse response, FilterChain chain, Authentication authentication)
            throws IOException, ServletException {

        onAuthenticationSuccess(request, response, authentication);

    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {

        clearAuthenticationAttributes(request);

        response.setHeader("Content-Type", "application/json; charset=utf8;");
        Writer w = response.getWriter();
        R<Void> r = new R<>(200);
        w.write(JSON.toJSONString(r));

    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
