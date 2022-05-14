package com.songfeifan.samples.sbt.advice;

import com.songfeifan.samples.sbt.model.vo.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseAdvice.class);

    private static final List<String> ignoreTypes = Arrays.asList(
            R.class.getName(),
            ResponseEntity.class.getName()
    );

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        String returnTypeName = returnType.getMethod().getReturnType().getTypeName();
        for (String ignoreType: ignoreTypes) {
            if (ignoreType.equals(returnTypeName)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public R beforeBodyWrite(
            Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request, ServerHttpResponse response) {
        return new R<>(body);
    }
}
