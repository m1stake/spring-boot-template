package com.songfeifan.samples.sbt.advice;

import com.songfeifan.samples.sbt.model.vo.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.songfeifan.samples.sbt.controller")
public class ExceptionAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public R exception(Exception e) {
        LOGGER.error("", e);
        return R.error(500, e.getMessage());
    }


}
