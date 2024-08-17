package com.demo.redis.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_order")
public class Order {

    private Long id;
    private Long userId;
    private Long couponId;
    private LocalDateTime createTime;

}
