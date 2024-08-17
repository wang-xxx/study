package com.demo.ioc.alarm;

/**
 * @author wangxing
 * @date 2024-08-12 14:24
 */
public class AlarmMail extends AbstractAlarmFactory {

    @Override
    protected void doAlarm() {
        System.out.println("邮件告警");
    }
}
