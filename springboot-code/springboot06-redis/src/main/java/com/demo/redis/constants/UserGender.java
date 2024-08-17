package com.demo.redis.constants;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum UserGender {
    MAN(1, "男"),
    WOMAN(2, "女");

    @EnumValue
    private final int value;
    @JsonValue
    private final String desc;

    UserGender(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
