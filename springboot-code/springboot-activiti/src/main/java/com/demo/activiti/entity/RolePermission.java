package com.demo.activiti.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName role_permission
 */
@TableName(value ="role_permission")
@Data
public class RolePermission implements Serializable {
    private Integer id;

    private Long roleId;

    private Long permissionId;

    private static final long serialVersionUID = 1L;
}