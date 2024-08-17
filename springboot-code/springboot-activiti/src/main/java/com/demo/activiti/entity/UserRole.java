package com.demo.activiti.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName user_role
 */
@TableName(value ="user_role")
@Data
public class UserRole implements Serializable {
    private Integer id;

    private Long userId;

    private Long roleId;

    private static final long serialVersionUID = 1L;
}