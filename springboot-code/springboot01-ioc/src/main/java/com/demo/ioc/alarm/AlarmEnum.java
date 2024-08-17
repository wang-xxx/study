package com.demo.ioc.alarm;

/**
 * @author wangxing
 * @date 2024-08-12 14:20
 */
public enum AlarmEnum {

    MAIL(1, "邮件"),
    PHONE(2, "电话");

    private int value;
    private String name;

    AlarmEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
