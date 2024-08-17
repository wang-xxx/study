package com.demo.activiti.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName permission
 */
@TableName(value ="permission")
@Data
public class Permission implements Serializable {
    private Long id;

    private String url;

    private String name;

    private String description;

    private Long pid;

    private static final long serialVersionUID = 1L;
}