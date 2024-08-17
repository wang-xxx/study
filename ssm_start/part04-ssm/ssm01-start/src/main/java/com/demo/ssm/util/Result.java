package com.demo.ssm.util;

import lombok.Data;

@Data
public class Result {

    private int code = 200;

    private boolean flag = true;

    private Object data;

    public static Result ok(Object data) {
        Result result = new Result();
        result.data = data;
        return result;
    }

    public static Result fail(Object data) {
        Result result = new Result();
        result.code = 500;
        result.flag = false;
        result.data = data;
        return result;
    }

}
