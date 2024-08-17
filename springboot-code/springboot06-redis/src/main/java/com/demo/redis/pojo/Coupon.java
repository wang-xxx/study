package com.demo.redis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {

    private Long id;
    private String title;
    private Integer num;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;

}
