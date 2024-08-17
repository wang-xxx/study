package com.demo.redis.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.demo.redis.constants.UserGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user", autoResultMap = true)
public class User {

    private Long id;
    private String name;
    private Short age;
    private UserGender gender;
    private String phone;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private UserInfo info;

    public User(Long id, String name, Short age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
