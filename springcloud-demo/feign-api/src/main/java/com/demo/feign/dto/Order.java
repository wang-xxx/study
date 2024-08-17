package com.demo.feign.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_order")
public class Order {

    private Long id;
    private Double price;
    private String name;
    private Long num;
    private Long userId;

    @TableField(exist = false)
    private User user;

}
