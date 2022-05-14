package com.songfeifan.samples.sbt.model.vo;

import lombok.Data;

@Data
public class R<T> {

    private int code = 200;

    private String msg;

    private T data;

    public R() {  }

    public R(T data) {
        this.data = data;
    }

    public R(int code) {
        this.code = code;
    }

    public R(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public static R error(int code, String msg) {
        return new R(code, msg);
    }
}
