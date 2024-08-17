package com.demo.ioc.alarm;

/**
 * @author wangxing
 * @date 2024-08-12 14:24
 */
public class AlarmPhone extends AbstractAlarmFactory {

    @Override
    protected void doAlarm() {
        System.out.println("电话告警");
    }
}
