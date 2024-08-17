package com.demo.mp.constants;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum GenderEnum {
    MAN(1, "男"),
    WOMAN(2, "女"),
    OTHER(3, "其他");

    @EnumValue
    private final int value;
    @JsonValue
    private final String desc;

    GenderEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
