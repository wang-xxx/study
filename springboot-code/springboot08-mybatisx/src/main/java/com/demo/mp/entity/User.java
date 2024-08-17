package com.demo.mp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.demo.mp.constants.GenderEnum;
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
    private GenderEnum gender;
    private String phone;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private UserInfo info;

}
