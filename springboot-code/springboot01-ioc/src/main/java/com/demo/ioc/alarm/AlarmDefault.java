package com.demo.ioc.alarm;

/**
 * @author wangxing
 * @date 2024-08-12 14:24
 */
public class AlarmDefault extends AbstractAlarmFactory {

    @Override
    protected void doAlarm() {
        System.out.println("默认告警");
    }
}
