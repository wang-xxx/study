package com.demo.activiti.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName role
 */
@TableName(value ="role")
@Data
public class Role implements Serializable {
    private Long id;

    private String name;

    private static final long serialVersionUID = 1L;
}