package com.demo.feign.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class User {

    private Long id;
    private String username;
    private String address;

}
