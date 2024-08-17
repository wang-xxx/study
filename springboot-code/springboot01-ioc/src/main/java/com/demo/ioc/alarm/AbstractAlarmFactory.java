package com.demo.ioc.alarm;

import cn.hutool.core.date.DateTime;

/**
 * @author wangxing
 * @date 2024-08-12 14:18
 */
public abstract class AbstractAlarmFactory {

    private String deviceId;
    private DateTime dateTime;
    private AlarmType alarmType;
    private AlarmEnum alarmEnum;

    protected abstract void doAlarm();

    public AbstractAlarmFactory getAlarmImpl() {
        switch (alarmEnum) {
            case MAIL:
                return new AlarmMail();
            case PHONE:
                return new AlarmPhone();
            default:
                return new AlarmDefault();
        }
    }

}
