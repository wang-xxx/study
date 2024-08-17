package com.demo.skywalking.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_user")
public class User {
    private Integer id;

    private String name;

    private String phone;

    private String email;

    private String profession;

    private Integer age;

    private String gender;

    private String status;

    private Date createtime;
}
