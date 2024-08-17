package com.demo.feign.clients;

import com.demo.feign.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-user")
public interface UserClient {

    @GetMapping("/user/{id}")
    User findById(@PathVariable Long id);

}
