package com.demo.mongodb.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author wangxing
 * @date 2024-08-12 19:40
 */
@Data
public class User {

    @Id
    private Long id;

    private String username;
    private Integer age;

}
